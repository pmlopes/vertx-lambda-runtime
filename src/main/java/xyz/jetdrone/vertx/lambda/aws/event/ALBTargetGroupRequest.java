package xyz.jetdrone.vertx.lambda.aws.event;

import io.vertx.codegen.annotations.DataObject;
import io.vertx.core.json.JsonObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@DataObject(generateConverter = true)
public class ALBTargetGroupRequest {

  public ALBTargetGroupRequest() {}

  public ALBTargetGroupRequest(JsonObject json) {
    ALBTargetGroupRequestConverter.fromJson(json, this);
  }

  public JsonObject toJson() {
    JsonObject json = new JsonObject();
    ALBTargetGroupRequestConverter.toJson(this, json);
    return json;
  }

  private String httpMethod;
  private String path;
  private Map<String, String> queryStringParameters;
  private Map<String, List<String>> multiValueQueryStringParameters;
  private Map<String, String> headers;
  private Map<String, List<String>> multiValueHeaders;
  private ALBTargetGroupRequestContext requestContext;
  private boolean isBase64Encoded;
  private String body;

  public String getHttpMethod() {
    return httpMethod;
  }

  public ALBTargetGroupRequest setHttpMethod(String httpMethod) {
    this.httpMethod = httpMethod;
    return this;
  }

  public String getPath() {
    return path;
  }

  public ALBTargetGroupRequest setPath(String path) {
    this.path = path;
    return this;
  }

  public Map<String, String> getQueryStringParameters() {
    return queryStringParameters;
  }

  public ALBTargetGroupRequest setQueryStringParameters(Map<String, String> queryStringParameters) {
    this.queryStringParameters = queryStringParameters;
    return this;
  }

  public ALBTargetGroupRequest putQueryStringParameter(String key, String value) {
    if (this.queryStringParameters == null) {
      this.queryStringParameters = new HashMap<>();
    }
    this.queryStringParameters.put(key, value);
    return this;
  }

  public Map<String, List<String>> getMultiValueQueryStringParameters() {
    return multiValueQueryStringParameters;
  }

  public ALBTargetGroupRequest setMultiValueQueryStringParameters(Map<String, List<String>> multiValueQueryStringParameters) {
    this.multiValueQueryStringParameters = multiValueQueryStringParameters;
    return this;
  }

  public ALBTargetGroupRequest putMultiValueQueryStringParameter(String key, List<String> values) {
    if (this.multiValueQueryStringParameters == null) {
      this.multiValueQueryStringParameters = new HashMap<>();
    }
    this.multiValueQueryStringParameters.put(key, values);
    return this;
  }

  public Map<String, String> getHeaders() {
    return headers;
  }

  public ALBTargetGroupRequest setHeaders(Map<String, String> headers) {
    this.headers = headers;
    return this;
  }

  public ALBTargetGroupRequest putHeader(String key, String value) {
    if (this.headers == null) {
      this.headers = new HashMap<>();
    }
    this.headers.put(key, value);
    return this;
  }

  public Map<String, List<String>> getMultiValueHeaders() {
    return multiValueHeaders;
  }

  public ALBTargetGroupRequest setMultiValueHeaders(Map<String, List<String>> multiValueHeaders) {
    this.multiValueHeaders = multiValueHeaders;
    return this;
  }

  public ALBTargetGroupRequest putMultiValueHeader(String key, List<String> values) {
    if (this.multiValueHeaders == null) {
      this.multiValueHeaders = new HashMap<>();
    }
    this.multiValueHeaders.put(key, values);
    return this;
  }

  public ALBTargetGroupRequestContext getRequestContext() {
    return requestContext;
  }

  public ALBTargetGroupRequest setRequestContext(ALBTargetGroupRequestContext requestContext) {
    this.requestContext = requestContext;
    return this;
  }

  public boolean getIsBase64Encoded() {
    return isBase64Encoded;
  }

  public ALBTargetGroupRequest setIsBase64Encoded(boolean base64Encoded) {
    isBase64Encoded = base64Encoded;
    return this;
  }

  public String getBody() {
    return body;
  }

  public ALBTargetGroupRequest setBody(String body) {
    this.body = body;
    return this;
  }
}
