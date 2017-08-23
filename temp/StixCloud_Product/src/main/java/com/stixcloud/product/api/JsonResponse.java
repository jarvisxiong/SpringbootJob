package com.stixcloud.product.api;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by chongye on 18-Aug-16.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "httpStatus",
    "status",
    "statusMessage"
})
public class JsonResponse implements Serializable {
  @JsonProperty("httpStatus")
  private String httpStatus;
  @JsonProperty("status")
  private String status;
  @JsonProperty("statusMessage")
  private String statusMessage;
  @JsonIgnore
  private Map<String, Object> additionalProperties = new HashMap<>();

  public JsonResponse() {
  }

  private JsonResponse(Builder builder) {
    setHttpStatus(builder.httpStatus);
    setStatus(builder.status);
    setStatusMessage(builder.statusMessage);
    additionalProperties = builder.additionalProperties;
  }

  @JsonProperty("httpStatus")
  public String getHttpStatus() {
    return httpStatus;
  }

  public void setHttpStatus(String httpStatus) {
    this.httpStatus = httpStatus;
  }

  @JsonProperty("statusMessage")
  public String getStatusMessage() {
    return statusMessage;
  }

  public void setStatusMessage(String statusMessage) {
    this.statusMessage = statusMessage;
  }

  @JsonProperty("status")
  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  @JsonAnyGetter
  public Map<String, Object> getAdditionalProperties() {
    return this.additionalProperties;
  }

  @JsonAnySetter
  public void setAdditionalProperty(String name, Object value) {
    this.additionalProperties.put(name, value);
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.JSON_STYLE)
        .append("httpStatus", httpStatus)
        .append("status", status)
        .append("statusMessage", statusMessage)
        .toString();
  }

  public static final class Builder {
    private String httpStatus;
    private String status;
    private String statusMessage;
    private Map<String, Object> additionalProperties;

    public Builder() {
      this.additionalProperties = new HashedMap();
    }

    public Builder(JsonResponse copy) {
      this.httpStatus = copy.httpStatus;
      this.status = copy.status;
      this.statusMessage = copy.statusMessage;
      this.additionalProperties = copy.additionalProperties;
    }

    public Builder httpStatus(String httpStatus) {
      this.httpStatus = httpStatus;
      return this;
    }

    public Builder status(String status) {
      this.status = status;
      return this;
    }

    public Builder statusMessage(String statusMessage) {
      this.statusMessage = statusMessage;
      return this;
    }

    public Builder additionalProperties(String name, Object value) {
      this.additionalProperties.put(name, value);
      return this;
    }

    public JsonResponse build() {
      return new JsonResponse(this);
    }
  }
}
