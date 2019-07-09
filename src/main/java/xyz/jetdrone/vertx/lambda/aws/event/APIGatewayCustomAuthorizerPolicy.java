
package xyz.jetdrone.vertx.lambda.aws.event;

import io.vertx.codegen.annotations.DataObject;
import io.vertx.core.json.JsonObject;

import java.util.ArrayList;
import java.util.List;

@DataObject(generateConverter = true)
public class APIGatewayCustomAuthorizerPolicy {

  public APIGatewayCustomAuthorizerPolicy() {}

  public APIGatewayCustomAuthorizerPolicy(JsonObject json) {
    APIGatewayCustomAuthorizerPolicyConverter.fromJson(json, this);
  }

  public JsonObject toJson() {
    JsonObject json = new JsonObject();
    APIGatewayCustomAuthorizerPolicyConverter.toJson(this, json);
    return json;
  }

  private List<IAMPolicyStatement> statement = new ArrayList<>();
  private String version;

  public List<IAMPolicyStatement> getStatement() {
    return statement;
  }

  public APIGatewayCustomAuthorizerPolicy setStatement(List<IAMPolicyStatement> statement) {
    this.statement = statement;
    return this;
  }

  public String getVersion() {
    return version;
  }

  public APIGatewayCustomAuthorizerPolicy setVersion(String version) {
    this.version = version;
    return this;
  }

  @Override
  public String toString() {
    return toJson().encodePrettily();
  }
}
