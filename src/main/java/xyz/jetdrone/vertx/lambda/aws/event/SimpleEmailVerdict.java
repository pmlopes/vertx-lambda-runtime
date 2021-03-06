
package xyz.jetdrone.vertx.lambda.aws.event;


import io.vertx.codegen.annotations.DataObject;
import io.vertx.core.json.JsonObject;

@DataObject(generateConverter = true)
public class SimpleEmailVerdict {

  public SimpleEmailVerdict() {}

  public SimpleEmailVerdict(JsonObject json) {
    SimpleEmailVerdictConverter.fromJson(json, this);
  }

  public JsonObject toJson() {
    JsonObject json = new JsonObject();
    SimpleEmailVerdictConverter.toJson(this, json);
    return json;
  }

  private String status;

  public String getStatus() {
    return status;
  }

  public SimpleEmailVerdict setStatus(String status) {
    this.status = status;
    return this;
  }

  @Override
  public String toString() {
    return toJson().encodePrettily();
  }
}
