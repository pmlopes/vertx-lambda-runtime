
package xyz.jetdrone.vertx.lambda.aws.event;

import io.vertx.codegen.annotations.DataObject;
import io.vertx.core.json.JsonObject;

@DataObject(generateConverter = true)
public class APIGatewayProxyResponse {

  public APIGatewayProxyResponse() {}

  public APIGatewayProxyResponse(JsonObject json) {
    APIGatewayProxyResponseConverter.fromJson(json, this);
  }

  public JsonObject toJson() {
    JsonObject json = new JsonObject();
    APIGatewayProxyResponseConverter.toJson(this, json);
    return json;
  }

  private String body;
  private JsonObject headers;
  private Boolean isBase64Encoded;
  private Integer statusCode;

  public String getBody() {
    return body;
  }

  public APIGatewayProxyResponse setBody(String body) {
    this.body = body;
    return this;
  }

  public JsonObject getHeaders() {
    return headers;
  }

  public APIGatewayProxyResponse setHeaders(JsonObject headers) {
    this.headers = headers;
    return this;
  }

  public Boolean getIsBase64Encoded() {
    return isBase64Encoded;
  }

  public APIGatewayProxyResponse setIsBase64Encoded(Boolean isBase64Encoded) {
    this.isBase64Encoded = isBase64Encoded;
    return this;
  }

  public Integer getStatusCode() {
    return statusCode;
  }

  public APIGatewayProxyResponse setStatusCode(Integer statusCode) {
    this.statusCode = statusCode;
    return this;
  }

  @Override
  public String toString() {
    return toJson().encodePrettily();
  }
}
