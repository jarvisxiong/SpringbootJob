package com.stixcloud.common.exception;

import java.io.Serializable;
import java.time.OffsetDateTime;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"httpStatus","errorCode", "exceptionName", "statusMessage", "errorTime","url"})
public class JsonResponse implements Serializable {

  private static final long serialVersionUID = -4567588170761125746L;
  @JsonProperty("httpStatus")
  private String httpStatus;
  @JsonProperty("statusMessage")
  private String statusMessage;
  @JsonProperty("url")
  private String url;
  @JsonProperty("exceptionName")
  private String exceptionName;
  @JsonProperty("errorCode")
  private String errorCode;
  @JsonProperty("errorTime")
  private OffsetDateTime errorTime;
  
  public JsonResponse() {
  }

  public JsonResponse(String httpStatus, String statusMessage) {
    this.httpStatus = httpStatus;
    this.statusMessage = statusMessage;
  }

  /**
   * @return the httpStatus.
   */
  @JsonProperty("httpStatus")
  public String getHttpStatus() {
    return httpStatus;
  }

  /**
   * @param httpStatus the httpStatus
   */
  public void setHttpStatus(String httpStatus) {
    this.httpStatus = httpStatus;
  }

  /**
   * @return the statusMessage.
   */
  @JsonProperty("statusMessage")
  public String getStatusMessage() {
    return statusMessage;
  }

  /**
   * @param statusMessage the statusMessage
   */
  public void setStatusMessage(String statusMessage) {
    this.statusMessage = statusMessage;
  }

  /**
   * @return the url
   */
  public String getUrl() {
    return url;
  }

  /**
   * @param url the url to set
   */
  public void setUrl(String url) {
    this.url = url;
  }

  /**
   * @return the exceptionName
   */
  public String getExceptionName() {
    return exceptionName;
  }

  /**
   * @param exceptionName the exceptionName to set
   */
  public void setExceptionName(String exceptionName) {
    this.exceptionName = exceptionName;
  }

  /**
   * @return the errorCode
   */
  public String getErrorCode() {
    return errorCode;
  }

  /**
   * @param errorCode the errorCode to set
   */
  public void setErrorCode(String errorCode) {
    this.errorCode = errorCode;
  }

  /**
   * @return the errorTime
   */
  public OffsetDateTime getErrorTime() {
    return errorTime;
  }

  /**
   * @param errorTime the errorTime to set
   */
  public void setErrorTime(OffsetDateTime errorTime) {
    this.errorTime = errorTime;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }

    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    JsonResponse that = (JsonResponse) o;
    return new EqualsBuilder().append(httpStatus, that.getHttpStatus())
        .append(statusMessage, that.getStatusMessage()).isEquals();
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder(17, 37).append(httpStatus).append(statusMessage).toHashCode();
  }

}
