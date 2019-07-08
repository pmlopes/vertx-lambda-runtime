
package xyz.jetdrone.vertx.lambda.aws.event;


import io.vertx.codegen.annotations.DataObject;
import io.vertx.core.json.JsonObject;

@DataObject(generateConverter = true)
public class S3Object {

  public S3Object() {}

  public S3Object(JsonObject json) {
    S3ObjectConverter.fromJson(json, this);
  }

  public JsonObject toJson() {
    JsonObject json = new JsonObject();
    S3ObjectConverter.toJson(this, json);
    return json;
  }

  private String eTag;
  private String key;
  private String sequencer;
  private Integer size;
  private String urlDecodedKey;
  private String versionId;

  public String geteTag() {
    return eTag;
  }

  public S3Object seteTag(String eTag) {
    this.eTag = eTag;
    return this;
  }

  public String getKey() {
    return key;
  }

  public S3Object setKey(String key) {
    this.key = key;
    return this;
  }

  public String getSequencer() {
    return sequencer;
  }

  public S3Object setSequencer(String sequencer) {
    this.sequencer = sequencer;
    return this;
  }

  public Integer getSize() {
    return size;
  }

  public S3Object setSize(Integer size) {
    this.size = size;
    return this;
  }

  public String getUrlDecodedKey() {
    return urlDecodedKey;
  }

  public S3Object setUrlDecodedKey(String urlDecodedKey) {
    this.urlDecodedKey = urlDecodedKey;
    return this;
  }

  public String getVersionId() {
    return versionId;
  }

  public S3Object setVersionId(String versionId) {
    this.versionId = versionId;
    return this;
  }

  @Override
  public String toString() {
    return toJson().encodePrettily();
  }
}
