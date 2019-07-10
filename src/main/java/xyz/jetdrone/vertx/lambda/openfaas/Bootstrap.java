/*
 * Copyright 2019 Paulo Lopes.
 *
 *  All rights reserved. This program and the accompanying materials
 *  are made available under the terms of the Eclipse Public License v1.0
 *  and Apache License v2.0 which accompanies this distribution.
 *
 *  The Eclipse Public License is available at
 *  http://www.eclipse.org/legal/epl-v10.html
 *
 *  The Apache License v2.0 is available at
 *  http://www.opensource.org/licenses/apache2.0.php
 *
 *  You may elect to redistribute this code under either of these licenses.
 */
package xyz.jetdrone.vertx.lambda.openfaas;

import io.netty.handler.codec.http.HttpHeaderValues;
import io.vertx.core.*;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.eventbus.DeliveryOptions;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.file.FileSystem;
import io.vertx.core.http.HttpHeaders;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.json.DecodeException;
import io.vertx.core.json.EncodeException;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import xyz.jetdrone.vertx.lambda.Lambda;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashSet;
import java.util.ServiceLoader;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.BiConsumer;

import static io.vertx.core.http.HttpHeaders.CONTENT_TYPE;
import static java.lang.System.getenv;

/**
 * Main entrypoint for the application.
 */
public final class Bootstrap extends AbstractVerticle {

  private static final int DEFAULT_INITIAL_BODY_BUFFER_SIZE = 1024; //bytes
  private static final String PORT_ENV_VAR = "http_port";

  private static final Logger log = LoggerFactory.getLogger(Bootstrap.class);

  public static void main(String[] args) {
    try {
      final VertxOptions vertxOptions = new VertxOptions();
      final JsonObject config = new JsonObject();
      final DeploymentOptions deploymentOptions = new DeploymentOptions().setConfig(config);

      if (getenv(PORT_ENV_VAR) != null) {
        config.put("port", Integer.parseInt(getenv(PORT_ENV_VAR), 10));
      }

      Vertx.vertx(vertxOptions).deployVerticle(new Bootstrap(), deploymentOptions, deploy -> {
        if (deploy.failed()) {
          System.err.println(deploy.cause().getMessage());
          // the whole startup failed
          System.exit(1);
        }
      });
    } catch (RuntimeException e) {
      e.printStackTrace();
      System.exit(1);
    }
  }

  @Override
  public void start() {
    final JsonObject config = context.config();
    final EventBus eb = vertx.eventBus();

    // Get the default handler class and method name from the Lambda Configuration in the format of <fqcn>
    final String defaultFn = getenv("_HANDLER");

    // register all lambda's into the eventbus
    for (Lambda fn : ServiceLoader.load(Lambda.class)) {
      fn.init(vertx);
      eb.localConsumer("/" + fn.getClass().getName().replace('.', '/'), fn);
    }

    // create an http server
    vertx.createHttpServer()
      .requestHandler(request -> {
        long contentLength = parseContentLengthHeader(request);
        BHandler handler = new BHandler(request, contentLength, (body, files) -> {
          Object event;

          // parse body
          if (request.headers() != null && "application/json".equals(request.headers().get(CONTENT_TYPE))) {
            try {
              event = new JsonObject(body);
            } catch (DecodeException e) {
              fail(request, e);
              return;
            }
          } else {
            event = body;
          }

          final String path = request.path();
          // Invoke Handler Method
          eb.send("/".equals(path) ? defaultFn : path, event, new DeliveryOptions().setHeaders(request.headers()), msg -> {
            if (msg.failed()) {
              fail(request, msg.cause());
            } else {
              // Post the results of Handler Invocation
              final MultiMap fnHeaders = msg.result().headers();
              Object fnResult = msg.result().body();

              if (fnResult == null) {
                request.response().setStatusCode(204);
              } else {
                if (fnResult instanceof JsonObject) {
                  try {
                    request.response()
                      .putHeader("Content-Type", "application/json");

                    request.response()
                      .headers().setAll(fnHeaders);

                    request.response()
                      .end(((JsonObject) fnResult).toBuffer());
                  } catch (EncodeException e) {
                    fail(request, e);
                  }
                  return;
                }

                if (fnResult instanceof Buffer) {
                  request.response()
                    .headers().setAll(fnHeaders);

                  request.response()
                    .end((Buffer) fnResult);
                  return;
                }

                if (fnResult instanceof String) {
                  request.response()
                    .headers().setAll(fnHeaders);

                  request.response()
                    .end((String) fnResult);
                  return;
                }

                fail(request, "Response is not Buffer of JSON");
              }
            }
          });
        });
        request.handler(handler);
        request.endHandler(v -> handler.end());
      })
      .listen(config.getInteger("port", 8000), listen -> {
        if (listen.failed()) {
          listen.cause().printStackTrace();
          System.exit(1);
        }
      });
  }

  private long parseContentLengthHeader(HttpServerRequest request) {
    String contentLength = request.getHeader(HttpHeaders.CONTENT_LENGTH);
    if (contentLength == null || contentLength.isEmpty()) {
      return -1;
    }
    try {
      long parsedContentLength = Long.parseLong(contentLength);
      return parsedContentLength < 0 ? -1 : parsedContentLength;
    } catch (NumberFormatException ex) {
      return -1;
    }
  }

  private class BHandler implements Handler<Buffer> {

    private static final int MAX_PREALLOCATED_BODY_BUFFER_BYTES = 65535;

    private Buffer body;
    private Set<String> fileUploads;

    private HttpServerRequest request;

    boolean failed;
    AtomicInteger uploadCount = new AtomicInteger();
    AtomicBoolean cleanup = new AtomicBoolean(false);
    boolean ended;
    long uploadSize = 0L;
    final boolean isMultipart;
    final boolean isUrlEncoded;
    final BiConsumer<Buffer, Set<String>> ready;

    public BHandler(HttpServerRequest request, long contentLength, BiConsumer<Buffer, Set<String>> ready) {
      this.request = request;
      this.ready = ready;

      final String contentType = request.getHeader(HttpHeaders.CONTENT_TYPE);
      if (contentType == null) {
        isMultipart = false;
        isUrlEncoded = false;
      } else {
        final String lowerCaseContentType = contentType.toLowerCase();
        isMultipart = lowerCaseContentType.startsWith(HttpHeaderValues.MULTIPART_FORM_DATA.toString());
        isUrlEncoded = lowerCaseContentType.startsWith(HttpHeaderValues.APPLICATION_X_WWW_FORM_URLENCODED.toString());
      }

      initBodyBuffer(contentLength);

      if (isMultipart || isUrlEncoded) {
        request.setExpectMultipart(true);
        fileUploads = new HashSet<>();
        request.uploadHandler(upload -> {
          // we actually upload to a file with a generated filename
          uploadCount.incrementAndGet();
          String uploadedFileName = new File(System.getProperty("java.io.tmpdir"), UUID.randomUUID().toString()).getPath();
          upload.streamToFileSystem(uploadedFileName);
          fileUploads.add(uploadedFileName);
          upload.exceptionHandler(t -> {
            deleteFileUploads(fileUploads);
            fail(request, t);
          });
          upload.endHandler(v -> uploadEnded());
        });
      }

      request.exceptionHandler(t -> {
        deleteFileUploads(fileUploads);
        fail(request, t);
      });
    }

    private void initBodyBuffer(long contentLength) {
      int initialBodyBufferSize;
      if (contentLength < 0) {
        initialBodyBufferSize = DEFAULT_INITIAL_BODY_BUFFER_SIZE;
      } else if (contentLength > MAX_PREALLOCATED_BODY_BUFFER_BYTES) {
        initialBodyBufferSize = MAX_PREALLOCATED_BODY_BUFFER_BYTES;
      } else {
        initialBodyBufferSize = (int) contentLength;
      }

      this.body = Buffer.buffer(initialBodyBufferSize);
    }

    @Override
    public void handle(Buffer buff) {
      if (failed) {
        return;
      }
      uploadSize += buff.length();
      // multipart requests will not end up in the request body
      // url encoded should also not, however jQuery by default
      // post in urlencoded even if the payload is something else
      if (!isMultipart /* && !isUrlEncoded */) {
        body.appendBuffer(buff);
      }
    }

    void uploadEnded() {
      int count = uploadCount.decrementAndGet();
      // only if parsing is done and count is 0 then all files have been processed
      if (ended && count == 0) {
        doEnd();
      }
    }

    void end() {
      // this marks the end of body parsing, calling doEnd should
      // only be possible from this moment onwards
      ended = true;

      // only if parsing is done and count is 0 then all files have been processed
      if (uploadCount.get() == 0) {
        doEnd();
      }
    }

    void doEnd() {

      if (failed) {
        deleteFileUploads(fileUploads);
        return;
      }

      request.response().endHandler(v -> deleteFileUploads(fileUploads));
      request.response().exceptionHandler(v -> deleteFileUploads(fileUploads));

      ready.accept(body, fileUploads);
    }

    private void deleteFileUploads(Set<String> fileUploads) {
      if (cleanup.compareAndSet(false, true)) {
        if (fileUploads != null) {
          FileSystem fileSystem = vertx.fileSystem();
          for (String uploadedFileName : fileUploads) {
            fileSystem.exists(uploadedFileName, existResult -> {
              if (existResult.failed()) {
                log.warn("Could not detect if uploaded file exists, not deleting: " + uploadedFileName, existResult.cause());
              } else if (existResult.result()) {
                fileSystem.delete(uploadedFileName, deleteResult -> {
                  if (deleteResult.failed()) {
                    log.warn("Delete of uploaded file failed: " + uploadedFileName, deleteResult.cause());
                  }
                });
              }
            });
          }
        }
      }
    }
  }

  private void fail(HttpServerRequest request, String errType, String errMsg, String errTrace) {
    System.err.println("ERR: " + errMsg);

    final JsonObject error = new JsonObject()
      .put("errorType", errType)
      .put("errorMessage", errMsg);

    if (errTrace != null) {
      final JsonArray trace = new JsonArray();
      error.put("trace", trace);

      for (String line : errTrace.split("\r?\n")) {
        trace.add(line);
      }
    }

    request.response()
      .setStatusCode(500)
      .putHeader("Content-Type", "application/json")
      .end(error.toBuffer());
  }

  private void fail(HttpServerRequest request, String errMsg) {
    fail(request, "RuntimeError", errMsg, null);
  }

  private void fail(HttpServerRequest request, Throwable throwable) {
    try (StringWriter sw = new StringWriter()) {
      PrintWriter pw = new PrintWriter(sw);
      // print the thrown to String
      throwable.printStackTrace(pw);

      fail(request, throwable.getClass().getSimpleName(), throwable.getMessage(), sw.toString());
    } catch (IOException e) {
      e.printStackTrace();
      // terminate the process
      System.exit(1);
    }
  }
}
