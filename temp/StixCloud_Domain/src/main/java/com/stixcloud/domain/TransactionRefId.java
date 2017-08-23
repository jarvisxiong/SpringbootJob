package com.stixcloud.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by sengkai on 12/15/2016.
 */
@Entity
@Table(name = "TRANSACTION_REF_ID")
public class TransactionRefId {
  private Long transactionrefid;
  private String txndate;
  private Long txnseqid;

  public TransactionRefId() {
  }

  public TransactionRefId(Long transactionrefid, String txndate, Long txnseqid) {
    this.transactionrefid = transactionrefid;
    this.txndate = txndate;
    this.txnseqid = txnseqid;
  }

  @Id
  @Column(name = "TRANSACTIONREFID", nullable = false, precision = 0)
  @GeneratedValue(strategy = GenerationType.AUTO, generator = "transactionRefIdSeq")
  @GenericGenerator(name = "transactionRefIdSeq", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
      parameters = {
          @Parameter(name = "sequence_name", value = "TRANSACTION_REF_ID_SEQ"),
          @Parameter(name = "optimizer", value = "none")
      })
  public Long getTransactionrefid() {
    return transactionrefid;
  }

  public void setTransactionrefid(Long transactionrefid) {
    this.transactionrefid = transactionrefid;
  }

  @Column(name = "TXNDATE", nullable = false, length = 10)
  public String getTxndate() {
    return txndate;
  }

  public void setTxndate(String txndate) {
    this.txndate = txndate;
  }

  @Column(name = "TXNSEQID", nullable = false, precision = 0)
  public Long getTxnseqid() {
    return txnseqid;
  }

  public void setTxnseqid(Long txnseqid) {
    this.txnseqid = txnseqid;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TransactionRefId that = (TransactionRefId) o;
    return Objects.equals(transactionrefid, that.transactionrefid) &&
        Objects.equals(txndate, that.txndate) &&
        Objects.equals(txnseqid, that.txnseqid);
  }

  @Override
  public int hashCode() {
    return Objects.hash(transactionrefid, txndate, txnseqid);
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.JSON_STYLE)
        .append("transactionrefid", transactionrefid)
        .append("txndate", txndate)
        .append("txnseqid", txnseqid)
        .toString();
  }
}
