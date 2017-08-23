package com.stixcloud.masterpass.api;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;

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

  public JsonResponse() {
  }

  public JsonResponse(String httpStatus) {
    this.httpStatus = httpStatus;
  }

  public JsonResponse(String httpStatus, String status, String statusMessage) {
    this.httpStatus = httpStatus;
    this.status = status;
    this.statusMessage = statusMessage;
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

  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.JSON_STYLE)
        .append("httpStatus", httpStatus)
        .append("status", status)
        .append("statusMessage", statusMessage)
        .toString();
  }
}
