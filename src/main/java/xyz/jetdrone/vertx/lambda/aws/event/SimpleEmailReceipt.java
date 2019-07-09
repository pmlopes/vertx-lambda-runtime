
package xyz.jetdrone.vertx.lambda.aws.event;

import io.vertx.codegen.annotations.DataObject;
import io.vertx.core.json.JsonObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@DataObject(generateConverter = true)
public class SimpleEmailReceipt {

  public SimpleEmailReceipt() {}

  public SimpleEmailReceipt(JsonObject json) {
    SimpleEmailReceiptConverter.fromJson(json, this);
  }

  public JsonObject toJson() {
    JsonObject json = new JsonObject();
    SimpleEmailReceiptConverter.toJson(this, json);
    return json;
  }

  private SimpleEmailReceiptAction action;
  private SimpleEmailVerdict dkimVerdict;
  private SimpleEmailVerdict dmarcPolicy;
  private SimpleEmailVerdict dmarcVerdict;
  private Integer processingTimeMillis;
  private List<String> recipients = new ArrayList<>();
  private SimpleEmailVerdict spamVerdict;
  private SimpleEmailVerdict spfVerdict;
  private Date timestamp;
  private SimpleEmailVerdict virusVerdict;

  public SimpleEmailReceiptAction getAction() {
    return action;
  }

  public SimpleEmailReceipt setAction(SimpleEmailReceiptAction action) {
    this.action = action;
    return this;
  }

  public SimpleEmailVerdict getDkimVerdict() {
    return dkimVerdict;
  }

  public SimpleEmailReceipt setDkimVerdict(SimpleEmailVerdict dkimVerdict) {
    this.dkimVerdict = dkimVerdict;
    return this;
  }

  public SimpleEmailVerdict getDmarcPolicy() {
    return dmarcPolicy;
  }

  public SimpleEmailReceipt setDmarcPolicy(SimpleEmailVerdict dmarcPolicy) {
    this.dmarcPolicy = dmarcPolicy;
    return this;
  }

  public SimpleEmailVerdict getDmarcVerdict() {
    return dmarcVerdict;
  }

  public SimpleEmailReceipt setDmarcVerdict(SimpleEmailVerdict dmarcVerdict) {
    this.dmarcVerdict = dmarcVerdict;
    return this;
  }

  public Integer getProcessingTimeMillis() {
    return processingTimeMillis;
  }

  public SimpleEmailReceipt setProcessingTimeMillis(Integer processingTimeMillis) {
    this.processingTimeMillis = processingTimeMillis;
    return this;
  }

  public List<String> getRecipients() {
    return recipients;
  }

  public SimpleEmailReceipt setRecipients(List<String> recipients) {
    this.recipients = recipients;
    return this;
  }

  public SimpleEmailVerdict getSpamVerdict() {
    return spamVerdict;
  }

  public SimpleEmailReceipt setSpamVerdict(SimpleEmailVerdict spamVerdict) {
    this.spamVerdict = spamVerdict;
    return this;
  }

  public SimpleEmailVerdict getSpfVerdict() {
    return spfVerdict;
  }

  public SimpleEmailReceipt setSpfVerdict(SimpleEmailVerdict spfVerdict) {
    this.spfVerdict = spfVerdict;
    return this;
  }

  public Date getTimestamp() {
    return timestamp;
  }

  public SimpleEmailReceipt setTimestamp(Date timestamp) {
    this.timestamp = timestamp;
    return this;
  }

  public SimpleEmailVerdict getVirusVerdict() {
    return virusVerdict;
  }

  public SimpleEmailReceipt setVirusVerdict(SimpleEmailVerdict virusVerdict) {
    this.virusVerdict = virusVerdict;
    return this;
  }

  @Override
  public String toString() {
    return toJson().encodePrettily();
  }
}
