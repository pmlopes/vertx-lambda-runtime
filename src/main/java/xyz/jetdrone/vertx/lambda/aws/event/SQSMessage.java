
package xyz.jetdrone.vertx.lambda.aws.event;


import io.vertx.codegen.annotations.DataObject;
import io.vertx.core.json.JsonObject;

@DataObject(generateConverter = true)
public class SQSMessage {

  public SQSMessage() {}

  public SQSMessage(JsonObject json) {
    SQSMessageConverter.fromJson(json, this);
  }

  public JsonObject toJson() {
    JsonObject json = new JsonObject();
    SQSMessageConverter.toJson(this, json);
    return json;
  }

  private JsonObject attributes;
  private String awsRegion;
  private String body;
  private String eventSource;
  private String eventSourceARN;
  private String md5OfBody;
  private String md5OfMessageAttributes;
  private JsonObject messageAttributes;
  private String messageId;
  private String receiptHandle;

  public JsonObject getAttributes() {
    return attributes;
  }

  public SQSMessage setAttributes(JsonObject attributes) {
    this.attributes = attributes;
    return this;
  }

  public String getAwsRegion() {
    return awsRegion;
  }

  public SQSMessage setAwsRegion(String awsRegion) {
    this.awsRegion = awsRegion;
    return this;
  }

  public String getBody() {
    return body;
  }

  public SQSMessage setBody(String body) {
    this.body = body;
    return this;
  }

  public String getEventSource() {
    return eventSource;
  }

  public SQSMessage setEventSource(String eventSource) {
    this.eventSource = eventSource;
    return this;
  }

  public String getEventSourceARN() {
    return eventSourceARN;
  }

  public SQSMessage setEventSourceARN(String eventSourceARN) {
    this.eventSourceARN = eventSourceARN;
    return this;
  }

  public String getMd5OfBody() {
    return md5OfBody;
  }

  public SQSMessage setMd5OfBody(String md5OfBody) {
    this.md5OfBody = md5OfBody;
    return this;
  }

  public String getMd5OfMessageAttributes() {
    return md5OfMessageAttributes;
  }

  public SQSMessage setMd5OfMessageAttributes(String md5OfMessageAttributes) {
    this.md5OfMessageAttributes = md5OfMessageAttributes;
    return this;
  }

  public JsonObject getMessageAttributes() {
    return messageAttributes;
  }

  public SQSMessage setMessageAttributes(JsonObject messageAttributes) {
    this.messageAttributes = messageAttributes;
    return this;
  }

  public String getMessageId() {
    return messageId;
  }

  public SQSMessage setMessageId(String messageId) {
    this.messageId = messageId;
    return this;
  }

  public String getReceiptHandle() {
    return receiptHandle;
  }

  public SQSMessage setReceiptHandle(String receiptHandle) {
    this.receiptHandle = receiptHandle;
    return this;
  }

  @Override
  public String toString() {
    return toJson().encodePrettily();
  }
}
