
package xyz.jetdrone.vertx.lambda.aws.event;

import io.vertx.codegen.annotations.DataObject;
import io.vertx.core.json.JsonObject;

import java.util.ArrayList;
import java.util.List;

@DataObject(generateConverter = true)
public class IAMPolicyStatement {

  public IAMPolicyStatement() {}

  public IAMPolicyStatement(JsonObject json) {
    IAMPolicyStatementConverter.fromJson(json, this);
  }

  public JsonObject toJson() {
    JsonObject json = new JsonObject();
    IAMPolicyStatementConverter.toJson(this, json);
    return json;
  }

  private List<String> action = new ArrayList<>();
  private String effect;
  private List<String> resource = new ArrayList<>();

  public List<String> getAction() {
    return action;
  }

  public IAMPolicyStatement setAction(List<String> action) {
    this.action = action;
    return this;
  }

  public String getEffect() {
    return effect;
  }

  public IAMPolicyStatement setEffect(String effect) {
    this.effect = effect;
    return this;
  }

  public List<String> getResource() {
    return resource;
  }

  public IAMPolicyStatement setResource(List<String> resource) {
    this.resource = resource;
    return this;
  }

  @Override
  public String toString() {
    return toJson().encodePrettily();
  }
}
