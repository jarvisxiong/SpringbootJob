package com.stixcloud.product.api;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.annotation.Generated;
import javax.money.MonetaryAmount;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
    "priceCatAlias",
    "priceCatAmount",
    "SeatLevel"
})
public class SeatSectionList {

  @JsonProperty("priceCatAlias")
  private String priceCatAlias;
  @JsonProperty("priceCatAmount")
  private MonetaryAmount priceCatAmount;
  @JsonProperty("SeatLevel")
  private List<SeatLevel> seatLevel = new ArrayList<SeatLevel>();

  /**
   * No args constructor for use in serialization
   */
  public SeatSectionList() {
  }

  public SeatSectionList(String priceCatAlias) {
    this.priceCatAlias = priceCatAlias;
  }

  public SeatSectionList(String priceCatAlias, MonetaryAmount priceCatAmount,
                         List<SeatLevel> seatLevel) {
    this.priceCatAlias = priceCatAlias;
    this.priceCatAmount = priceCatAmount;
    this.seatLevel = seatLevel;
  }

  /**
   * @return The priceCatAlias
   */
  @JsonProperty("priceCatAlias")
  public String getPriceCatAlias() {
    return priceCatAlias;
  }

  /**
   * @param priceCatAlias The priceCatAlias
   */
  @JsonProperty("priceCatAlias")
  public void setPriceCatAlias(String priceCatAlias) {
    this.priceCatAlias = priceCatAlias;
  }

  /**
   * @return The seatLevel
   */
  @JsonProperty("SeatLevel")
  public List<SeatLevel> getSeatLevel() {
    return seatLevel;
  }

  /**
   * @param seatLevel The SeatLevel
   */
  @JsonProperty("SeatLevel")
  public void setSeatLevel(List<SeatLevel> seatLevel) {
    this.seatLevel = seatLevel;
  }

  @JsonProperty("priceCatAmount")
  public MonetaryAmount getPriceCatAmount() {
    return priceCatAmount;
  }

  @JsonProperty("priceCatAmount")
  public void setPriceCatAmount(MonetaryAmount priceCatAmount) {
    this.priceCatAmount = priceCatAmount;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;

    SeatSectionList that = (SeatSectionList) o;

    return Objects.equals(this.priceCatAlias, that.priceCatAlias) &&
        Objects.equals(this.priceCatAmount, that.priceCatAmount) &&
        Objects.equals(this.seatLevel, that.seatLevel);
  }

  @Override
  public int hashCode() {
    return Objects.hash(priceCatAlias, priceCatAmount, seatLevel);
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this,
        ToStringStyle.JSON_STYLE)
        .append("priceCatAlias", priceCatAlias)
        .append("priceCatAmount", priceCatAmount)
        .append("seatLevel", seatLevel)
        .toString();
  }
}
