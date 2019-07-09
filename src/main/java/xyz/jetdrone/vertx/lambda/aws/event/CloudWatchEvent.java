
package xyz.jetdrone.vertx.lambda.aws.event;

import io.vertx.codegen.annotations.DataObject;
import io.vertx.core.json.JsonObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@DataObject(generateConverter = true)
public class CloudWatchEvent {

  public CloudWatchEvent() {}

  public CloudWatchEvent(JsonObject json) {
    CloudWatchEventConverter.fromJson(json, this);
  }

  public JsonObject toJson() {
    JsonObject json = new JsonObject();
    CloudWatchEventConverter.toJson(this, json);
    return json;
  }

  private String account;
  private List<Integer> detail = new ArrayList<>();
  private String detailType;
  private String id;
  private String region;
  private List<String> resources = new ArrayList<>();
  private String source;
  private Date time;
  private String version;

  public String getAccount() {
    return account;
  }

  public CloudWatchEvent setAccount(String account) {
    this.account = account;
    return this;
  }

  public List<Integer> getDetail() {
    return detail;
  }

  public CloudWatchEvent setDetail(List<Integer> detail) {
    this.detail = detail;
    return this;
  }

  public String getDetailType() {
    return detailType;
  }

  public CloudWatchEvent setDetailType(String detailType) {
    this.detailType = detailType;
    return this;
  }

  public String getId() {
    return id;
  }

  public CloudWatchEvent setId(String id) {
    this.id = id;
    return this;
  }

  public String getRegion() {
    return region;
  }

  public CloudWatchEvent setRegion(String region) {
    this.region = region;
    return this;
  }

  public List<String> getResources() {
    return resources;
  }

  public CloudWatchEvent setResources(List<String> resources) {
    this.resources = resources;
    return this;
  }

  public String getSource() {
    return source;
  }

  public CloudWatchEvent setSource(String source) {
    this.source = source;
    return this;
  }

  public Date getTime() {
    return time;
  }

  public CloudWatchEvent setTime(Date time) {
    this.time = time;
    return this;
  }

  public String getVersion() {
    return version;
  }

  public CloudWatchEvent setVersion(String version) {
    this.version = version;
    return this;
  }

  @Override
  public String toString() {
    return toJson().encodePrettily();
  }
}
