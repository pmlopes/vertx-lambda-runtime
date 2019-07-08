
package xyz.jetdrone.vertx.lambda.aws.event;

import io.vertx.codegen.annotations.DataObject;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.json.JsonObject;

@DataObject(generateConverter = true)
public class KinesisRecord {

  public KinesisRecord() {}

  public KinesisRecord(JsonObject json) {
    KinesisRecordConverter.fromJson(json, this);
  }

  public JsonObject toJson() {
    JsonObject json = new JsonObject();
    KinesisRecordConverter.toJson(this, json);
    return json;
  }

  private SecondsEpochTime approximateArrivalTimestamp;
  private Buffer data;
  private String encryptionType;
  private String kinesisSchemaVersion;
  private String partitionKey;
  private String sequenceNumber;

  public SecondsEpochTime getApproximateArrivalTimestamp() {
    return approximateArrivalTimestamp;
  }

  public KinesisRecord setApproximateArrivalTimestamp(SecondsEpochTime approximateArrivalTimestamp) {
    this.approximateArrivalTimestamp = approximateArrivalTimestamp;
    return this;
  }

  public Buffer getData() {
    return data;
  }

  public KinesisRecord setData(Buffer data) {
    this.data = data;
    return this;
  }

  public String getEncryptionType() {
    return encryptionType;
  }

  public KinesisRecord setEncryptionType(String encryptionType) {
    this.encryptionType = encryptionType;
    return this;
  }

  public String getKinesisSchemaVersion() {
    return kinesisSchemaVersion;
  }

  public KinesisRecord setKinesisSchemaVersion(String kinesisSchemaVersion) {
    this.kinesisSchemaVersion = kinesisSchemaVersion;
    return this;
  }

  public String getPartitionKey() {
    return partitionKey;
  }

  public KinesisRecord setPartitionKey(String partitionKey) {
    this.partitionKey = partitionKey;
    return this;
  }

  public String getSequenceNumber() {
    return sequenceNumber;
  }

  public KinesisRecord setSequenceNumber(String sequenceNumber) {
    this.sequenceNumber = sequenceNumber;
    return this;
  }

  @Override
  public String toString() {
    return toJson().encodePrettily();
  }
}
