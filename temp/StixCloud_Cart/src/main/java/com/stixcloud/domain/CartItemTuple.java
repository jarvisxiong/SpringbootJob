package com.stixcloud.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Objects;

/**
 * Created by chongye on 4/10/2016.
 */
public class CartItemTuple<ProductId, PriceCatId, SeatSectionId> {
  private ProductId productId;
  private PriceCatId priceCatId;
  private SeatSectionId seatSectionId;

  public CartItemTuple(ProductId productId, PriceCatId priceCatId, SeatSectionId seatSectionId) {
    this.productId = productId;
    this.priceCatId = priceCatId;
    this.seatSectionId = seatSectionId;
  }

  public ProductId getProductId() {
    return productId;
  }

  public PriceCatId getPriceCatId() {
    return priceCatId;
  }

  public SeatSectionId getSeatSectionId() {
    return seatSectionId;
  }

  public void setProductId(ProductId productId) {
    this.productId = productId;
  }

  public void setPriceCatId(PriceCatId priceCatId) {
    this.priceCatId = priceCatId;
  }

  public void setSeatSectionId(SeatSectionId seatSectionId) {
    this.seatSectionId = seatSectionId;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    CartItemTuple that = (CartItemTuple) o;

    return Objects.equals(this.productId, that.productId) &&
        Objects.equals(this.priceCatId, that.priceCatId) &&
        Objects.equals(this.seatSectionId, that.seatSectionId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(productId, priceCatId, seatSectionId);
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.JSON_STYLE)
        .append("productId", productId)
        .append("priceCatId", priceCatId)
        .append("seatSectionId", seatSectionId)
        .toString();
  }
}
