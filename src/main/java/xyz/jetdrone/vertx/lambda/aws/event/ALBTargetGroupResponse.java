package xyz.jetdrone.vertx.lambda.aws.event;

import io.vertx.codegen.annotations.DataObject;
import io.vertx.core.json.JsonObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@DataObject(generateConverter = true)
public class ALBTargetGroupResponse {

  public ALBTargetGroupResponse() {}

  public ALBTargetGroupResponse(JsonObject json) {
    ALBTargetGroupResponseConverter.fromJson(json, this);
  }

  public JsonObject toJson() {
    JsonObject json = new JsonObject();
    ALBTargetGroupResponseConverter.toJson(this, json);
    return json;
  }

  private int statusCode;
  private String statusDescription;
  private Map<String, String> headers;
  private Map<String, List<String>> multiValueHeaders;
  private String body;
  private boolean isBase64Encoded;

  public int getStatusCode() {
    return statusCode;
  }

  public ALBTargetGroupResponse setStatusCode(int statusCode) {
    this.statusCode = statusCode;
    return this;
  }

  public String getStatusDescription() {
    return statusDescription;
  }

  public ALBTargetGroupResponse setStatusDescription(String statusDescription) {
    this.statusDescription = statusDescription;
    return this;
  }

  public Map<String, String> getHeaders() {
    return headers;
  }

  public ALBTargetGroupResponse setHeaders(Map<String, String> headers) {
    this.headers = headers;
    return this;
  }

  public ALBTargetGroupResponse putHeader(String key, String value) {
    if (this.headers == null) {
      this.headers = new HashMap<>();
    }
    this.headers.put(key, value);
    return this;
  }

  public Map<String, List<String>> getMultiValueHeaders() {
    return multiValueHeaders;
  }

  public ALBTargetGroupResponse setMultiValueHeaders(Map<String, List<String>> multiValueHeaders) {
    this.multiValueHeaders = multiValueHeaders;
    return this;
  }

  public ALBTargetGroupResponse putMultiValueHeader(String key, List<String> values) {
    if (this.multiValueHeaders == null) {
      this.multiValueHeaders = new HashMap<>();
    }
    this.multiValueHeaders.put(key, values);
    return this;
  }

  public String getBody() {
    return body;
  }

  public ALBTargetGroupResponse setBody(String body) {
    this.body = body;
    return this;
  }

  public boolean getIsBase64Encoded() {
    return isBase64Encoded;
  }

  public ALBTargetGroupResponse setIsBase64Encoded(boolean base64Encoded) {
    isBase64Encoded = base64Encoded;
    return this;
  }
}
