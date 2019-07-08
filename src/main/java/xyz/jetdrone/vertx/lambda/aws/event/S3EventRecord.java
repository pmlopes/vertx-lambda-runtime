
package xyz.jetdrone.vertx.lambda.aws.event;

import io.vertx.codegen.annotations.DataObject;
import io.vertx.core.json.JsonObject;

import java.util.Date;

@DataObject(generateConverter = true)
public class S3EventRecord {

  public S3EventRecord() {}

  public S3EventRecord(JsonObject json) {
    S3EventRecordConverter.fromJson(json, this);
  }

  public JsonObject toJson() {
    JsonObject json = new JsonObject();
    S3EventRecordConverter.toJson(this, json);
    return json;
  }

  private String awsRegion;
  private String eventName;
  private String eventSource;
  private Date eventTime;
  private String eventVersion;
  private S3RequestParameters requestParameters;
  private JsonObject responseElements;
  private S3Entity s3;
  private S3UserIdentity userIdentity;

  public String getAwsRegion() {
    return awsRegion;
  }

  public S3EventRecord setAwsRegion(String awsRegion) {
    this.awsRegion = awsRegion;
    return this;
  }

  public String getEventName() {
    return eventName;
  }

  public S3EventRecord setEventName(String eventName) {
    this.eventName = eventName;
    return this;
  }

  public String getEventSource() {
    return eventSource;
  }

  public S3EventRecord setEventSource(String eventSource) {
    this.eventSource = eventSource;
    return this;
  }

  public Date getEventTime() {
    return eventTime;
  }

  public S3EventRecord setEventTime(Date eventTime) {
    this.eventTime = eventTime;
    return this;
  }

  public String getEventVersion() {
    return eventVersion;
  }

  public S3EventRecord setEventVersion(String eventVersion) {
    this.eventVersion = eventVersion;
    return this;
  }

  public S3RequestParameters getRequestParameters() {
    return requestParameters;
  }

  public S3EventRecord setRequestParameters(S3RequestParameters requestParameters) {
    this.requestParameters = requestParameters;
    return this;
  }

  public JsonObject getResponseElements() {
    return responseElements;
  }

  public S3EventRecord setResponseElements(JsonObject responseElements) {
    this.responseElements = responseElements;
    return this;
  }

  public S3Entity getS3() {
    return s3;
  }

  public S3EventRecord setS3(S3Entity s3) {
    this.s3 = s3;
    return this;
  }

  public S3UserIdentity getUserIdentity() {
    return userIdentity;
  }

  public S3EventRecord setUserIdentity(S3UserIdentity userIdentity) {
    this.userIdentity = userIdentity;
    return this;
  }

  @Override
  public String toString() {
    return toJson().encodePrettily();
  }
}
