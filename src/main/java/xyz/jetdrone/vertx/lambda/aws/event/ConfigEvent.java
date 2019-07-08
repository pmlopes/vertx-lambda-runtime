
package xyz.jetdrone.vertx.lambda.aws.event;

import io.vertx.codegen.annotations.DataObject;
import io.vertx.core.json.JsonObject;

@DataObject(generateConverter = true)
public class ConfigEvent {

  public ConfigEvent() {}

  public ConfigEvent(JsonObject json) {
    ConfigEventConverter.fromJson(json, this);
  }

  public JsonObject toJson() {
    JsonObject json = new JsonObject();
    ConfigEventConverter.toJson(this, json);
    return json;
  }

  private String accountId;
  private String configRuleArn;
  private String configRuleId;
  private String configRuleName;
  private Boolean eventLeftScope;
  private String executionRoleArn;
  private String invokingEvent;
  private String resultToken;
  private String ruleParameters;
  private String version;

  public String getAccountId() {
    return accountId;
  }

  public ConfigEvent setAccountId(String accountId) {
    this.accountId = accountId;
    return this;
  }

  public String getConfigRuleArn() {
    return configRuleArn;
  }

  public ConfigEvent setConfigRuleArn(String configRuleArn) {
    this.configRuleArn = configRuleArn;
    return this;
  }

  public String getConfigRuleId() {
    return configRuleId;
  }

  public ConfigEvent setConfigRuleId(String configRuleId) {
    this.configRuleId = configRuleId;
    return this;
  }

  public String getConfigRuleName() {
    return configRuleName;
  }

  public ConfigEvent setConfigRuleName(String configRuleName) {
    this.configRuleName = configRuleName;
    return this;
  }

  public Boolean getEventLeftScope() {
    return eventLeftScope;
  }

  public ConfigEvent setEventLeftScope(Boolean eventLeftScope) {
    this.eventLeftScope = eventLeftScope;
    return this;
  }

  public String getExecutionRoleArn() {
    return executionRoleArn;
  }

  public ConfigEvent setExecutionRoleArn(String executionRoleArn) {
    this.executionRoleArn = executionRoleArn;
    return this;
  }

  public String getInvokingEvent() {
    return invokingEvent;
  }

  public ConfigEvent setInvokingEvent(String invokingEvent) {
    this.invokingEvent = invokingEvent;
    return this;
  }

  public String getResultToken() {
    return resultToken;
  }

  public ConfigEvent setResultToken(String resultToken) {
    this.resultToken = resultToken;
    return this;
  }

  public String getRuleParameters() {
    return ruleParameters;
  }

  public ConfigEvent setRuleParameters(String ruleParameters) {
    this.ruleParameters = ruleParameters;
    return this;
  }

  public String getVersion() {
    return version;
  }

  public ConfigEvent setVersion(String version) {
    this.version = version;
    return this;
  }

  @Override
  public String toString() {
    return toJson().encodePrettily();
  }
}
