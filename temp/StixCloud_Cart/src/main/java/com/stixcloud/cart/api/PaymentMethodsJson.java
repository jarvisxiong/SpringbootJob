package com.stixcloud.cart.api;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

/**
 * Created by chongye on 2/11/2016.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PaymentMethodsJson implements Serializable {

  private static final long serialVersionUID = -1636882442075165135L;
  @JsonProperty("paymentMethodList")
  private List<PaymentMethodJson> paymentMethodJsons;

  public PaymentMethodsJson() {
  }

  public PaymentMethodsJson(
      List<PaymentMethodJson> commonPaymentMethod) {
    this.paymentMethodJsons = commonPaymentMethod;
  }

  @JsonProperty("paymentMethodList")
  public List<PaymentMethodJson> getPaymentMethodJsons() {
    return paymentMethodJsons;
  }

  @JsonProperty("paymentMethodList")
  public void setPaymentMethodJsons(List<PaymentMethodJson> paymentMethodJsons) {
    this.paymentMethodJsons = paymentMethodJsons;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    PaymentMethodsJson that = (PaymentMethodsJson) o;

    return Objects.equals(this.paymentMethodJsons, that.paymentMethodJsons);
  }

  @Override
  public int hashCode() {
    return Objects.hash(paymentMethodJsons);
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.JSON_STYLE)
        .append("paymentMethodJsons", paymentMethodJsons)
        .toString();
  }
}
