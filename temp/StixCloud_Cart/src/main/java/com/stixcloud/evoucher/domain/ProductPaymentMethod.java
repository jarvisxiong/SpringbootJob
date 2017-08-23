package com.stixcloud.evoucher.domain;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "PRODUCT_PAYMENT_METHOD")
public class ProductPaymentMethod implements Serializable {

  private static final long serialVersionUID = -3625845457551209135L;
  @Id
  private Long productId;
  @Id
  private Long paymentMethodId;
  private String paymentMethodCode;

  public ProductPaymentMethod() {

  }

  public ProductPaymentMethod(Long productId, Long paymentMethodId, String paymentMethodCode) {
    this.productId = productId;
    this.paymentMethodId = paymentMethodId;
    this.paymentMethodCode = paymentMethodCode;
  }

  /**
   * @return The paymentMethodId
   */
  @Column(name = "PAYMENT_METHOD_ID", nullable = false, length = 10)
  public Long getPaymentMethodId() {
    return paymentMethodId;
  }

  /**
   * @param paymentMethodId The paymentMethodId
   */
  public void setPaymentMethodId(Long paymentMethodId) {
    this.paymentMethodId = paymentMethodId;
  }

  /**
   * @return The paymentMethodCode
   */
  @Column(name = "PAYMENT_METHOD_CODE", nullable = false, length = 128)
  public String getPaymentMethodCode() {
    return paymentMethodCode;
  }

  /**
   * @param paymentMethodCode The paymentMethodCode
   */
  public void setPaymentMethodCode(String paymentMethodCode) {
    this.paymentMethodCode = paymentMethodCode;
  }

  /**
   * @return The productId
   */
  @Column(name = "PRODUCT_ID", length = 10)
  public Long getProductId() {
    return productId;
  }

  /**
   * @param productId The productId
   */
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

    ProductPaymentMethod that = (ProductPaymentMethod) o;
    return new EqualsBuilder().append(this.getPaymentMethodId(), that.getPaymentMethodId())
        .isEquals();
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder(17, 37).append(this.getPaymentMethodId()).toHashCode();
  }
}
