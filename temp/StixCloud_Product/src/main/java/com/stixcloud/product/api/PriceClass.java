package com.stixcloud.product.api;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;
import javax.annotation.Generated;
import javax.money.MonetaryAmount;

/**
 * Created by mhviet on 25-Aug-16.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({"priceClassCode", "priceClassAlias", "priceValueAmount", "availableQty", "passwordRequired"})
public class PriceClass implements Serializable {

  /**
   *
   */
  private static final long serialVersionUID = -1534116593628293235L;

  @JsonProperty("priceClassCode")
  private String priceClassCode;
  @JsonProperty("priceClassAlias")
  private String priceClassAlias;
  @JsonProperty("priceValueAmount")
  private MonetaryAmount priceValueAmount;
  @JsonProperty("availableQty")
  private String availableQty;
  @JsonProperty("passwordRequired")
  private Integer passwordRequired;

  public PriceClass() {

  }

  public PriceClass(String priceClassCode, String priceClassAlias, MonetaryAmount priceValueAmount,
                    String availableQty, Integer passwordRequired) {
    this.priceClassCode = priceClassCode;
    this.priceClassAlias = priceClassAlias;
    this.priceValueAmount = priceValueAmount;
    this.availableQty = availableQty;
    this.passwordRequired = passwordRequired;
  }

  /**
   * @return The priceClassCode
   */
  @JsonProperty("priceClassCode")
  public String getPriceClassCode() {
    return priceClassCode;
  }

  /**
   * @param priceClassCode The priceClassCode
   */
  public void setPriceClassCode(String priceClassCode) {
    this.priceClassCode = priceClassCode;
  }

  /**
   * @return The priceClassAlias
   */
  public String getPriceClassAlias() {
    return priceClassAlias;
  }

  /**
   * @param priceClassAlias The priceClassAlias
   */
  public void setPriceClassAlias(String priceClassAlias) {
    this.priceClassAlias = priceClassAlias;
  }

  /**
   * @return The priceValueAmount
   */
  public MonetaryAmount getPriceValueAmount() {
    return priceValueAmount;
  }

  /**
   * @param priceValueAmount The priceValueAmount
   */
  public void setPriceValueAmount(MonetaryAmount priceValueAmount) {
    this.priceValueAmount = priceValueAmount;
  }

  /**
   * @return The availableQty
   */
  public String getAvailableQty() {
    return availableQty;
  }

  /**
   * @param availableQty The availableQty
   */
  public void setAvailableQty(String availableQty) {
    this.availableQty = availableQty;
  }

  /**
   * @return The passwordRequired
   */
  @JsonProperty("passwordRequired")
  public Integer getPasswordRequired() {
    return passwordRequired;
  }

  /**
   * @param passwordRequired The passwordRequired
   */
  public void setPasswordRequired(Integer passwordRequired) {
    this.passwordRequired = passwordRequired;
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.JSON_STYLE)
        .append("priceClassCode", priceClassCode).append("priceClassAlias", priceClassAlias)
        .append("priceValueAmount", priceValueAmount).append("availableQty", availableQty)
        .append("passwordRequired", passwordRequired)
        .toString();
  }

  @Override
  public boolean equals(Object o) {
    if (o == null) {
      return false;
    }

    PriceClass priceClass = (PriceClass) o;
    return priceClass.getPriceClassCode().equals(this.priceClassCode)
        && priceClass.getPriceClassAlias().equals(this.priceClassAlias)
        && priceClass.getAvailableQty().equals(this.availableQty)
        && priceClass.getPriceValueAmount().equals(this.priceValueAmount)
        && priceClass.getPasswordRequired().equals(this.passwordRequired);
  }

  @Override
  public int hashCode() {
    return this.priceClassCode.hashCode() ^ this.priceClassAlias.hashCode()
        ^ this.availableQty.hashCode() ^ this.priceValueAmount.hashCode()
        ^ this.passwordRequired.hashCode();
  }
}
