package com.stixcloud.patron.api.json;

import java.io.Serializable;

import javax.annotation.Generated;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({"countryName", "countryCode", "countryCallingCode"})
public class CountryJson implements Serializable {
  /**
   * 
   */
  private static final long serialVersionUID = -7734834731680498847L;
  @JsonProperty("countryName")
  private String countryName;
  @JsonProperty("countryCode")
  private String countryCode;
  @JsonProperty("countryCallingCode")
  private String countryCallingCode;

  public CountryJson() {
  }

  public CountryJson(String countryName, String countryCode, String countryCallingCode) {
    super();
    this.countryName = countryName;
    this.countryCode = countryCode;
    this.countryCallingCode = countryCallingCode;
  }

  /**
   * @return the countryName
   */
  public String getCountryName() {
    return countryName;
  }

  /**
   * @param countryName the countryName to set
   */
  public void setCountryName(String countryName) {
    this.countryName = countryName;
  }

  /**
   * @return the countryCode
   */
  public String getCountryCode() {
    return countryCode;
  }

  /**
   * @param countryCode the countryCode to set
   */
  public void setCountryCode(String countryCode) {
    this.countryCode = countryCode;
  }

  /**
   * @return the countryCallingCode
   */
  public String getCountryCallingCode() {
    return countryCallingCode;
  }

  /**
   * @param countryCallingCode the countryCallingCode to set
   */
  public void setCountryCallingCode(String countryCallingCode) {
    this.countryCallingCode = countryCallingCode;
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.JSON_STYLE).append("countryName", countryName)
        .append("countryCode", countryCode).append("countryCallingCode", countryCallingCode)
        .toString();
  }

  @Override
  public boolean equals(Object o) {
    if (o == null) {
      return false;
    }

    CountryJson country = (CountryJson) o;
    return country.getCountryCode().equals(this.countryCode)
        && country.getCountryName().equals(this.countryName)
        && country.getCountryCallingCode().equals(this.countryCallingCode);
  }

  @Override
  public int hashCode() {
    return this.countryCode.hashCode() ^ this.countryName.hashCode()
        ^ this.countryCallingCode.hashCode();
  }
}
