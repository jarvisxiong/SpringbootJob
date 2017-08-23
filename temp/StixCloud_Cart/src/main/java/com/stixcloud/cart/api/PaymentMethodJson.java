package com.stixcloud.cart.api;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;
import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "code"
})
public class PaymentMethodJson implements Serializable {

  private static final long serialVersionUID = -2069355911136059556L;
  @JsonProperty("code")
  private String code;

  /**
   * No args constructor for use in serialization
   */
  public PaymentMethodJson() {
  }

  /**
   *
   * @param priceClassName
   */
  public PaymentMethodJson(String priceClassName) {
    this.code = priceClassName;
  }

  /**
   * @return The code
   */
  @JsonProperty("code")
  public String getCode() {
    return code;
  }

  /**
   * @param code The code
   */
  @JsonProperty("code")
  public void setCode(String code) {
    this.code = code;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    PaymentMethodJson that = (PaymentMethodJson) o;

    return Objects.equals(this.code, that.code);
  }

  @Override
  public int hashCode() {
    return Objects.hash(code);
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.JSON_STYLE)
        .append("code", code)
        .toString();
  }
}
