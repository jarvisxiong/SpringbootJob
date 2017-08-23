package com.stixcloud.cart.api;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import javax.money.CurrencyUnit;
import javax.money.MonetaryAmount;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "lineItemList",
    "lineItemTotal",
    "commonPaymentMethod",
    "commonDeliveryMethod",
    "currencyUnit"
})
public class ShoppingCartJson implements Serializable {

  private static final long serialVersionUID = -387995020966294429L;
  @JsonProperty("lineItemList")
  private List<CartLineItem> lineItemList;
  @JsonProperty("lineItemTotal")
  private MonetaryAmount lineItemTotal;
  @JsonProperty("commonPaymentMethod")
  private PaymentMethodsJson commonPaymentMethod;
  @JsonProperty("commonDeliveryMethod")
  private DeliveryMethodsJson commonDeliveryMethod;
  @JsonProperty("currencyUnit")
  private CurrencyUnit unitPrice;

  public ShoppingCartJson() {
  }

  private ShoppingCartJson(List<CartLineItem> lineItemList,
                           MonetaryAmount lineItemTotal,
                           PaymentMethodsJson commonPaymentMethod,
                           DeliveryMethodsJson commonDeliveryMethod, CurrencyUnit unitPrice) {
    this.lineItemList = lineItemList;
    this.lineItemTotal = lineItemTotal;
    this.commonPaymentMethod = commonPaymentMethod;
    this.commonDeliveryMethod = commonDeliveryMethod;
    this.unitPrice = unitPrice;
  }

  private ShoppingCartJson(Builder builder) {
    lineItemList = builder.lineItemList;
    lineItemTotal = builder.lineItemTotal;
    commonPaymentMethod = builder.commonPaymentMethod;
    commonDeliveryMethod = builder.commonDeliveryMethod;
    unitPrice = builder.unitPrice;
  }

  @JsonProperty("lineItemList")
  public List<CartLineItem> getLineItemList() {
    return lineItemList;
  }

  @JsonProperty("lineItemTotal")
  public MonetaryAmount getLineItemTotal() {
    return lineItemTotal;
  }

  @JsonProperty("commonPaymentMethod")
  public PaymentMethodsJson getCommonPaymentMethod() {
    return commonPaymentMethod;
  }

  @JsonProperty("commonDeliveryMethod")
  public DeliveryMethodsJson getCommonDeliveryMethod() {
    return commonDeliveryMethod;
  }

  @JsonProperty("currencyUnit")
  public CurrencyUnit getUnitPrice() {
    return unitPrice;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ShoppingCartJson that = (ShoppingCartJson) o;
    return Objects.equals(lineItemList, that.lineItemList) &&
        Objects.equals(lineItemTotal, that.lineItemTotal) &&
        Objects.equals(commonPaymentMethod, that.commonPaymentMethod) &&
        Objects.equals(commonDeliveryMethod, that.commonDeliveryMethod) &&
        Objects.equals(unitPrice, that.unitPrice);
  }

  @Override
  public int hashCode() {
    return Objects
        .hash(lineItemList, lineItemTotal, commonPaymentMethod, commonDeliveryMethod, unitPrice);
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.JSON_STYLE)
        .append("lineItemList", lineItemList)
        .append("lineItemTotal", lineItemTotal)
        .append("commonPaymentMethod", commonPaymentMethod)
        .append("commonDeliveryMethod", commonDeliveryMethod)
        .append("unitPrice", unitPrice)
        .toString();
  }

  public static class Builder {
    private List<CartLineItem> lineItemList;
    private MonetaryAmount lineItemTotal;
    private PaymentMethodsJson commonPaymentMethod;
    private DeliveryMethodsJson commonDeliveryMethod;
    private CurrencyUnit unitPrice;

    public Builder() {
    }

    public Builder(ShoppingCartJson copy) {
      this.lineItemList = copy.lineItemList;
      this.lineItemTotal = copy.lineItemTotal;
      this.commonPaymentMethod = copy.commonPaymentMethod;
      this.commonDeliveryMethod = copy.commonDeliveryMethod;
      this.unitPrice = copy.unitPrice;
    }

    public Builder cartLineItems(List<CartLineItem> lineItemList) {
      this.lineItemList = lineItemList;
      return this;
    }

    public Builder commonPaymentMethods(PaymentMethodsJson commonPaymentMethod) {
      this.commonPaymentMethod = commonPaymentMethod;
      return this;
    }

    public Builder commonDeliveryMethods(DeliveryMethodsJson commonDeliveryMethod) {
      this.commonDeliveryMethod = commonDeliveryMethod;
      return this;
    }

    public Builder currencyUnit(CurrencyUnit unitPrice) {
      this.unitPrice = unitPrice;
      return this;
    }

    public ShoppingCartJson build() {
      return new ShoppingCartJson(lineItemList, lineItemTotal, commonPaymentMethod,
          commonDeliveryMethod,
          unitPrice);
    }

    public Builder lineItemList(List<CartLineItem> lineItemList) {
      this.lineItemList = lineItemList;
      return this;
    }

    public Builder lineItemTotal(MonetaryAmount lineItemTotal) {
      this.lineItemTotal = lineItemTotal;
      return this;
    }

    public Builder commonPaymentMethod(PaymentMethodsJson commonPaymentMethod) {
      this.commonPaymentMethod = commonPaymentMethod;
      return this;
    }

    public Builder commonDeliveryMethod(DeliveryMethodsJson commonDeliveryMethod) {
      this.commonDeliveryMethod = commonDeliveryMethod;
      return this;
    }

    public Builder unitPrice(CurrencyUnit unitPrice) {
      this.unitPrice = unitPrice;
      return this;
    }
  }
}
