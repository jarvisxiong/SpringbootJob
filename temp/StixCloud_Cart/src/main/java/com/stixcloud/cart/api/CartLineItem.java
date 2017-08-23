package com.stixcloud.cart.api;

import java.io.Serializable;
import java.util.Objects;

import javax.money.MonetaryAmount;
import javax.validation.Valid;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "cartItemId",
    "icc",
    "product",
    "priceclass",
    "quantity",
    "unitPrice",
    "bookingFee",
    "subTotal"
})
public class CartLineItem implements Serializable {

  private static final long serialVersionUID = 8049380334331166908L;
  @JsonProperty("cartItemId")
  private String cartItemId;
  @JsonProperty("icc")
  private String icc;
  @JsonProperty("product")
  @Valid
  private Product product;
  @JsonProperty("priceclass")
  @Valid
  private Priceclass priceclass;
  @JsonProperty("quantity")
  private Long quantity;
  @JsonProperty("unitPrice")
  private MonetaryAmount unitPrice;
  @JsonProperty("bookingFee")
  private MonetaryAmount bookingFee;
  @JsonProperty("subTotal")
  private MonetaryAmount subTotal;

  /**
   * No args constructor for use in serialization
   */
  public CartLineItem() {
  }

  private CartLineItem(Builder builder) {
    cartItemId = builder.cartItemId;
    icc = builder.icc;
    product = builder.product;
    priceclass = builder.priceclass;
    quantity = builder.quantity;
    unitPrice = builder.unitPrice;
    bookingFee = builder.bookingFee;
    subTotal = builder.subTotal;
  }

  @JsonProperty("product")
  public Product getProduct() {
    return product;
  }

  @JsonProperty("priceclass")
  public Priceclass getPriceclass() {
    return priceclass;
  }

  @JsonProperty("quantity")
  public Long getQuantity() {
    return quantity;
  }

  @JsonProperty("unitPrice")
  public MonetaryAmount getUnitPrice() {
    return unitPrice;
  }

  @JsonProperty("bookingFee")
  public MonetaryAmount getBookingFee() {
    return bookingFee;
  }

  @JsonProperty("subTotal")
  public MonetaryAmount getSubTotal() {
    return subTotal;
  }

  @JsonProperty("cartItemId")
  public String getCartItemId() {
    return cartItemId;
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.JSON_STYLE)
        .append("cartItemId", cartItemId)
        .append("product", product)
        .append("priceclass", priceclass)
        .append("quantity", quantity)
        .append("unitPrice", unitPrice)
        .append("bookingFee", bookingFee)
        .append("subTotal", subTotal)
        .toString();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    CartLineItem that = (CartLineItem) o;

    return Objects.equals(this.bookingFee, that.bookingFee) &&
        Objects.equals(this.cartItemId, that.cartItemId) &&
        Objects.equals(this.priceclass, that.priceclass) &&
        Objects.equals(this.product, that.product) &&
        Objects.equals(this.quantity, that.quantity) &&
        Objects.equals(this.subTotal, that.subTotal) &&
        Objects.equals(this.unitPrice, that.unitPrice);
  }

  @Override
  public int hashCode() {
    return Objects.hash(bookingFee, cartItemId, priceclass, product, quantity, subTotal, unitPrice);
  }


  public static class Builder {

    private Product product;
    private Priceclass priceclass;
    private Long quantity;
    private MonetaryAmount unitPrice;
    private MonetaryAmount bookingFee;
    private MonetaryAmount subTotal;
    private String cartItemId;
    private String icc;

    public Builder() {
    }

    public Builder(CartLineItem copy) {
      this.cartItemId = copy.cartItemId;
      this.icc = copy.icc;
      this.product = copy.product;
      this.priceclass = copy.priceclass;
      this.quantity = copy.quantity;
      this.unitPrice = copy.unitPrice;
      this.bookingFee = copy.bookingFee;
      this.subTotal = copy.subTotal;
    }
    
    public Builder icc(String icc) {
      this.icc = icc;
      return this;
    }

    public Builder product(Product product) {
      this.product = product;
      return this;
    }

    public Builder priceclass(Priceclass priceclass) {
      this.priceclass = priceclass;
      return this;
    }

    public Builder quantity(Long quantity) {
      this.quantity = quantity;
      return this;
    }

    public Builder ticketQuantity(Long quantity) {
      this.quantity = quantity;
      return this;
    }

    public Builder unitPrice(MonetaryAmount unitPrice) {
      this.unitPrice = unitPrice;
      return this;
    }

    public Builder bookingFee(MonetaryAmount bookingFee) {
      this.bookingFee = bookingFee;
      return this;
    }

    public Builder subTotal(MonetaryAmount subTotal) {
      this.subTotal = subTotal;
      return this;
    }

    public CartLineItem build() {
      return new CartLineItem(this);
    }

    public Builder cartItemId(String cartItemId) {
      this.cartItemId = cartItemId;
      return this;
    }
    
  }
}
