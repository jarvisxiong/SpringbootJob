package com.stixcloud.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import java.math.BigDecimal;
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
@Table(name = "TRANSACTION_PRODUCT_PAYMENT")
public class TransactionProductPayment {
  private Long txnprdtpymtid;
  private Long txnProductId;
  private Long paymentMethodId;
  private BigDecimal paidAmount;
  private BigDecimal productValue;
  private Long origPaymentMethodId;
  private String transactionrefnumber;

  public TransactionProductPayment() {
  }

  public TransactionProductPayment(Long txnprdtpymtid, Long txnProductId,
                                   Long paymentMethodId, BigDecimal paidAmount,
                                   BigDecimal productValue, Long origPaymentMethodId,
                                   String transactionrefnumber) {

    this.txnprdtpymtid = txnprdtpymtid;
    this.txnProductId = txnProductId;
    this.paymentMethodId = paymentMethodId;
    this.paidAmount = paidAmount;
    this.productValue = productValue;
    this.origPaymentMethodId = origPaymentMethodId;
    this.transactionrefnumber = transactionrefnumber;
  }

  @Id
  @Column(name = "TXNPRDTPYMTID", nullable = false, precision = 0)
  @GeneratedValue(strategy = GenerationType.AUTO, generator = "TxnProductPaymentIdSeq")
  @GenericGenerator(name = "TxnProductPaymentIdSeq",
      strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
      parameters = {
          @Parameter(name = "sequence_name", value = "TXN_PRODUCT_PAYMENT_ID_SEQ"),
          @Parameter(name = "optimizer", value = "none")
      })
  public Long getTxnprdtpymtid() {
    return txnprdtpymtid;
  }

  public void setTxnprdtpymtid(Long txnprdtpymtid) {
    this.txnprdtpymtid = txnprdtpymtid;
  }

  @Column(name = "TXN_PRODUCT_ID", nullable = false, precision = 0)
  public Long getTxnProductId() {
    return txnProductId;
  }

  public void setTxnProductId(Long txnProductId) {
    this.txnProductId = txnProductId;
  }

  @Column(name = "PAYMENT_METHOD_ID", nullable = false, precision = 0)
  public Long getPaymentMethodId() {
    return paymentMethodId;
  }

  public void setPaymentMethodId(Long paymentMethodId) {
    this.paymentMethodId = paymentMethodId;
  }

  @Column(name = "PAID_AMOUNT", nullable = true, precision = 5)
  public BigDecimal getPaidAmount() {
    return paidAmount;
  }

  public void setPaidAmount(BigDecimal paidAmount) {
    this.paidAmount = paidAmount;
  }

  @Column(name = "PRODUCT_VALUE", nullable = true, precision = 5)
  public BigDecimal getProductValue() {
    return productValue;
  }

  public void setProductValue(BigDecimal productValue) {
    this.productValue = productValue;
  }

  @Column(name = "ORIG_PAYMENT_METHOD_ID", nullable = true, precision = 0)
  public Long getOrigPaymentMethodId() {
    return origPaymentMethodId;
  }

  public void setOrigPaymentMethodId(Long origPaymentMethodId) {
    this.origPaymentMethodId = origPaymentMethodId;
  }

  @Column(name = "TRANSACTIONREFNUMBER", nullable = false, length = 40)
  public String getTransactionrefnumber() {
    return transactionrefnumber;
  }

  public void setTransactionrefnumber(String transactionrefnumber) {
    this.transactionrefnumber = transactionrefnumber;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TransactionProductPayment that = (TransactionProductPayment) o;
    return Objects.equals(txnprdtpymtid, that.txnprdtpymtid) &&
        Objects.equals(txnProductId, that.txnProductId) &&
        Objects.equals(paymentMethodId, that.paymentMethodId) &&
        Objects.equals(paidAmount, that.paidAmount) &&
        Objects.equals(productValue, that.productValue) &&
        Objects.equals(origPaymentMethodId, that.origPaymentMethodId) &&
        Objects.equals(transactionrefnumber, that.transactionrefnumber);
  }

  @Override
  public int hashCode() {
    return Objects.hash(txnprdtpymtid, txnProductId, paymentMethodId, paidAmount, productValue,
        origPaymentMethodId, transactionrefnumber);
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.JSON_STYLE)
        .append("txnprdtpymtid", txnprdtpymtid)
        .append("txnProductId", txnProductId)
        .append("paymentMethodId", paymentMethodId)
        .append("paidAmount", paidAmount)
        .append("productValue", productValue)
        .append("origPaymentMethodId", origPaymentMethodId)
        .append("transactionrefnumber", transactionrefnumber)
        .toString();
  }
}
