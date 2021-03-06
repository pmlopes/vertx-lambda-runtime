
package xyz.jetdrone.vertx.lambda.aws.event;

import io.vertx.codegen.annotations.DataObject;
import io.vertx.core.json.JsonObject;

@DataObject(generateConverter = true)
public class CloudwatchLogsEvent {

  public CloudwatchLogsEvent() {}

  public CloudwatchLogsEvent(JsonObject json) {
    CloudwatchLogsEventConverter.fromJson(json, this);
  }

  public JsonObject toJson() {
    JsonObject json = new JsonObject();
    CloudwatchLogsEventConverter.toJson(this, json);
    return json;
  }

  private CloudwatchLogsRawData awslogs;

  public CloudwatchLogsRawData getAwslogs() {
    return awslogs;
  }

  public CloudwatchLogsEvent setAwslogs(CloudwatchLogsRawData awslogs) {
    this.awslogs = awslogs;
    return this;
  }

  @Override
  public String toString() {
    return toJson().encodePrettily();
  }
}
