package com.stixcloud.cart.builder;

import com.stixcloud.domain.TransactionProductPayment;

import java.math.BigDecimal;

/**
 * Created by chongye on 16/12/2016.
 */
public final class TransactionProductPaymentBuilder {
  private Long txnprdtpymtid;
  private Long txnProductId;
  private Long paymentMethodId;
  private BigDecimal paidAmount;
  private BigDecimal productValue;
  private Long origPaymentMethodId;
  private String transactionrefnumber;

  private TransactionProductPaymentBuilder() {
  }

  public static TransactionProductPaymentBuilder aTransactionProductPayment() {
    return new TransactionProductPaymentBuilder();
  }

  public TransactionProductPaymentBuilder txnprdtpymtid(Long txnprdtpymtid) {
    this.txnprdtpymtid = txnprdtpymtid;
    return this;
  }

  public TransactionProductPaymentBuilder txnProductId(Long txnProductId) {
    this.txnProductId = txnProductId;
    return this;
  }

  public TransactionProductPaymentBuilder paymentMethodId(Long paymentMethodId) {
    this.paymentMethodId = paymentMethodId;
    return this;
  }

  public TransactionProductPaymentBuilder paidAmount(BigDecimal paidAmount) {
    this.paidAmount = paidAmount;
    return this;
  }

  public TransactionProductPaymentBuilder productValue(BigDecimal productValue) {
    this.productValue = productValue;
    return this;
  }

  public TransactionProductPaymentBuilder origPaymentMethodId(Long origPaymentMethodId) {
    this.origPaymentMethodId = origPaymentMethodId;
    return this;
  }

  public TransactionProductPaymentBuilder transactionrefnumber(String transactionrefnumber) {
    this.transactionrefnumber = transactionrefnumber;
    return this;
  }

  public TransactionProductPayment build() {
    TransactionProductPayment transactionProductPayment = new TransactionProductPayment();
    transactionProductPayment.setTxnprdtpymtid(txnprdtpymtid);
    transactionProductPayment.setTxnProductId(txnProductId);
    transactionProductPayment.setPaymentMethodId(paymentMethodId);
    transactionProductPayment.setPaidAmount(paidAmount);
    transactionProductPayment.setProductValue(productValue);
    transactionProductPayment.setOrigPaymentMethodId(origPaymentMethodId);
    transactionProductPayment.setTransactionrefnumber(transactionrefnumber);
    return transactionProductPayment;
  }
}
