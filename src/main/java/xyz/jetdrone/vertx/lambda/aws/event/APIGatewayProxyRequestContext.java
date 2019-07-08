
package xyz.jetdrone.vertx.lambda.aws.event;

import io.vertx.codegen.annotations.DataObject;
import io.vertx.core.json.JsonObject;

@DataObject(generateConverter = true)
public class APIGatewayProxyRequestContext {

  public APIGatewayProxyRequestContext() {}

  public APIGatewayProxyRequestContext(JsonObject json) {
    APIGatewayProxyRequestContextConverter.fromJson(json, this);
  }

  public JsonObject toJson() {
    JsonObject json = new JsonObject();
    APIGatewayProxyRequestContextConverter.toJson(this, json);
    return json;
  }

  private String accountId;
  private String apiId;
  private JsonObject authorizer;
  private String httpMethod;
  private APIGatewayRequestIdentity identity;
  private String requestId;
  private String resourceId;
  private String resourcePath;
  private String stage;

  public String getAccountId() {
    return accountId;
  }

  public APIGatewayProxyRequestContext setAccountId(String accountId) {
    this.accountId = accountId;
    return this;
  }

  public String getApiId() {
    return apiId;
  }

  public APIGatewayProxyRequestContext setApiId(String apiId) {
    this.apiId = apiId;
    return this;
  }

  public JsonObject getAuthorizer() {
    return authorizer;
  }

  public APIGatewayProxyRequestContext setAuthorizer(JsonObject authorizer) {
    this.authorizer = authorizer;
    return this;
  }

  public String getHttpMethod() {
    return httpMethod;
  }

  public APIGatewayProxyRequestContext setHttpMethod(String httpMethod) {
    this.httpMethod = httpMethod;
    return this;
  }

  public APIGatewayRequestIdentity getIdentity() {
    return identity;
  }

  public APIGatewayProxyRequestContext setIdentity(APIGatewayRequestIdentity identity) {
    this.identity = identity;
    return this;
  }

  public String getRequestId() {
    return requestId;
  }

  public APIGatewayProxyRequestContext setRequestId(String requestId) {
    this.requestId = requestId;
    return this;
  }

  public String getResourceId() {
    return resourceId;
  }

  public APIGatewayProxyRequestContext setResourceId(String resourceId) {
    this.resourceId = resourceId;
    return this;
  }

  public String getResourcePath() {
    return resourcePath;
  }

  public APIGatewayProxyRequestContext setResourcePath(String resourcePath) {
    this.resourcePath = resourcePath;
    return this;
  }

  public String getStage() {
    return stage;
  }

  public APIGatewayProxyRequestContext setStage(String stage) {
    this.stage = stage;
    return this;
  }

  @Override
  public String toString() {
    return toJson().encodePrettily();
  }
}
