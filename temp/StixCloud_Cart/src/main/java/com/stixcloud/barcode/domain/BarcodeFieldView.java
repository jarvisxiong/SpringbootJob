package com.stixcloud.barcode.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

@Entity
@Table(name = "BARCODE_FIELD_VIEW")
public class BarcodeFieldView implements Serializable {

  private static final long serialVersionUID = -7530369134862564019L;

  @Id
  private Long productId;
  @Id
  private Long priceClassId;
  @Id
  private Long priceCategoryId;
  @Id
  private String dataType;
  @Id
  private Integer length;
  @Id
  private String padValue;
  private String staticValue;
  @Id
  private Integer padprefix;
  @Id
  private Integer encryptedType;
  @Id
  private Integer ordering;

  public BarcodeFieldView() {}

  public BarcodeFieldView(Long productId, Long priceClassId, Long priceCategoryId, String dataType,
      Integer length, String padValue, String staticValue, Integer padPrefix, Integer encryptedType,
      Integer ordering) {
    this.productId = productId;
    this.priceClassId = priceClassId;
    this.priceCategoryId = priceCategoryId;
    this.dataType = dataType;
    this.length = length;
    this.padValue = padValue;
    this.staticValue = staticValue;
    this.padprefix = padPrefix;
    this.encryptedType = encryptedType;
    this.ordering = ordering;
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
   * @return The priceClassId.
   */
  @Column(name = "PRICE_CLASS_ID", nullable = false, length = 10)
  public Long getPriceClassId() {
    return priceClassId;
  }

  /**
   * @param priceClassId The priceClassId.
   */
  public void setPriceClassId(Long priceClassId) {
    this.priceClassId = priceClassId;
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
   * @return The dataType.
   */
  @Column(name = "DATA_TYPE", nullable = false, length = 150)
  public String getDataType() {
    return dataType;
  }

  /**
   * @param dataType The dataType.
   */
  public void setDataType(String dataType) {
    this.dataType = dataType;
  }

  /**
   * @return The length.
   */
  @Column(name = "LENGTH", nullable = false, precision = 10)
  public Integer getLength() {
    return length;
  }

  /**
   * @param length The length.
   */
  public void setLength(Integer length) {
    this.length = length;
  }

  /**
   * @return The padValue.
   */
  @Column(name = "PAD_VALUE", length = 50)
  public String getPadValue() {
    return padValue;
  }

  /**
   * @param padValue The padValue.
   */
  public void setPadValue(String padValue) {
    this.padValue = padValue;
  }

  /**
   * @return The staticValue.
   */
  @Column(name = "STATIC_VALUE", length = 150)
  public String getStaticValue() {
    return staticValue;
  }

  /**
   * @param staticString The staticValue.
   */
  public void setStaticValue(String staticString) {
    this.staticValue = staticString;
  }

  /**
   * @return The padPrefix.
   */
  @Column(name = "PADPREFIX", nullable = false, precision = 1)
  public Integer getPadprefix() {
    return padprefix;
  }

  /**
   * @param padPrefix The padPrefix.
   */
  public void setPadprefix(Integer padPrefix) {
    this.padprefix = padPrefix;
  }

  /**
   * @return The encryptedType.
   */
  @Column(name = "ENCRYPTED_TYPE", nullable = false, precision = 1)
  public Integer getEncryptedType() {
    return encryptedType;
  }

  /**
   * @param encryptedType The encryptedType.
   */
  public void setEncryptedType(Integer encryptedType) {
    this.encryptedType = encryptedType;
  }

  /**
   * @return The ordering.
   */
  @Column(name = "ORDERING", nullable = false, precision = 1)
  public Integer getOrdering() {
    return ordering;
  }

  /**
   * @param encryptedType The encryptedType.
   */
  public void setOrdering(Integer ordering) {
    this.ordering = ordering;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }

    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    BarcodeFieldView that = (BarcodeFieldView) o;
    return new EqualsBuilder().append(productId, that.getProductId())
        .append(priceClassId, that.getPriceClassId())
        .append(priceCategoryId, that.getPriceCategoryId()).append(dataType, that.getDataType())
        .append(length, that.getLength()).append(padValue, that.getPadValue())
        .append(staticValue, that.getStaticValue()).append(padprefix, that.getPadprefix())
        .append(encryptedType, that.getEncryptedType()).append(ordering, that.getOrdering())
        .isEquals();
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder(17, 37).append(productId).append(priceClassId)
        .append(priceCategoryId).append(dataType).append(length).append(padValue)
        .append(staticValue).append(padprefix).append(encryptedType).append(ordering).toHashCode();
  }

}
