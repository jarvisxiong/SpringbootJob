package com.stixcloud.evoucher.domain;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "USER_PAYMENT_METHOD")
public class UserPaymentMethod implements Serializable {

  private static final long serialVersionUID = -3625845457551209135L;
  @Id
  private Long paymentMethodId;
  private String paymentMethodCode;
  @Id
  private Long userInfoId;
  @Id
  private Long profileInfoId;

  public UserPaymentMethod() {

  }

  public UserPaymentMethod(Long paymentMethodId, String paymentMethodCode, Long userInfoId,
                           Long profileInfoId) {
    this.paymentMethodId = paymentMethodId;
    this.paymentMethodCode = paymentMethodCode;
    this.userInfoId = userInfoId;
    this.profileInfoId = profileInfoId;
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
   * @return The userInfoId
   */
  @Column(name = "USER_INFO_ID", length = 10)
  public Long getUserInfoId() {
    return userInfoId;
  }

  /**
   * @param userInfoId The userInfoId
   */
  public void setUserInfoId(Long userInfoId) {
    this.userInfoId = userInfoId;
  }

  @Column(name = "PROFILE_INFO_ID", length = 10)
  public Long getProfileInfoId() {
    return profileInfoId;
  }

  public void setProfileInfoId(Long profileInfoId) {
    this.profileInfoId = profileInfoId;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }

    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    UserPaymentMethod that = (UserPaymentMethod) o;
    return new EqualsBuilder().append(this.getPaymentMethodId(), that.getPaymentMethodId())
        .isEquals();
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder(17, 37).append(this.getPaymentMethodId()).toHashCode();
  }
}
