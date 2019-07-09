package com.example.aws.lambdas;

import java.util.Map;

import io.vertx.core.eventbus.Message;
import io.vertx.core.json.JsonObject;
import xyz.jetdrone.vertx.lambda.Lambda;
import xyz.jetdrone.vertx.lambda.aws.event.*;

/**
 * ALB Target Group events consist of a request that was routed to a Lambda function which is a registered target of an
 * Application Load Balancer Target Group. When this happens, ALB expects the result of the function to be the response
 * that ALB should respond with.
 *
 * https://docs.aws.amazon.com/elasticloadbalancing/latest/application/lambda-functions.html
 *
 * The following is a sample class and Lambda function that receives an ALB Target Group event as an input, writes
 * some of the incoming data to CloudWatch Logs, and responds with a 200 status and the same body as the request.
 * (Note that by default anything written to Console will be logged as CloudWatch Logs events.)
 */
public class ALBLambda implements Lambda<JsonObject> {

  @Override
  public void handle(Message<JsonObject> event) {
    ALBTargetGroupRequest request = new ALBTargetGroupRequest(event.body());

    System.out.printf("Processing request data for traceId %s.\n", request.getHeaders().get("x-amzn-trace-id"));
    System.out.printf("Body size = %d.\n", len(request.getBody()));

    System.out.println("Headers:");
    for (Map.Entry<String, String> kv : request.getHeaders().entrySet()) {
      System.out.printf("    %s: %s\n", kv.getKey(), kv.getValue());
    }

    event.reply(
      new ALBTargetGroupResponse()
        .setStatusCode(200)
        .setStatusDescription("200 OK")
        .setIsBase64Encoded(false)
        .setBody(request.getBody())
        .toJson());
  }

  private int len(String body) {
    if (body != null) {
      return body.length();
    }

    return -1;
  }
}
