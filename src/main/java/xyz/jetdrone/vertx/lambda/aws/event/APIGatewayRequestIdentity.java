
package xyz.jetdrone.vertx.lambda.aws.event;

import io.vertx.codegen.annotations.DataObject;
import io.vertx.core.json.JsonObject;

@DataObject(generateConverter = true)
public class APIGatewayRequestIdentity {

  public APIGatewayRequestIdentity() {}

  public APIGatewayRequestIdentity(JsonObject json) {
    APIGatewayRequestIdentityConverter.fromJson(json, this);
  }

  public JsonObject toJson() {
    JsonObject json = new JsonObject();
    APIGatewayRequestIdentityConverter.toJson(this, json);
    return json;
  }

  private String accountId;
  private String apiKey;
  private String caller;
  private String cognitoAuthenticationProvider;
  private String cognitoAuthenticationType;
  private String cognitoIdentityId;
  private String cognitoIdentityPoolId;
  private String sourceIp;
  private String user;
  private String userAgent;
  private String userArn;

  public String getAccountId() {
    return accountId;
  }

  public APIGatewayRequestIdentity setAccountId(String accountId) {
    this.accountId = accountId;
    return this;
  }

  public String getApiKey() {
    return apiKey;
  }

  public APIGatewayRequestIdentity setApiKey(String apiKey) {
    this.apiKey = apiKey;
    return this;
  }

  public String getCaller() {
    return caller;
  }

  public APIGatewayRequestIdentity setCaller(String caller) {
    this.caller = caller;
    return this;
  }

  public String getCognitoAuthenticationProvider() {
    return cognitoAuthenticationProvider;
  }

  public APIGatewayRequestIdentity setCognitoAuthenticationProvider(String cognitoAuthenticationProvider) {
    this.cognitoAuthenticationProvider = cognitoAuthenticationProvider;
    return this;
  }

  public String getCognitoAuthenticationType() {
    return cognitoAuthenticationType;
  }

  public APIGatewayRequestIdentity setCognitoAuthenticationType(String cognitoAuthenticationType) {
    this.cognitoAuthenticationType = cognitoAuthenticationType;
    return this;
  }

  public String getCognitoIdentityId() {
    return cognitoIdentityId;
  }

  public APIGatewayRequestIdentity setCognitoIdentityId(String cognitoIdentityId) {
    this.cognitoIdentityId = cognitoIdentityId;
    return this;
  }

  public String getCognitoIdentityPoolId() {
    return cognitoIdentityPoolId;
  }

  public APIGatewayRequestIdentity setCognitoIdentityPoolId(String cognitoIdentityPoolId) {
    this.cognitoIdentityPoolId = cognitoIdentityPoolId;
    return this;
  }

  public String getSourceIp() {
    return sourceIp;
  }

  public APIGatewayRequestIdentity setSourceIp(String sourceIp) {
    this.sourceIp = sourceIp;
    return this;
  }

  public String getUser() {
    return user;
  }

  public APIGatewayRequestIdentity setUser(String user) {
    this.user = user;
    return this;
  }

  public String getUserAgent() {
    return userAgent;
  }

  public APIGatewayRequestIdentity setUserAgent(String userAgent) {
    this.userAgent = userAgent;
    return this;
  }

  public String getUserArn() {
    return userArn;
  }

  public APIGatewayRequestIdentity setUserArn(String userArn) {
    this.userArn = userArn;
    return this;
  }

  @Override
  public String toString() {
    return toJson().encodePrettily();
  }
}
