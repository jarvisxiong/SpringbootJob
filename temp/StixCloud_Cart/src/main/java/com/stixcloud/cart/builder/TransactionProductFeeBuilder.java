package com.stixcloud.cart.builder;

import com.stixcloud.domain.TransactionProductFee;

import java.math.BigDecimal;

/**
 * Created by chongye on 16/12/2016.
 */
public final class TransactionProductFeeBuilder {
  private Long txnproductfeeid;
  private Long txnProductId;
  private Long txnLineItemId;
  private String transactionrefnumber;
  private String levyby;
  private Long txnCodeId;
  private Long feeRuleId;
  private BigDecimal feeamount;
  private Integer charge;
  private Integer iscredit;
  private Long feeId;
  private String feename;

  private TransactionProductFeeBuilder() {
  }

  public static TransactionProductFeeBuilder aTransactionProductFee() {
    return new TransactionProductFeeBuilder();
  }

  public TransactionProductFeeBuilder txnproductfeeid(Long txnproductfeeid) {
    this.txnproductfeeid = txnproductfeeid;
    return this;
  }

  public TransactionProductFeeBuilder txnProductId(Long txnProductId) {
    this.txnProductId = txnProductId;
    return this;
  }

  public TransactionProductFeeBuilder txnLineItemId(Long txnLineItemId) {
    this.txnLineItemId = txnLineItemId;
    return this;
  }

  public TransactionProductFeeBuilder transactionrefnumber(String transactionrefnumber) {
    this.transactionrefnumber = transactionrefnumber;
    return this;
  }

  public TransactionProductFeeBuilder levyby(String levyby) {
    this.levyby = levyby;
    return this;
  }

  public TransactionProductFeeBuilder txnCodeId(Long txnCodeId) {
    this.txnCodeId = txnCodeId;
    return this;
  }

  public TransactionProductFeeBuilder feeRuleId(Long feeRuleId) {
    this.feeRuleId = feeRuleId;
    return this;
  }

  public TransactionProductFeeBuilder feeamount(BigDecimal feeamount) {
    this.feeamount = feeamount;
    return this;
  }

  public TransactionProductFeeBuilder charge(Integer charge) {
    this.charge = charge;
    return this;
  }

  public TransactionProductFeeBuilder iscredit(Integer iscredit) {
    this.iscredit = iscredit;
    return this;
  }

  public TransactionProductFeeBuilder feeId(Long feeId) {
    this.feeId = feeId;
    return this;
  }

  public TransactionProductFeeBuilder feename(String feename) {
    this.feename = feename;
    return this;
  }

  public TransactionProductFee build() {
    TransactionProductFee transactionProductFee = new TransactionProductFee();
    transactionProductFee.setTxnproductfeeid(txnproductfeeid);
    transactionProductFee.setTxnProductId(txnProductId);
    transactionProductFee.setTxnLineItemId(txnLineItemId);
    transactionProductFee.setTransactionrefnumber(transactionrefnumber);
    transactionProductFee.setLevyby(levyby);
    transactionProductFee.setTxnCodeId(txnCodeId);
    transactionProductFee.setFeeRuleId(feeRuleId);
    transactionProductFee.setFeeamount(feeamount);
    transactionProductFee.setCharge(charge);
    transactionProductFee.setIscredit(iscredit);
    transactionProductFee.setFeeId(feeId);
    transactionProductFee.setFeename(feename);
    return transactionProductFee;
  }
}
