package com.stixcloud.product.api;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.HashSet;
import java.util.Set;
import javax.annotation.Generated;
import javax.money.MonetaryAmount;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
    "seatLevelAlias",
    "seatSectionId",
    "seatSectionAlias",
    "seatSectionType",
    "seatEntrance",
    "priceCategoryNum",
    "priceCategoryId",
    "price",
    "redeem",
    "coordinatesList"
})
public class SeatLevel {

  @JsonProperty("seatLevelAlias")
  private String seatLevelAlias;
  @JsonProperty("seatSectionId")
  private Long seatSectionId;
  @JsonProperty("seatSectionAlias")
  private String seatSectionAlias;
  @JsonProperty("seatSectionType")
  private String seatSectionType;
  @JsonProperty("seatEntrance")
  private String seatEntrance;
  @JsonProperty("priceCategoryNum")
  private Integer priceCategoryNum;
  @JsonProperty("priceCategoryId")
  private Long priceCategoryId;
  @JsonProperty("price")
  private MonetaryAmount price;
  @JsonProperty("redeem")
  private Boolean redeem;
  @JsonProperty("coordinatesList")
  private Set<String> coordinatesList = new HashSet<>();

  /**
   * No args constructor for use in serialization
   */
  public SeatLevel() {
  }

  public SeatLevel(String seatLevelAlias, Long seatSectionId, String seatSectionAlias,
                   String seatSectionType, String seatEntrance, Integer priceCategoryNum,
                   Long priceCategoryId, MonetaryAmount price, Boolean redeem) {
    this.seatLevelAlias = seatLevelAlias;
    this.seatSectionId = seatSectionId;
    this.seatSectionAlias = seatSectionAlias;
    this.seatSectionType = seatSectionType;
    this.seatEntrance = seatEntrance;
    this.priceCategoryNum = priceCategoryNum;
    this.priceCategoryId = priceCategoryId;
    this.price = price;
    this.redeem = redeem;
  }

  /**
   *
   * @param seatEntrance
   * @param price
   * @param seatSectionId
   * @param seatLevelAlias
   * @param coordinatesList
   * @param priceCategoryId
   * @param redeem
   * @param priceCategoryNum
   * @param seatSectionType
   * @param seatSectionAlias
   */
  public SeatLevel(String seatLevelAlias, Long seatSectionId, String seatSectionAlias,
                   String seatSectionType, String seatEntrance, Integer priceCategoryNum,
                   Long priceCategoryId, MonetaryAmount price, Boolean redeem,
                   Set<String> coordinatesList) {
    this.seatLevelAlias = seatLevelAlias;
    this.seatSectionId = seatSectionId;
    this.seatSectionAlias = seatSectionAlias;
    this.seatSectionType = seatSectionType;
    this.seatEntrance = seatEntrance;
    this.priceCategoryNum = priceCategoryNum;
    this.priceCategoryId = priceCategoryId;
    this.price = price;
    this.redeem = redeem;
    this.coordinatesList = coordinatesList;
  }

  /**
   * @return The seatLevelAlias
   */
  @JsonProperty("seatLevelAlias")
  public String getSeatLevelAlias() {
    return seatLevelAlias;
  }

  /**
   * @param seatLevelAlias The seatLevelAlias
   */
  @JsonProperty("seatLevelAlias")
  public void setSeatLevelAlias(String seatLevelAlias) {
    this.seatLevelAlias = seatLevelAlias;
  }

  /**
   * @return The seatSectionId
   */
  @JsonProperty("seatSectionId")
  public Long getSeatSectionId() {
    return seatSectionId;
  }

  /**
   * @param seatSectionId The seatSectionId
   */
  @JsonProperty("seatSectionId")
  public void setSeatSectionId(Long seatSectionId) {
    this.seatSectionId = seatSectionId;
  }

  /**
   * @return The seatSectionAlias
   */
  @JsonProperty("seatSectionAlias")
  public String getSeatSectionAlias() {
    return seatSectionAlias;
  }

  /**
   * @param seatSectionAlias The seatSectionAlias
   */
  @JsonProperty("seatSectionAlias")
  public void setSeatSectionAlias(String seatSectionAlias) {
    this.seatSectionAlias = seatSectionAlias;
  }

  /**
   * @return The seatSectionType
   */
  @JsonProperty("seatSectionType")
  public String getSeatSectionType() {
    return seatSectionType;
  }

  /**
   * @param seatSectionType The seatSectionType
   */
  @JsonProperty("seatSectionType")
  public void setSeatSectionType(String seatSectionType) {
    this.seatSectionType = seatSectionType;
  }

  /**
   * @return The seatEntrance
   */
  @JsonProperty("seatEntrance")
  public String getSeatEntrance() {
    return seatEntrance;
  }

  /**
   * @param seatEntrance The seatEntrance
   */
  @JsonProperty("seatEntrance")
  public void setSeatEntrance(String seatEntrance) {
    this.seatEntrance = seatEntrance;
  }

  /**
   * @return The priceCategoryNum
   */
  @JsonProperty("priceCategoryNum")
  public Integer getPriceCategoryNum() {
    return priceCategoryNum;
  }

  /**
   * @param priceCategoryNum The priceCategoryNum
   */
  @JsonProperty("priceCategoryNum")
  public void setPriceCategoryNum(Integer priceCategoryNum) {
    this.priceCategoryNum = priceCategoryNum;
  }

  /**
   * @return The priceCategoryId
   */
  @JsonProperty("priceCategoryId")
  public Long getPriceCategoryId() {
    return priceCategoryId;
  }

  /**
   * @param priceCategoryId The priceCategoryId
   */
  @JsonProperty("priceCategoryId")
  public void setPriceCategoryId(Long priceCategoryId) {
    this.priceCategoryId = priceCategoryId;
  }

  /**
   * @return The price
   */
  @JsonProperty("price")
  public MonetaryAmount getPrice() {
    return price;
  }

  /**
   * @param price The price
   */
  @JsonProperty("price")
  public void setPrice(MonetaryAmount price) {
    this.price = price;
  }

  /**
   * @return The redeem
   */
  @JsonProperty("redeem")
  public Boolean getRedeem() {
    return redeem;
  }

  /**
   * @param redeem The redeem
   */
  @JsonProperty("redeem")
  public void setRedeem(Boolean redeem) {
    this.redeem = redeem;
  }

  /**
   * @return The coordinatesList
   */
  @JsonProperty("coordinatesList")
  public Set<String> getCoordinatesList() {
    return coordinatesList;
  }

  /**
   * @param coordinatesList The coordinatesList
   */
  @JsonProperty("coordinatesList")
  public void setCoordinatesList(Set<String> coordinatesList) {
    this.coordinatesList = coordinatesList;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }

    if (!(o instanceof SeatLevel)) {
      return false;
    }

    SeatLevel seatLevel = (SeatLevel) o;

    return new EqualsBuilder()
        .append(getSeatLevelAlias(), seatLevel.getSeatLevelAlias())
        .append(getSeatSectionId(), seatLevel.getSeatSectionId())
        .append(getSeatSectionAlias(), seatLevel.getSeatSectionAlias())
        .append(getSeatSectionType(), seatLevel.getSeatSectionType())
        .append(getSeatEntrance(), seatLevel.getSeatEntrance())
        .append(getPriceCategoryNum(), seatLevel.getPriceCategoryNum())
        .append(getPriceCategoryId(), seatLevel.getPriceCategoryId())
        .append(getPrice(), seatLevel.getPrice())
        .append(getRedeem(), seatLevel.getRedeem())
        .append(getCoordinatesList(), seatLevel.getCoordinatesList())
        .isEquals();
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder(17, 37)
        .append(getSeatLevelAlias())
        .append(getSeatSectionId())
        .append(getSeatSectionAlias())
        .append(getSeatSectionType())
        .append(getSeatEntrance())
        .append(getPriceCategoryNum())
        .append(getPriceCategoryId())
        .append(getPrice())
        .append(getRedeem())
        .append(getCoordinatesList())
        .toHashCode();
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.JSON_STYLE)
        .append("seatLevelAlias", seatLevelAlias)
        .append("seatSectionId", seatSectionId)
        .append("seatSectionAlias", seatSectionAlias)
        .append("seatSectionType", seatSectionType)
        .append("seatEntrance", seatEntrance)
        .append("priceCategoryNum", priceCategoryNum)
        .append("priceCategoryId", priceCategoryId)
        .append("price", price)
        .append("redeem", redeem)
        .append("coordinatesList", coordinatesList)
        .toString();
  }
}
