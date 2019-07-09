package com.example.aws.lambdas;

import io.vertx.core.eventbus.Message;
import io.vertx.core.json.JsonObject;
import xyz.jetdrone.vertx.lambda.Lambda;
import xyz.jetdrone.vertx.lambda.aws.event.*;

import java.util.Arrays;
import java.util.Map;

/**
 * The following is a simple TOKEN authorizer example to demonstrate how to use an authorization token to allow or deny
 * a request. In this example, the caller named "user" is allowed to invoke a request if the client-supplied token
 * value is "allow". The caller is not allowed to invoke the request if the token value is "deny". If the token value
 * is "Unauthorized", the function returns the "Unauthorized" error with an HTTP status code of 401. For any other
 * token value, the authorizer returns an "Invalid token" error.
 * <p>
 * This example is based on the <a href="https://docs.aws.amazon.com/apigateway/latest/developerguide/use-custom-authorizer.html#api-gateway-custom-authorizer-token-lambda-function-create">JavaScript</a>
 * sample from the API Gateway documentation.
 */
public class APIGWLambda implements Lambda<JsonObject> {

  @Override
  public void handle(Message<JsonObject> event) {

    APIGatewayCustomAuthorizerRequest request = new APIGatewayCustomAuthorizerRequest(event.body());

    String token = request.getAuthorizationToken();

    switch (token.toLowerCase()) {
      case "allow":
        event.reply(
          generatePolicy("user", "Allow", request.getMethodArn()).toJson());
        break;
      case "deny":
        event.reply(
          generatePolicy("user", "Deny", request.getMethodArn()).toJson());
        break;
      case "unauthorized":
        event.fail(401, "Unauthorized"); // Return a 401 Unauthorized response
        break;
      default:
        event.fail(400, "Invalid token");
        break;
    }
  }

  private APIGatewayCustomAuthorizerResponse generatePolicy(String principalId, String effect, String resource) {

    APIGatewayCustomAuthorizerResponse authResponse = new APIGatewayCustomAuthorizerResponse()
      .setPrincipalId(principalId);

    if (!"".equals(effect) && !"".equals(resource)) {
      authResponse
        .setPolicyDocument(
          new APIGatewayCustomAuthorizerPolicy()
            .setVersion("2012-10-17")
            .setStatement(Arrays.asList(
              new IAMPolicyStatement()
                .setAction(Arrays.asList("execute-api:Invoke"))
                .setEffect(effect)
                .setResource(Arrays.asList(resource))
            ))
        );
    }

    // Optional output with custom properties of the String, Number or Boolean type.
    authResponse.setContext(new JsonObject()
      .put("stringKey", "stringval")
      .put("numberKey", 123)
      .put("booleanKey", true));

    return authResponse;
  }
}
