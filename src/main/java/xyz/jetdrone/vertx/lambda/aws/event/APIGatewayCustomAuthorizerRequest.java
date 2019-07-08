
package xyz.jetdrone.vertx.lambda.aws.event;


import io.vertx.codegen.annotations.DataObject;
import io.vertx.core.json.JsonObject;

@DataObject(generateConverter = true)
public class APIGatewayCustomAuthorizerRequest {

  public APIGatewayCustomAuthorizerRequest() {}

  public APIGatewayCustomAuthorizerRequest(JsonObject json) {
    APIGatewayCustomAuthorizerRequestConverter.fromJson(json, this);
  }

  public JsonObject toJson() {
    JsonObject json = new JsonObject();
    APIGatewayCustomAuthorizerRequestConverter.toJson(this, json);
    return json;
  }

  private String authorizationToken;
  private String methodArn;
  private String type;

  public String getAuthorizationToken() {
    return authorizationToken;
  }

  public APIGatewayCustomAuthorizerRequest setAuthorizationToken(String authorizationToken) {
    this.authorizationToken = authorizationToken;
    return this;
  }

  public String getMethodArn() {
    return methodArn;
  }

  public APIGatewayCustomAuthorizerRequest setMethodArn(String methodArn) {
    this.methodArn = methodArn;
    return this;
  }

  public String getType() {
    return type;
  }

  public APIGatewayCustomAuthorizerRequest setType(String type) {
    this.type = type;
    return this;
  }

  @Override
  public String toString() {
    return toJson().encodePrettily();
  }
}
