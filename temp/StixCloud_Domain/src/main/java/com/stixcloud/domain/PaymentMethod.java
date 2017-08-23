package com.stixcloud.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by sengkai on 10/19/2016.
 */
@Entity
@Table(name = "PAYMENT_METHOD")
public class PaymentMethod implements Serializable {

  private static final long serialVersionUID = -6474463326958197677L;
  @Id
  private Long paymentmethodid;
  private Long paymentGatewayId;
  private String description;
  private Boolean integrated;
  private Long ordermethod;
  private String paymentmethodname;
  private String paymenttype;
  private String paymentmethodcode;
  private Boolean unpaid;
  private Long organizationId;
  private String validforreplace;
  private String isalternatepayment;
  private String paymentmethodtypeshort;
  private String ismasspull;
  private String accpacacctcode;

  public PaymentMethod() {
  }

  public PaymentMethod(Long paymentmethodid, Long paymentGatewayId, String description,
                       Boolean integrated, Long ordermethod, String paymentmethodname,
                       String paymenttype, String paymentmethodcode, Boolean unpaid,
                       Long organizationId, String validforreplace,
                       String isalternatepayment, String paymentmethodtypeshort,
                       String ismasspull, String accpacacctcode) {
    this.paymentmethodid = paymentmethodid;
    this.paymentGatewayId = paymentGatewayId;
    this.description = description;
    this.integrated = integrated;
    this.ordermethod = ordermethod;
    this.paymentmethodname = paymentmethodname;
    this.paymenttype = paymenttype;
    this.paymentmethodcode = paymentmethodcode;
    this.unpaid = unpaid;
    this.organizationId = organizationId;
    this.validforreplace = validforreplace;
    this.isalternatepayment = isalternatepayment;
    this.paymentmethodtypeshort = paymentmethodtypeshort;
    this.ismasspull = ismasspull;
    this.accpacacctcode = accpacacctcode;
  }

  @Id
  @Column(name = "PAYMENTMETHODID", nullable = false, precision = 0)
  public Long getPaymentmethodid() {
    return paymentmethodid;
  }

  public void setPaymentmethodid(Long paymentmethodid) {
    this.paymentmethodid = paymentmethodid;
  }

  @Column(name = "PAYMENT_GATEWAY_ID", nullable = true, precision = 0)
  public Long getPaymentGatewayId() {
    return paymentGatewayId;
  }

  public void setPaymentGatewayId(Long paymentGatewayId) {
    this.paymentGatewayId = paymentGatewayId;
  }

  @Column(name = "DESCRIPTION", nullable = true, length = 128)
  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  @Column(name = "INTEGRATED", nullable = true, precision = 0)
  public Boolean getIntegrated() {
    return integrated;
  }

  public void setIntegrated(Boolean integrated) {
    this.integrated = integrated;
  }

  @Column(name = "ORDERMETHOD", nullable = true, precision = 0)
  public Long getOrdermethod() {
    return ordermethod;
  }

  public void setOrdermethod(Long ordermethod) {
    this.ordermethod = ordermethod;
  }

  @Column(name = "PAYMENTMETHODNAME", nullable = false, length = 128)
  public String getPaymentmethodname() {
    return paymentmethodname;
  }

  public void setPaymentmethodname(String paymentmethodname) {
    this.paymentmethodname = paymentmethodname;
  }

  @Column(name = "PAYMENTTYPE", nullable = true, length = 255)
  public String getPaymenttype() {
    return paymenttype;
  }

  public void setPaymenttype(String paymenttype) {
    this.paymenttype = paymenttype;
  }

  @Column(name = "PAYMENTMETHODCODE", nullable = true, length = 128)
  public String getPaymentmethodcode() {
    return paymentmethodcode;
  }

  public void setPaymentmethodcode(String paymentmethodcode) {
    this.paymentmethodcode = paymentmethodcode;
  }

  @Column(name = "UNPAID", nullable = true, precision = 0)
  public Boolean getUnpaid() {
    return unpaid;
  }

  public void setUnpaid(Boolean unpaid) {
    this.unpaid = unpaid;
  }

  @Column(name = "ORGANIZATION_ID", nullable = true, precision = 0)
  public Long getOrganizationId() {
    return organizationId;
  }

  public void setOrganizationId(Long organizationId) {
    this.organizationId = organizationId;
  }

  @Column(name = "VALIDFORREPLACE", nullable = true, length = 1)
  public String getValidforreplace() {
    return validforreplace;
  }

  public void setValidforreplace(String validforreplace) {
    this.validforreplace = validforreplace;
  }

  @Column(name = "ISALTERNATEPAYMENT", nullable = false, length = 1)
  public String getIsalternatepayment() {
    return isalternatepayment;
  }

  public void setIsalternatepayment(String isalternatepayment) {
    this.isalternatepayment = isalternatepayment;
  }

  @Column(name = "PAYMENTMETHODTYPESHORT", nullable = true, length = 10)
  public String getPaymentmethodtypeshort() {
    return paymentmethodtypeshort;
  }

  public void setPaymentmethodtypeshort(String paymentmethodtypeshort) {
    this.paymentmethodtypeshort = paymentmethodtypeshort;
  }

  @Column(name = "ISMASSPULL", nullable = true, length = 1)
  public String getIsmasspull() {
    return ismasspull;
  }

  public void setIsmasspull(String ismasspull) {
    this.ismasspull = ismasspull;
  }

  @Column(name = "ACCPACACCTCODE", nullable = true, length = 50)
  public String getAccpacacctcode() {
    return accpacacctcode;
  }

  public void setAccpacacctcode(String accpacacctcode) {
    this.accpacacctcode = accpacacctcode;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PaymentMethod that = (PaymentMethod) o;
    return Objects.equals(paymentmethodid, that.paymentmethodid) &&
        Objects.equals(paymentGatewayId, that.paymentGatewayId) &&
        Objects.equals(description, that.description) &&
        Objects.equals(integrated, that.integrated) &&
        Objects.equals(ordermethod, that.ordermethod) &&
        Objects.equals(paymentmethodname, that.paymentmethodname) &&
        Objects.equals(paymenttype, that.paymenttype) &&
        Objects.equals(paymentmethodcode, that.paymentmethodcode) &&
        Objects.equals(unpaid, that.unpaid) &&
        Objects.equals(organizationId, that.organizationId) &&
        Objects.equals(validforreplace, that.validforreplace) &&
        Objects.equals(isalternatepayment, that.isalternatepayment) &&
        Objects.equals(paymentmethodtypeshort, that.paymentmethodtypeshort) &&
        Objects.equals(ismasspull, that.ismasspull) &&
        Objects.equals(accpacacctcode, that.accpacacctcode);
  }

  @Override
  public int hashCode() {
    return Objects.hash(paymentmethodid, paymentGatewayId, description, integrated, ordermethod,
        paymentmethodname, paymenttype, paymentmethodcode, unpaid, organizationId, validforreplace,
        isalternatepayment, paymentmethodtypeshort, ismasspull, accpacacctcode);
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.JSON_STYLE)
        .append("paymentmethodid", paymentmethodid)
        .append("paymentGatewayId", paymentGatewayId)
        .append("description", description)
        .append("integrated", integrated)
        .append("ordermethod", ordermethod)
        .append("paymentmethodname", paymentmethodname)
        .append("paymenttype", paymenttype)
        .append("paymentmethodcode", paymentmethodcode)
        .append("unpaid", unpaid)
        .append("organizationId", organizationId)
        .append("validforreplace", validforreplace)
        .append("isalternatepayment", isalternatepayment)
        .append("paymentmethodtypeshort", paymentmethodtypeshort)
        .append("ismasspull", ismasspull)
        .append("accpacacctcode", accpacacctcode)
        .toString();
  }
}
