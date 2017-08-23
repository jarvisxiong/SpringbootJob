package com.stixcloud.domain;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by dbthan on 06-Sep-16.
 */

@Entity
@Table(name = "RETRIEVE_SECTION_GA_VIEW")
public class RetrieveSectionGaView implements Serializable {

  private static final long serialVersionUID = -1534116593628293235L;

  @Id private Long productId;
  @Id private Long seatSectionId;
  @Id private String seatSectionAlias;
  @Id private String seatSectionType;
  @Id private Integer priceCategoryNum;
  @Id private Long priceCategoryId;
  @Id private Integer seatSectionAvailability;
  @Id private BigDecimal price;
  @Id private Long profileInfoId;
  @Id private Long priceClassId;
  @Id private Long holdCodeId;
  @Id private Integer seatSalesStatus;

  /**
   * Constructor without argument.
   */
  public RetrieveSectionGaView() {

  }

  /**
   * Constructor with arguments.
   * @param productId The productId
   * @param seatSectionId The seatSectionId
   * @param seatSectionAlias The seatSectionAlias
   * @param seatSectionType The seatSectionType
   * @param priceCategoryNum The priceCategoryNum
   * @param priceCategoryId The priceCategoryId
   * @param seatSectionAvailability The seatSectionAvailability
   * @param price The price
   * @param priceClassId The priceClassId
   * @param holdCodeId The holdCodeId
   * @param seatSalesStatus The seatSalesStatus
   */
  public RetrieveSectionGaView(Long productId, Long seatSectionId, String seatSectionAlias,
                               String seatSectionType, Integer priceCategoryNum,
                               Long priceCategoryId,
                               Integer seatSectionAvailability, BigDecimal price,
                               Long profileInfoId, Long priceClassId, Long holdCodeId,
                               Integer seatSalesStatus) {
    this.productId = productId;
    this.seatSectionId = seatSectionId;
    this.seatSectionAlias = seatSectionAlias;
    this.seatSectionType = seatSectionType;
    this.priceCategoryNum = priceCategoryNum;
    this.priceCategoryId = priceCategoryId;
    this.seatSectionAvailability = seatSectionAvailability;
    this.price = price;
    this.profileInfoId = profileInfoId;
    this.priceClassId = priceClassId;
    this.holdCodeId = holdCodeId;
    this.seatSalesStatus = seatSalesStatus;
  }

  /**
   * @return The productId.
   */
  @Column(name = "PRODUCT_ID", nullable = false, length = 10)
  public Long getProductId() {
    return productId;
  }

  /**
   * @param productId The productId.
   */
  public void setProductId(Long productId) {
    this.productId = productId;
  }

  /**
   * @return The seatSectionId.
   */
  @Column(name = "SEAT_SECTION_ID", nullable = false, length = 10)
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
  @Column(name = "SEAT_SECTION_ALIAS", nullable = false, length = 50)
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
  @Column(name = "SEAT_SECTION_TYPE", nullable = false, length = 2)
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
  @Column(name = "PRICE_CATEGORY_NUM", nullable = false, precision = 2)
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
  @Column(name = "PRICE_CATEGORY_ID", nullable = false, length = 10)
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
  @Column(name = "SEAT_SECTION_AVAILABILITY", nullable = false)
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
  @Column(name = "PRICE", nullable = false, precision = 20, scale = 5)
  public BigDecimal getPrice() {
    return price;
  }

  /**
   * @param price The price.
   */
  public void setPrice(BigDecimal price) {
    this.price = price;
  }

  /**
   * @return The profileInfoId.
   */
  @Column(name = "PROFILE_INFO_ID", nullable = false, length = 10)
  public Long getProfileInfoId() {
    return profileInfoId;
  }

  /**
   * @param profileInfoId The profileInfoId.
   */
  public void setProfileInfoId(Long profileInfoId) {
    this.profileInfoId = profileInfoId;
  }
  

  /**
   * @return the priceClassId
   */
  @Column(name = "PRICE_CLASS_ID", nullable = false, length = 10)
  public Long getPriceClassId() {
    return priceClassId;
  }

  /**
   * @param priceClassId the priceClassId to set
   */
  public void setPriceClassId(Long priceClassId) {
    this.priceClassId = priceClassId;
  }

  /**
   * @return the holdCodeId
   */
  @Column(name = "HOLD_CODE_ID", nullable = true, length = 10)
  public Long getHoldCodeId() {
    return holdCodeId;
  }

  /**
   * @param holdCodeId the holdCodeId to set
   */
  public void setHoldCodeId(Long holdCodeId) {
    this.holdCodeId = holdCodeId;
  }

  /**
   * @return the seatSalesStatus
   */
  @Column(name = "SEAT_SALES_STATUS", nullable = true, precision = 1)
  public Integer getSeatSalesStatus() {
    return seatSalesStatus;
  }

  /**
   * @param seatSalesStatus the seatSalesStatus to set
   */
  public void setSeatSalesStatus(Integer seatSalesStatus) {
    this.seatSalesStatus = seatSalesStatus;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }

    if (!(o instanceof RetrieveSectionGaView)) {
      return false;
    }

    RetrieveSectionGaView that = (RetrieveSectionGaView) o;

    return new EqualsBuilder()
        .append(getProductId(), that.getProductId())
        .append(getSeatSectionId(), that.getSeatSectionId())
        .append(getSeatSectionAlias(), that.getSeatSectionAlias())
        .append(getSeatSectionType(), that.getSeatSectionType())
        .append(getPriceCategoryNum(), that.getPriceCategoryNum())
        .append(getPriceCategoryId(), that.getPriceCategoryId())
        .append(getSeatSectionAvailability(), that.getSeatSectionAvailability())
        .append(getPrice(), that.getPrice())
        .append(getProfileInfoId(), that.getProfileInfoId())
        .append(getPriceClassId(), that.getPriceClassId())
        .append(getHoldCodeId(), that.getHoldCodeId())
        .append(getSeatSalesStatus(), that.getSeatSalesStatus())
        .isEquals();
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder(17, 37)
        .append(getProductId())
        .append(getSeatSectionId())
        .append(getSeatSectionAlias())
        .append(getSeatSectionType())
        .append(getPriceCategoryNum())
        .append(getPriceCategoryId())
        .append(getSeatSectionAvailability())
        .append(getPrice())
        .append(getProfileInfoId())
        .append(getPriceClassId())
        .append(getHoldCodeId())
        .append(getSeatSalesStatus())  
        .toHashCode();
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.JSON_STYLE)
        .append("productId", productId)
        .append("seatSectionId", seatSectionId)
        .append("seatSectionAlias", seatSectionAlias)
        .append("seatSectionType", seatSectionType)
        .append("priceCategoryNum", priceCategoryNum)
        .append("priceCategoryId", priceCategoryId)
        .append("seatSectionAvailability", seatSectionAvailability)
        .append("price", price)
        .append("priceClassId", priceClassId)
        .append("holdCodeId", holdCodeId)
        .append("seatSalesStatus", seatSalesStatus)
        .toString();
  }
}
