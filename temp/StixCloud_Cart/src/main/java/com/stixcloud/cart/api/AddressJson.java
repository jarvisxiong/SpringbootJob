package com.stixcloud.cart.api;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;
import java.util.Objects;
import javax.annotation.Generated;

/**
 * Created by sengkai on 11/30/2016.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
    "city",
    "country",
    "countrySubdivision",
    "line1",
    "line2",
    "line3",
    "postalCode",
    "type",
    "isPrimary"
})
public class AddressJson implements Serializable {

  private static final long serialVersionUID = 7203575315681848642L;
  @JsonProperty("city")
  private String city;
  @JsonProperty("country")
  private String country;
  @JsonProperty("countrySubdivision")
  private String countrySubdivision;
  @JsonProperty("line1")
  private String line1;
  @JsonProperty("line2")
  private String line2;
  @JsonProperty("line3")
  private String line3;
  @JsonProperty("postalCode")
  private String postalCode;
  @JsonProperty("type")
  private String type;
  @JsonProperty("isPrimary")
  private Boolean isPrimary;

  public AddressJson() {
  }

  private AddressJson(Builder builder) {
    setCity(builder.city);
    setCountry(builder.country);
    setCountrySubdivision(builder.countrySubdivision);
    setLine1(builder.line1);
    setLine2(builder.line2);
    setLine3(builder.line3);
    setPostalCode(builder.postalCode);
    setType(builder.type);
    setIsPrimary(builder.isPrimary);
  }

  /**
   * @return The city
   */
  @JsonProperty("city")
  public String getCity() {
    return city;
  }

  /**
   * @param city The city
   */
  @JsonProperty("city")
  public void setCity(String city) {
    this.city = city;
  }

  public AddressJson withCity(String city) {
    this.city = city;
    return this;
  }

  /**
   * @return The country
   */
  @JsonProperty("country")
  public String getCountry() {
    return country;
  }

  /**
   * @param country The country
   */
  @JsonProperty("country")
  public void setCountry(String country) {
    this.country = country;
  }

  public AddressJson withCountry(String country) {
    this.country = country;
    return this;
  }

  /**
   * @return The countrySubdivision
   */
  @JsonProperty("countrySubdivision")
  public String getCountrySubdivision() {
    return countrySubdivision;
  }

  /**
   * @param countrySubdivision The countrySubdivision
   */
  @JsonProperty("countrySubdivision")
  public void setCountrySubdivision(String countrySubdivision) {
    this.countrySubdivision = countrySubdivision;
  }

  public AddressJson withCountrySubdivision(String countrySubdivision) {
    this.countrySubdivision = countrySubdivision;
    return this;
  }

  /**
   * @return The line1
   */
  @JsonProperty("line1")
  public String getLine1() {
    return line1;
  }

  /**
   * @param line1 The line1
   */
  @JsonProperty("line1")
  public void setLine1(String line1) {
    this.line1 = line1;
  }

  public AddressJson withLine1(String line1) {
    this.line1 = line1;
    return this;
  }

  /**
   * @return The line2
   */
  @JsonProperty("line2")
  public String getLine2() {
    return line2;
  }

  /**
   * @param line2 The line2
   */
  @JsonProperty("line2")
  public void setLine2(String line2) {
    this.line2 = line2;
  }

  public AddressJson withLine2(String line2) {
    this.line2 = line2;
    return this;
  }

  /**
   * @return The line3
   */
  @JsonProperty("line3")
  public String getLine3() {
    return line3;
  }

  /**
   * @param line3 The line3
   */
  @JsonProperty("line3")
  public void setLine3(String line3) {
    this.line3 = line3;
  }

  public AddressJson withLine3(String line3) {
    this.line3 = line3;
    return this;
  }

  /**
   * @return The postalCode
   */
  @JsonProperty("postalCode")
  public String getPostalCode() {
    return postalCode;
  }

  /**
   * @param postalCode The postalCode
   */
  @JsonProperty("postalCode")
  public void setPostalCode(String postalCode) {
    this.postalCode = postalCode;
  }

  public AddressJson withPostalCode(String postalCode) {
    this.postalCode = postalCode;
    return this;
  }

  /**
   * @return The type
   */
  @JsonProperty("type")
  public String getType() {
    return type;
  }

  /**
   * @param type The type
   */
  @JsonProperty("type")
  public void setType(String type) {
    this.type = type;
  }

  public AddressJson withType(String type) {
    this.type = type;
    return this;
  }

  /**
   * @return The isPrimary
   */
  @JsonProperty("isPrimary")
  public Boolean getIsPrimary() {
    return isPrimary;
  }

  /**
   * @param isPrimary The isPrimary
   */
  @JsonProperty("isPrimary")
  public void setIsPrimary(Boolean isPrimary) {
    this.isPrimary = isPrimary;
  }

  public AddressJson withIsPrimary(Boolean isPrimary) {
    this.isPrimary = isPrimary;
    return this;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AddressJson addressJson = (AddressJson) o;
    return Objects.equals(city, addressJson.city) &&
        Objects.equals(country, addressJson.country) &&
        Objects.equals(countrySubdivision, addressJson.countrySubdivision) &&
        Objects.equals(line1, addressJson.line1) &&
        Objects.equals(line2, addressJson.line2) &&
        Objects.equals(line3, addressJson.line3) &&
        Objects.equals(postalCode, addressJson.postalCode) &&
        Objects.equals(type, addressJson.type) &&
        Objects.equals(isPrimary, addressJson.isPrimary);
  }

  @Override
  public int hashCode() {
    return Objects
        .hash(city, country, countrySubdivision, line1, line2, line3, postalCode, type, isPrimary);
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.JSON_STYLE)
        .append("city", city)
        .append("country", country)
        .append("countrySubdivision", countrySubdivision)
        .append("line1", line1)
        .append("line2", line2)
        .append("line3", line3)
        .append("postalCode", postalCode)
        .append("type", type)
        .append("isPrimary", isPrimary)
        .toString();
  }


  public static final class Builder {
    private String city;
    private String country;
    private String countrySubdivision;
    private String line1;
    private String line2;
    private String line3;
    private String postalCode;
    private String type;
    private Boolean isPrimary;

    public Builder() {
    }

    public Builder(AddressJson copy) {
      this.city = copy.city;
      this.country = copy.country;
      this.countrySubdivision = copy.countrySubdivision;
      this.line1 = copy.line1;
      this.line2 = copy.line2;
      this.line3 = copy.line3;
      this.postalCode = copy.postalCode;
      this.type = copy.type;
      this.isPrimary = copy.isPrimary;
    }

    public Builder city(String city) {
      this.city = city;
      return this;
    }

    public Builder country(String country) {
      this.country = country;
      return this;
    }

    public Builder countrySubdivision(String countrySubdivision) {
      this.countrySubdivision = countrySubdivision;
      return this;
    }

    public Builder line1(String line1) {
      this.line1 = line1;
      return this;
    }

    public Builder line2(String line2) {
      this.line2 = line2;
      return this;
    }

    public Builder line3(String line3) {
      this.line3 = line3;
      return this;
    }

    public Builder postalCode(String postalCode) {
      this.postalCode = postalCode;
      return this;
    }

    public Builder type(String type) {
      this.type = type;
      return this;
    }

    public Builder isPrimary(Boolean isPrimary) {
      this.isPrimary = isPrimary;
      return this;
    }

    public AddressJson build() {
      return new AddressJson(this);
    }
  }
}
