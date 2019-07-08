
package xyz.jetdrone.vertx.lambda.aws.event;

import io.vertx.codegen.annotations.DataObject;
import io.vertx.core.json.JsonObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@DataObject(generateConverter = true)
public class SimpleEmailMessage {

  public SimpleEmailMessage() {}

  public SimpleEmailMessage(JsonObject json) {
    SimpleEmailMessageConverter.fromJson(json, this);
  }

  public JsonObject toJson() {
    JsonObject json = new JsonObject();
    SimpleEmailMessageConverter.toJson(this, json);
    return json;
  }

  private SimpleEmailCommonHeaders commonHeaders;
  private List<String> destination = new ArrayList<>();
  private List<SimpleEmailHeader> headers = new ArrayList<>();
  private Boolean headersTruncated;
  private String messageId;
  private String source;
  private Date timestamp;

  public SimpleEmailCommonHeaders getCommonHeaders() {
    return commonHeaders;
  }

  public SimpleEmailMessage setCommonHeaders(SimpleEmailCommonHeaders commonHeaders) {
    this.commonHeaders = commonHeaders;
    return this;
  }

  public List<String> getDestination() {
    return destination;
  }

  public SimpleEmailMessage setDestination(List<String> destination) {
    this.destination = destination;
    return this;
  }

  public List<SimpleEmailHeader> getHeaders() {
    return headers;
  }

  public SimpleEmailMessage setHeaders(List<SimpleEmailHeader> headers) {
    this.headers = headers;
    return this;
  }

  public Boolean getHeadersTruncated() {
    return headersTruncated;
  }

  public SimpleEmailMessage setHeadersTruncated(Boolean headersTruncated) {
    this.headersTruncated = headersTruncated;
    return this;
  }

  public String getMessageId() {
    return messageId;
  }

  public SimpleEmailMessage setMessageId(String messageId) {
    this.messageId = messageId;
    return this;
  }

  public String getSource() {
    return source;
  }

  public SimpleEmailMessage setSource(String source) {
    this.source = source;
    return this;
  }

  public Date getTimestamp() {
    return timestamp;
  }

  public SimpleEmailMessage setTimestamp(Date timestamp) {
    this.timestamp = timestamp;
    return this;
  }

  @Override
  public String toString() {
    return toJson().encodePrettily();
  }
}
