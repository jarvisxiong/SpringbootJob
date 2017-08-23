package com.stixcloud.paymentgateway.api;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;
import java.util.Objects;
import javax.annotation.Generated;
import javax.validation.Valid;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
    "paymentMethodCode",
    "paymentGatewayStatus",
    "paymentGatewayConfig"
})
public class PaymentConfigDetailsJson implements Serializable {

  private static final long serialVersionUID = -3102335263229703031L;
  @JsonProperty("paymentMethodCode")
  private String paymentMethodCode;
  @JsonProperty("paymentGatewayStatus")
  private String paymentGatewayStatus;
  @JsonProperty("paymentGatewayConfig")
  @Valid
  private PaymentGatewayConfig paymentGatewayConfig;

  /**
   * No args constructor for use in serialization
   */
  public PaymentConfigDetailsJson() {
  }

  public PaymentConfigDetailsJson(String paymentMethodCode, String paymentGatewayStatus,
                                  PaymentGatewayConfig paymentGatewayConfig) {
    this.paymentMethodCode = paymentMethodCode;
    this.paymentGatewayStatus = paymentGatewayStatus;
    this.paymentGatewayConfig = paymentGatewayConfig;
  }

  public PaymentConfigDetailsJson(String paymentMethodCode, String paymentGatewayStatus) {
    this.paymentMethodCode = paymentMethodCode;
    this.paymentGatewayStatus = paymentGatewayStatus;
  }

  private PaymentConfigDetailsJson(Builder builder) {
    setPaymentMethodCode(builder.paymentMethodCode);
    setPaymentGatewayStatus(builder.paymentGatewayStatus);
    setPaymentGatewayConfig(builder.paymentGatewayConfig);
  }

  public String getPaymentMethodCode() {
    return paymentMethodCode;
  }

  public void setPaymentMethodCode(String paymentMethodCode) {
    this.paymentMethodCode = paymentMethodCode;
  }

  public String getPaymentGatewayStatus() {
    return paymentGatewayStatus;
  }

  public void setPaymentGatewayStatus(String paymentGatewayStatus) {
    this.paymentGatewayStatus = paymentGatewayStatus;
  }

  public PaymentGatewayConfig getPaymentGatewayConfig() {
    return paymentGatewayConfig;
  }

  public void setPaymentGatewayConfig(
      PaymentGatewayConfig paymentGatewayConfig) {
    this.paymentGatewayConfig = paymentGatewayConfig;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PaymentConfigDetailsJson that = (PaymentConfigDetailsJson) o;
    return Objects.equals(paymentMethodCode, that.paymentMethodCode) &&
        Objects.equals(paymentGatewayStatus, that.paymentGatewayStatus) &&
        Objects.equals(paymentGatewayConfig, that.paymentGatewayConfig);
  }

  @Override
  public int hashCode() {
    return Objects.hash(paymentMethodCode, paymentGatewayStatus, paymentGatewayConfig);
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.JSON_STYLE)
        .append("paymentMethodCode", paymentMethodCode)
        .append("paymentGatewayStatus", paymentGatewayStatus)
        .append("paymentGatewayConfig", paymentGatewayConfig)
        .toString();
  }

  public static final class Builder {
    private String paymentMethodCode;
    private String paymentGatewayStatus;
    private PaymentGatewayConfig paymentGatewayConfig;

    public Builder() {
    }

    public Builder(PaymentConfigDetailsJson copy) {
      this.paymentMethodCode = copy.paymentMethodCode;
      this.paymentGatewayStatus = copy.paymentGatewayStatus;
      this.paymentGatewayConfig = copy.paymentGatewayConfig;
    }

    public Builder paymentMethodCode(String paymentMethodCode) {
      this.paymentMethodCode = paymentMethodCode;
      return this;
    }

    public Builder paymentGatewayStatus(String paymentGatewayStatus) {
      this.paymentGatewayStatus = paymentGatewayStatus;
      return this;
    }

    public Builder paymentGatewayConfig(PaymentGatewayConfig paymentGatewayConfig) {
      this.paymentGatewayConfig = paymentGatewayConfig;
      return this;
    }

    public PaymentConfigDetailsJson build() {
      return new PaymentConfigDetailsJson(paymentMethodCode, paymentGatewayStatus,
          paymentGatewayConfig);
    }
  }
}