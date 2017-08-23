package com.stixcloud.evoucher.api;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;
import java.util.List;

import javax.money.MonetaryAmount;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"exceedMaxQtyAllowed", "validationResultList","totalRedeemValue"})
public class EvoucherValidationResponse implements Serializable {
  private static final long serialVersionUID = -5635863099342235921L;
  @JsonProperty("exceedMaxQtyAllowed")
  private boolean exceedMaxQtyAllowed;
  @JsonProperty("validationResultList")
  private List<EVoucherValidation> validationResultList;
  @JsonProperty("totalRedeemValue")
  private MonetaryAmount totalRedeemValue;
  public EvoucherValidationResponse() {
    super();
  }

  public EvoucherValidationResponse(boolean exceedMaxQtyAllowed,
      List<EVoucherValidation> validationResultList, MonetaryAmount totalRedeemValue) {
    this.exceedMaxQtyAllowed = exceedMaxQtyAllowed;
    this.validationResultList = validationResultList;
    this.totalRedeemValue = totalRedeemValue;
  }

  /**
   * @return the exceedMaxQtyAllowed
   */
  public boolean isExceedMaxQtyAllowed() {
    return exceedMaxQtyAllowed;
  }

  /**
   * @param exceedMaxQtyAllowed the exceedMaxQtyAllowed to set
   */
  public void setExceedMaxQtyAllowed(boolean exceedMaxQtyAllowed) {
    this.exceedMaxQtyAllowed = exceedMaxQtyAllowed;
  }

  /**
   * @return the validationResultList
   */
  public List<EVoucherValidation> getValidationResultList() {
    return validationResultList;
  }

  /**
   * @param validationResultList the validationResultList to set
   */
  public void setValidationResultList(List<EVoucherValidation> validationResultList) {
    this.validationResultList = validationResultList;
  }

  
  /**
   * @return the totalRedeemValue
   */
  public MonetaryAmount getTotalRedeemValue() {
    return totalRedeemValue;
  }

  /**
   * @param totalRedeemValue the totalRedeemValue to set
   */
  public void setTotalRedeemValue(MonetaryAmount totalRedeemValue) {
    this.totalRedeemValue = totalRedeemValue;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }

    if (!(o instanceof EvoucherValidationResponse)) {
      return false;
    }

    EvoucherValidationResponse that = (EvoucherValidationResponse) o;

    return new EqualsBuilder()
        .append(isExceedMaxQtyAllowed(), that.isExceedMaxQtyAllowed())
        .append(getValidationResultList(), that.getValidationResultList())
        .append(getTotalRedeemValue(), that.getTotalRedeemValue())
        .isEquals();
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder(17, 37)
        .append(isExceedMaxQtyAllowed())
        .append(getValidationResultList())
        .append(getTotalRedeemValue())
        .toHashCode();
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.JSON_STYLE)
        .append("exceedMaxQtyAllowed", exceedMaxQtyAllowed)
        .append("validationResultList", validationResultList)
        .append("totalRedeemValue", totalRedeemValue)
        .toString();
  }
}
