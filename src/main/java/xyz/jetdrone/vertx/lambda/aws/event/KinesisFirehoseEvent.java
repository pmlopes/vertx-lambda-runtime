
package xyz.jetdrone.vertx.lambda.aws.event;

import io.vertx.codegen.annotations.DataObject;
import io.vertx.core.json.JsonObject;

import java.util.ArrayList;
import java.util.List;

@DataObject(generateConverter = true)
public class KinesisFirehoseEvent {

  public KinesisFirehoseEvent() {}

  public KinesisFirehoseEvent(JsonObject json) {
    KinesisFirehoseEventConverter.fromJson(json, this);
  }

  public JsonObject toJson() {
    JsonObject json = new JsonObject();
    KinesisFirehoseEventConverter.toJson(this, json);
    return json;
  }

  private String deliveryStreamArn;
  private String invocationId;
  private List<KinesisFirehoseEventRecord> records = new ArrayList<>();
  private String region;

  public String getDeliveryStreamArn() {
    return deliveryStreamArn;
  }

  public KinesisFirehoseEvent setDeliveryStreamArn(String deliveryStreamArn) {
    this.deliveryStreamArn = deliveryStreamArn;
    return this;
  }

  public String getInvocationId() {
    return invocationId;
  }

  public KinesisFirehoseEvent setInvocationId(String invocationId) {
    this.invocationId = invocationId;
    return this;
  }

  public List<KinesisFirehoseEventRecord> getRecords() {
    return records;
  }

  public KinesisFirehoseEvent setRecords(List<KinesisFirehoseEventRecord> records) {
    this.records = records;
    return this;
  }

  public String getRegion() {
    return region;
  }

  public KinesisFirehoseEvent setRegion(String region) {
    this.region = region;
    return this;
  }

  @Override
  public String toString() {
    return toJson().encodePrettily();
  }
}
