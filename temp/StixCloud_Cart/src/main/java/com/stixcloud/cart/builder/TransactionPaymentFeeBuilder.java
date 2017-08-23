package com.stixcloud.cart.builder;

import com.stixcloud.domain.TransactionPaymentFee;

import java.math.BigDecimal;

/**
 * Created by chongye on 16/12/2016.
 */
public final class TransactionPaymentFeeBuilder {
  private Long txnpaymentfeeid;
  private Long txnPrdtFeeId;
  private String transactionrefnumber;
  private String levyby;
  private Long txnCodeId;
  private Long paymentMethodId;
  private Long feeRuleId;
  private BigDecimal feeamount;
  private Integer chargetype;
  private Long origPaymentMethod;
  private Long feeId;
  private String feename;

  private TransactionPaymentFeeBuilder() {
  }

  public static TransactionPaymentFeeBuilder aTransactionPaymentFee() {
    return new TransactionPaymentFeeBuilder();
  }

  public TransactionPaymentFeeBuilder txnpaymentfeeid(Long txnpaymentfeeid) {
    this.txnpaymentfeeid = txnpaymentfeeid;
    return this;
  }

  public TransactionPaymentFeeBuilder txnPrdtFeeId(Long txnPrdtFeeId) {
    this.txnPrdtFeeId = txnPrdtFeeId;
    return this;
  }

  public TransactionPaymentFeeBuilder transactionrefnumber(String transactionrefnumber) {
    this.transactionrefnumber = transactionrefnumber;
    return this;
  }

  public TransactionPaymentFeeBuilder levyby(String levyby) {
    this.levyby = levyby;
    return this;
  }

  public TransactionPaymentFeeBuilder txnCodeId(Long txnCodeId) {
    this.txnCodeId = txnCodeId;
    return this;
  }

  public TransactionPaymentFeeBuilder paymentMethodId(Long paymentMethodId) {
    this.paymentMethodId = paymentMethodId;
    return this;
  }

  public TransactionPaymentFeeBuilder feeRuleId(Long feeRuleId) {
    this.feeRuleId = feeRuleId;
    return this;
  }

  public TransactionPaymentFeeBuilder feeamount(BigDecimal feeamount) {
    this.feeamount = feeamount;
    return this;
  }

  public TransactionPaymentFeeBuilder chargetype(Integer chargetype) {
    this.chargetype = chargetype;
    return this;
  }

  public TransactionPaymentFeeBuilder origPaymentMethod(Long origPaymentMethod) {
    this.origPaymentMethod = origPaymentMethod;
    return this;
  }

  public TransactionPaymentFeeBuilder feeId(Long feeId) {
    this.feeId = feeId;
    return this;
  }

  public TransactionPaymentFeeBuilder feename(String feename) {
    this.feename = feename;
    return this;
  }

  public TransactionPaymentFee build() {
    TransactionPaymentFee transactionPaymentFee = new TransactionPaymentFee();
    transactionPaymentFee.setTxnpaymentfeeid(txnpaymentfeeid);
    transactionPaymentFee.setTxnPrdtFeeId(txnPrdtFeeId);
    transactionPaymentFee.setTransactionrefnumber(transactionrefnumber);
    transactionPaymentFee.setLevyby(levyby);
    transactionPaymentFee.setTxnCodeId(txnCodeId);
    transactionPaymentFee.setPaymentMethodId(paymentMethodId);
    transactionPaymentFee.setFeeRuleId(feeRuleId);
    transactionPaymentFee.setFeeamount(feeamount);
    transactionPaymentFee.setChargetype(chargetype);
    transactionPaymentFee.setOrigPaymentMethod(origPaymentMethod);
    transactionPaymentFee.setFeeId(feeId);
    transactionPaymentFee.setFeename(feename);
    return transactionPaymentFee;
  }
}
