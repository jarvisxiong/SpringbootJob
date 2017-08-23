package com.stixcloud.product.api;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;
import javax.annotation.Generated;
import javax.money.MonetaryAmount;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
    "priceCatNum",
    "priceValue",
    "priceStatus",
    "priceCategoryId",
    "seatSectionId"
})
public class PriceCatList implements Serializable {
  private static final long serialVersionUID = -1088947324012605566L;
  @JsonProperty("priceCatNum")
  private Integer priceCatNum;
  @JsonProperty("priceValue")
  private MonetaryAmount priceValue;
  @JsonProperty("priceStatus")
  private Integer priceStatus;
  @JsonProperty("priceCategoryId")
  private Long priceCategoryId;
  @JsonProperty("seatSectionId")
  private Long seatSectionId;


  /**
   * No args constructor for use in serialization
   */
  public PriceCatList() {
  }

  /**
   *
   * @param priceCatNum
   * @param priceStatus
   * @param priceValue
   */
  public PriceCatList(Integer priceCatNum, MonetaryAmount priceValue, Integer priceStatus) {
    this.priceCatNum = priceCatNum;
    this.priceValue = priceValue;
    this.priceStatus = priceStatus;
  }
  
  public PriceCatList(Integer priceCatNum, MonetaryAmount priceValue,
      Long priceCategoryId, Long seatSectionId) {
    this.priceCatNum = priceCatNum;
    this.priceValue = priceValue;
    this.priceCategoryId = priceCategoryId;
    this.seatSectionId = seatSectionId;
  }

  public PriceCatList(Integer priceCatNum, MonetaryAmount priceValue, Integer priceStatus,
                      Long priceCategoryId, Long seatSectionId) {
    this.priceCatNum = priceCatNum;
    this.priceValue = priceValue;
    this.priceStatus = priceStatus;
    this.priceCategoryId = priceCategoryId;
    this.seatSectionId = seatSectionId;
  }

  /**
   * @return The priceCatNum
   */
  @JsonProperty("priceCatNum")
  public Integer getPriceCatNum() {
    return priceCatNum;
  }

  /**
   * @param priceCatNum The priceCatNum
   */
  @JsonProperty("priceCatNum")
  public void setPriceCatNum(Integer priceCatNum) {
    this.priceCatNum = priceCatNum;
  }

  /**
   * @return The priceValue
   */
  @JsonProperty("priceValue")
  public MonetaryAmount getPriceValue() {
    return priceValue;
  }

  /**
   * @param priceValue The priceValue
   */
  @JsonProperty("priceValue")
  public void setPriceValue(MonetaryAmount priceValue) {
    this.priceValue = priceValue;
  }

  /**
   * @return The priceStatus
   */
  @JsonProperty("priceStatus")
  public Integer getPriceStatus() {
    return priceStatus;
  }

  /**
   * @param priceStatus The priceStatus
   */
  @JsonProperty("priceStatus")
  public void setPriceStatus(Integer priceStatus) {
    this.priceStatus = priceStatus;
  }

  @JsonProperty("priceCategoryId")
  public Long getPriceCategoryId() {
    return priceCategoryId;
  }

  public void setPriceCategoryId(Long priceCategoryId) {
    this.priceCategoryId = priceCategoryId;
  }

  @JsonProperty("seatSectionId")
  public Long getSeatSectionId() {
    return seatSectionId;
  }

  public void setSeatSectionId(Long seatSectionId) {
    this.seatSectionId = seatSectionId;
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.JSON_STYLE)
        .append("priceCatNum", priceCatNum)
        .append("priceValue", priceValue)
        .append("priceStatus", priceStatus)
        .append("priceCategoryId", priceCategoryId)
        .append("seatSectionId", seatSectionId)
        .toString();
  }
}
