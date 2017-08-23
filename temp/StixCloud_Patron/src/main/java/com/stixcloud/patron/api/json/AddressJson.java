package com.stixcloud.patron.api.json;

import java.io.Serializable;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"type", "countryCode", "addressLineOne", "addressLineTwo", "addressLineThree", "postalCode"})
public class AddressJson implements Serializable {

  private static final long serialVersionUID = -6484090944756540323L;

  @JsonProperty("type")
  private String type;
  @JsonProperty("countryCode")
  private String countryCode;
  @JsonProperty("addressLineOne")
  private String addressLineOne;
  @JsonProperty("addressLineTwo")
  private String addressLineTwo;
  @JsonProperty("addressLineThree")
  private String addressLineThree;
  @JsonProperty("postalCode")
  private String postalCode;

  public AddressJson() {}

  public AddressJson(String type, String countryCode, String addressLineOne, String addressLineTwo,
      String addressLineThree, String postalCode) {
    this.type = type;
    this.countryCode = countryCode;
    this.addressLineOne = addressLineOne;
    this.addressLineTwo = addressLineTwo;
    this.addressLineThree = addressLineThree;
    this.postalCode = postalCode;
  }

  /**
   * @return the type
   */
  @JsonProperty("type")
  public String getType() {
    return type;
  }

  /**
   * @param type the type to set
   */
  @JsonProperty("type")
  public void setType(String type) {
    this.type = type;
  }

  /**
   * @return the countryCode
   */
  @JsonProperty("countryCode")
  public String getCountryCode() {
    return countryCode;
  }

  /**
   * @param countryCode the countryCode to set
   */
  @JsonProperty("countryCode")
  public void setCountryCode(String countryCode) {
    this.countryCode = countryCode;
  }

  /**
   * @return the postalCode
   */
  @JsonProperty("postalCode")
  public String getPostalCode() {
    return postalCode;
  }

  /**
   * @param postalCode the postalCode to set
   */
  @JsonProperty("postalCode")
  public void setPostalCode(String postalCode) {
    this.postalCode = postalCode;
  }

 

  /**
   * @return the addressOne
   */
  public String getAddressLineOne() {
    return addressLineOne;
  }

  /**
   * @param addressOne the addressOne to set
   */
  public void setAddressLineOne(String addressLineOne) {
    this.addressLineOne = addressLineOne;
  }


  /**
   * @return the addressLineTwo
   */
  public String getAddressLineTwo() {
    return addressLineTwo;
  }

  /**
   * @param addressLineTwo the addressLineTwo to set
   */
  public void setAddressLineTwo(String addressLineTwo) {
    this.addressLineTwo = addressLineTwo;
  }

  /**
   * @return the addressLineThree
   */
  public String getAddressLineThree() {
    return addressLineThree;
  }

  /**
   * @param addressLineThree the addressLineThree to set
   */
  public void setAddressLineThree(String addressLineThree) {
    this.addressLineThree = addressLineThree;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }

    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    AddressJson that = (AddressJson) o;
    return new EqualsBuilder().append(type, that.getType())
        .append(countryCode, that.getCountryCode()).append(addressLineOne, that.getAddressLineOne())
        .append(addressLineTwo, that.getAddressLineTwo()).append(addressLineThree, that.getAddressLineThree())
        .append(postalCode, that.getPostalCode()).isEquals();
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder(17, 37).append(type).append(countryCode).append(addressLineOne)
        .append(addressLineTwo).append(addressLineThree).append(postalCode).toHashCode();
  }

}
