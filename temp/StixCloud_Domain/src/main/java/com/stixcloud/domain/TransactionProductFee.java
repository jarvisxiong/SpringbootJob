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
@Table(name = "TRANSACTION_PRODUCT_FEE")
public class TransactionProductFee {
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

  public TransactionProductFee() {
  }

  public TransactionProductFee(Long txnproductfeeid, Long txnProductId, Long txnLineItemId,
                               String transactionrefnumber, String levyby, Long txnCodeId,
                               Long feeRuleId, BigDecimal feeamount, Integer charge,
                               Integer iscredit, Long feeId, String feename) {

    this.txnproductfeeid = txnproductfeeid;
    this.txnProductId = txnProductId;
    this.txnLineItemId = txnLineItemId;
    this.transactionrefnumber = transactionrefnumber;
    this.levyby = levyby;
    this.txnCodeId = txnCodeId;
    this.feeRuleId = feeRuleId;
    this.feeamount = feeamount;
    this.charge = charge;
    this.iscredit = iscredit;
    this.feeId = feeId;
    this.feename = feename;
  }

  @Id
  @Column(name = "TXNPRODUCTFEEID", nullable = false, precision = 0)
  @GeneratedValue(strategy = GenerationType.AUTO, generator = "TxnProductFeeIdSeq")
  @GenericGenerator(name = "TxnProductFeeIdSeq",
      strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
      parameters = {
          @Parameter(name = "sequence_name", value = "TXN_PRODUCT_FEE_ID_SEQ"),
          @Parameter(name = "optimizer", value = "none")
      })
  public Long getTxnproductfeeid() {
    return txnproductfeeid;
  }

  public void setTxnproductfeeid(Long txnproductfeeid) {
    this.txnproductfeeid = txnproductfeeid;
  }

  @Column(name = "TXN_PRODUCT_ID", nullable = false, precision = 0)
  public Long getTxnProductId() {
    return txnProductId;
  }

  public void setTxnProductId(Long txnProductId) {
    this.txnProductId = txnProductId;
  }

  @Column(name = "TXN_LINE_ITEM_ID", nullable = false, precision = 0)
  public Long getTxnLineItemId() {
    return txnLineItemId;
  }

  public void setTxnLineItemId(Long txnLineItemId) {
    this.txnLineItemId = txnLineItemId;
  }

  @Column(name = "TRANSACTIONREFNUMBER", nullable = false, length = 40)
  public String getTransactionrefnumber() {
    return transactionrefnumber;
  }

  public void setTransactionrefnumber(String transactionrefnumber) {
    this.transactionrefnumber = transactionrefnumber;
  }

  @Column(name = "LEVYBY", nullable = true, length = 50)
  public String getLevyby() {
    return levyby;
  }

  public void setLevyby(String levyby) {
    this.levyby = levyby;
  }

  @Column(name = "TXN_CODE_ID", nullable = true, precision = 0)
  public Long getTxnCodeId() {
    return txnCodeId;
  }

  public void setTxnCodeId(Long txnCodeId) {
    this.txnCodeId = txnCodeId;
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

  @Column(name = "CHARGE", nullable = true, precision = 0)
  public Integer getCharge() {
    return charge;
  }

  public void setCharge(Integer charge) {
    this.charge = charge;
  }

  @Column(name = "ISCREDIT", nullable = true, precision = 0)
  public Integer getIscredit() {
    return iscredit;
  }

  public void setIscredit(Integer iscredit) {
    this.iscredit = iscredit;
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
    TransactionProductFee that = (TransactionProductFee) o;
    return Objects.equals(txnproductfeeid, that.txnproductfeeid) &&
        Objects.equals(txnProductId, that.txnProductId) &&
        Objects.equals(txnLineItemId, that.txnLineItemId) &&
        Objects.equals(transactionrefnumber, that.transactionrefnumber) &&
        Objects.equals(levyby, that.levyby) &&
        Objects.equals(txnCodeId, that.txnCodeId) &&
        Objects.equals(feeRuleId, that.feeRuleId) &&
        Objects.equals(feeamount, that.feeamount) &&
        Objects.equals(charge, that.charge) &&
        Objects.equals(iscredit, that.iscredit) &&
        Objects.equals(feeId, that.feeId) &&
        Objects.equals(feename, that.feename);
  }

  @Override
  public int hashCode() {
    return Objects
        .hash(txnproductfeeid, txnProductId, txnLineItemId, transactionrefnumber, levyby, txnCodeId,
            feeRuleId, feeamount, charge, iscredit, feeId, feename);
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.JSON_STYLE)
        .append("txnproductfeeid", txnproductfeeid)
        .append("txnProductId", txnProductId)
        .append("txnLineItemId", txnLineItemId)
        .append("transactionrefnumber", transactionrefnumber)
        .append("levyby", levyby)
        .append("txnCodeId", txnCodeId)
        .append("feeRuleId", feeRuleId)
        .append("feeamount", feeamount)
        .append("charge", charge)
        .append("iscredit", iscredit)
        .append("feeId", feeId)
        .append("feename", feename)
        .toString();
  }
}
