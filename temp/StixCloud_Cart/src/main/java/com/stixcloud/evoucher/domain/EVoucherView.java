package com.stixcloud.evoucher.domain;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.annotations.Type;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by dbthan on 13/10/2016.
 */

@Entity
@Table(name = "EVOUCHER_VIEW")
public class EVoucherView implements Serializable {

  private static final long serialVersionUID = 2480488172522813020L;
  @Id
  private String eVoucherId;
  @Id
  private String eVoucherType;
  @Id
  private Long redeemValue;
  @Id
  private String eVoucherStatus;
  @Id
  private Date startDate;
  @Id
  private Date endDate;
  //  @Id
  private Long organizationId;
  //  @Id
  private Long patronId;
  //  @Id
  private Boolean isGiftVoucher;
  @Id
  private String attributeName;
  @Id
  private String attributeValue;
  @Id
  private Integer priority;

  public EVoucherView() {

  }

  public EVoucherView(String eVoucherId, String eVoucherType, Long redeemValue,
                      String eVoucherStatus, Date startDate, Date endDate, Long organizationId,
                      Long patronId,
                      Boolean isGiftVoucher, String attributeName, String attributeValue,
                      Integer priority) {
    this.eVoucherId = eVoucherId;
    this.eVoucherType = eVoucherType;
    this.redeemValue = redeemValue;
    this.eVoucherStatus = eVoucherStatus;
    this.startDate = startDate;
    this.endDate = endDate;
    this.organizationId = organizationId;
    this.patronId = patronId;
    this.isGiftVoucher = isGiftVoucher;
    this.attributeName = attributeName;
    this.attributeValue = attributeValue;
    this.priority = priority;
  }

  @Column(name = "E_VOUCHER_ID", nullable = false, length = 100)
  public String geteVoucherId() {
    return eVoucherId;
  }

  public void seteVoucherId(String eVoucherId) {
    this.eVoucherId = eVoucherId;
  }

  @Column(name = "E_VOUCHER_TYPE", nullable = false, length = 200)
  public String geteVoucherType() {
    return eVoucherType;
  }

  public void seteVoucherType(String eVoucherType) {
    this.eVoucherType = eVoucherType;
  }

  @Column(name = "REDEEM_VALUE", nullable = false, length = 20)
  public Long getRedeemValue() {
    return redeemValue;
  }

  public void setRedeemValue(Long redeemValue) {
    this.redeemValue = redeemValue;
  }

  @Column(name = "E_VOUCHER_STATUS", nullable = false, length = 1)
  public String geteVoucherStatus() {
    return eVoucherStatus;
  }

  public void seteVoucherStatus(String eVoucherStatus) {
    this.eVoucherStatus = eVoucherStatus;
  }

  @Column(name = "START_DATE", nullable = false)
  public Date getStartDate() {
    return startDate;
  }

  public void setStartDate(Date startDate) {
    this.startDate = startDate;
  }

  @Column(name = "END_DATE", nullable = false)
  public Date getEndDate() {
    return endDate;
  }

  public void setEndDate(Date endDate) {
    this.endDate = endDate;
  }

  @Column(name = "ORGANIZATION_ID", nullable = true, length = 10)
  public Long getOrganizationId() {
    return organizationId;
  }

  public void setOrganizationId(Long organizationId) {
    this.organizationId = organizationId;
  }

  @Column(name = "PATRON_ID", nullable = true, length = 10)
  public Long getPatronId() {
    return patronId;
  }

  public void setPatronId(Long patronId) {
    this.patronId = patronId;
  }

  @Column(name = "IS_GIFT_VOUCHER")
  @Type(type = "NumericBooleanType")
  public Boolean isGiftVoucher() {
    return isGiftVoucher;
  }

  public void setIsGiftVoucher(Boolean isGiftVoucher) {
    this.isGiftVoucher = isGiftVoucher;
  }

  @Column(name = "ATTRIBUTE_NAME", nullable = false, length = 50)
  public String getAttributeName() {
    return attributeName;
  }

  public void setAttributeName(String attributeName) {
    this.attributeName = attributeName;
  }

  @Column(name = "ATTRIBUTE_VALUE", nullable = false, length = 100)
  public String getAttributeValue() {
    return attributeValue;
  }

  public void setAttributeValue(String attributeValue) {
    this.attributeValue = attributeValue;
  }

  @Column(name = "PRIORITY", nullable = false, precision = 3)
  public Integer getPriority() {
    return priority;
  }

  public void setPriority(Integer priority) {
    this.priority = priority;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }

    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    EVoucherView that = (EVoucherView) o;
    return new EqualsBuilder().append(eVoucherId, that.geteVoucherId()).isEquals();
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder(17, 37).append(eVoucherId).toHashCode();
  }
}
