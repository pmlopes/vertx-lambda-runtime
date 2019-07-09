
package xyz.jetdrone.vertx.lambda.aws.event;

import io.vertx.codegen.annotations.DataObject;
import io.vertx.core.json.JsonObject;

import java.util.Date;

@DataObject(generateConverter = true)
public class SNSEntity {

  public SNSEntity() {}

  public SNSEntity(JsonObject json) {
    SNSEntityConverter.fromJson(json, this);
  }

  public JsonObject toJson() {
    JsonObject json = new JsonObject();
    SNSEntityConverter.toJson(this, json);
    return json;
  }

  private String message;
  private JsonObject messageAttributes;
  private String messageId;
  private String signature;
  private String signatureVersion;
  private String signingCertUrl;
  private String subject;
  private Date timestamp;
  private String topicArn;
  private String type;
  private String unsubscribeUrl;

  public String getMessage() {
    return message;
  }

  public SNSEntity setMessage(String message) {
    this.message = message;
    return this;
  }

  public JsonObject getMessageAttributes() {
    return messageAttributes;
  }

  public SNSEntity setMessageAttributes(JsonObject messageAttributes) {
    this.messageAttributes = messageAttributes;
    return this;
  }

  public String getMessageId() {
    return messageId;
  }

  public SNSEntity setMessageId(String messageId) {
    this.messageId = messageId;
    return this;
  }

  public String getSignature() {
    return signature;
  }

  public SNSEntity setSignature(String signature) {
    this.signature = signature;
    return this;
  }

  public String getSignatureVersion() {
    return signatureVersion;
  }

  public SNSEntity setSignatureVersion(String signatureVersion) {
    this.signatureVersion = signatureVersion;
    return this;
  }

  public String getSigningCertUrl() {
    return signingCertUrl;
  }

  public SNSEntity setSigningCertUrl(String signingCertUrl) {
    this.signingCertUrl = signingCertUrl;
    return this;
  }

  public String getSubject() {
    return subject;
  }

  public SNSEntity setSubject(String subject) {
    this.subject = subject;
    return this;
  }

  public Date getTimestamp() {
    return timestamp;
  }

  public SNSEntity setTimestamp(Date timestamp) {
    this.timestamp = timestamp;
    return this;
  }

  public String getTopicArn() {
    return topicArn;
  }

  public SNSEntity setTopicArn(String topicArn) {
    this.topicArn = topicArn;
    return this;
  }

  public String getType() {
    return type;
  }

  public SNSEntity setType(String type) {
    this.type = type;
    return this;
  }

  public String getUnsubscribeUrl() {
    return unsubscribeUrl;
  }

  public SNSEntity setUnsubscribeUrl(String unsubscribeUrl) {
    this.unsubscribeUrl = unsubscribeUrl;
    return this;
  }

  @Override
  public String toString() {
    return toJson().encodePrettily();
  }
}
