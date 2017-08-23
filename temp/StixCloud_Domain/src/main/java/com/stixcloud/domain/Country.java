package com.stixcloud.domain;


import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Cacheable
@Entity
@Table(name = "COUNTRY")
public class Country implements Serializable  {

  private static final long serialVersionUID = 2564351946591573253L;
  private Long countryID;
  private String countryCode;
  private String currencyCode;
  private String countryCallingCode;
  private String currencySymbol;

  public Country() {

  }

  public Country(Long countryID, String countryCode, String currencyCode,
      String countryCallingCode) {
    super();
    this.countryID = countryID;
    this.countryCode = countryCode;
    this.currencyCode = currencyCode;
    this.countryCallingCode = countryCallingCode;
  }



  /**
   * Gets the country id.
   *
   * @return the country id
   */
  @Id
  @Column(name = "COUNTRYID", unique = true, nullable = false, precision = 10, scale = 0)
  public Long getCountryID() {
    return this.countryID;
  }

  /**
   * Sets the country id.
   *
   * @param countryID the new country id
   */
  public void setCountryID(Long countryID) {
    this.countryID = countryID;
  }

  /**
   * Gets the country code.
   *
   * @return the country code
   */
  @Column(name = "COUNTRYCODE", length = 2)
  public String getCountryCode() {
    return this.countryCode;
  }

  /**
   * Sets the country code.
   *
   * @param countryCode the new country code
   */
  public void setCountryCode(String countryCode) {
    this.countryCode = countryCode;
  }

  /**
   * Gets the country calling code.
   *
   * @return the country calling code
   */
  @Column(name = "COUNTRYCALLINGCODE", length = 4)
  public String getCountryCallingCode() {
    return this.countryCallingCode;
  }

  /**
   * Sets the country calling code.
   *
   * @param countryCallingCode the new country calling code
   */
  public void setCountryCallingCode(String countryCallingCode) {
    this.countryCallingCode = countryCallingCode;
  }

  /**
   * Gets the currency code.
   *
   * @return the currency code
   */
  @Column(name = "CURRENCYCODE", length = 4)
  public String getCurrencyCode() {
    return this.currencyCode;
  }

  /**
   * Sets the currency code.
   *
   * @param currencyCode the new currency code
   */
  public void setCurrencyCode(String currencyCode) {
    this.currencyCode = currencyCode;
  }

  /**
   * Gets the currency symbol.
   *
   * @return the currency code
   */
  @Column(name = "CURRENCYSYMBOL", length = 4)
  public String getCurrencySymbol() {
    return currencySymbol;
  }

  /**
   * Sets the currency code.
   *
   * @param currencyCode the new currency symbol
   */
  public void setCurrencySymbol(String currencySymbol) {
    this.currencySymbol = currencySymbol;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Country that = (Country) o;
    return Objects.equals(countryID, that.getCountryID())
        && Objects.equals(countryCode, that.getCountryCode())
        && Objects.equals(currencyCode, that.getCurrencyCode())
        && Objects.equals(countryCallingCode, that.getCountryCallingCode());
  }

  @Override
  public int hashCode() {
    return Objects.hash(countryID, countryCode, currencyCode, countryCallingCode);
  }

  /*
   * (non-Javadoc)
   * 
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString() {
    return "Country [" + (countryID != null ? "countryID=" + countryID + ", " : "")
        + (countryCode != null ? "countryCode=" + countryCode + ", " : "")
        + (currencyCode != null ? "currencyCode=" + currencyCode + ", " : "")
        + (countryCallingCode != null ? "countryCallingCode=" + countryCallingCode : "") + "]";
  }
}
