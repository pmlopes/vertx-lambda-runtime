
package xyz.jetdrone.vertx.lambda.aws.event;


import io.vertx.codegen.annotations.DataObject;
import io.vertx.core.json.JsonObject;

@DataObject(generateConverter = true)
public class SimpleEmailHeader {

  public SimpleEmailHeader() {}

  public SimpleEmailHeader(JsonObject json) {
    SimpleEmailHeaderConverter.fromJson(json, this);
  }

  public JsonObject toJson() {
    JsonObject json = new JsonObject();
    SimpleEmailHeaderConverter.toJson(this, json);
    return json;
  }

  private String name;
  private String value;

  public String getName() {
    return name;
  }

  public SimpleEmailHeader setName(String name) {
    this.name = name;
    return this;
  }

  public String getValue() {
    return value;
  }

  public SimpleEmailHeader setValue(String value) {
    this.value = value;
    return this;
  }

  @Override
  public String toString() {
    return toJson().encodePrettily();
  }
}
