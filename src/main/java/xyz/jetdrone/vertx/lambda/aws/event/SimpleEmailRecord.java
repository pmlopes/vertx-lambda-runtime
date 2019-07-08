
package xyz.jetdrone.vertx.lambda.aws.event;


import io.vertx.codegen.annotations.DataObject;
import io.vertx.core.json.JsonObject;

@DataObject(generateConverter = true)
public class SimpleEmailRecord {

  public SimpleEmailRecord() {}

  public SimpleEmailRecord(JsonObject json) {
    SimpleEmailRecordConverter.fromJson(json, this);
  }

  public JsonObject toJson() {
    JsonObject json = new JsonObject();
    SimpleEmailRecordConverter.toJson(this, json);
    return json;
  }

  private String eventSource;
  private String eventVersion;
  private SimpleEmailService ses;

  public String getEventSource() {
    return eventSource;
  }

  public SimpleEmailRecord setEventSource(String eventSource) {
    this.eventSource = eventSource;
    return this;
  }

  public String getEventVersion() {
    return eventVersion;
  }

  public SimpleEmailRecord setEventVersion(String eventVersion) {
    this.eventVersion = eventVersion;
    return this;
  }

  public SimpleEmailService getSes() {
    return ses;
  }

  public SimpleEmailRecord setSes(SimpleEmailService ses) {
    this.ses = ses;
    return this;
  }

  @Override
  public String toString() {
    return toJson().encodePrettily();
  }
}
