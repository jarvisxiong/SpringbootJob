package com.stixcloud.dto;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@Entity
public class PromoCodeDTO {
  @Id
  private Long rownum;
  private Long productId;
  private Long priceClassId;
  private Long holdCodeId;
  private String exclusived;
  private String promotionCode;

  public PromoCodeDTO() {}

  /**
   * @param productId
   * @param priceClassId
   * @param holdCodeId
   * @param exclusived
   */
  public PromoCodeDTO(Long productId, Long priceClassId, Long holdCodeId, String exclusived, String promoCode) {
    super();
    this.productId = productId;
    this.priceClassId = priceClassId;
    this.holdCodeId = holdCodeId;
    this.exclusived = exclusived;
    this.promotionCode = promoCode;
  }

  public Long getProductId() {
    return productId;
  }

  public void setProductId(Long productId) {
    this.productId = productId;
  }

  public Long getPriceClassId() {
    return priceClassId;
  }

  public void setPriceClassId(Long priceClassId) {
    this.priceClassId = priceClassId;
  }

  public Long getHoldCodeId() {
    return holdCodeId;
  }

  public void setHoldCodeId(Long holdCodeId) {
    this.holdCodeId = holdCodeId;
  }

  public String getExclusived() {
    return exclusived;
  }

  public void setExclusived(String exclusived) {
    this.exclusived = exclusived;
  }
  
  public String getPromoCode() {
    return promotionCode;
  }

  public void setPromoCode(String promoCode) {
    this.promotionCode = promoCode;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PromoCodeDTO that = (PromoCodeDTO) o;
    return Objects.equals(productId, that.productId)
        && Objects.equals(priceClassId, that.priceClassId)
        && Objects.equals(holdCodeId, that.holdCodeId)
        && Objects.equals(exclusived, that.exclusived);
  }

  @Override
  public int hashCode() {
    return Objects.hash(productId, priceClassId, holdCodeId, exclusived);
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.JSON_STYLE).append("productId", productId)
        .append("priceClassId", priceClassId).append("holdCodeId", holdCodeId)
        .append("exclusived", exclusived).toString();
  }
}
