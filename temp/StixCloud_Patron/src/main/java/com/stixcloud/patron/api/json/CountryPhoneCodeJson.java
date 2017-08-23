package com.stixcloud.patron.api.json;

import java.io.Serializable;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"code", "callingCode"})
public class CountryPhoneCodeJson implements Serializable {

  private static final long serialVersionUID = -1720303444301804643L;

  @JsonProperty("code")
  private String code;
  @JsonProperty("callingCode")
  private Integer callingCode;

  public CountryPhoneCodeJson() {

  }

  public CountryPhoneCodeJson(String code, Integer callingCode) {
    this.code = code;
    this.callingCode = callingCode;
  }

  /**
   * @return the code
   */
  @JsonProperty("code")
  public String getCode() {
    return code;
  }

  /**
   * @param code the code to set
   */
  @JsonProperty("code")
  public void setCode(String code) {
    this.code = code;
  }

  /**
   * @return the callingCode
   */
  @JsonProperty("callingCode")
  public Integer getCallingCode() {
    return callingCode;
  }

  /**
   * @param callingCode the callingCode to set
   */
  @JsonProperty("callingCode")
  public void setCallingCode(Integer callingCode) {
    this.callingCode = callingCode;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }

    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    CountryPhoneCodeJson that = (CountryPhoneCodeJson) o;
    return new EqualsBuilder().append(code, that.getCode())
        .append(callingCode, that.getCallingCode()).isEquals();
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder(17, 37).append(code).append(callingCode).toHashCode();
  }
}
