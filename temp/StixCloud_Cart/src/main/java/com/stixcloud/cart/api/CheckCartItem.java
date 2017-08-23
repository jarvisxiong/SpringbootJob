package com.stixcloud.cart.api;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;
import java.util.Objects;
import javax.validation.Valid;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "product",
    "quantity"
})
public class CheckCartItem implements Serializable {
  private static final long serialVersionUID = -1530381559538580241L;

  @JsonProperty("product")
  @Valid
  private CheckCartProduct product;
  @JsonProperty("quantity")
  private Long quantity;

  /**
   * No args constructor for use in serialization
   */
  public CheckCartItem() {
  }

  private CheckCartItem(Builder builder) {
    product = builder.product;
    quantity = builder.quantity;
  }

  @JsonProperty("product")
  public CheckCartProduct getProduct() {
    return product;
  }

  @JsonProperty("quantity")
  public Long getQuantity() {
    return quantity;
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.JSON_STYLE)
        .append("product", product)
        .append("quantity", quantity)
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

    CheckCartItem that = (CheckCartItem) o;

    return
        Objects.equals(this.product, that.product) &&
            Objects.equals(this.quantity, that.quantity);
  }

  @Override
  public int hashCode() {
    return Objects.hash(product, quantity);
  }


  public static class Builder {

    private CheckCartProduct product;
    private Long quantity;

    public Builder() {
    }

    public Builder(CheckCartItem copy) {
      this.product = copy.product;
      this.quantity = copy.quantity;
    }

    public Builder product(CheckCartProduct product) {
      this.product = product;
      return this;
    }

    public Builder quantity(Long quantity) {
      this.quantity = quantity;
      return this;
    }

    public CheckCartItem build() {
      return new CheckCartItem(this);
    }

  }
}
