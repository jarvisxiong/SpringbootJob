package com.stixcloud.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Objects;

/**
 * Created by chongye on 1/11/2016.
 */
public class CartLineItemsViewTuple<ProductId, PriceClassCode, PriceCatId, SeatSectionId> {
  private ProductId productid;
  private PriceClassCode priceclasscode;
  private PriceCatId pricecatid;
  private SeatSectionId seatsectionid;

  public CartLineItemsViewTuple(ProductId productid, PriceClassCode priceclasscode,
                                PriceCatId pricecatid, SeatSectionId seatsectionid) {
    this.productid = productid;
    this.priceclasscode = priceclasscode;
    this.pricecatid = pricecatid;
    this.seatsectionid = seatsectionid;
  }

  public ProductId getProductid() {
    return productid;
  }

  public void setProductid(ProductId productid) {
    this.productid = productid;
  }

  public PriceClassCode getPriceclasscode() {
    return priceclasscode;
  }

  public void setPriceclasscode(PriceClassCode priceclasscode) {
    this.priceclasscode = priceclasscode;
  }

  public PriceCatId getPricecatid() {
    return pricecatid;
  }

  public void setPricecatid(PriceCatId pricecatid) {
    this.pricecatid = pricecatid;
  }

  public SeatSectionId getSeatsectionid() {
    return seatsectionid;
  }

  public void setSeatsectionid(SeatSectionId seatsectionid) {
    this.seatsectionid = seatsectionid;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    CartLineItemsViewTuple that = (CartLineItemsViewTuple) o;

    return Objects.equals(this.productid, that.productid) &&
        Objects.equals(this.priceclasscode, that.priceclasscode) &&
        Objects.equals(this.pricecatid, that.pricecatid) &&
        Objects.equals(this.seatsectionid, that.seatsectionid);
  }

  @Override
  public int hashCode() {
    return Objects.hash(productid, priceclasscode, pricecatid, seatsectionid);
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.JSON_STYLE)
        .append("productid", productid)
        .append("priceclasscode", priceclasscode)
        .append("pricecatid", pricecatid)
        .append("seatsectionid", seatsectionid)
        .toString();
  }
}
