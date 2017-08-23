package com.stixcloud.cart.rules.postcommit;

import javax.money.MonetaryAmount;

/**
 * Created by chongye on 23/1/2017.
 */
public class PaymentResponse {
  private String orderId;
  private String paymentReferenceNo;
  private int paymentStatus;
  private String respMessage;
  private String approvalCode;
  private MonetaryAmount totalPaymentAmount;
  private int orderStatus;
  private String reqData;
  private String respData;

  private PaymentResponse(Builder builder) {
    setOrderId(builder.orderId);
    setPaymentReferenceNo(builder.paymentReferenceNo);
    setPaymentStatus(builder.paymentStatus);
    setRespMessage(builder.respMessage);
    setApprovalCode(builder.approvalCode);
    setTotalPaymentAmount(builder.totalPaymentAmount);
    setOrderStatus(builder.orderStatus);
    setReqData(builder.reqData);
    setRespData(builder.respData);
  }

  public String getPaymentReferenceNo() {
    return paymentReferenceNo;
  }

  public void setPaymentReferenceNo(String paymentReferenceNo) {
    this.paymentReferenceNo = paymentReferenceNo;
  }

  public int getPaymentStatus() {
    return paymentStatus;
  }

  public void setPaymentStatus(int paymentStatus) {
    this.paymentStatus = paymentStatus;
  }

  public String getRespMessage() {
    return respMessage;
  }

  public void setRespMessage(String respMessage) {
    this.respMessage = respMessage;
  }

  public String getApprovalCode() {
    return approvalCode;
  }

  public void setApprovalCode(String approvalCode) {
    this.approvalCode = approvalCode;
  }

  public String getReqData() {
    return reqData;
  }

  public void setReqData(String reqData) {
    this.reqData = reqData;
  }

  public String getRespData() {
    return respData;
  }

  public void setRespData(String respData) {
    this.respData = respData;
  }

  public MonetaryAmount getTotalPaymentAmount() {
    return totalPaymentAmount;
  }

  public void setTotalPaymentAmount(MonetaryAmount totalPaymentAmount) {
    this.totalPaymentAmount = totalPaymentAmount;
  }

  public int getOrderStatus() {
    return orderStatus;
  }

  public void setOrderStatus(int orderStatus) {
    this.orderStatus = orderStatus;
  }

  public String getOrderId() {
    return orderId;
  }

  public void setOrderId(String orderId) {
    this.orderId = orderId;
  }

  public static final class Builder {
    private String paymentReferenceNo;
    private int paymentStatus;
    private String respMessage;
    private String approvalCode;
    private MonetaryAmount totalPaymentAmount;
    private int orderStatus;
    private String reqData;
    private String respData;
    private String orderId;

    public Builder() {
    }

    public Builder(PaymentResponse copy) {
      this.orderId = copy.orderId;
      this.paymentReferenceNo = copy.paymentReferenceNo;
      this.paymentStatus = copy.paymentStatus;
      this.respMessage = copy.respMessage;
      this.approvalCode = copy.approvalCode;
      this.totalPaymentAmount = copy.totalPaymentAmount;
      this.orderStatus = copy.orderStatus;
      this.reqData = copy.reqData;
      this.respData = copy.respData;
    }

    public Builder paymentReferenceNo(String paymentReferenceNo) {
      this.paymentReferenceNo = paymentReferenceNo;
      return this;
    }

    public Builder paymentStatus(int paymentStatus) {
      this.paymentStatus = paymentStatus;
      return this;
    }

    public Builder respMessage(String respMessage) {
      this.respMessage = respMessage;
      return this;
    }

    public Builder approvalCode(String approvalCode) {
      this.approvalCode = approvalCode;
      return this;
    }

    public Builder totalPaymentAmount(MonetaryAmount totalPaymentAmount) {
      this.totalPaymentAmount = totalPaymentAmount;
      return this;
    }

    public Builder orderStatus(int orderStatus) {
      this.orderStatus = orderStatus;
      return this;
    }

    public Builder reqData(String reqData) {
      this.reqData = reqData;
      return this;
    }

    public Builder respData(String respData) {
      this.respData = respData;
      return this;
    }

    public PaymentResponse build() {
      return new PaymentResponse(this);
    }

    public Builder orderId(String orderId) {
      this.orderId = orderId;
      return this;
    }
  }
}

