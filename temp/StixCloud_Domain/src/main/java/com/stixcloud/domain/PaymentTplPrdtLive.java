package com.stixcloud.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by sengkai on 10/19/2016.
 */
@Entity
@Table(name = "PAYMENT_TPL_PRDT_LIVE")
public class PaymentTplPrdtLive {
  private Long paymenttplprdtid;
  private Long paymentMethodTemplateId;
  private Long productId;

  public PaymentTplPrdtLive() {
  }

  public PaymentTplPrdtLive(Long paymenttplprdtid, Long paymentMethodTemplateId,
                            Long productId) {
    this.paymenttplprdtid = paymenttplprdtid;
    this.paymentMethodTemplateId = paymentMethodTemplateId;
    this.productId = productId;
  }

  @Id
  @Column(name = "PAYMENTTPLPRDTID", nullable = false, precision = 0)
  public Long getPaymenttplprdtid() {
    return paymenttplprdtid;
  }

  public void setPaymenttplprdtid(Long paymenttplprdtid) {
    this.paymenttplprdtid = paymenttplprdtid;
  }

  @Column(name = "PAYMENT_METHOD_TEMPLATE_ID", nullable = false, precision = 0)
  public Long getPaymentMethodTemplateId() {
    return paymentMethodTemplateId;
  }

  public void setPaymentMethodTemplateId(Long paymentMethodTemplateId) {
    this.paymentMethodTemplateId = paymentMethodTemplateId;
  }

  @Column(name = "PRODUCT_ID", nullable = false, precision = 0)
  public Long getProductId() {
    return productId;
  }

  public void setProductId(Long productId) {
    this.productId = productId;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PaymentTplPrdtLive that = (PaymentTplPrdtLive) o;
    return Objects.equals(paymenttplprdtid, that.paymenttplprdtid) &&
        Objects.equals(paymentMethodTemplateId, that.paymentMethodTemplateId) &&
        Objects.equals(productId, that.productId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(paymenttplprdtid, paymentMethodTemplateId, productId);
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.JSON_STYLE)
        .append("paymenttplprdtid", paymenttplprdtid)
        .append("paymentMethodTemplateId", paymentMethodTemplateId)
        .append("productId", productId)
        .toString();
  }
}
