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
package xyz.jetdrone.vertx.lambda.aws;

import io.vertx.core.*;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.eventbus.DeliveryOptions;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.json.DecodeException;
import io.vertx.core.json.EncodeException;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.client.HttpRequest;
import io.vertx.ext.web.client.HttpResponse;
import io.vertx.ext.web.client.WebClient;
import io.vertx.ext.web.client.WebClientOptions;
import xyz.jetdrone.vertx.lambda.Lambda;

import static io.vertx.core.http.HttpHeaders.CONTENT_TYPE;
import static java.lang.System.getenv;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ServiceLoader;

/**
 * Main entrypoint for the application.
 */
public final class Bootstrap extends AbstractVerticle {

  private static final String LAMBDA_VERSION_DATE = "2018-06-01";

  private static final String LAMBDA_INIT_ERROR = "/" + LAMBDA_VERSION_DATE + "/runtime/init/error";
  private static final String LAMBDA_INVOCATION = "/" + LAMBDA_VERSION_DATE + "/runtime/invocation/";

  private static final String LAMBDA_INVOCATION_NEXT = LAMBDA_INVOCATION + "next";

  private static final String RESPONSE = "/response";
  private static final String ERROR = "/error";

  // preload the service loader
  private static final ServiceLoader<Lambda> SERVICE_LOADER = ServiceLoader.load(Lambda.class);

  static {
    System.setProperty("vertx.disableMetrics", "true");
    System.setProperty("vertx.disableH2c", "true");
    System.setProperty("vertx.disableWebsockets", "true");
    System.setProperty("vertx.flashPolicyHandler", "true");
//    System.setProperty("vertx.disableTCCL", "true");
  }

  public static void main(String[] args) {
    // default vertx instance and options
    final Vertx vertx = Vertx.vertx(new VertxOptions().setEventLoopPoolSize(1));
    try {
      main(vertx);
    } catch (RuntimeException e) {
      e.printStackTrace();
      vertx.close();
      System.exit(1);
    }
  }

  public static void main(Vertx vertx) {
    String runtimeApi = getenv("AWS_LAMBDA_RUNTIME_API");
    String host = runtimeApi;
    int port = 80;

    if (runtimeApi == null) {
      throw new IllegalStateException("Missing ENV_VAR [AWS_LAMBDA_RUNTIME_API]");
    }

    int sep = runtimeApi.indexOf(':');
    if (sep != -1) {
      host = runtimeApi.substring(0, sep);
      port = Integer.parseInt(runtimeApi.substring(sep + 1));
    }

    // set the eventloop to 1 as we're a single process running a single task
    vertx.deployVerticle(new Bootstrap(host, port), deploy -> {
      if (deploy.failed()) {
        System.err.println(deploy.cause().getMessage());
        // the whole startup failed
        try {
          vertx.close();
        } catch (Throwable t) {
          t.printStackTrace();
        }
        System.exit(1);
      }
    });
  }

  private final String host;
  private final int port;

  private WebClient client;
  private String fn;

  private Bootstrap(String host, int port) {
    this.host = host;
    this.port = port;
  }

  @Override
  public void start() {
    final EventBus eb = vertx.eventBus();

    // register all lambda's into the eventbus
    for (Lambda fn : SERVICE_LOADER) {
      fn.init(vertx);
      eb.localConsumer(fn.getClass().getName(), fn);
    }

    // create an WebClient
    this.client = WebClient.create(vertx, new WebClientOptions()
      .setDefaultPort(port)
      .setDefaultHost(host));

    // Get the handler class and method name from the Lambda Configuration in the format of <fqcn>
    fn = getenv("_HANDLER");

    if (fn == null) {
      // Not much else to do handler can't be found.
      fail(LAMBDA_INIT_ERROR, "Could not find handler [" + fn + "]");
    } else {
      processEvents();
    }
  }

  private void processEvents() {
    final EventBus eb = vertx.eventBus();

    client.get(LAMBDA_INVOCATION_NEXT).send(getAbs -> {
      if (getAbs.succeeded()) {
        HttpResponse<Buffer> response = getAbs.result();

        switch (response.statusCode()) {
          case 200:
            break;
          case 404:
            exit(0);
          default:
            System.err.println("ERR: HTTP status code <" + response.statusCode() + ">");
            exit(1);
        }

        String requestId = response.getHeader(Headers.LAMBDA_RUNTIME_AWS_REQUEST_ID);
        Object event;

        // parse body
        if (response.headers() != null && "application/json".equals(response.headers().get(CONTENT_TYPE))) {
          try {
            event = new JsonObject(response.body());
          } catch (DecodeException e) {
            fail(LAMBDA_INVOCATION + requestId + ERROR, e);
            return;
          }
        } else {
          event = response.body();
        }

        // Invoke Handler Method
        eb.send(fn, event, new DeliveryOptions().setHeaders(response.headers()), msg -> {
          if (msg.failed()) {
            fail(LAMBDA_INVOCATION + requestId + ERROR, msg.cause());
          } else {
            // Post the results of Handler Invocation
            final String invocationUrl = LAMBDA_INVOCATION + requestId + RESPONSE;
            final MultiMap fnHeaders = msg.result().headers();
            Object fnResult = msg.result().body();

            if (fnResult instanceof JsonObject) {
              try {
                success(invocationUrl, (JsonObject) fnResult, fnHeaders, this::next);
              } catch (EncodeException e) {
                fail(LAMBDA_INVOCATION + requestId + ERROR, e);
              }
              return;
            }

            if (fnResult instanceof Buffer) {
              success(invocationUrl, (Buffer) fnResult, fnHeaders, this::next);
              return;
            }

            if (fnResult instanceof String) {
              success(invocationUrl, Buffer.buffer((String) fnResult), fnHeaders, this::next);
              return;
            }

            fail(LAMBDA_INVOCATION + requestId + ERROR, "Response is not Buffer of JSON");
          }
        });
      } else {
        getAbs.cause().printStackTrace();
        exit(1);
      }
    });
  }

  private void next(AsyncResult<Void> ack) {
    if (ack.failed()) {
      ack.cause().printStackTrace();
      // terminate the process
      exit(1);
    } else {
      // process the next call
      // run on context to avoid large stacks
      vertx.runOnContext(v -> processEvents());
    }
  }

  private void success(String requestURI, Buffer result, MultiMap header, Handler<AsyncResult<Void>> handler) {
    client.post(requestURI)
      .putHeaders(header)
      .sendBuffer(result, ar -> {
        if (ar.succeeded()) {
          // we don't really care about the response
          handler.handle(Future.succeededFuture());
        } else {
          handler.handle(Future.failedFuture(ar.cause()));
        }
      });
  }

  private void success(String requestURI, JsonObject result, MultiMap headers, Handler<AsyncResult<Void>> handler) {
    final HttpRequest<Buffer> request = client.post(requestURI);

    if (headers != null) {
      request.putHeaders(headers);
    }

    request.sendJson(result, ar -> {
        if (ar.succeeded()) {
          // we don't really care about the response
          handler.handle(Future.succeededFuture());
        } else {
          handler.handle(Future.failedFuture(ar.cause()));
        }
      });
  }

  private void fail(String requestURI, String errType, String errMsg, String errTrace) {
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

    client.post(requestURI)
      .sendJson(error, ar -> {
        if (ar.failed()) {
          ar.cause().printStackTrace();
        }
        // terminate the process
        exit(1);
      });
  }

  private void fail(String requestURI, String errMsg) {
    fail(requestURI, "RuntimeError", errMsg, null);
  }

  private void fail(String requestURI, Throwable throwable) {
    try (StringWriter sw = new StringWriter()) {
      PrintWriter pw = new PrintWriter(sw);
      // print the thrown to String
      throwable.printStackTrace(pw);

      fail(requestURI, throwable.getClass().getSimpleName(), throwable.getMessage(), sw.toString());
    } catch (IOException e) {
      e.printStackTrace();
      // terminate the process
      exit(1);
    }
  }

  private void exit(int status) {
    try {
      vertx.close();
    } catch (Throwable t) {
      t.printStackTrace();
    }
    System.exit(status);
  }
}
