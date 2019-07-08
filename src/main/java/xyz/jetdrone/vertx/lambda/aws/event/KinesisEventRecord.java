
package xyz.jetdrone.vertx.lambda.aws.event;


import io.vertx.codegen.annotations.DataObject;
import io.vertx.core.json.JsonObject;

@DataObject(generateConverter = true)
public class KinesisEventRecord {

  public KinesisEventRecord() {}

  public KinesisEventRecord(JsonObject json) {
    KinesisEventRecordConverter.fromJson(json, this);
  }

  public JsonObject toJson() {
    JsonObject json = new JsonObject();
    KinesisEventRecordConverter.toJson(this, json);
    return json;
  }

  private String awsRegion;
  private String eventID;
  private String eventName;
  private String eventSource;
  private String eventSourceARN;
  private String eventVersion;
  private String invokeIdentityArn;
  private KinesisRecord kinesis;

  public String getAwsRegion() {
    return awsRegion;
  }

  public KinesisEventRecord setAwsRegion(String awsRegion) {
    this.awsRegion = awsRegion;
    return this;
  }

  public String getEventID() {
    return eventID;
  }

  public KinesisEventRecord setEventID(String eventID) {
    this.eventID = eventID;
    return this;
  }

  public String getEventName() {
    return eventName;
  }

  public KinesisEventRecord setEventName(String eventName) {
    this.eventName = eventName;
    return this;
  }

  public String getEventSource() {
    return eventSource;
  }

  public KinesisEventRecord setEventSource(String eventSource) {
    this.eventSource = eventSource;
    return this;
  }

  public String getEventSourceARN() {
    return eventSourceARN;
  }

  public KinesisEventRecord setEventSourceARN(String eventSourceARN) {
    this.eventSourceARN = eventSourceARN;
    return this;
  }

  public String getEventVersion() {
    return eventVersion;
  }

  public KinesisEventRecord setEventVersion(String eventVersion) {
    this.eventVersion = eventVersion;
    return this;
  }

  public String getInvokeIdentityArn() {
    return invokeIdentityArn;
  }

  public KinesisEventRecord setInvokeIdentityArn(String invokeIdentityArn) {
    this.invokeIdentityArn = invokeIdentityArn;
    return this;
  }

  public KinesisRecord getKinesis() {
    return kinesis;
  }

  public KinesisEventRecord setKinesis(KinesisRecord kinesis) {
    this.kinesis = kinesis;
    return this;
  }

  @Override
  public String toString() {
    return toJson().encodePrettily();
  }
}
