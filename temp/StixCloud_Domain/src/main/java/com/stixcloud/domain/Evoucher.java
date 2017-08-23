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
 * Created by sengkai on 12/14/2016.
 */
@Entity
@Table(name = "EVOUCHER")
public class Evoucher {
  private String evoucherId;
  private String evoucherType;
  private Long redeemValue;
  private Integer status;
  private OffsetDateTime startDate;
  private OffsetDateTime endDate;
  private OffsetDateTime whenCreated;
  private String whoCreated;
  private String redeemTxnId;

  public Evoucher() {

  }

  public Evoucher(String evoucherId, String evoucherType, Long redeemValue, Integer status,
                  OffsetDateTime startDate, OffsetDateTime endDate,
                  OffsetDateTime whenCreated, String whoCreated, String redeemTxnId) {
    this.evoucherId = evoucherId;
    this.evoucherType = evoucherType;
    this.redeemValue = redeemValue;
    this.status = status;
    this.startDate = startDate;
    this.endDate = endDate;
    this.whenCreated = whenCreated;
    this.whoCreated = whoCreated;
    this.redeemTxnId = redeemTxnId;
  }

  @Id
  @Column(name = "EVOUCHER_ID", nullable = false, length = 100)
  public String getEvoucherId() {
    return evoucherId;
  }

  public void setEvoucherId(String evoucherId) {
    this.evoucherId = evoucherId;
  }

  @Column(name = "EVOUCHER_TYPE", nullable = false, length = 50)
  public String getEvoucherType() {
    return evoucherType;
  }

  public void setEvoucherType(String evoucherType) {
    this.evoucherType = evoucherType;
  }

  @Column(name = "REDEEM_VALUE", nullable = false, precision = 10)
  public Long getRedeemValue() {
    return redeemValue;
  }

  public void setRedeemValue(Long redeemValue) {
    this.redeemValue = redeemValue;
  }

  @Column(name = "STATUS", nullable = false, precision = 0)
  public Integer getStatus() {
    return status;
  }

  public void setStatus(Integer status) {
    this.status = status;
  }

  @Column(name = "START_DATE", nullable = false)
  public OffsetDateTime getStartDate() {
    return startDate;
  }

  public void setStartDate(OffsetDateTime startDate) {
    this.startDate = startDate;
  }

  @Column(name = "END_DATE", nullable = false)
  public OffsetDateTime getEndDate() {
    return endDate;
  }

  public void setEndDate(OffsetDateTime endDate) {
    this.endDate = endDate;
  }

  @Column(name = "WHEN_CREATED", nullable = false)
  public OffsetDateTime getWhenCreated() {
    return whenCreated;
  }

  public void setWhenCreated(OffsetDateTime whenCreated) {
    this.whenCreated = whenCreated;
  }

  @Column(name = "WHO_CREATED", nullable = false, length = 128)
  public String getWhoCreated() {
    return whoCreated;
  }

  public void setWhoCreated(String whoCreated) {
    this.whoCreated = whoCreated;
  }

  @Column(name = "REDEEM_TXN_ID", nullable = true, length = 40)
  public String getRedeemTxnId() {
    return redeemTxnId;
  }

  public void setRedeemTxnId(String redeemTxnId) {
    this.redeemTxnId = redeemTxnId;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Evoucher evoucher = (Evoucher) o;
    return Objects.equals(evoucherId, evoucher.evoucherId) &&
        Objects.equals(evoucherType, evoucher.evoucherType) &&
        Objects.equals(redeemValue, evoucher.redeemValue) &&
        Objects.equals(status, evoucher.status) &&
        Objects.equals(startDate, evoucher.startDate) &&
        Objects.equals(endDate, evoucher.endDate) &&
        Objects.equals(whenCreated, evoucher.whenCreated) &&
        Objects.equals(whoCreated, evoucher.whoCreated) &&
        Objects.equals(redeemTxnId, evoucher.redeemTxnId);
  }

  @Override
  public int hashCode() {
    return Objects
        .hash(evoucherId, evoucherType, redeemValue, status, startDate, endDate, whenCreated,
            whoCreated, redeemTxnId);
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.JSON_STYLE)
        .append("evoucherId", evoucherId)
        .append("evoucherType", evoucherType)
        .append("redeemValue", redeemValue)
        .append("status", status)
        .append("startDate", startDate)
        .append("endDate", endDate)
        .append("whenCreated", whenCreated)
        .append("whoCreated", whoCreated)
        .append("redeemTxnId", redeemTxnId)
        .toString();
  }
}
