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
 * Created by sengkai on 12/14/2016.
 */
@Entity
@Table(name = "TRANSACTION_LINE_ITEM_PRODUCT")
public class TransactionLineItemProduct {
  private Long txnlineitemproductid;
  private String transactionrefnumber;
  private Long txnLineItemId;
  private Long txnProductId;
  private Long txnCodeId;

  public TransactionLineItemProduct() {
  }

  public TransactionLineItemProduct(Long txnlineitemproductid, String transactionrefnumber,
                                    Long txnLineItemId, Long txnProductId, Long txnCodeId) {

    this.txnlineitemproductid = txnlineitemproductid;
    this.transactionrefnumber = transactionrefnumber;
    this.txnLineItemId = txnLineItemId;
    this.txnProductId = txnProductId;
    this.txnCodeId = txnCodeId;
  }

  @Id
  @Column(name = "TXNLINEITEMPRODUCTID", nullable = false, precision = 0)
  @GeneratedValue(strategy = GenerationType.AUTO, generator = "TxnLineItemPrdtIdSeq")
  @GenericGenerator(name = "TxnLineItemPrdtIdSeq",
      strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
      parameters = {
          @Parameter(name = "sequence_name", value = "TXN_LINE_ITEM_PRDT_ID_SEQ"),
          @Parameter(name = "optimizer", value = "none")
      })
  public Long getTxnlineitemproductid() {
    return txnlineitemproductid;
  }

  public void setTxnlineitemproductid(Long txnlineitemproductid) {
    this.txnlineitemproductid = txnlineitemproductid;
  }

  @Column(name = "TRANSACTIONREFNUMBER", nullable = false, length = 40)
  public String getTransactionrefnumber() {
    return transactionrefnumber;
  }

  public void setTransactionrefnumber(String transactionrefnumber) {
    this.transactionrefnumber = transactionrefnumber;
  }

  @Column(name = "TXN_LINE_ITEM_ID", nullable = false, precision = 0)
  public Long getTxnLineItemId() {
    return txnLineItemId;
  }

  public void setTxnLineItemId(Long txnLineItemId) {
    this.txnLineItemId = txnLineItemId;
  }

  @Column(name = "TXN_PRODUCT_ID", nullable = false, precision = 0)
  public Long getTxnProductId() {
    return txnProductId;
  }

  public void setTxnProductId(Long txnProductId) {
    this.txnProductId = txnProductId;
  }

  @Column(name = "TXN_CODE_ID", nullable = false, precision = 0)
  public Long getTxnCodeId() {
    return txnCodeId;
  }

  public void setTxnCodeId(Long txnCodeId) {
    this.txnCodeId = txnCodeId;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TransactionLineItemProduct that = (TransactionLineItemProduct) o;
    return Objects.equals(txnlineitemproductid, that.txnlineitemproductid) &&
        Objects.equals(transactionrefnumber, that.transactionrefnumber) &&
        Objects.equals(txnLineItemId, that.txnLineItemId) &&
        Objects.equals(txnProductId, that.txnProductId) &&
        Objects.equals(txnCodeId, that.txnCodeId);
  }

  @Override
  public int hashCode() {
    return Objects
        .hash(txnlineitemproductid, transactionrefnumber, txnLineItemId, txnProductId, txnCodeId);
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.JSON_STYLE)
        .append("txnlineitemproductid", txnlineitemproductid)
        .append("transactionrefnumber", transactionrefnumber)
        .append("txnLineItemId", txnLineItemId)
        .append("txnProductId", txnProductId)
        .append("txnCodeId", txnCodeId)
        .toString();
  }
}
