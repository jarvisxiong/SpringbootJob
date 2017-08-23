package com.stixcloud.cart.rules.postcommit;

import com.stixcloud.cart.TransactionPaymentDetails;
import com.stixcloud.cart.api.ShoppingCartJson;
import com.stixcloud.domain.Transaction;

import java.util.List;

public class PostCommitOrder {
  private Long profileInfoId;
  private Long userProfileId;
  private Long patronProfileId;
  private PaymentResponse paymentResponse;
  private String transactionReferenceNumber;
  private String paymentMethodCode;
  private Transaction transaction;
  private List<TransactionPaymentDetails> paymentList;
  private ShoppingCartJson ShoppingCartJson;

  public PostCommitOrder() {
    super();
  }

  public Long getProfileInfoId() {
    return profileInfoId;
  }

  public void setProfileInfoId(Long profileInfoId) {
    this.profileInfoId = profileInfoId;
  }

  public Long getUserProfileId() {
    return userProfileId;
  }

  public void setUserProfileId(Long userProfileId) {
    this.userProfileId = userProfileId;
  }

  public Long getPatronProfileId() {
    return patronProfileId;
  }

  public void setPatronProfileId(Long patronProfileId) {
    this.patronProfileId = patronProfileId;
  }

  public PaymentResponse getPaymentResponse() {
    return paymentResponse;
  }

  public void setPaymentResponse(PaymentResponse paymentResponse) {
    this.paymentResponse = paymentResponse;
  }

  public String getTransactionReferenceNumber() {
    return transactionReferenceNumber;
  }

  public void setTransactionReferenceNumber(String transactionReferenceNumber) {
    this.transactionReferenceNumber = transactionReferenceNumber;
  }

  public String getPaymentMethodCode() {
    return paymentMethodCode;
  }

  public void setPaymentMethodCode(String paymentMethodCode) {
    this.paymentMethodCode = paymentMethodCode;
  }

  public Transaction getTransaction() {
    return transaction;
  }

  public void setTransaction(Transaction transaction) {
    this.transaction = transaction;
  }

  public List<TransactionPaymentDetails> getPaymentList() {
    return paymentList;
  }

  public void setPaymentList(
      List<TransactionPaymentDetails> paymentList) {
    this.paymentList = paymentList;
  }

  public ShoppingCartJson getShoppingCartJson() {
    return ShoppingCartJson;
  }

  public void setShoppingCartJson(ShoppingCartJson shoppingCartJson) {
    ShoppingCartJson = shoppingCartJson;
  }


}
