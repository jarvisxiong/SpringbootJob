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
@Table(name = "PROFILE_PAYMENT_METHOD")
public class ProfilePaymentMethod {
  private Long profilePaymentId;
  private Long paymentMethodId;
  private Long profileInfoId;

  @Id
  @Column(name = "PROFILEPAYMENTID", nullable = false, precision = 0)
  public Long getProfilePaymentId() {
    return profilePaymentId;
  }

  public void setProfilePaymentId(Long profilePaymentId) {
    this.profilePaymentId = profilePaymentId;
  }

  @Column(name = "PAYMENT_METHOD_ID", nullable = false, precision = 0)
  public Long getPaymentMethodId() {
    return paymentMethodId;
  }

  public void setPaymentMethodId(Long paymentMethodId) {
    this.paymentMethodId = paymentMethodId;
  }

  @Column(name = "PROFILE_INFO_ID", nullable = false, precision = 0)
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
    ProfilePaymentMethod that = (ProfilePaymentMethod) o;
    return Objects.equals(profilePaymentId, that.profilePaymentId) &&
        Objects.equals(paymentMethodId, that.paymentMethodId) &&
        Objects.equals(profileInfoId, that.profileInfoId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(profilePaymentId, paymentMethodId, profileInfoId);
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.JSON_STYLE)
        .append("profilePaymentId", profilePaymentId)
        .append("paymentMethodId", paymentMethodId)
        .append("profileInfoId", profileInfoId)
        .toString();
  }
}
