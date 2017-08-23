package com.stixcloud.domain;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * PaymentGateway domain class to model a table
 */
@Entity
@Table(name = "PAYMENT_GATEWAY_TXN")
public class PaymentGatewayTxn implements Serializable {

  private Long paymentGatewayTxnId;
  private Long paymentId;
  private Long revenueCentreId;
  private String paytxnref;
  private BigDecimal txnamount;
  private OffsetDateTime txndate;
  private Long txntype;
  private Long status;
  private String errmsg;
  private Long paymentGatewayId;
  private Long retrynumber;
  private String approvalcode;
  private String reqdata;
  private String respdata;

  /**
   * Instantiates a new payment gateway txn.
   */
  public PaymentGatewayTxn() {
  }

  /**
   * Gets the payment gateway txn id.
   * @return the payment gateway txn id
   */
  @Id
  @Column(name = "PAYMENTGATEWAYTXNID", unique = true, nullable = false, precision = 19, scale = 0)
  @GeneratedValue(strategy = GenerationType.AUTO, generator = "paymentGatewayTxnIdSeq")
  @GenericGenerator(name = "paymentGatewayTxnIdSeq", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
      parameters = {
          @Parameter(name = "sequence_name", value = "PAYMENT_GATEWAY_TXN_ID_SEQ"),
          @Parameter(name = "optimizer", value = "none")
      })
  public Long getPaymentGatewayTxnId() {
    return this.paymentGatewayTxnId;
  }

  /**
   * Sets the payment gateway txn id.
   * @param paymentGatewayTxnId the new payment gateway txn id
   */
  public void setPaymentGatewayTxnId(Long paymentGatewayTxnId) {
    this.paymentGatewayTxnId = paymentGatewayTxnId;
  }

  /**
   * Gets the payment id.
   * @return the payment id
   */
  @Column(name = "PAYMENT_ID", nullable = true, precision = 19, scale = 0)
  public Long getPaymentId() {
    return paymentId;
  }

  /**
   * Sets the payment id.
   * @param paymentId the new payment id
   */
  public void setPaymentId(Long paymentId) {
    this.paymentId = paymentId;
  }

  /**
   * Gets the paytxnref.
   * @return the paytxnref
   */
  @Column(name = "PAYTXNREF", nullable = false, length = 50)
  public String getPaytxnref() {
    return paytxnref;
  }

  /**
   * Sets the paytxnref.
   * @param paytxnref the new paytxnref
   */
  public void setPaytxnref(String paytxnref) {
    this.paytxnref = paytxnref;
  }

  /**
   * Gets the txnamount.
   * @return the txnamount
   */
  @Column(name = "TXNAMOUNT", nullable = true, precision = 25)
  public BigDecimal getTxnamount() {
    return txnamount;
  }

  /**
   * Sets the txnamount.
   * @param txnamount the new txnamount
   */
  public void setTxnamount(BigDecimal txnamount) {
    this.txnamount = txnamount;
  }

  /**
   * Gets the txndate.
   * @return the txndate
   */
  @Column(name = "TXNDATE", nullable = false)
  public OffsetDateTime getTxndate() {
    return txndate;
  }

  /**
   * Sets the txndate.
   * @param txndate the new txndate
   */
  public void setTxndate(OffsetDateTime txndate) {
    this.txndate = txndate;
  }

  /**
   * Gets the txntype.
   * @return the txntype
   */
  @Column(name = "TXNTYPE", nullable = true, precision = 1, scale = 0)
  public Long getTxntype() {
    return txntype;
  }

  /**
   * Sets the txntype.
   * @param txntype the new txntype
   */
  public void setTxntype(Long txntype) {
    this.txntype = txntype;
  }

  /**
   * Gets the status.
   * @return the status
   */
  @Column(name = "STATUS", nullable = true, precision = 1, scale = 0)
  public Long getStatus() {
    return status;
  }

  /**
   * Sets the status.
   * @param status the new status
   */
  public void setStatus(Long status) {
    this.status = status;
  }

  /**
   * Gets the errmsg.
   * @return the errmsg
   */
  @Column(name = "ERRMSG", nullable = true, length = 255)
  public String getErrmsg() {
    return errmsg;
  }

  /**
   * Sets the errmsg.
   * @param errmsg the new errmsg
   */
  public void setErrmsg(String errmsg) {
    this.errmsg = errmsg;
  }

  /**
   * Gets the payment gateway.
   * @return the payment gateway
   */
  @Column(name = "PAYMENT_GATEWAY_ID", nullable = false)
  public Long getPaymentGateway() {
    return paymentGatewayId;
  }

  /**
   * Sets the payment gateway.
   * @param paymentGateway the new payment gateway
   */
  public void setPaymentGateway(Long paymentGatewayId) {
    this.paymentGatewayId = paymentGatewayId;
  }

  /**
   * Gets the retrynumber.
   * @return the retrynumber
   */
  @Column(name = "RETRY", nullable = true, precision = 2, scale = 0)
  public Long getRetrynumber() {
    return retrynumber;
  }

  /**
   * Sets the retrynumber.
   * @param retrynumber the new retrynumber
   */
  public void setRetrynumber(Long retrynumber) {
    this.retrynumber = retrynumber;
  }

  /**
   * Gets the approvalcode.
   * @return the approvalcode
   */
  @Column(name = "APPROVALCODE", nullable = true, length = 15)
  public String getApprovalcode() {
    return approvalcode;
  }

  /**
   * Sets the approvalcode.
   * @param approvalcode the new approvalcode
   */
  public void setApprovalcode(String approvalcode) {
    this.approvalcode = approvalcode;
  }

  /**
   * Gets the reqdata.
   * @return the reqdata
   */
  @Column(name = "REQDATA", nullable = true, length = 2000)
  public String getReqdata() {
    return reqdata;
  }

  /**
   * Sets the reqdata.
   * @param reqdata the new reqdata
   */
  public void setReqdata(String reqdata) {
    this.reqdata = reqdata;
  }

  /**
   * Gets the respdata.
   * @return the respdata
   */
  @Column(name = "RESPDATA", nullable = true, length = 2000)
  public String getRespdata() {
    return respdata;
  }

  /**
   * Sets the respdata.
   * @param respdata the new respdata
   */
  public void setRespdata(String respdata) {
    this.respdata = respdata;
  }

  /**
   * Gets the revenue centre.
   * @return the revenue centre
   */
  @Column(name = "REVENUE_CENTER_ID", nullable = false)
  public Long getRevenueCentre() {
    return revenueCentreId;
  }

  /**
   * Sets the revenue centre.
   * @param revenueCentre the new revenue centre
   */
  public void setRevenueCentre(Long revenueCentreId) {
    this.revenueCentreId = revenueCentreId;
  }

}
