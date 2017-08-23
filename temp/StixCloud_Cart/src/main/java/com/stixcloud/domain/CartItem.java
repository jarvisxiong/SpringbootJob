package com.stixcloud.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;
import java.util.Objects;
import java.util.Set;
import javax.persistence.Transient;

/**
 * Created by chongye on 28-Sep-16.
 */
//@RedisHash(value = "cartItem", timeToLive = 60)
public class CartItem implements Serializable {

  private static final long serialVersionUID = -3203534734793256493L;
  private String cartItemId;
  private Long productId;
  private Long priceCatId;
  private Long priceClassId;
  private Long seatSectionId;
  private String mode;
  private Set<Long> inventoryIdList;
  private String priceClass;
  private Integer quantity;
  private Long feeRuleId;
  private Long feeId;
  private Boolean sCBWaiverFlag;
  private String icc;
  private String promoPassword;
  private String promoCode;
  public CartItem() {
  }

  private CartItem(Builder builder) {
    cartItemId = builder.cartItemId;
    productId = builder.productId;
    priceCatId = builder.priceCatId;
    priceClassId = builder.priceClassId;
    seatSectionId = builder.seatSectionId;
    mode = builder.mode;
    inventoryIdList = builder.inventoryIdList;
    priceClass = builder.priceClass;
    quantity = builder.quantity;
    feeRuleId = builder.feeRuleId;
    feeId = builder.feeId;
    sCBWaiverFlag = builder.sCBWaiverFlag;
    icc=builder.icc;
    promoPassword = builder.promoPassword;
    promoCode = builder.promoCode;
  }

  public String getCartItemId() {
    return cartItemId;
  }

  public Long getProductId() {
    return productId;
  }

  public Long getPriceCatId() {
    return priceCatId;
  }

  public Long getSeatSectionId() {
    return seatSectionId;
  }

  public String getMode() {
    return mode;
  }

  public Set<Long> getInventoryIdList() {
    return inventoryIdList;
  }

  public String getPriceClass() {
    return priceClass;
  }

  public Integer getQuantity() {
    return quantity;
  }

  public Long getFeeRuleId() {
    return feeRuleId;
  }

  public Long getFeeId() {
    return feeId;
  }

  public Long getPriceClassId() {
    return priceClassId;
  }

  public Boolean isSCBWaiverFlag() {
    return sCBWaiverFlag;
  }
  
  public String getIcc() {
	 return icc;
 }
	  
  public String getPromoPassword() {
    return promoPassword;
  }

  public void setPromoPassword(String promoPassword) {
    this.promoPassword = promoPassword;
  }

  public String getPromoCode() {
    return promoCode;
  }

  public void setPromoCode(String promoCode) {
    this.promoCode = promoCode;
  }

  @Transient
  public CartItemTuple<Long, Long, Long> getTuple() {
    return
        new CartItemTuple<>(this.getProductId(), this.getPriceCatId(), this.getSeatSectionId());
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    CartItem that = (CartItem) o;

    return Objects.equals(this.cartItemId, that.cartItemId) &&
        Objects.equals(this.feeId, that.feeId) &&
        Objects.equals(this.feeRuleId, that.feeRuleId) &&
        Objects.equals(this.inventoryIdList, that.inventoryIdList) &&
        Objects.equals(this.mode, that.mode) &&
        Objects.equals(this.priceCatId, that.priceCatId) &&
        Objects.equals(this.priceClass, that.priceClass) &&
        Objects.equals(this.priceClassId, that.priceClassId) &&
        Objects.equals(this.productId, that.productId) &&
        Objects.equals(this.quantity, that.quantity) &&
        Objects.equals(this.sCBWaiverFlag, that.sCBWaiverFlag) &&
        Objects.equals(this.icc, that.icc) &&
        Objects.equals(this.seatSectionId, that.seatSectionId) &&
        Objects.equals(this.promoPassword, that.promoPassword) &&
        Objects.equals(this.promoCode, that.promoCode);
  }

  @Override
  public int hashCode() {
    return Objects.hash(cartItemId, feeId, feeRuleId, inventoryIdList, mode, priceCatId,
        priceClass, priceClassId, productId, quantity, seatSectionId, sCBWaiverFlag, icc, promoPassword, promoCode);
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.JSON_STYLE)
        .append("cartItemId", cartItemId)
        .append("productId", productId)
        .append("priceCatId", priceCatId)
        .append("priceClassId", priceClassId)
        .append("seatSectionId", seatSectionId)
        .append("mode", mode)
        .append("inventoryIdList", inventoryIdList)
        .append("priceClass", priceClass)
        .append("quantity", quantity)
        .append("feeRuleId", feeRuleId)
        .append("feeId", feeId)
        .append("sCBWaiverFlag", sCBWaiverFlag)
        .append("icc",icc)
        .append("promoPassword", promoPassword)
        .append("promoCode", promoCode)
        .toString();
  }

  public static final class Builder {
    private String cartItemId;
    private Long productId;
    private Long priceCatId;
    private Long priceClassId;
    private Long seatSectionId;
    private String mode;
    private Set<Long> inventoryIdList;
    private String priceClass;
    private Integer quantity;
    private Long feeRuleId;
    private Long feeId;
    private Boolean sCBWaiverFlag;
    private String icc;
    private String promoPassword;
    private String promoCode;
    
    public Builder() {
    }

    public Builder(CartItem copy) {
      this.cartItemId = copy.cartItemId;
      this.productId = copy.productId;
      this.priceCatId = copy.priceCatId;
      this.priceClassId = copy.priceClassId;
      this.seatSectionId = copy.seatSectionId;
      this.mode = copy.mode;
      this.inventoryIdList = copy.inventoryIdList;
      this.priceClass = copy.priceClass;
      this.quantity = copy.quantity;
      this.feeRuleId = copy.feeRuleId;
      this.feeId = copy.feeId;
      this.sCBWaiverFlag = copy.sCBWaiverFlag;
      this.icc=copy.icc;
      this.promoPassword = copy.promoPassword;
      this.promoCode = copy.promoCode;
    }

    public Builder cartItemId(String cartItemId) {
      this.cartItemId = cartItemId;
      return this;
    }

    public Builder productId(Long productId) {
      this.productId = productId;
      return this;
    }

    public Builder priceCatId(Long priceCatId) {
      this.priceCatId = priceCatId;
      return this;
    }

    public Builder priceClassId(Long priceClassId) {
      this.priceClassId = priceClassId;
      return this;
    }

    public Builder seatSectionId(Long seatSectionId) {
      this.seatSectionId = seatSectionId;
      return this;
    }

    public Builder mode(String mode) {
      this.mode = mode;
      return this;
    }

    public Builder inventoryIdList(Set<Long> inventoryIdList) {
      this.inventoryIdList = inventoryIdList;
      return this;
    }

    public Builder priceClass(String priceClass) {
      this.priceClass = priceClass;
      return this;
    }

    public Builder quantity(Integer quantity) {
      this.quantity = quantity;
      return this;
    }

    public Builder feeRuleId(Long feeRuleId) {
      this.feeRuleId = feeRuleId;
      return this;
    }

    public Builder feeId(Long feeId) {
      this.feeId = feeId;
      return this;
    }

    public Builder sCBWaiverFlag(Boolean sCBWaiverFlag) {
      this.sCBWaiverFlag = sCBWaiverFlag;
      return this;
    }
    
    public Builder icc(String icc) {
        this.icc = icc;
        return this;
      }
    
    public Builder promoPassword(String promoPassword) {
      this.promoPassword = promoPassword;
      return this;
    }

    public Builder promoCode(String promoCode) {
      this.promoCode = promoCode;
      return this;
    }
    
    public CartItem build() {
      return new CartItem(this);
    }
  }
}
