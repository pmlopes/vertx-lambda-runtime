
package xyz.jetdrone.vertx.lambda.aws.event;

import io.vertx.codegen.annotations.DataObject;
import io.vertx.core.json.JsonObject;

@DataObject(generateConverter = true)
public class APIGatewayProxyRequest {

  public APIGatewayProxyRequest() {}

  public APIGatewayProxyRequest(JsonObject json) {
    APIGatewayProxyRequestConverter.fromJson(json, this);
  }

  public JsonObject toJson() {
    JsonObject json = new JsonObject();
    APIGatewayProxyRequestConverter.toJson(this, json);
    return json;
  }

  private String body;
  private JsonObject headers;
  private String httpMethod;
  private Boolean isBase64Encoded;
  private String path;
  private JsonObject pathParameters;
  private JsonObject queryStringParameters;
  private APIGatewayProxyRequestContext requestContext;
  private String resource;
  private JsonObject stageVariables;

  public String getBody() {
    return body;
  }

  public APIGatewayProxyRequest setBody(String body) {
    this.body = body;
    return this;
  }

  public JsonObject getHeaders() {
    return headers;
  }

  public APIGatewayProxyRequest setHeaders(JsonObject headers) {
    this.headers = headers;
    return this;
  }

  public String getHttpMethod() {
    return httpMethod;
  }

  public APIGatewayProxyRequest setHttpMethod(String httpMethod) {
    this.httpMethod = httpMethod;
    return this;
  }

  public Boolean getIsBase64Encoded() {
    return isBase64Encoded;
  }

  public APIGatewayProxyRequest setIsBase64Encoded(Boolean isBase64Encoded) {
    this.isBase64Encoded = isBase64Encoded;
    return this;
  }

  public String getPath() {
    return path;
  }

  public APIGatewayProxyRequest setPath(String path) {
    this.path = path;
    return this;
  }

  public JsonObject getPathParameters() {
    return pathParameters;
  }

  public APIGatewayProxyRequest setPathParameters(JsonObject pathParameters) {
    this.pathParameters = pathParameters;
    return this;
  }

  public JsonObject getQueryStringParameters() {
    return queryStringParameters;
  }

  public APIGatewayProxyRequest setQueryStringParameters(JsonObject queryStringParameters) {
    this.queryStringParameters = queryStringParameters;
    return this;
  }

  public APIGatewayProxyRequestContext getRequestContext() {
    return requestContext;
  }

  public APIGatewayProxyRequest setRequestContext(APIGatewayProxyRequestContext requestContext) {
    this.requestContext = requestContext;
    return this;
  }

  public String getResource() {
    return resource;
  }

  public APIGatewayProxyRequest setResource(String resource) {
    this.resource = resource;
    return this;
  }

  public JsonObject getStageVariables() {
    return stageVariables;
  }

  public APIGatewayProxyRequest setStageVariables(JsonObject stageVariables) {
    this.stageVariables = stageVariables;
    return this;
  }

  @Override
  public String toString() {
    return toJson().encodePrettily();
  }
}
