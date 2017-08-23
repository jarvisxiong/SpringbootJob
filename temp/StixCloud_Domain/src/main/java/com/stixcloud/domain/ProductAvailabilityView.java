package com.stixcloud.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.hibernate.annotations.Type;

@Table(name = "PRODUCT_AVAILABILITY_VIEW")
@Entity
public class ProductAvailabilityView implements Serializable {

  private static final long serialVersionUID = 3366529803555922484L;
  @Id
  private Long productId;
  @Id
  private Long priceClassId;
  @Id
  private String code;
  @Id
  private String isStandard;
  @Id
  private String sectionType;
  @Id
  private Long profileInfoId;
  
  public ProductAvailabilityView() {}
  
  public ProductAvailabilityView(Long productId, Long priceClassId, String code, String isStandard,
      String sectionType, Long profileInfoId) {
    this.productId = productId;
    this.priceClassId = priceClassId;
    this.code = code;
    this.isStandard = isStandard;
    this.sectionType = sectionType;
    this.profileInfoId = profileInfoId;
  }

  /**
   * @return the productId
   */
  @Column(name = "PRODUCT_ID", nullable = false, length = 10)
  public Long getProductId() {
    return productId;
  }

  /**
   * @param productId the productId to set
   */
  public void setProductId(Long productId) {
    this.productId = productId;
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
   * @return the code
   */
  @Column(name = "CODE", nullable = false, length = 50)
  public String getCode() {
    return code;
  }

  /**
   * @param code the code to set
   */
  public void setCode(String code) {
    this.code = code;
  }

  /**
   * @return the isStandard
   */
  @Column(name = "IS_STANDARD", nullable = false, length = 1)
  public String getIsStandard() {
    return isStandard;
  }

  /**
   * @param isStandard the isStandard to set
   */
  public void setIsStandard(String isStandard) {
    this.isStandard = isStandard;
  }

  /**
   * @return the sectionType
   */
  @Column(name = "SECTION_TYPE", nullable = true, length = 2)
  public String getSectionType() {
    return sectionType;
  }

  /**
   * @param sectionType the sectionType to set
   */
  public void setSectionType(String sectionType) {
    this.sectionType = sectionType;
  }

  /**
   * @return the profileInfoId
   */
  @Column(name = "PROFILE_INFO_ID", nullable = false, length = 10)
  public Long getProfileInfoId() {
    return profileInfoId;
  }

  /**
   * @param profileInfoId the profileInfoId to set
   */
  public void setProfileInfoId(Long profileInfoId) {
    this.profileInfoId = profileInfoId;
  }
  
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }

    if (!(o instanceof ProductAvailabilityView)) {
      return false;
    }

    ProductAvailabilityView product = (ProductAvailabilityView) o;

    return new EqualsBuilder()
        .append(getProductId(), product.getProductId())
        .append(getPriceClassId(), product.getPriceClassId())
        .append(getCode(), product.getCode())
        .append(getIsStandard(), product.getIsStandard())
        .append(getSectionType(), product.getSectionType())
        .append(getProfileInfoId(), product.getProfileInfoId())
        .isEquals();
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder(17, 37)
        .append(getProductId())
        .append(getPriceClassId())
        .append(getCode())
        .append(getIsStandard())
        .append(getSectionType())
        .append(getProfileInfoId())
        .toHashCode();
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.JSON_STYLE)
        .append("productId", productId)
        .append("priceClassId", priceClassId)
        .append("code", code)
        .append("isStandard", isStandard)
        .append("sectionType", sectionType)
        .append("profileInfoId", profileInfoId)
        .toString();
  }
  
}
