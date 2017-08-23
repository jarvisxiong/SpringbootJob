package com.stixcloud.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by sengkai on 11/17/2016.
 */
@Entity
@Table(name = "REVENUE_CENTER")
public class RevenueCenter {
  private Long revenuecenterid;
  private Long paymentGatewayId;
  private String revenuecentername;
  private String revenuecentercode;

  public RevenueCenter() {
  }

  public RevenueCenter(Long revenuecenterid, Long paymentGatewayId,
                       String revenuecentername, String revenuecentercode) {
    this.revenuecenterid = revenuecenterid;
    this.paymentGatewayId = paymentGatewayId;
    this.revenuecentername = revenuecentername;
    this.revenuecentercode = revenuecentercode;
  }

  @Id
  @Column(name = "REVENUECENTERID", nullable = false, precision = 0)
  public Long getRevenuecenterid() {
    return revenuecenterid;
  }

  public void setRevenuecenterid(Long revenuecenterid) {
    this.revenuecenterid = revenuecenterid;
  }

  @Column(name = "PAYMENT_GATEWAY_ID", nullable = false, precision = 0)
  public Long getPaymentGatewayId() {
    return paymentGatewayId;
  }

  public void setPaymentGatewayId(Long paymentGatewayId) {
    this.paymentGatewayId = paymentGatewayId;
  }

  @Column(name = "REVENUECENTERNAME", nullable = false, length = 128)
  public String getRevenuecentername() {
    return revenuecentername;
  }

  public void setRevenuecentername(String revenuecentername) {
    this.revenuecentername = revenuecentername;
  }

  @Column(name = "REVENUECENTERCODE", nullable = false, length = 50)
  public String getRevenuecentercode() {
    return revenuecentercode;
  }

  public void setRevenuecentercode(String revenuecentercode) {
    this.revenuecentercode = revenuecentercode;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    RevenueCenter that = (RevenueCenter) o;
    return Objects.equals(revenuecenterid, that.revenuecenterid) &&
        Objects.equals(paymentGatewayId, that.paymentGatewayId) &&
        Objects.equals(revenuecentername, that.revenuecentername) &&
        Objects.equals(revenuecentercode, that.revenuecentercode);
  }

  @Override
  public int hashCode() {
    return Objects.hash(revenuecenterid, paymentGatewayId, revenuecentername, revenuecentercode);
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.JSON_STYLE)
        .append("revenuecenterid", revenuecenterid)
        .append("paymentGatewayId", paymentGatewayId)
        .append("revenuecentername", revenuecentername)
        .append("revenuecentercode", revenuecentercode)
        .toString();
  }
}
