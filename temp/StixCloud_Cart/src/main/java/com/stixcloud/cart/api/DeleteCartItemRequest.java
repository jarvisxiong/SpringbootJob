package com.stixcloud.cart.api;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.hibernate.validator.constraints.NotBlank;

import java.util.Objects;
import javax.annotation.Generated;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
    "cartItemId",
    "priceClassCode"
})
public class DeleteCartItemRequest {

  @NotBlank
  @JsonProperty("cartItemId")
  private String cartItemId;

  @NotBlank
  @JsonProperty("priceClassCode")
  private String priceClassCode;

  public DeleteCartItemRequest() {
  }

  private DeleteCartItemRequest(Builder builder) {
    cartItemId = builder.cartItemId;
    priceClassCode = builder.priceClassCode;
  }

  @JsonProperty("cartItemId")
  public String getCartItemId() {

    return cartItemId;
  }

  @JsonProperty("priceClassCode")
  public String getPriceClassCode() {
    return priceClassCode;
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.JSON_STYLE)
        .append("cartItemId", cartItemId)
        .append("priceClassCode", priceClassCode)
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

    DeleteCartItemRequest that = (DeleteCartItemRequest) o;

    return Objects.equals(this.cartItemId, that.cartItemId) &&
        Objects.equals(this.priceClassCode, that.priceClassCode);
  }

  @Override
  public int hashCode() {
    return Objects.hash(cartItemId, priceClassCode);
  }

  public static final class Builder {
    private String cartItemId;
    private String priceClassCode;

    public Builder() {
    }

    public Builder(DeleteCartItemRequest copy) {
      this.cartItemId = copy.cartItemId;
      this.priceClassCode = copy.priceClassCode;
    }

    public Builder cartItemId(String cartItemId) {
      this.cartItemId = cartItemId;
      return this;
    }

    public Builder priceClassCode(String priceClassCode) {
      this.priceClassCode = priceClassCode;
      return this;
    }

    public DeleteCartItemRequest build() {
      return new DeleteCartItemRequest(this);
    }
  }
}