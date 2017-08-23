package com.stixcloud.cart.builder;

import com.stixcloud.domain.TransactionPayment;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

/**
 * Created by chongye on 16/12/2016.
 */
public final class TransactionPaymentBuilder {
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

  private TransactionPaymentBuilder() {
  }

  private TransactionPaymentBuilder(TransactionPayment transactionPayment) {
    this.transactionId = transactionPayment.getTransactionId();
    this.transactionrefnumber = transactionPayment.getTransactionrefnumber();
    this.paymentdate = transactionPayment.getPaymentdate();
    this.paymentmethodname = transactionPayment.getPaymentmethodname();
    this.isintegrated = transactionPayment.getIsintegrated();
    this.paymentstatus = transactionPayment.getPaymentstatus();
    this.transacedamount = transactionPayment.getTransacedamount();
    this.ccholdername = transactionPayment.getCcholdername();
    this.ccnumber = transactionPayment.getCcnumber();
    this.ccexpirymonth = transactionPayment.getCcexpirymonth();
    this.ccexpiryyear = transactionPayment.getCcexpiryyear();
    this.cctype = transactionPayment.getCctype();
    this.paymentreferenceno = transactionPayment.getPaymentreferenceno();
    this.iscredit = transactionPayment.getIscredit();
    this.origpaymentmethodname = transactionPayment.getOrigpaymentmethodname();
    this.ispaymentoffline = transactionPayment.getIspaymentoffline();
    this.extpatronid = transactionPayment.getExtpatronid();
    this.extpatroninfo = transactionPayment.getExtpatroninfo();
    this.paymentMethodId = transactionPayment.getPaymentMethodId();
    this.origPaymentMethodId = transactionPayment.getOrigPaymentMethodId();
    this.revenuecentreid = transactionPayment.getRevenuecentreid();
    this.isevoucherpayment = transactionPayment.getIsevoucherpayment();
    this.ccnumberBak = transactionPayment.getCcnumberBak();
    this.ccnumberMasked = transactionPayment.getCcnumberMasked();
  }

  public static TransactionPaymentBuilder aTransactionPayment() {
    return new TransactionPaymentBuilder();
  }

  public static TransactionPaymentBuilder aTransactionPayment(
      TransactionPayment transactionPayment) {
    return new TransactionPaymentBuilder(transactionPayment);
  }

  public TransactionPaymentBuilder transactionId(Long transactionId) {
    this.transactionId = transactionId;
    return this;
  }

  public TransactionPaymentBuilder transactionrefnumber(String transactionrefnumber) {
    this.transactionrefnumber = transactionrefnumber;
    return this;
  }

  public TransactionPaymentBuilder paymentdate(OffsetDateTime paymentdate) {
    this.paymentdate = paymentdate;
    return this;
  }

  public TransactionPaymentBuilder paymentmethodname(String paymentmethodname) {
    this.paymentmethodname = paymentmethodname;
    return this;
  }

  public TransactionPaymentBuilder isintegrated(Boolean isintegrated) {
    this.isintegrated = isintegrated;
    return this;
  }

  public TransactionPaymentBuilder paymentstatus(Integer paymentstatus) {
    this.paymentstatus = paymentstatus;
    return this;
  }

  public TransactionPaymentBuilder transacedamount(BigDecimal transacedamount) {
    this.transacedamount = transacedamount;
    return this;
  }

  public TransactionPaymentBuilder ccholdername(String ccholdername) {
    this.ccholdername = ccholdername;
    return this;
  }

  public TransactionPaymentBuilder ccnumber(String ccnumber) {
    this.ccnumber = ccnumber;
    return this;
  }

  public TransactionPaymentBuilder ccexpirymonth(Integer ccexpirymonth) {
    this.ccexpirymonth = ccexpirymonth;
    return this;
  }

  public TransactionPaymentBuilder ccexpiryyear(Integer ccexpiryyear) {
    this.ccexpiryyear = ccexpiryyear;
    return this;
  }

  public TransactionPaymentBuilder cctype(Integer cctype) {
    this.cctype = cctype;
    return this;
  }

  public TransactionPaymentBuilder paymentreferenceno(String paymentreferenceno) {
    this.paymentreferenceno = paymentreferenceno;
    return this;
  }

  public TransactionPaymentBuilder iscredit(Boolean iscredit) {
    this.iscredit = iscredit;
    return this;
  }

  public TransactionPaymentBuilder origpaymentmethodname(String origpaymentmethodname) {
    this.origpaymentmethodname = origpaymentmethodname;
    return this;
  }

  public TransactionPaymentBuilder ispaymentoffline(Boolean ispaymentoffline) {
    this.ispaymentoffline = ispaymentoffline;
    return this;
  }

  public TransactionPaymentBuilder extpatronid(String extpatronid) {
    this.extpatronid = extpatronid;
    return this;
  }

  public TransactionPaymentBuilder extpatroninfo(String extpatroninfo) {
    this.extpatroninfo = extpatroninfo;
    return this;
  }

  public TransactionPaymentBuilder paymentMethodId(Long paymentMethodId) {
    this.paymentMethodId = paymentMethodId;
    return this;
  }

  public TransactionPaymentBuilder origPaymentMethodId(Long origPaymentMethodId) {
    this.origPaymentMethodId = origPaymentMethodId;
    return this;
  }

  public TransactionPaymentBuilder revenuecentreid(Long revenuecentreid) {
    this.revenuecentreid = revenuecentreid;
    return this;
  }

  public TransactionPaymentBuilder isevoucherpayment(Boolean isevoucherpayment) {
    this.isevoucherpayment = isevoucherpayment;
    return this;
  }

  public TransactionPaymentBuilder ccnumberBak(String ccnumberBak) {
    this.ccnumberBak = ccnumberBak;
    return this;
  }

  public TransactionPaymentBuilder ccnumberMasked(String ccnumberMasked) {
    this.ccnumberMasked = ccnumberMasked;
    return this;
  }

  public TransactionPayment build() {
    TransactionPayment transactionPayment = new TransactionPayment();
    transactionPayment.setTransactionId(transactionId);
    transactionPayment.setTransactionrefnumber(transactionrefnumber);
    transactionPayment.setPaymentdate(paymentdate);
    transactionPayment.setPaymentmethodname(paymentmethodname);
    transactionPayment.setIsintegrated(isintegrated);
    transactionPayment.setPaymentstatus(paymentstatus);
    transactionPayment.setTransacedamount(transacedamount);
    transactionPayment.setCcholdername(ccholdername);
    transactionPayment.setCcnumber(ccnumber);
    transactionPayment.setCcexpirymonth(ccexpirymonth);
    transactionPayment.setCcexpiryyear(ccexpiryyear);
    transactionPayment.setCctype(cctype);
    transactionPayment.setPaymentreferenceno(paymentreferenceno);
    transactionPayment.setIscredit(iscredit);
    transactionPayment.setOrigpaymentmethodname(origpaymentmethodname);
    transactionPayment.setIspaymentoffline(ispaymentoffline);
    transactionPayment.setExtpatronid(extpatronid);
    transactionPayment.setExtpatroninfo(extpatroninfo);
    transactionPayment.setPaymentMethodId(paymentMethodId);
    transactionPayment.setOrigPaymentMethodId(origPaymentMethodId);
    transactionPayment.setRevenuecentreid(revenuecentreid);
    transactionPayment.setIsevoucherpayment(isevoucherpayment);
    transactionPayment.setCcnumberBak(ccnumberBak);
    transactionPayment.setCcnumberMasked(ccnumberMasked);
    return transactionPayment;
  }
}
