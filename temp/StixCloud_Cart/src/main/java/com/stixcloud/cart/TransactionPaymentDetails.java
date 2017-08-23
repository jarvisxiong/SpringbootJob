package com.stixcloud.cart;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Objects;
import javax.money.MonetaryAmount;

/**
 * Created by sengkai on 3/15/2017.
 */
public class TransactionPaymentDetails {

  private MonetaryAmount subAmount;
  private String paymentMethodCode;

  public TransactionPaymentDetails() {
  }

  public TransactionPaymentDetails(MonetaryAmount subAmount, String paymentMethodCode) {
    this.subAmount = subAmount;
    this.paymentMethodCode = paymentMethodCode;
  }

  public MonetaryAmount getSubAmount() {
    return subAmount;
  }

  public void setSubAmount(MonetaryAmount subAmount) {
    this.subAmount = subAmount;
  }

  public String getPaymentMethodCode() {
    return paymentMethodCode;
  }

  public void setPaymentMethodCode(String paymentMethodCode) {
    this.paymentMethodCode = paymentMethodCode;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TransactionPaymentDetails that = (TransactionPaymentDetails) o;
    return Objects.equals(subAmount, that.subAmount) &&
        Objects.equals(paymentMethodCode, that.paymentMethodCode);
  }

  @Override
  public int hashCode() {
    return Objects.hash(subAmount, paymentMethodCode);
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.JSON_STYLE)
        .append("subAmount", subAmount)
        .append("paymentMethodCode", paymentMethodCode)
        .toString();
  }
}
