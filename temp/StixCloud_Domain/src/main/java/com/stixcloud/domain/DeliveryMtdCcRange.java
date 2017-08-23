package com.stixcloud.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by chongye on 28/11/2016.
 */
@Entity
@Table(name = "DELIVERY_MTD_CC_RANGE")
public class DeliveryMtdCcRange {
  private Long deliverymtdccrangeid;
  private Long deliveryMethodId;
  private Long creditCardRangeId;
  private String validationErrorMessageId;

  @Id
  @Column(name = "DELIVERYMTDCCRANGEID", nullable = false, precision = 0)
  public Long getDeliverymtdccrangeid() {
    return deliverymtdccrangeid;
  }

  public void setDeliverymtdccrangeid(Long deliverymtdccrangeid) {
    this.deliverymtdccrangeid = deliverymtdccrangeid;
  }

  @Column(name = "DELIVERY_METHOD_ID", nullable = true, precision = 0)
  public Long getDeliveryMethodId() {
    return deliveryMethodId;
  }

  public void setDeliveryMethodId(Long deliveryMethodId) {
    this.deliveryMethodId = deliveryMethodId;
  }

  @Column(name = "CREDIT_CARD_RANGE_ID", nullable = true, precision = 0)
  public Long getCreditCardRangeId() {
    return creditCardRangeId;
  }

  public void setCreditCardRangeId(Long creditCardRangeId) {
    this.creditCardRangeId = creditCardRangeId;
  }

  @Column(name = "VALIDATION_ERROR_MESSAGE_ID", nullable = true, length = 500)
  public String getValidationErrorMessageId() {
    return validationErrorMessageId;
  }

  public void setValidationErrorMessageId(String validationErrorMessageId) {
    this.validationErrorMessageId = validationErrorMessageId;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    DeliveryMtdCcRange that = (DeliveryMtdCcRange) o;

    return Objects.equals(this.deliverymtdccrangeid, that.deliverymtdccrangeid) &&
        Objects.equals(this.deliveryMethodId, that.deliveryMethodId) &&
        Objects.equals(this.creditCardRangeId, that.creditCardRangeId) &&
        Objects.equals(this.validationErrorMessageId, that.validationErrorMessageId);
  }

  @Override
  public int hashCode() {
    return Objects
        .hash(deliverymtdccrangeid, deliveryMethodId, creditCardRangeId, validationErrorMessageId);
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.JSON_STYLE)
        .append("deliverymtdccrangeid", deliverymtdccrangeid)
        .append("deliveryMethodId", deliveryMethodId)
        .append("creditCardRangeId", creditCardRangeId)
        .append("validationErrorMessageId", validationErrorMessageId)
        .toString();
  }
}
