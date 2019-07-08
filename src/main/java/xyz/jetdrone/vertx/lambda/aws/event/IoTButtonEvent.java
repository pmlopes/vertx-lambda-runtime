
package xyz.jetdrone.vertx.lambda.aws.event;


import io.vertx.codegen.annotations.DataObject;
import io.vertx.core.json.JsonObject;

@DataObject(generateConverter = true)
public class IoTButtonEvent {

  public IoTButtonEvent() {}

  public IoTButtonEvent(JsonObject json) {
    IoTButtonEventConverter.fromJson(json, this);
  }

  public JsonObject toJson() {
    JsonObject json = new JsonObject();
    IoTButtonEventConverter.toJson(this, json);
    return json;
  }

  private String batteryVoltage;
  private String clickType;
  private String serialNumber;

  public String getBatteryVoltage() {
    return batteryVoltage;
  }

  public IoTButtonEvent setBatteryVoltage(String batteryVoltage) {
    this.batteryVoltage = batteryVoltage;
    return this;
  }

  public String getClickType() {
    return clickType;
  }

  public IoTButtonEvent setClickType(String clickType) {
    this.clickType = clickType;
    return this;
  }

  public String getSerialNumber() {
    return serialNumber;
  }

  public IoTButtonEvent setSerialNumber(String serialNumber) {
    this.serialNumber = serialNumber;
    return this;
  }

  @Override
  public String toString() {
    return toJson().encodePrettily();
  }
}
