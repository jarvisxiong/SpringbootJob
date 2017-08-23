package com.stixcloud.patron.api.json;

import java.io.Serializable;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"contactType", "country", "phoneNumber"})
public class ContactJson implements Serializable {

  private static final long serialVersionUID = -52181688741805891L;

  @JsonProperty("contactType")
  private String contactType;
  @JsonProperty("country")
  private CountryPhoneCodeJson country;
  @JsonProperty("phoneNumber")
  private String phoneNumber;

  public ContactJson() {

  }

  public ContactJson(String contactType, CountryPhoneCodeJson country,
      String phoneNumber) {
    this.contactType = contactType;
    this.country = country;
    this.phoneNumber = phoneNumber;
  }

  /**
   * @return the contactType
   */
  @JsonProperty("contactType")
  public String getContactType() {
    return contactType;
  }

  /**
   * @param contactType the contactType to set
   */
  @JsonProperty("contactType")
  public void setContactType(String contactType) {
    this.contactType = contactType;
  }

  /**
   * @return the country
   */
  @JsonProperty("country")
  public CountryPhoneCodeJson getCountry() {
    return country;
  }

  /**
   * @param country the country to set
   */
  @JsonProperty("country")
  public void setCountry(CountryPhoneCodeJson country) {
    this.country = country;
  }

  /**
   * @return the phoneNumber
   */
  @JsonProperty("phoneNumber")
  public String getPhoneNumber() {
    if (StringUtils.isEmpty(phoneNumber)) {
      return StringUtils.EMPTY;
    }
    return phoneNumber;
  }

  /**
   * @param phoneNumber the phoneNumber to set
   */
  @JsonProperty("phoneNumber")
  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }

    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    ContactJson that = (ContactJson) o;
    return new EqualsBuilder().append(contactType, that.getContactType())
        .append(country, that.getCountry()).append(phoneNumber, that.getPhoneNumber()).isEquals();
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder(17, 37).append(contactType).append(country).append(phoneNumber)
        .toHashCode();
  }

}
