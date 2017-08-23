package com.stixcloud.paymentgateway.api;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.hibernate.validator.constraints.CreditCardNumber;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import java.io.Serializable;
import java.util.Objects;
import javax.validation.constraints.NotNull;

@JsonInclude(JsonInclude.Include.ALWAYS)
@JsonPropertyOrder({
    "cartGuid",
    "cc_number",
    "cc_exp_month",
    "cc_exp_year",
    "cc_csc",
    "pm_code",
    "mode"
})
public class PaymentGatewayRequest implements Serializable {
  @CreditCardNumber
  @JsonProperty("cc_number")
  private String creditCardNumber;

  @Length(min = 2, max = 2)
  @JsonProperty("cc_exp_month")
  private String expiryMonth;

  @Length(min = 2, max = 2)
  @JsonProperty("cc_exp_year")
  private String expiryYear;

  @Length(min = 3, max = 4)
  @JsonProperty("cc_csc")
  private String securityCode;

  @NotBlank
  @JsonProperty("pm_code")
  private String paymentMethodCode;

  @NotNull
  @JsonProperty("mode")
  private RequestMode requestMode;

  @NotBlank
  @JsonProperty
  private String cartGuid;

  public PaymentGatewayRequest() {
  }

  private PaymentGatewayRequest(Builder builder) {
    creditCardNumber = builder.creditCardNumber;
    expiryMonth = builder.expiryMonth;
    expiryYear = builder.expiryYear;
    securityCode = builder.securityCode;
    paymentMethodCode = builder.paymentMethodCode;
    requestMode = builder.mode;
    cartGuid = builder.cartGuid;
  }

  public String getCreditCardNumber() {
    return creditCardNumber;
  }

  public String getExpiryMonth() {
    return expiryMonth;
  }

  public String getExpiryYear() {
    return expiryYear;
  }

  public String getSecurityCode() {
    return securityCode;
  }

  public String getPaymentMethodCode() {
    return paymentMethodCode;
  }

  public RequestMode getMode() {
    return requestMode;
  }

  public String getCartGuid() {
    return cartGuid;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    PaymentGatewayRequest that = (PaymentGatewayRequest) o;

    return Objects.equals(this.creditCardNumber, that.creditCardNumber) &&
        Objects.equals(this.expiryMonth, that.expiryMonth) &&
        Objects.equals(this.expiryYear, that.expiryYear) &&
        Objects.equals(this.securityCode, that.securityCode) &&
        Objects.equals(this.paymentMethodCode, that.paymentMethodCode) &&
        Objects.equals(this.requestMode, that.requestMode) &&
        Objects.equals(this.cartGuid, that.cartGuid);
  }

  @Override
  public int hashCode() {
    return Objects.hash(creditCardNumber, expiryMonth, expiryYear, securityCode, paymentMethodCode,
        requestMode,
        cartGuid);
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.JSON_STYLE)
        .append("creditCardNumber", creditCardNumber)
        .append("expiryMonth", expiryMonth)
        .append("expiryYear", expiryYear)
        .append("securityCode", securityCode)
        .append("paymentMethodCode", paymentMethodCode)
        .append("requestMode", requestMode)
        .append("cartGuid", cartGuid)
        .toString();
  }

  public static final class Builder {
    private String creditCardNumber;
    private String expiryMonth;
    private String expiryYear;
    private String securityCode;
    private String paymentMethodCode;
    private RequestMode mode;
    private String cartGuid;

    public Builder() {
    }

    public Builder(PaymentGatewayRequest copy) {
      this.creditCardNumber = copy.creditCardNumber;
      this.expiryMonth = copy.expiryMonth;
      this.expiryYear = copy.expiryYear;
      this.securityCode = copy.securityCode;
      this.paymentMethodCode = copy.paymentMethodCode;
      this.mode = copy.requestMode;
      this.cartGuid = copy.cartGuid;
    }

    public Builder creditCardNumber(String creditCardNumber) {
      this.creditCardNumber = creditCardNumber;
      return this;
    }

    public Builder expiryMonth(String expiryMonth) {
      this.expiryMonth = expiryMonth;
      return this;
    }

    public Builder expiryYear(String expiryYear) {
      this.expiryYear = expiryYear;
      return this;
    }

    public Builder securityCode(String securityCode) {
      this.securityCode = securityCode;
      return this;
    }

    public Builder paymentMethodCode(String paymentMethodCode) {
      this.paymentMethodCode = paymentMethodCode;
      return this;
    }

    public Builder mode(RequestMode mode) {
      this.mode = mode;
      return this;
    }

    public Builder cartGuid(String cartGuid) {
      this.cartGuid = cartGuid;
      return this;
    }

    public PaymentGatewayRequest build() {
      return new PaymentGatewayRequest(this);
    }
  }
}
