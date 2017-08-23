package com.stixcloud.evoucher.api;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.stixcloud.evoucher.domain.EVoucherView;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.javamoney.moneta.Money;

import javax.annotation.Generated;
import javax.money.CurrencyUnit;
import javax.money.MonetaryAmount;

/**
 * Created by dbthan on 10-14-16.
 */

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({"eVoucherID", "redeemValue", "evoucherType", "validationResult",
    "validationConstant"})
public class EVoucherValidation implements Cloneable {

  @JsonProperty("eVoucherID")
  private String eVoucherID;
  @JsonProperty("redeemValue")
  private MonetaryAmount redeemValue;
  @JsonProperty("evoucherType")
  private String evoucherType;
  @JsonProperty("validationResult")
  private boolean validationResult;
  @JsonProperty("validationConstant")
  private String validationConstant;

  public EVoucherValidation() {

  }

  public EVoucherValidation(String eVoucherID, MonetaryAmount redeemValue, String evoucherType,
      boolean validationResult, String validationConstant) {
    this.eVoucherID = eVoucherID;
    this.redeemValue = redeemValue;
    this.evoucherType = evoucherType;
    this.validationResult = validationResult;
    this.validationConstant = validationConstant;
  }

  public EVoucherValidation(EVoucherView evoucher, CurrencyUnit currencyUnit) {
    this.eVoucherID = evoucher.geteVoucherId();
    this.redeemValue = Money.of(evoucher.getRedeemValue(), currencyUnit);
    this.evoucherType = evoucher.geteVoucherType();
    this.validationResult = Boolean.FALSE;
  }

  /**
   * @return The eVoucherID
   */
  @JsonProperty("eVoucherID")
  public String getEVoucherID() {
    return eVoucherID;
  }

  /**
   * @param eVoucherID The eVoucherID
   */
  public void setEVoucherID(String eVoucherID) {
    this.eVoucherID = eVoucherID;
  }

  /**
   * @return The redeemValue
   */
  @JsonProperty("redeemValue")
  public MonetaryAmount getRedeemValue() {
    return redeemValue;
  }

  /**
   * @param redeemValue The redeemValue
   */
  public void setRedeemValue(MonetaryAmount redeemValue) {
    this.redeemValue = redeemValue;
  }

  /**
   * @return The evoucherType
   */
  @JsonProperty("evoucherType")
  public String getEvoucherType() {
    return evoucherType;
  }

  /**
   * @param evoucherType The evoucherType
   */
  public void setEvoucherType(String evoucherType) {
    this.evoucherType = evoucherType;
  }

  /**
   * @return The validationResult
   */
  @JsonProperty("validationResult")
  public boolean isValidationResult() {
    return validationResult;
  }

  /**
   * @param validationResult The validationResult
   */
  public void setValidationResult(boolean validationResult) {
    this.validationResult = validationResult;
  }

  /**
   * @return The validationConstant
   */
  @JsonProperty("validationConstant")
  public String getValidationConstant() {
    return validationConstant;
  }

  /**
   * @param validationConstant The validationConstant
   */
  public void setValidationConstant(String validationConstant) {
    this.validationConstant = validationConstant;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }

    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    EVoucherValidation that = (EVoucherValidation) o;
    return new EqualsBuilder().append(eVoucherID, that.getEVoucherID())
        .append(evoucherType, that.getEvoucherType()).append(redeemValue, that.getRedeemValue())
        .append(validationResult, that.isValidationResult())
        .append(validationConstant, that.getValidationConstant()).isEquals();
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder(17, 37).append(eVoucherID).append(evoucherType).append(redeemValue)
        .append(validationResult).append(validationConstant).toHashCode();
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.JSON_STYLE).append("eVoucherID", eVoucherID)
        .append("evoucherType", evoucherType).append("redeemValue", redeemValue)
        .append("validationResult", validationResult)
        .append("validationConstant", validationConstant).toString();
  }

  public Object clone() throws CloneNotSupportedException {
    return super.clone();
  }
}
