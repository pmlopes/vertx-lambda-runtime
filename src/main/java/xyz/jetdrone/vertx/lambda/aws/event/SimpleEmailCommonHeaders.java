
package xyz.jetdrone.vertx.lambda.aws.event;

import io.vertx.codegen.annotations.DataObject;
import io.vertx.core.json.JsonObject;

import java.util.ArrayList;
import java.util.List;

@DataObject(generateConverter = true)
public class SimpleEmailCommonHeaders {

  public SimpleEmailCommonHeaders() {}

  public SimpleEmailCommonHeaders(JsonObject json) {
    SimpleEmailCommonHeadersConverter.fromJson(json, this);
  }

  public JsonObject toJson() {
    JsonObject json = new JsonObject();
    SimpleEmailCommonHeadersConverter.toJson(this, json);
    return json;
  }

  private String date;
  private List<String> from = new ArrayList<>();
  private String messageId;
  private String returnPath;
  private String subject;
  private List<String> to = new ArrayList<>();

  public String getDate() {
    return date;
  }

  public SimpleEmailCommonHeaders setDate(String date) {
    this.date = date;
    return this;
  }

  public List<String> getFrom() {
    return from;
  }

  public SimpleEmailCommonHeaders setFrom(List<String> from) {
    this.from = from;
    return this;
  }

  public String getMessageId() {
    return messageId;
  }

  public SimpleEmailCommonHeaders setMessageId(String messageId) {
    this.messageId = messageId;
    return this;
  }

  public String getReturnPath() {
    return returnPath;
  }

  public SimpleEmailCommonHeaders setReturnPath(String returnPath) {
    this.returnPath = returnPath;
    return this;
  }

  public String getSubject() {
    return subject;
  }

  public SimpleEmailCommonHeaders setSubject(String subject) {
    this.subject = subject;
    return this;
  }

  public List<String> getTo() {
    return to;
  }

  public SimpleEmailCommonHeaders setTo(List<String> to) {
    this.to = to;
    return this;
  }

  @Override
  public String toString() {
    return toJson().encodePrettily();
  }
}
