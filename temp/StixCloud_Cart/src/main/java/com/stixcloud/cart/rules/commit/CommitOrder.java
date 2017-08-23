package com.stixcloud.cart.rules.commit;

import com.stixcloud.cart.api.JsonResponse;
import com.stixcloud.cart.api.ShoppingCartJson;
import com.stixcloud.domain.Transaction;
import com.stixcloud.domain.TransactionCode;
import com.stixcloud.domain.TransactionLineItem;
import com.stixcloud.domain.TransactionLineItemProduct;
import com.stixcloud.domain.TransactionPayment;
import com.stixcloud.domain.TransactionPaymentFee;
import com.stixcloud.domain.TransactionProduct;
import com.stixcloud.domain.TransactionProductFee;
import com.stixcloud.domain.TransactionProductPayment;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by chongye on 20/12/2016.
 */
public class CommitOrder {
  private Long profileInfoId;
  private Long userProfileId;
  private Long patronProfileId;
  private ShoppingCartJson shoppingCartJson;
  private TransactionCode transactionCode;
  private Transaction transaction;
  private List<TransactionPayment> transactionPayments;
  private List<TransactionLineItem> transactionLineItems;
  private List<TransactionLineItemProduct> transactionLineItemProducts;
  private List<TransactionProduct> transactionProducts;
  private List<TransactionProductPayment> transactionProductPayments;
  private List<TransactionProductFee> transactionProductFees;
  private List<TransactionPaymentFee> transactionPaymentFees;
  private boolean isFullyRedeemed = false;
  private BigDecimal payableAmount;
  private JsonResponse sCBJsonResponse;

  public CommitOrder() {
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

  public ShoppingCartJson getShoppingCartJson() {
    return shoppingCartJson;
  }

  public void setShoppingCartJson(ShoppingCartJson shoppingCartJson) {
    this.shoppingCartJson = shoppingCartJson;
  }

  public Transaction getTransaction() {
    return transaction;
  }

  public void setTransaction(Transaction transaction) {
    this.transaction = transaction;
  }

  public TransactionCode getTransactionCode() {
    return transactionCode;
  }

  public void setTransactionCode(TransactionCode transactionCode) {
    this.transactionCode = transactionCode;
  }

  public List<TransactionPayment> getTransactionPayments() {
    return transactionPayments;
  }

  public void setTransactionPayments(
      List<TransactionPayment> transactionPayments) {
    this.transactionPayments = transactionPayments;
  }

  public List<TransactionLineItem> getTransactionLineItems() {
    return transactionLineItems;
  }

  public void setTransactionLineItems(
      List<TransactionLineItem> transactionLineItems) {
    this.transactionLineItems = transactionLineItems;
  }

  public List<TransactionLineItemProduct> getTransactionLineItemProducts() {
    return transactionLineItemProducts;
  }

  public void setTransactionLineItemProducts(
      List<TransactionLineItemProduct> transactionLineItemProducts) {
    this.transactionLineItemProducts = transactionLineItemProducts;
  }

  public List<TransactionProduct> getTransactionProducts() {
    return transactionProducts;
  }

  public void setTransactionProducts(
      List<TransactionProduct> transactionProducts) {
    this.transactionProducts = transactionProducts;
  }

  public List<TransactionProductFee> getTransactionProductFees() {
    return transactionProductFees;
  }

  public void setTransactionProductFees(
      List<TransactionProductFee> transactionProductFees) {
    this.transactionProductFees = transactionProductFees;
  }

  public List<TransactionPaymentFee> getTransactionPaymentFees() {
    return transactionPaymentFees;
  }

  public void setTransactionPaymentFees(
      List<TransactionPaymentFee> transactionPaymentFees) {
    this.transactionPaymentFees = transactionPaymentFees;
  }

  public List<TransactionProductPayment> getTransactionProductPayments() {
    return transactionProductPayments;
  }

  public void setTransactionProductPayments(
      List<TransactionProductPayment> transactionProductPayments) {
    this.transactionProductPayments = transactionProductPayments;
  }

  public boolean getIsFullyRedeemed() {
    return isFullyRedeemed;
  }

  public void setIsFullyRedeemed(boolean isFullyRedeemed) {
    this.isFullyRedeemed = isFullyRedeemed;
  }

  public BigDecimal getPayableAmount() {
    return payableAmount;
  }

  public void setPayableAmount(BigDecimal payableAmount) {
    this.payableAmount = payableAmount;
  }


  public JsonResponse getSCBJsonResponse() {
    return sCBJsonResponse;
  }

  public void setSCBJsonResponse(JsonResponse sCBJsonResponse) {
    this.sCBJsonResponse = sCBJsonResponse;
  }
}
