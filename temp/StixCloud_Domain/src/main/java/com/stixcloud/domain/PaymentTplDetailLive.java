package com.stixcloud.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by sengkai on 10/20/2016.
 */
@Entity
@Table(name = "PAYMENT_TPL_DETAIL_LIVE")
public class PaymentTplDetailLive {
  private Long paymenttpldetailid;
  private Long paymentMethodTemplateId;
  private Long paymentMethodId;

  public PaymentTplDetailLive() {
  }

  public PaymentTplDetailLive(Long paymenttpldetailid, Long paymentMethodTemplateId,
                              Long paymentMethodId) {
    this.paymenttpldetailid = paymenttpldetailid;
    this.paymentMethodTemplateId = paymentMethodTemplateId;
    this.paymentMethodId = paymentMethodId;
  }

  @Id
  @Column(name = "PAYMENTTPLDETAILID", nullable = false, precision = 0)
  public Long getPaymenttpldetailid() {
    return paymenttpldetailid;
  }

  public void setPaymenttpldetailid(Long paymenttpldetailid) {
    this.paymenttpldetailid = paymenttpldetailid;
  }

  @Column(name = "PAYMENT_METHOD_TEMPLATE_ID", nullable = false, precision = 0)
  public Long getPaymentMethodTemplateId() {
    return paymentMethodTemplateId;
  }

  public void setPaymentMethodTemplateId(Long paymentMethodTemplateId) {
    this.paymentMethodTemplateId = paymentMethodTemplateId;
  }

  @Column(name = "PAYMENT_METHOD_ID", nullable = false, precision = 0)
  public Long getPaymentMethodId() {
    return paymentMethodId;
  }

  public void setPaymentMethodId(Long paymentMethodId) {
    this.paymentMethodId = paymentMethodId;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PaymentTplDetailLive that = (PaymentTplDetailLive) o;
    return Objects.equals(paymenttpldetailid, that.paymenttpldetailid) &&
        Objects.equals(paymentMethodTemplateId, that.paymentMethodTemplateId) &&
        Objects.equals(paymentMethodId, that.paymentMethodId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(paymenttpldetailid, paymentMethodTemplateId, paymentMethodId);
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.JSON_STYLE)
        .append("paymenttpldetailid", paymenttpldetailid)
        .append("paymentMethodTemplateId", paymentMethodTemplateId)
        .append("paymentMethodId", paymentMethodId)
        .toString();
  }
}
