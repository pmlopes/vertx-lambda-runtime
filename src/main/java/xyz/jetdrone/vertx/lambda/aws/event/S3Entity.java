
package xyz.jetdrone.vertx.lambda.aws.event;


import io.vertx.codegen.annotations.DataObject;
import io.vertx.core.json.JsonObject;

@DataObject(generateConverter = true)
public class S3Entity {

  public S3Entity() {}

  public S3Entity(JsonObject json) {
    S3EntityConverter.fromJson(json, this);
  }

  public JsonObject toJson() {
    JsonObject json = new JsonObject();
    S3EntityConverter.toJson(this, json);
    return json;
  }

  private S3Bucket bucket;
  private String configurationId;
  private S3Object object;
  private String s3SchemaVersion;

  public S3Bucket getBucket() {
    return bucket;
  }

  public S3Entity setBucket(S3Bucket bucket) {
    this.bucket = bucket;
    return this;
  }

  public String getConfigurationId() {
    return configurationId;
  }

  public S3Entity setConfigurationId(String configurationId) {
    this.configurationId = configurationId;
    return this;
  }

  public S3Object getObject() {
    return object;
  }

  public S3Entity setObject(S3Object object) {
    this.object = object;
    return this;
  }

  public String getS3SchemaVersion() {
    return s3SchemaVersion;
  }

  public S3Entity setS3SchemaVersion(String s3SchemaVersion) {
    this.s3SchemaVersion = s3SchemaVersion;
    return this;
  }

  @Override
  public String toString() {
    return toJson().encodePrettily();
  }
}
