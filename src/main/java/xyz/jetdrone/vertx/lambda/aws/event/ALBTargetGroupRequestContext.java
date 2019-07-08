package xyz.jetdrone.vertx.lambda.aws.event;

import io.vertx.codegen.annotations.DataObject;
import io.vertx.core.json.JsonObject;

@DataObject(generateConverter = true)
public class ALBTargetGroupRequestContext {

  public ALBTargetGroupRequestContext() {}

  public ALBTargetGroupRequestContext(JsonObject json) {
    ALBTargetGroupRequestContextConverter.fromJson(json, this);
  }

  public JsonObject toJson() {
    JsonObject json = new JsonObject();
    ALBTargetGroupRequestContextConverter.toJson(this, json);
    return json;
  }

  private ELBContext elb;

  public ELBContext getElb() {
    return elb;
  }

  public ALBTargetGroupRequestContext setElb(ELBContext elb) {
    this.elb = elb;
    return this;
  }
}
