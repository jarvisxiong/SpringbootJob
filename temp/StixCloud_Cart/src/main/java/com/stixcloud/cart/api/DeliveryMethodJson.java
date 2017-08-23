package com.stixcloud.cart.api;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;
import java.util.Objects;
import javax.money.MonetaryAmount;

/**
 * Created by chongye on 19/10/2016.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "code",
    "charge",
    "order",
    "mobileNumberRequired",
    "addressRequired",
    "feeWaived"
})
public class DeliveryMethodJson implements Serializable {

  private static final long serialVersionUID = 1999218619741357716L;
  @JsonProperty("code")
  private String code;
  @JsonProperty("charge")
  private MonetaryAmount charge;
  @JsonProperty("order")
  private Integer order;
  @JsonProperty("addressRequired")
  private Boolean addressRequired;
  @JsonProperty("feeWaived")
  private Boolean feeWaived;

  public DeliveryMethodJson() {
  }

  private DeliveryMethodJson(String code, MonetaryAmount charge, Integer order,
                             Boolean addressRequired, Boolean feeWaived) {
    this.code = code;
    this.charge = charge;
    this.order = order;
    this.addressRequired = addressRequired;
    this.feeWaived = feeWaived;
  }

  @JsonProperty("code")
  public String getCode() {
    return code;
  }

  @JsonProperty("charge")
  public MonetaryAmount getCharge() {
    return charge;
  }

  @JsonProperty("order")
  public Integer getOrder() {
    return order;
  }

  @JsonProperty("addressRequired")
  public Boolean getAddressRequired() {
    return addressRequired;
  }

  @JsonProperty("feeWaived")
  public Boolean getFeeWaived() {
    return feeWaived;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    DeliveryMethodJson that = (DeliveryMethodJson) o;

    return Objects.equals(this.code, that.code) &&
        Objects.equals(this.charge, that.charge) &&
        Objects.equals(this.order, that.order) &&
        Objects.equals(this.addressRequired, that.addressRequired) &&
        Objects.equals(this.feeWaived, that.feeWaived);
  }

  @Override
  public int hashCode() {
    return Objects.hash(code, charge, order, addressRequired, feeWaived);
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.JSON_STYLE)
        .append("code", code)
        .append("charge", charge)
        .append("order", order)
        .append("addressRequired", addressRequired)
        .append("feeWaived", feeWaived)
        .toString();
  }

  public static class Builder {

    private String code;
    private MonetaryAmount charge;
    private Integer order;
    private Boolean addressRequired;
    private Boolean feeWaived;

    public Builder code(String code) {
      this.code = code;
      return this;
    }

    public Builder charge(MonetaryAmount charge) {
      this.charge = charge;
      return this;
    }

    public Builder order(Integer order) {
      this.order = order;
      return this;
    }

    public Builder addressRequired(Boolean addressRequired) {
      this.addressRequired = addressRequired;
      return this;
    }

    public Builder feeWaived(Boolean feeWaived) {
      this.feeWaived = feeWaived;
      return this;
    }

    public DeliveryMethodJson build() {
      return new DeliveryMethodJson(code, charge, order, addressRequired, feeWaived);
    }
  }
}
