
package xyz.jetdrone.vertx.lambda.aws.event;


import io.vertx.codegen.annotations.DataObject;
import io.vertx.core.json.JsonObject;

@DataObject(generateConverter = true)
public class SimpleEmailReceiptAction {

  public SimpleEmailReceiptAction() {}

  public SimpleEmailReceiptAction(JsonObject json) {
    SimpleEmailReceiptActionConverter.fromJson(json, this);
  }

  public JsonObject toJson() {
    JsonObject json = new JsonObject();
    SimpleEmailReceiptActionConverter.toJson(this, json);
    return json;
  }

  private String functionArn;
  private String invocationType;
  private String type;

  public String getFunctionArn() {
    return functionArn;
  }

  public SimpleEmailReceiptAction setFunctionArn(String functionArn) {
    this.functionArn = functionArn;
    return this;
  }

  public String getInvocationType() {
    return invocationType;
  }

  public SimpleEmailReceiptAction setInvocationType(String invocationType) {
    this.invocationType = invocationType;
    return this;
  }

  public String getType() {
    return type;
  }

  public SimpleEmailReceiptAction setType(String type) {
    this.type = type;
    return this;
  }

  @Override
  public String toString() {
    return toJson().encodePrettily();
  }
}
