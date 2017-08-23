package com.stixcloud.evoucher.api;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.io.Serializable;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"evoucherIdList", "creditCardNo", "paymentMethodCode"})
public class EVoucherValidationRequest implements Serializable {

  private static final long serialVersionUID = -2078282306440229997L;

  @JsonProperty("evoucherIdList")
  private List<String> evoucherIdList;
  @JsonProperty("creditCardNo")
  private String creditCardNo;
  @JsonProperty("paymentMethodCode")
  private String paymentMethodCode;

  public EVoucherValidationRequest() {
    super();
  }

  public EVoucherValidationRequest(List<String> evoucherIdList, String creditCardNo,
                                   String paymentMethodCode) {
    this.evoucherIdList = evoucherIdList;
    this.creditCardNo = creditCardNo;
    this.paymentMethodCode = paymentMethodCode;
  }

  /**
   * @return The evoucherIdList
   */
  @JsonProperty("evoucherIdList")
  public List<String> getEvoucherIdList() {
    return evoucherIdList;
  }

  /**
   * @param evoucherIdList The evoucherIdList
   */
  public void setEvoucherIdList(List<String> evoucherIdList) {
    this.evoucherIdList = evoucherIdList;
  }

  /**
   * @return The creditCardNo
   */
  @JsonProperty("creditCardNo")
  public String getCreditCardNo() {
    return creditCardNo;
  }

  /**
   * @param creditCardNo The creditCardNo
   */
  public void setCreditCardNo(String creditCardNo) {
    this.creditCardNo = creditCardNo;
  }

  /**
   * @return The paymentMethodCode
   */
  @JsonProperty("paymentMethodCode")
  public String getPaymentMethodCode() {
    return paymentMethodCode;
  }

  /**
   * @param paymentMethodCode The paymentMethodCode
   */
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
    EVoucherValidationRequest that = (EVoucherValidationRequest) o;
    return new EqualsBuilder().append(evoucherIdList, that.getEvoucherIdList())
        .append(paymentMethodCode, that.getPaymentMethodCode())
        .append(creditCardNo, that.getCreditCardNo()).isEquals();
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder(17, 37).append(evoucherIdList).append(paymentMethodCode)
        .append(creditCardNo).toHashCode();
  }

}
