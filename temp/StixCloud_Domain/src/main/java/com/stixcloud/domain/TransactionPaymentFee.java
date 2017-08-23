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
@Table(name = "TRANSACTION_PAYMENT_FEE")
public class TransactionPaymentFee {
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

  public TransactionPaymentFee() {
  }

  public TransactionPaymentFee(Long txnpaymentfeeid, Long txnPrdtFeeId,
                               String transactionrefnumber, String levyby, Long txnCodeId,
                               Long paymentMethodId, Long feeRuleId, BigDecimal feeamount,
                               Integer chargetype, Long origPaymentMethod, Long feeId,
                               String feename) {

    this.txnpaymentfeeid = txnpaymentfeeid;
    this.txnPrdtFeeId = txnPrdtFeeId;
    this.transactionrefnumber = transactionrefnumber;
    this.levyby = levyby;
    this.txnCodeId = txnCodeId;
    this.paymentMethodId = paymentMethodId;
    this.feeRuleId = feeRuleId;
    this.feeamount = feeamount;
    this.chargetype = chargetype;
    this.origPaymentMethod = origPaymentMethod;
    this.feeId = feeId;
    this.feename = feename;
  }

  @Id
  @Column(name = "TXNPAYMENTFEEID", nullable = false, precision = 0)
  @GeneratedValue(strategy = GenerationType.AUTO, generator = "TxnPaymentFeeIdSeq")
  @GenericGenerator(name = "TxnPaymentFeeIdSeq",
      strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
      parameters = {
          @Parameter(name = "sequence_name", value = "TXN_PAYMENT_FEE_ID_SEQ"),
          @Parameter(name = "optimizer", value = "none")
      })
  public Long getTxnpaymentfeeid() {
    return txnpaymentfeeid;
  }

  public void setTxnpaymentfeeid(Long txnpaymentfeeid) {
    this.txnpaymentfeeid = txnpaymentfeeid;
  }

  @Column(name = "TXN_PRDT_FEE_ID", nullable = false, precision = 0)
  public Long getTxnPrdtFeeId() {
    return txnPrdtFeeId;
  }

  public void setTxnPrdtFeeId(Long txnPrdtFeeId) {
    this.txnPrdtFeeId = txnPrdtFeeId;
  }

  @Column(name = "TRANSACTIONREFNUMBER", nullable = false, length = 40)
  public String getTransactionrefnumber() {
    return transactionrefnumber;
  }

  public void setTransactionrefnumber(String transactionrefnumber) {
    this.transactionrefnumber = transactionrefnumber;
  }

  @Column(name = "LEVYBY", nullable = true, length = 30)
  public String getLevyby() {
    return levyby;
  }

  public void setLevyby(String levyby) {
    this.levyby = levyby;
  }

  @Column(name = "TXN_CODE_ID", nullable = false, precision = 0)
  public Long getTxnCodeId() {
    return txnCodeId;
  }

  public void setTxnCodeId(Long txnCodeId) {
    this.txnCodeId = txnCodeId;
  }

  @Column(name = "PAYMENT_METHOD_ID", nullable = false, precision = 0)
  public Long getPaymentMethodId() {
    return paymentMethodId;
  }

  public void setPaymentMethodId(Long paymentMethodId) {
    this.paymentMethodId = paymentMethodId;
  }

  @Column(name = "FEE_RULE_ID", nullable = false, precision = 0)
  public Long getFeeRuleId() {
    return feeRuleId;
  }

  public void setFeeRuleId(Long feeRuleId) {
    this.feeRuleId = feeRuleId;
  }

  @Column(name = "FEEAMOUNT", nullable = true, precision = 5)
  public BigDecimal getFeeamount() {
    return feeamount;
  }

  public void setFeeamount(BigDecimal feeamount) {
    this.feeamount = feeamount;
  }

  @Column(name = "CHARGETYPE", nullable = true, precision = 0)
  public Integer getChargetype() {
    return chargetype;
  }

  public void setChargetype(Integer chargetype) {
    this.chargetype = chargetype;
  }

  @Column(name = "ORIG_PAYMENT_METHOD", nullable = true, precision = 0)
  public Long getOrigPaymentMethod() {
    return origPaymentMethod;
  }

  public void setOrigPaymentMethod(Long origPaymentMethod) {
    this.origPaymentMethod = origPaymentMethod;
  }

  @Column(name = "FEE_ID", nullable = false, precision = 0)
  public Long getFeeId() {
    return feeId;
  }

  public void setFeeId(Long feeId) {
    this.feeId = feeId;
  }

  @Column(name = "FEENAME", nullable = true, length = 50)
  public String getFeename() {
    return feename;
  }

  public void setFeename(String feename) {
    this.feename = feename;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TransactionPaymentFee that = (TransactionPaymentFee) o;
    return Objects.equals(txnpaymentfeeid, that.txnpaymentfeeid) &&
        Objects.equals(txnPrdtFeeId, that.txnPrdtFeeId) &&
        Objects.equals(transactionrefnumber, that.transactionrefnumber) &&
        Objects.equals(levyby, that.levyby) &&
        Objects.equals(txnCodeId, that.txnCodeId) &&
        Objects.equals(paymentMethodId, that.paymentMethodId) &&
        Objects.equals(feeRuleId, that.feeRuleId) &&
        Objects.equals(feeamount, that.feeamount) &&
        Objects.equals(chargetype, that.chargetype) &&
        Objects.equals(origPaymentMethod, that.origPaymentMethod) &&
        Objects.equals(feeId, that.feeId) &&
        Objects.equals(feename, that.feename);
  }

  @Override
  public int hashCode() {
    return Objects
        .hash(txnpaymentfeeid, txnPrdtFeeId, transactionrefnumber, levyby, txnCodeId,
            paymentMethodId,
            feeRuleId, feeamount, chargetype, origPaymentMethod, feeId, feename);
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.JSON_STYLE)
        .append("txnpaymentfeeid", txnpaymentfeeid)
        .append("txnPrdtFeeId", txnPrdtFeeId)
        .append("transactionrefnumber", transactionrefnumber)
        .append("levyby", levyby)
        .append("txnCodeId", txnCodeId)
        .append("paymentMethodId", paymentMethodId)
        .append("feeRuleId", feeRuleId)
        .append("feeamount", feeamount)
        .append("chargetype", chargetype)
        .append("origPaymentMethod", origPaymentMethod)
        .append("feeId", feeId)
        .append("feename", feename)
        .toString();
  }
}
