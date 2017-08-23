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
 * Created by dbthan on 06-Sep-16.
 */

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({"seatSectionId", "seatSectionAlias", "seatSectionType", "priceCategoryNum",
    "priceCategoryId", "seatSectionAvailability", "price"})
public class SectionGa implements Serializable {

  private static final long serialVersionUID = -1534116593628293235L;

  @JsonProperty("seatSectionId")
  private Long seatSectionId;
  @JsonProperty("seatSectionAlias")
  private String seatSectionAlias;
  @JsonProperty("seatSectionType")
  private String seatSectionType;
  @JsonProperty("priceCategoryNum")
  private Integer priceCategoryNum;
  @JsonProperty("priceCategoryId")
  private Long priceCategoryId;
  @JsonProperty("seatSectionAvailability")
  private Integer seatSectionAvailability;
  @JsonProperty("price")
  private MonetaryAmount price;

  /**
   * Constructor without argument.
   */
  public SectionGa() {

  }

  /**
   * Constructor with arguments.
   * @param seatSectionId The seatSectionId
   * @param seatSectionAlias The seatSectionAlias
   * @param seatSectionType The seatSectionType
   * @param priceCategoryNum The priceCategoryNum
   * @param priceCategoryId The priceCategoryId
   * @param seatSectionAvailability The seatSectionAvailability
   * @param price The price
   */
  public SectionGa(Long seatSectionId, String seatSectionAlias,
                   String seatSectionType, Integer priceCategoryNum, Long priceCategoryId,
                   Integer seatSectionAvailability, MonetaryAmount price) {
    this.seatSectionId = seatSectionId;
    this.seatSectionAlias = seatSectionAlias;
    this.seatSectionType = seatSectionType;
    this.priceCategoryNum = priceCategoryNum;
    this.priceCategoryId = priceCategoryId;
    this.seatSectionAvailability = seatSectionAvailability;
    this.price = price;
  }

  /**
   * @return The seatSectionId.
   */
  @JsonProperty("seatSectionId")
  public Long getSeatSectionId() {
    return seatSectionId;
  }

  /**
   * @param seatSectionId The seatSectionId.
   */
  public void setSeatSectionId(Long seatSectionId) {
    this.seatSectionId = seatSectionId;
  }

  /**
   * @return The seatSectionAlias.
   */
  @JsonProperty("seatSectionAlias")
  public String getSeatSectionAlias() {
    return seatSectionAlias;
  }

  /**
   * @param seatSectionAlias The seatSectionAlias.
   */
  public void setSeatSectionAlias(String seatSectionAlias) {
    this.seatSectionAlias = seatSectionAlias;
  }

  /**
   * @return The seatSectionType.
   */
  @JsonProperty("seatSectionType")
  public String getSeatSectionType() {
    return seatSectionType;
  }

  /**
   * @param seatSectionType The seatSectionType.
   */
  public void setSeatSectionType(String seatSectionType) {
    this.seatSectionType = seatSectionType;
  }

  /**
   * @return The priceCategoryNum.
   */
  @JsonProperty("priceCategoryNum")
  public Integer getPriceCategoryNum() {
    return priceCategoryNum;
  }

  /**
   * @param priceCategoryNum The priceCategoryNum.
   */
  public void setPriceCategoryNum(Integer priceCategoryNum) {
    this.priceCategoryNum = priceCategoryNum;
  }

  /**
   * @return The priceCategoryId.
   */
  @JsonProperty("priceCategoryId")
  public Long getPriceCategoryId() {
    return priceCategoryId;
  }

  /**
   * @param priceCategoryId The priceCategoryId.
   */
  public void setPriceCategoryId(Long priceCategoryId) {
    this.priceCategoryId = priceCategoryId;
  }

  /**
   * @return The seatSectionAvailability.
   */
  @JsonProperty("seatSectionAvailability")
  public Integer getSeatSectionAvailability() {
    return seatSectionAvailability;
  }

  /**
   * @param seatSectionAvailability The seatSectionAvailability.
   */
  public void setSeatSectionAvailability(Integer seatSectionAvailability) {
    this.seatSectionAvailability = seatSectionAvailability;
  }

  /**
   * @return The price.
   */
  @JsonProperty("price")
  public MonetaryAmount getPrice() {
    return price;
  }

  /**
   * @param price The price.
   */
  public void setPrice(MonetaryAmount price) {
    this.price = price;
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.JSON_STYLE)
        .append("seatSectionId", seatSectionId)
        .append("seatSectionAlias", seatSectionAlias)
        .append("seatSectionType", seatSectionType)
        .append("priceCategoryNum", priceCategoryNum)
        .append("priceCategoryId", priceCategoryId)
        .append("seatSectionAvailability", seatSectionAvailability)
        .append("price", price)
        .toString();
  }
}
