
package xyz.jetdrone.vertx.lambda.aws.event;


import io.vertx.codegen.annotations.DataObject;
import io.vertx.core.json.JsonObject;

@DataObject(generateConverter = true)
public class SimpleEmailService {

  public SimpleEmailService() {}

  public SimpleEmailService(JsonObject json) {
    SimpleEmailServiceConverter.fromJson(json, this);
  }

  public JsonObject toJson() {
    JsonObject json = new JsonObject();
    SimpleEmailServiceConverter.toJson(this, json);
    return json;
  }

  private SimpleEmailMessage mail;
  private SimpleEmailReceipt receipt;

  public SimpleEmailMessage getMail() {
    return mail;
  }

  public SimpleEmailService setMail(SimpleEmailMessage mail) {
    this.mail = mail;
    return this;
  }

  public SimpleEmailReceipt getReceipt() {
    return receipt;
  }

  public SimpleEmailService setReceipt(SimpleEmailReceipt receipt) {
    this.receipt = receipt;
    return this;
  }

  @Override
  public String toString() {
    return toJson().encodePrettily();
  }
}
