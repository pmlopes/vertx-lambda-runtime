package xyz.jetdrone.vertx.lambda.aws.event;

import io.vertx.codegen.annotations.DataObject;
import io.vertx.core.json.JsonObject;

@DataObject(generateConverter = true)
public class ELBContext {

  public ELBContext() {}

  public ELBContext(JsonObject json) {
    ELBContextConverter.fromJson(json, this);
  }

  public JsonObject toJson() {
    JsonObject json = new JsonObject();
    ELBContextConverter.toJson(this, json);
    return json;
  }

  private String targetGroupArn;

  public String getTargetGroupArn() {
    return targetGroupArn;
  }

  public ELBContext setTargetGroupArn(String targetGroupArn) {
    this.targetGroupArn = targetGroupArn;
    return this;
  }
}
