
package xyz.jetdrone.vertx.lambda.aws.event;

import io.vertx.codegen.annotations.DataObject;
import io.vertx.core.json.JsonObject;

@DataObject(generateConverter = true)
public class CloudwatchLogsRawData {

  public CloudwatchLogsRawData() {}

  public CloudwatchLogsRawData(JsonObject json) {
    CloudwatchLogsRawDataConverter.fromJson(json, this);
  }

  public JsonObject toJson() {
    JsonObject json = new JsonObject();
    CloudwatchLogsRawDataConverter.toJson(this, json);
    return json;
  }

  private String data;

  public String getData() {
    return data;
  }

  public CloudwatchLogsRawData setData(String data) {
    this.data = data;
    return this;
  }

  @Override
  public String toString() {
    return toJson().encodePrettily();
  }
}
