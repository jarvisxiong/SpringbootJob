package com.stixcloud.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.Objects;
import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

/**
 * Created by chongye on 20/10/2016.
 */
@Cacheable
@Entity
@Table(name = "TRANSACTION_CODE")
public class TransactionCode implements Serializable {
  private Long transactioncodeid;
  private String txnreason;
  private String txndescription;
  private Long createdBy;
  private OffsetDateTime createddate;
  private Long updatedBy;
  private OffsetDateTime updateddate;
  private Long txnCodeMctId;
  private Long salesStatusMctId;

  public TransactionCode() {
  }

  @Id
  @Column(name = "TRANSACTIONCODEID", nullable = false, precision = 0)
  public Long getTransactioncodeid() {
    return transactioncodeid;
  }

  public void setTransactioncodeid(Long transactioncodeid) {
    this.transactioncodeid = transactioncodeid;
  }

  @Column(name = "TXNREASON", nullable = false, length = 255)
  public String getTxnreason() {
    return txnreason;
  }

  public void setTxnreason(String txnreason) {
    this.txnreason = txnreason;
  }

  @Column(name = "TXNDESCRIPTION", nullable = false, length = 255)
  public String getTxndescription() {
    return txndescription;
  }

  public void setTxndescription(String txndescription) {
    this.txndescription = txndescription;
  }

  @Column(name = "CREATED_BY", nullable = false, precision = 0)
  public Long getCreatedBy() {
    return createdBy;
  }

  public void setCreatedBy(Long createdBy) {
    this.createdBy = createdBy;
  }

  @Column(name = "CREATEDDATE", nullable = false)
  public OffsetDateTime getCreateddate() {
    return createddate;
  }

  public void setCreateddate(OffsetDateTime createddate) {
    this.createddate = createddate;
  }

  @Column(name = "UPDATED_BY", nullable = true, precision = 0)
  public Long getUpdatedBy() {
    return updatedBy;
  }

  public void setUpdatedBy(Long updatedBy) {
    this.updatedBy = updatedBy;
  }

  @Column(name = "UPDATEDDATE", nullable = true)
  public OffsetDateTime getUpdateddate() {
    return updateddate;
  }

  public void setUpdateddate(OffsetDateTime updateddate) {
    this.updateddate = updateddate;
  }

  @Column(name = "TXN_CODE_MCT_ID", nullable = false)
  public Long getTxnCodeMctId() {
    return txnCodeMctId;
  }

  public void setTxnCodeMctId(Long txnCodeMctId) {
    this.txnCodeMctId = txnCodeMctId;
  }

  @JoinColumn(name = "SALES_STATUS_MCT_ID")
  public Long getSalesStatusMctId() {
    return salesStatusMctId;
  }

  public void setSalesStatusMctId(Long salesStatusMctId) {
    this.salesStatusMctId = salesStatusMctId;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    TransactionCode that = (TransactionCode) o;

    return Objects.equals(this.transactioncodeid, that.transactioncodeid) &&
        Objects.equals(this.txnreason, that.txnreason) &&
        Objects.equals(this.txndescription, that.txndescription) &&
        Objects.equals(this.createdBy, that.createdBy) &&
        Objects.equals(this.createddate, that.createddate) &&
        Objects.equals(this.updatedBy, that.updatedBy) &&
        Objects.equals(this.updateddate, that.updateddate) &&
        Objects.equals(this.txnCodeMctId, that.txnCodeMctId) &&
        Objects.equals(this.salesStatusMctId, that.salesStatusMctId);
  }

  @Override
  public int hashCode() {
    return Objects
        .hash(transactioncodeid, txnreason, txndescription, createdBy, createddate, updatedBy,
            updateddate, txnCodeMctId, salesStatusMctId);
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.JSON_STYLE)
        .append("transactioncodeid", transactioncodeid)
        .append("txnreason", txnreason)
        .append("txndescription", txndescription)
        .append("createdBy", createdBy)
        .append("createddate", createddate)
        .append("updatedBy", updatedBy)
        .append("updateddate", updateddate)
        .toString();
  }
}
