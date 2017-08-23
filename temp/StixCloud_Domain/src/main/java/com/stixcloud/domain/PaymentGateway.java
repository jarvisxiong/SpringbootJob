package com.stixcloud.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by sengkai on 11/16/2016.
 */
@Entity
@Table(name = "PAYMENT_GATEWAY")
public class PaymentGateway implements Serializable {

  private static final long serialVersionUID = -4712356267477912572L;
  private Long paymentgatewayid;
  private String paymentgatewayname;
  private Boolean status;
  private Boolean currentstate;
  private String paymentgatewayimplclass;
  private OffsetDateTime createddate;
  private Integer createdBy;
  private OffsetDateTime updateddate;
  private Integer updatedBy;

  public PaymentGateway() {
  }

  public PaymentGateway(Long paymentgatewayid, String paymentgatewayname, Boolean status,
                        Boolean currentstate, String paymentgatewayimplclass,
                        OffsetDateTime createddate, Integer createdBy,
                        OffsetDateTime updateddate, Integer updatedBy) {
    this.paymentgatewayid = paymentgatewayid;
    this.paymentgatewayname = paymentgatewayname;
    this.status = status;
    this.currentstate = currentstate;
    this.paymentgatewayimplclass = paymentgatewayimplclass;
    this.createddate = createddate;
    this.createdBy = createdBy;
    this.updateddate = updateddate;
    this.updatedBy = updatedBy;
  }

  @Id
  @Column(name = "PAYMENTGATEWAYID", nullable = false, precision = 0)
  public Long getPaymentgatewayid() {
    return paymentgatewayid;
  }

  public void setPaymentgatewayid(Long paymentgatewayid) {
    this.paymentgatewayid = paymentgatewayid;
  }

  @Column(name = "PAYMENTGATEWAYNAME", nullable = false, length = 50)
  public String getPaymentgatewayname() {
    return paymentgatewayname;
  }

  public void setPaymentgatewayname(String paymentgatewayname) {
    this.paymentgatewayname = paymentgatewayname;
  }

  @Column(name = "STATUS", nullable = false, precision = 0)
  public Boolean getStatus() {
    return status;
  }

  public void setStatus(Boolean status) {
    this.status = status;
  }

  @Column(name = "CURRENTSTATE", nullable = false, precision = 0)
  public Boolean getCurrentstate() {
    return currentstate;
  }

  public void setCurrentstate(Boolean currentstate) {
    this.currentstate = currentstate;
  }

  @Column(name = "PAYMENTGATEWAYIMPLCLASS", nullable = true, length = 200)
  public String getPaymentgatewayimplclass() {
    return paymentgatewayimplclass;
  }

  public void setPaymentgatewayimplclass(String paymentgatewayimplclass) {
    this.paymentgatewayimplclass = paymentgatewayimplclass;
  }

  @Column(name = "CREATEDDATE", nullable = false)
  public OffsetDateTime getCreateddate() {
    return createddate;
  }

  public void setCreateddate(OffsetDateTime createddate) {
    this.createddate = createddate;
  }

  @Column(name = "CREATED_BY", nullable = false, precision = 0)
  public Integer getCreatedBy() {
    return createdBy;
  }

  public void setCreatedBy(Integer createdBy) {
    this.createdBy = createdBy;
  }

  @Column(name = "UPDATEDDATE", nullable = true)
  public OffsetDateTime getUpdateddate() {
    return updateddate;
  }

  public void setUpdateddate(OffsetDateTime updateddate) {
    this.updateddate = updateddate;
  }

  @Column(name = "UPDATED_BY", nullable = true, precision = 0)
  public Integer getUpdatedBy() {
    return updatedBy;
  }

  public void setUpdatedBy(Integer updatedBy) {
    this.updatedBy = updatedBy;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PaymentGateway that = (PaymentGateway) o;
    return Objects.equals(paymentgatewayid, that.paymentgatewayid) &&
        Objects.equals(paymentgatewayname, that.paymentgatewayname) &&
        Objects.equals(status, that.status) &&
        Objects.equals(currentstate, that.currentstate) &&
        Objects.equals(paymentgatewayimplclass, that.paymentgatewayimplclass) &&
        Objects.equals(createddate, that.createddate) &&
        Objects.equals(createdBy, that.createdBy) &&
        Objects.equals(updateddate, that.updateddate) &&
        Objects.equals(updatedBy, that.updatedBy);
  }

  @Override
  public int hashCode() {
    return Objects
        .hash(paymentgatewayid, paymentgatewayname, status, currentstate, paymentgatewayimplclass,
            createddate, createdBy, updateddate, updatedBy);
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.JSON_STYLE)
        .append("paymentgatewayid", paymentgatewayid)
        .append("paymentgatewayname", paymentgatewayname)
        .append("status", status)
        .append("currentstate", currentstate)
        .append("paymentgatewayimplclass", paymentgatewayimplclass)
        .append("createddate", createddate)
        .append("createdBy", createdBy)
        .append("updateddate", updateddate)
        .append("updatedBy", updatedBy)
        .toString();
  }
}
