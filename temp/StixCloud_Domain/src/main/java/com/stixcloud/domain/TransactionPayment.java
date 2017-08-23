package com.stixcloud.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
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
@Table(name = "TRANSACTION_PAYMENT")
public class TransactionPayment {
  private Long txnpaymentid;
  private Long transactionId;
  private String transactionrefnumber;
  private OffsetDateTime paymentdate;
  private String paymentmethodname;
  private Boolean isintegrated;
  private Integer paymentstatus;
  private BigDecimal transacedamount;
  private String ccholdername;
  private String ccnumber;
  private Integer ccexpirymonth;
  private Integer ccexpiryyear;
  private Integer cctype;
  private String paymentreferenceno;
  private Boolean iscredit;
  private String origpaymentmethodname;
  private Boolean ispaymentoffline;
  private String extpatronid;
  private String extpatroninfo;
  private Long paymentMethodId;
  private Long origPaymentMethodId;
  private Long revenuecentreid;
  private Boolean isevoucherpayment;
  private String ccnumberBak;
  private String ccnumberMasked;

  public TransactionPayment() {
  }

  public TransactionPayment(Long transactionId, String transactionrefnumber,
                            OffsetDateTime paymentdate, String paymentmethodname,
                            Boolean isintegrated, Integer paymentstatus,
                            BigDecimal transacedamount, String ccholdername, String ccnumber,
                            Integer ccexpirymonth, Integer ccexpiryyear, Integer cctype,
                            String paymentreferenceno, Boolean iscredit,
                            String origpaymentmethodname, Boolean ispaymentoffline,
                            String extpatronid, String extpatroninfo, Long paymentMethodId,
                            Long origPaymentMethodId, Long revenuecentreid,
                            Boolean isevoucherpayment, String ccnumberBak,
                            String ccnumberMasked) {
    this.transactionId = transactionId;
    this.transactionrefnumber = transactionrefnumber;
    this.paymentdate = paymentdate;
    this.paymentmethodname = paymentmethodname;
    this.isintegrated = isintegrated;
    this.paymentstatus = paymentstatus;
    this.transacedamount = transacedamount;
    this.ccholdername = ccholdername;
    this.ccnumber = ccnumber;
    this.ccexpirymonth = ccexpirymonth;
    this.ccexpiryyear = ccexpiryyear;
    this.cctype = cctype;
    this.paymentreferenceno = paymentreferenceno;
    this.iscredit = iscredit;
    this.origpaymentmethodname = origpaymentmethodname;
    this.ispaymentoffline = ispaymentoffline;
    this.extpatronid = extpatronid;
    this.extpatroninfo = extpatroninfo;
    this.paymentMethodId = paymentMethodId;
    this.origPaymentMethodId = origPaymentMethodId;
    this.revenuecentreid = revenuecentreid;
    this.isevoucherpayment = isevoucherpayment;
    this.ccnumberBak = ccnumberBak;
    this.ccnumberMasked = ccnumberMasked;
  }

  @Id
  @Column(name = "TXNPAYMENTID", nullable = false, precision = 0)
  @GeneratedValue(strategy = GenerationType.AUTO, generator = "TransactionPaymentIdSeq")
  @GenericGenerator(name = "TransactionPaymentIdSeq",
      strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
      parameters = {
          @Parameter(name = "sequence_name", value = "TXN_PAYMENT_ID_SEQ"),
          @Parameter(name = "optimizer", value = "none")
      })
  public Long getTxnpaymentid() {
    return txnpaymentid;
  }

  public void setTxnpaymentid(Long txnpaymentid) {
    this.txnpaymentid = txnpaymentid;
  }

  @Column(name = "TRANSACTION_ID", nullable = false, precision = 0)
  public Long getTransactionId() {
    return transactionId;
  }

  public void setTransactionId(Long transactionId) {
    this.transactionId = transactionId;
  }

  @Column(name = "TRANSACTIONREFNUMBER", nullable = false, length = 100)
  public String getTransactionrefnumber() {
    return transactionrefnumber;
  }

  public void setTransactionrefnumber(String transactionrefnumber) {
    this.transactionrefnumber = transactionrefnumber;
  }

  @Column(name = "PAYMENTDATE", nullable = true)
  public OffsetDateTime getPaymentdate() {
    return paymentdate;
  }

  public void setPaymentdate(OffsetDateTime paymentdate) {
    this.paymentdate = paymentdate;
  }

  @Column(name = "PAYMENTMETHODNAME", nullable = false, length = 40)
  public String getPaymentmethodname() {
    return paymentmethodname;
  }

  public void setPaymentmethodname(String paymentmethodname) {
    this.paymentmethodname = paymentmethodname;
  }

  @Type(type = "true_false")
  @Column(name = "ISINTEGRATED", nullable = true, length = 1)
  public Boolean getIsintegrated() {
    return isintegrated;
  }

  public void setIsintegrated(Boolean isintegrated) {
    this.isintegrated = isintegrated;
  }

  @Column(name = "PAYMENTSTATUS", nullable = true, precision = 0)
  public Integer getPaymentstatus() {
    return paymentstatus;
  }

  public void setPaymentstatus(Integer paymentstatus) {
    this.paymentstatus = paymentstatus;
  }

  @Column(name = "TRANSACEDAMOUNT", nullable = true, precision = 5)
  public BigDecimal getTransacedamount() {
    return transacedamount;
  }

  public void setTransacedamount(BigDecimal transacedamount) {
    this.transacedamount = transacedamount;
  }

  @Column(name = "CCHOLDERNAME", nullable = true, length = 50)
  public String getCcholdername() {
    return ccholdername;
  }

  public void setCcholdername(String ccholdername) {
    this.ccholdername = ccholdername;
  }

  @Column(name = "CCNUMBER", nullable = true, length = 150)
  public String getCcnumber() {
    return ccnumber;
  }

  public void setCcnumber(String ccnumber) {
    this.ccnumber = ccnumber;
  }

  @Column(name = "CCEXPIRYMONTH", nullable = true, precision = 0)
  public Integer getCcexpirymonth() {
    return ccexpirymonth;
  }

  public void setCcexpirymonth(Integer ccexpirymonth) {
    this.ccexpirymonth = ccexpirymonth;
  }

  @Column(name = "CCEXPIRYYEAR", nullable = true, precision = 0)
  public Integer getCcexpiryyear() {
    return ccexpiryyear;
  }

  public void setCcexpiryyear(Integer ccexpiryyear) {
    this.ccexpiryyear = ccexpiryyear;
  }

  @Column(name = "CCTYPE", nullable = true, precision = 0)
  public Integer getCctype() {
    return cctype;
  }

  public void setCctype(Integer cctype) {
    this.cctype = cctype;
  }

  @Column(name = "PAYMENTREFERENCENO", nullable = true, length = 50)
  public String getPaymentreferenceno() {
    return paymentreferenceno;
  }

  public void setPaymentreferenceno(String paymentreferenceno) {
    this.paymentreferenceno = paymentreferenceno;
  }

  @Type(type = "numeric_boolean")
  @Column(name = "ISCREDIT", nullable = true, precision = 0)
  public Boolean getIscredit() {
    return iscredit;
  }

  public void setIscredit(Boolean iscredit) {
    this.iscredit = iscredit;
  }

  @Column(name = "ORIGPAYMENTMETHODNAME", nullable = true, length = 40)
  public String getOrigpaymentmethodname() {
    return origpaymentmethodname;
  }

  public void setOrigpaymentmethodname(String origpaymentmethodname) {
    this.origpaymentmethodname = origpaymentmethodname;
  }

  @Type(type = "true_false")
  @Column(name = "ISPAYMENTOFFLINE", nullable = true, length = 1)
  public Boolean getIspaymentoffline() {
    return ispaymentoffline;
  }

  public void setIspaymentoffline(Boolean ispaymentoffline) {
    this.ispaymentoffline = ispaymentoffline;
  }

  @Column(name = "EXTPATRONID", nullable = true, length = 200)
  public String getExtpatronid() {
    return extpatronid;
  }

  public void setExtpatronid(String extpatronid) {
    this.extpatronid = extpatronid;
  }

  @Column(name = "EXTPATRONINFO", nullable = true, length = 20)
  public String getExtpatroninfo() {
    return extpatroninfo;
  }

  public void setExtpatroninfo(String extpatroninfo) {
    this.extpatroninfo = extpatroninfo;
  }

  @Column(name = "PAYMENT_METHOD_ID", nullable = false, precision = 0)
  public Long getPaymentMethodId() {
    return paymentMethodId;
  }

  public void setPaymentMethodId(Long paymentMethodId) {
    this.paymentMethodId = paymentMethodId;
  }

  @Column(name = "ORIG_PAYMENT_METHOD_ID", nullable = true, precision = 0)
  public Long getOrigPaymentMethodId() {
    return origPaymentMethodId;
  }

  public void setOrigPaymentMethodId(Long origPaymentMethodId) {
    this.origPaymentMethodId = origPaymentMethodId;
  }

  @Column(name = "REVENUECENTREID", nullable = true, precision = 0)
  public Long getRevenuecentreid() {
    return revenuecentreid;
  }

  public void setRevenuecentreid(Long revenuecentreid) {
    this.revenuecentreid = revenuecentreid;
  }

  @Type(type = "true_false")
  @Column(name = "ISEVOUCHERPAYMENT", nullable = true, length = 1)
  public Boolean getIsevoucherpayment() {
    return isevoucherpayment;
  }

  public void setIsevoucherpayment(Boolean isevoucherpayment) {
    this.isevoucherpayment = isevoucherpayment;
  }

  @Column(name = "CCNUMBER_BAK", nullable = true, length = 150)
  public String getCcnumberBak() {
    return ccnumberBak;
  }

  public void setCcnumberBak(String ccnumberBak) {
    this.ccnumberBak = ccnumberBak;
  }

  @Column(name = "CCNUMBER_MASKED", nullable = true, length = 150)
  public String getCcnumberMasked() {
    return ccnumberMasked;
  }

  public void setCcnumberMasked(String ccnumberMasked) {
    this.ccnumberMasked = ccnumberMasked;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TransactionPayment that = (TransactionPayment) o;
    return Objects.equals(txnpaymentid, that.txnpaymentid) &&
        Objects.equals(transactionId, that.transactionId) &&
        Objects.equals(transactionrefnumber, that.transactionrefnumber) &&
        Objects.equals(paymentdate, that.paymentdate) &&
        Objects.equals(paymentmethodname, that.paymentmethodname) &&
        Objects.equals(isintegrated, that.isintegrated) &&
        Objects.equals(paymentstatus, that.paymentstatus) &&
        Objects.equals(transacedamount, that.transacedamount) &&
        Objects.equals(ccholdername, that.ccholdername) &&
        Objects.equals(ccnumber, that.ccnumber) &&
        Objects.equals(ccexpirymonth, that.ccexpirymonth) &&
        Objects.equals(ccexpiryyear, that.ccexpiryyear) &&
        Objects.equals(cctype, that.cctype) &&
        Objects.equals(paymentreferenceno, that.paymentreferenceno) &&
        Objects.equals(iscredit, that.iscredit) &&
        Objects.equals(origpaymentmethodname, that.origpaymentmethodname) &&
        Objects.equals(ispaymentoffline, that.ispaymentoffline) &&
        Objects.equals(extpatronid, that.extpatronid) &&
        Objects.equals(extpatroninfo, that.extpatroninfo) &&
        Objects.equals(paymentMethodId, that.paymentMethodId) &&
        Objects.equals(origPaymentMethodId, that.origPaymentMethodId) &&
        Objects.equals(revenuecentreid, that.revenuecentreid) &&
        Objects.equals(isevoucherpayment, that.isevoucherpayment) &&
        Objects.equals(ccnumberBak, that.ccnumberBak) &&
        Objects.equals(ccnumberMasked, that.ccnumberMasked);
  }

  @Override
  public int hashCode() {
    return Objects
        .hash(txnpaymentid, transactionId, transactionrefnumber, paymentdate, paymentmethodname,
            isintegrated, paymentstatus, transacedamount, ccholdername, ccnumber, ccexpirymonth,
            ccexpiryyear, cctype, paymentreferenceno, iscredit, origpaymentmethodname,
            ispaymentoffline, extpatronid, extpatroninfo, paymentMethodId, origPaymentMethodId,
            revenuecentreid, isevoucherpayment, ccnumberBak, ccnumberMasked);
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.JSON_STYLE)
        .append("txnpaymentid", txnpaymentid)
        .append("transactionId", transactionId)
        .append("transactionrefnumber", transactionrefnumber)
        .append("paymentdate", paymentdate)
        .append("paymentmethodname", paymentmethodname)
        .append("isintegrated", isintegrated)
        .append("paymentstatus", paymentstatus)
        .append("transacedamount", transacedamount)
        .append("ccholdername", ccholdername)
        .append("ccnumber", ccnumber)
        .append("ccexpirymonth", ccexpirymonth)
        .append("ccexpiryyear", ccexpiryyear)
        .append("cctype", cctype)
        .append("paymentreferenceno", paymentreferenceno)
        .append("iscredit", iscredit)
        .append("origpaymentmethodname", origpaymentmethodname)
        .append("ispaymentoffline", ispaymentoffline)
        .append("extpatronid", extpatronid)
        .append("extpatroninfo", extpatroninfo)
        .append("paymentMethodId", paymentMethodId)
        .append("origPaymentMethodId", origPaymentMethodId)
        .append("revenuecentreid", revenuecentreid)
        .append("isevoucherpayment", isevoucherpayment)
        .append("ccnumberBak", ccnumberBak)
        .append("ccnumberMasked", ccnumberMasked)
        .toString();
  }
}
