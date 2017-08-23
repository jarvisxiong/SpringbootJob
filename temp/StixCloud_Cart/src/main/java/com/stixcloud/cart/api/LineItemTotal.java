package com.stixcloud.cart.api;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;
import java.util.Objects;
import javax.money.MonetaryAmount;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "amount"
})
public class LineItemTotal implements Serializable {

  private static final long serialVersionUID = -742263599825588145L;
  @JsonProperty("amount")
  private MonetaryAmount subTotal;

  /**
   * No args constructor for use in serialization
   */
  public LineItemTotal() {
  }

  private LineItemTotal(Builder builder) {
    subTotal = builder.subTotal;
  }

  @JsonProperty("amount")
  public MonetaryAmount getSubTotal() {
    return subTotal;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    LineItemTotal that = (LineItemTotal) o;
    return Objects.equals(subTotal, that.subTotal);
  }

  @Override
  public int hashCode() {
    return Objects.hash(subTotal);
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.JSON_STYLE)
        .append("subTotal", subTotal)
        .toString();
  }


  public static final class Builder {
    private MonetaryAmount subTotal;

    public Builder() {
    }

    public Builder(LineItemTotal copy) {
      this.subTotal = copy.subTotal;
    }

    public Builder subTotal(MonetaryAmount subTotal) {
      this.subTotal = subTotal;
      return this;
    }

    public LineItemTotal build() {
      return new LineItemTotal(this);
    }
  }
}
