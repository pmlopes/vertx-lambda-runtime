
package xyz.jetdrone.vertx.lambda.aws.event;


import io.vertx.codegen.annotations.DataObject;
import io.vertx.core.json.JsonObject;

@DataObject(generateConverter = true)
public class S3Bucket {

  public S3Bucket() {}

  public S3Bucket(JsonObject json) {
    S3BucketConverter.fromJson(json, this);
  }

  public JsonObject toJson() {
    JsonObject json = new JsonObject();
    S3BucketConverter.toJson(this, json);
    return json;
  }

  private String arn;
  private String name;
  private S3UserIdentity ownerIdentity;

  public String getArn() {
    return arn;
  }

  public S3Bucket setArn(String arn) {
    this.arn = arn;
    return this;
  }

  public String getName() {
    return name;
  }

  public S3Bucket setName(String name) {
    this.name = name;
    return this;
  }

  public S3UserIdentity getOwnerIdentity() {
    return ownerIdentity;
  }

  public S3Bucket setOwnerIdentity(S3UserIdentity ownerIdentity) {
    this.ownerIdentity = ownerIdentity;
    return this;
  }

  @Override
  public String toString() {
    return toJson().encodePrettily();
  }
}
