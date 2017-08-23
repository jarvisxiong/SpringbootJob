package com.stixcloud.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.time.OffsetDateTime;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by sengkai on 10/19/2016.
 */
@Entity
@Table(name = "PAYMENT_METHOD_TEMPLATE_LIVE")
public class PaymentMethodTemplateLive {
  private Long paymentmethodtemplateid;
  private Boolean completestatus;
  private String paymentmethodtemplatename;
  private Long globaltemplateid;
  private OffsetDateTime createddate;
  private OffsetDateTime updateddate;
  private Integer createdBy;
  private Integer updatedBy;

  public PaymentMethodTemplateLive() {
  }

  public PaymentMethodTemplateLive(Long paymentmethodtemplateid, Boolean completestatus,
                                   String paymentmethodtemplatename, Long globaltemplateid,
                                   OffsetDateTime createddate, OffsetDateTime updateddate,
                                   Integer createdBy, Integer updatedBy) {
    this.paymentmethodtemplateid = paymentmethodtemplateid;
    this.completestatus = completestatus;
    this.paymentmethodtemplatename = paymentmethodtemplatename;
    this.globaltemplateid = globaltemplateid;
    this.createddate = createddate;
    this.updateddate = updateddate;
    this.createdBy = createdBy;
    this.updatedBy = updatedBy;
  }

  @Id
  @Column(name = "PAYMENTMETHODTEMPLATEID", nullable = false, precision = 0)
  public Long getPaymentmethodtemplateid() {
    return paymentmethodtemplateid;
  }

  public void setPaymentmethodtemplateid(Long paymentmethodtemplateid) {
    this.paymentmethodtemplateid = paymentmethodtemplateid;
  }

  @Column(name = "COMPLETESTATUS", nullable = true, precision = 0)
  public Boolean getCompletestatus() {
    return completestatus;
  }

  public void setCompletestatus(Boolean completestatus) {
    this.completestatus = completestatus;
  }

  @Column(name = "PAYMENTMETHODTEMPLATENAME", nullable = false, length = 150)
  public String getPaymentmethodtemplatename() {
    return paymentmethodtemplatename;
  }

  public void setPaymentmethodtemplatename(String paymentmethodtemplatename) {
    this.paymentmethodtemplatename = paymentmethodtemplatename;
  }

  @Column(name = "GLOBALTEMPLATEID", nullable = true, precision = 0)
  public Long getGlobaltemplateid() {
    return globaltemplateid;
  }

  public void setGlobaltemplateid(Long globaltemplateid) {
    this.globaltemplateid = globaltemplateid;
  }

  @Column(name = "CREATEDDATE", nullable = false)
  public OffsetDateTime getCreateddate() {
    return createddate;
  }

  public void setCreateddate(OffsetDateTime createddate) {
    this.createddate = createddate;
  }

  @Column(name = "UPDATEDDATE", nullable = true)
  public OffsetDateTime getUpdateddate() {
    return updateddate;
  }

  public void setUpdateddate(OffsetDateTime updateddate) {
    this.updateddate = updateddate;
  }

  @Column(name = "CREATED_BY", nullable = false, precision = 0)
  public Integer getCreatedBy() {
    return createdBy;
  }

  public void setCreatedBy(Integer createdBy) {
    this.createdBy = createdBy;
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
    PaymentMethodTemplateLive that = (PaymentMethodTemplateLive) o;
    return Objects.equals(paymentmethodtemplateid, that.paymentmethodtemplateid) &&
        Objects.equals(completestatus, that.completestatus) &&
        Objects.equals(paymentmethodtemplatename, that.paymentmethodtemplatename) &&
        Objects.equals(globaltemplateid, that.globaltemplateid) &&
        Objects.equals(createddate, that.createddate) &&
        Objects.equals(updateddate, that.updateddate) &&
        Objects.equals(createdBy, that.createdBy) &&
        Objects.equals(updatedBy, that.updatedBy);
  }

  @Override
  public int hashCode() {
    return Objects
        .hash(paymentmethodtemplateid, completestatus, paymentmethodtemplatename, globaltemplateid,
            createddate, updateddate, createdBy, updatedBy);
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.JSON_STYLE)
        .append("paymentmethodtemplateid", paymentmethodtemplateid)
        .append("completestatus", completestatus)
        .append("paymentmethodtemplatename", paymentmethodtemplatename)
        .append("globaltemplateid", globaltemplateid)
        .append("createddate", createddate)
        .append("updateddate", updateddate)
        .append("createdBy", createdBy)
        .append("updatedBy", updatedBy)
        .toString();
  }
}
