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

@Entity
@Table(name = "RETRIEVE_PRICE_TYPE_VIEW")
public class RetrievePriceTypeView implements Serializable {

  private static final long serialVersionUID = -6408012583384054104L;
  @Id
  private String priceClassName;
  @Id
  private String priceClassCode;
  @Id
  private Integer ordering;
  @Id
  private BigDecimal priceValue;
  @Id
  private Long quantity;
  @Id
  private Long maxQuantity;
  @Id
  private Integer quantityStatus;
  @Id
  private Long productId;
  @Id
  private Long priceCategoryId;
  @Id
  private Long profileInfoId;
  @Id
  private Long userInfoId;
  @Id
  private Long priceClassId;
  @Id
  private Integer passwordRequired;
  @Id
  private boolean complimentary;
  @Id
  private String productCode;
  @Id
  private String productGroupCode;

  public RetrievePriceTypeView() {

  }

  /**
   * @param priceClassName
   * @param priceClassCode
   * @param ordering
   * @param priceValue
   * @param quantity
   * @param maxQuantity
   * @param quantityStatus
   * @param productId
   * @param priceCategoryId
   * @param profileInfoId
   * @param userInfoId
   * @param priceClassId
   * @param passwordRequired
   * @param complimentary
   * @param productCode
   * @param productGroupCode
   */
  public RetrievePriceTypeView(String priceClassName, String priceClassCode, Integer ordering, BigDecimal priceValue,
		  Long quantity, Long maxQuantity, Integer quantityStatus, Long productId, Long priceCategoryId,
		  Long profileInfoId, Long userInfoId, Long priceClassId, Integer passwordRequired, boolean complimentary,
		  String productCode, String productGroupCode) {
	  super();
	  this.priceClassName = priceClassName;
	  this.priceClassCode = priceClassCode;
	  this.ordering = ordering;
	  this.priceValue = priceValue;
	  this.quantity = quantity;
	  this.maxQuantity = maxQuantity;
	  this.quantityStatus = quantityStatus;
	  this.productId = productId;
	  this.priceCategoryId = priceCategoryId;
	  this.profileInfoId = profileInfoId;
	  this.userInfoId = userInfoId;
	  this.priceClassId = priceClassId;
	  this.passwordRequired = passwordRequired;
	  this.complimentary = complimentary;
	  this.productCode = productCode;
	  this.productGroupCode = productGroupCode;
  }

@Column(name = "PRICE_CLASS_NAME", nullable = false, length = 100)
  public String getPriceClassName() {
    return priceClassName;
  }

  public void setPriceClassName(String priceClassName) {
    this.priceClassName = priceClassName;
  }

  @Column(name = "PRICE_CLASS_CODE", nullable = false, length = 10)
  public String getPriceClassCode() {
    return priceClassCode;
  }


  public void setPriceClassCode(String priceClassCode) {
    this.priceClassCode = priceClassCode;
  }
  
  @Column(name = "ORDERING", length = 3)
  public Integer getOrdering() {
	return ordering;
  }

  public void setOrdering(Integer ordering) {
	this.ordering = ordering;
  }

  @Column(name = "PRICE_VALUE", nullable = false, precision = 20, scale = 5)
  public BigDecimal getPriceValue() {
    return priceValue;
  }

  public void setPriceValue(BigDecimal priceValue) {
    this.priceValue = priceValue;
  }

  @Column(name = "QUANTITY", nullable = false, length = 10)
  public Long getQuantity() {
    return quantity;
  }

  public void setQuantity(Long quantity) {
    this.quantity = quantity;
  }

  @Column(name = "MAX_QUANTITY", length = 10)
  public Long getMaxQuantity() {
    return maxQuantity;
  }

  public void setMaxQuantity(Long maxQuantity) {
    this.maxQuantity = maxQuantity;
  }

  @Column(name = "QUANTITY_STATUS", nullable = false, precision = 1)
  public Integer getQuantityStatus() {
    return quantityStatus;
  }

  public void setQuantityStatus(Integer quantityStatus) {
    this.quantityStatus = quantityStatus;
  }

  @Column(name = "PRODUCT_ID", nullable = false, precision = 10)
  public Long getProductId() {
    return productId;
  }

  public void setProductId(Long productId) {
    this.productId = productId;
  }

  @Column(name = "PRICE_CATEGORY_ID", nullable = false, precision = 10)
  public Long getPriceCategoryId() {
    return priceCategoryId;
  }

  public void setPriceCategoryId(Long priceCategoryId) {
    this.priceCategoryId = priceCategoryId;
  }

  @Column(name = "PROFILE_INFO_ID", nullable = false, precision = 10)
  public Long getProfileInfoId() {
    return profileInfoId;
  }

  public void setProfileInfoId(Long profileInfoId) {
    this.profileInfoId = profileInfoId;
  }

  @Column(name = "USER_INFO_ID", nullable = false, precision = 10)
  public Long getUserInfoId() {
    return userInfoId;
  }

  public void setUserInfoId(Long userInfoId) {
    this.userInfoId = userInfoId;
  }

  @Column(name = "PRICE_CLASS_ID", nullable = false, precision = 10)
  public Long getPriceClassId() {
    return priceClassId;
  }

  public void setPriceClassId(Long priceClassId) {
    this.priceClassId = priceClassId;
  }

  @Column(name = "PASSWORD_REQUIRED", nullable = false, length = 1)
  public Integer getPasswordRequired() {
    return passwordRequired;
  }

  public void setPasswordRequired(Integer passwordRequired) {
    this.passwordRequired = passwordRequired;
  }

  @Column(name = "COMPLIMENTARY", nullable = false, length = 1)
  public boolean isComplimentary() {
    return complimentary;
  }

  public void setComplimentary(boolean complimentary) {
    this.complimentary = complimentary;
  }

  @Column(name = "PRODUCT_CODE", nullable = false, length = 50)
  public String getProductCode() {
    return productCode;
  }

  public void setProductCode(String productCode) {
    this.productCode = productCode;
  }

  @Column(name = "PRODUCT_GROUP_CODE", nullable = false, length = 50)
  public String getProductGroupCode() {
    return productGroupCode;
  }

  public void setProductGroupCode(String productGroupCode) {
    this.productGroupCode = productGroupCode;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }

    if (!(o instanceof RetrievePriceTypeView)) {
      return false;
    }

    RetrievePriceTypeView that = (RetrievePriceTypeView) o;

    return new EqualsBuilder()
        .append(isComplimentary(), that.isComplimentary())
        .append(getPriceClassName(), that.getPriceClassName())
        .append(getPriceClassCode(), that.getPriceClassCode())
        .append(getOrdering(), that.getOrdering())
        .append(getPriceValue(), that.getPriceValue())
        .append(getQuantity(), that.getQuantity())
        .append(getMaxQuantity(), that.getMaxQuantity())
        .append(getQuantityStatus(), that.getQuantityStatus())
        .append(getProductId(), that.getProductId())
        .append(getPriceCategoryId(), that.getPriceCategoryId())
        .append(getProfileInfoId(), that.getProfileInfoId())
        .append(getUserInfoId(), that.getUserInfoId())
        .append(getPriceClassId(), that.getPriceClassId())
        .append(getPasswordRequired(), that.getPasswordRequired())
        .append(getProductCode(), that.getProductCode())
        .append(getProductGroupCode(), that.getProductGroupCode())
        .isEquals();
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder(17, 37)
        .append(getPriceClassName())
        .append(getPriceClassCode())
        .append(getOrdering())
        .append(getPriceValue())
        .append(getQuantity())
        .append(getMaxQuantity())
        .append(getQuantityStatus())
        .append(getProductId())
        .append(getPriceCategoryId())
        .append(getProfileInfoId())
        .append(getUserInfoId())
        .append(getPriceClassId())
        .append(getPasswordRequired())
        .append(isComplimentary())
        .append(getProductCode())
        .append(getProductGroupCode())
        .toHashCode();
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.JSON_STYLE)
        .append("priceClassCode", priceClassCode).append("priceClassName", priceClassName)
        .append("ordering", ordering)
        .append("priceValue", priceValue).append("quantity", quantity)
        .append("maxQuantity", maxQuantity).append("quantityStatus", quantityStatus)
        .append("productId", productId).append("priceCategoryId", priceCategoryId)
        .append("profileInfoId", profileInfoId).append("userInfoId", userInfoId)
        .append("priceClassId", priceClassId).append("passwordRequired", passwordRequired)
        .append("complimentary", complimentary).append("productCode", productCode)
        .append("productGroupCode", productGroupCode).toString();
  }

}
