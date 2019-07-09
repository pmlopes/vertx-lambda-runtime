
package xyz.jetdrone.vertx.lambda.aws.event;


import io.vertx.codegen.annotations.DataObject;
import io.vertx.core.json.JsonObject;

@DataObject(generateConverter = true)
public class SNSEventRecord {

  public SNSEventRecord() {}

  public SNSEventRecord(JsonObject json) {
    SNSEventRecordConverter.fromJson(json, this);
  }

  public JsonObject toJson() {
    JsonObject json = new JsonObject();
    SNSEventRecordConverter.toJson(this, json);
    return json;
  }

  private String eventSource;
  private String eventSubscriptionArn;
  private String eventVersion;
  private SNSEntity sns;

  public String getEventSource() {
    return eventSource;
  }

  public SNSEventRecord setEventSource(String eventSource) {
    this.eventSource = eventSource;
    return this;
  }

  public String getEventSubscriptionArn() {
    return eventSubscriptionArn;
  }

  public SNSEventRecord setEventSubscriptionArn(String eventSubscriptionArn) {
    this.eventSubscriptionArn = eventSubscriptionArn;
    return this;
  }

  public String getEventVersion() {
    return eventVersion;
  }

  public SNSEventRecord setEventVersion(String eventVersion) {
    this.eventVersion = eventVersion;
    return this;
  }

  public SNSEntity getSns() {
    return sns;
  }

  public SNSEventRecord setSns(SNSEntity sns) {
    this.sns = sns;
    return this;
  }

  @Override
  public String toString() {
    return toJson().encodePrettily();
  }
}
