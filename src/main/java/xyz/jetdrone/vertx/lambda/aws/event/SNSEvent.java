
package xyz.jetdrone.vertx.lambda.aws.event;

import io.vertx.codegen.annotations.DataObject;
import io.vertx.core.json.JsonObject;

import java.util.ArrayList;
import java.util.List;

@DataObject(generateConverter = true)
public class SNSEvent {

  public SNSEvent() {}

  public SNSEvent(JsonObject json) {
    SNSEventConverter.fromJson(json, this);
  }

  public JsonObject toJson() {
    JsonObject json = new JsonObject();
    SNSEventConverter.toJson(this, json);
    return json;
  }

  private List<SNSEventRecord> records = new ArrayList<>();

  public List<SNSEventRecord> getRecords() {
    return records;
  }

  public SNSEvent setRecords(List<SNSEventRecord> records) {
    this.records = records;
    return this;
  }

  @Override
  public String toString() {
    return toJson().encodePrettily();
  }
}
