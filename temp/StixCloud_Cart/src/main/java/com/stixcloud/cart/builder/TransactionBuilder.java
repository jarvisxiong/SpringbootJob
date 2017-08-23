package com.stixcloud.cart.builder;

import com.stixcloud.domain.Transaction;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

/**
 * Created by chongye on 16/12/2016.
 */
public final class TransactionBuilder {
  private String transactionrefnumber;
  private Long txnCodeId;
  private Long patronId;
  private String patronname;
  private String patronidentitynum;
  private Long transactedBy;
  private Long transactedProfile;
  private Long ownerProfile;
  private Long posId;
  private Integer orderstatus;
  private BigDecimal totalPayable;
  private Integer totalTickets;
  private OffsetDateTime transactedtime;
  private Integer paymentmode;
  private String remarks;
  private String externaltxnid;
  private Long ownerId;
  private Integer feefired;
  private Long updatedBy;
  private OffsetDateTime updateddate;
  private String channeltype;

  private TransactionBuilder() {
  }

  public static TransactionBuilder aTransaction() {
    return new TransactionBuilder();
  }

  public TransactionBuilder transactionrefnumber(String transactionrefnumber) {
    this.transactionrefnumber = transactionrefnumber;
    return this;
  }

  public TransactionBuilder txnCodeId(Long txnCodeId) {
    this.txnCodeId = txnCodeId;
    return this;
  }

  public TransactionBuilder patronId(Long patronId) {
    this.patronId = patronId;
    return this;
  }

  public TransactionBuilder patronname(String patronname) {
    this.patronname = patronname;
    return this;
  }

  public TransactionBuilder patronidentitynum(String patronidentitynum) {
    this.patronidentitynum = patronidentitynum;
    return this;
  }

  public TransactionBuilder transactedBy(Long transactedBy) {
    this.transactedBy = transactedBy;
    return this;
  }

  public TransactionBuilder transactedProfile(Long transactedProfile) {
    this.transactedProfile = transactedProfile;
    return this;
  }

  public TransactionBuilder ownerProfile(Long ownerProfile) {
    this.ownerProfile = ownerProfile;
    return this;
  }

  public TransactionBuilder posId(Long posId) {
    this.posId = posId;
    return this;
  }

  public TransactionBuilder orderstatus(Integer orderstatus) {
    this.orderstatus = orderstatus;
    return this;
  }

  public TransactionBuilder totalPayable(BigDecimal totalPayable) {
    this.totalPayable = totalPayable;
    return this;
  }

  public TransactionBuilder totalTickets(Integer totalTickets) {
    this.totalTickets = totalTickets;
    return this;
  }

  public TransactionBuilder transactedtime(OffsetDateTime transactedtime) {
    this.transactedtime = transactedtime;
    return this;
  }

  public TransactionBuilder paymentmode(Integer paymentmode) {
    this.paymentmode = paymentmode;
    return this;
  }

  public TransactionBuilder remarks(String remarks) {
    this.remarks = remarks;
    return this;
  }

  public TransactionBuilder externaltxnid(String externaltxnid) {
    this.externaltxnid = externaltxnid;
    return this;
  }

  public TransactionBuilder ownerId(Long ownerId) {
    this.ownerId = ownerId;
    return this;
  }

  public TransactionBuilder feefired(Integer feefired) {
    this.feefired = feefired;
    return this;
  }

  public TransactionBuilder updatedBy(Long updatedBy) {
    this.updatedBy = updatedBy;
    return this;
  }

  public TransactionBuilder updateddate(OffsetDateTime updateddate) {
    this.updateddate = updateddate;
    return this;
  }

  public TransactionBuilder channeltype(String channeltype) {
    this.channeltype = channeltype;
    return this;
  }

  public Transaction build() {
    Transaction transaction = new Transaction();
    transaction.setTransactionrefnumber(transactionrefnumber);
    transaction.setTxnCodeId(txnCodeId);
    transaction.setPatronId(patronId);
    transaction.setPatronname(patronname);
    transaction.setPatronidentitynum(patronidentitynum);
    transaction.setTransactedBy(transactedBy);
    transaction.setTransactedProfile(transactedProfile);
    transaction.setOwnerProfile(ownerProfile);
    transaction.setPosId(posId);
    transaction.setOrderstatus(orderstatus);
    transaction.setTotalPayable(totalPayable);
    transaction.setTotalTickets(totalTickets);
    transaction.setTransactedtime(transactedtime);
    transaction.setPaymentmode(paymentmode);
    transaction.setRemarks(remarks);
    transaction.setExternaltxnid(externaltxnid);
    transaction.setOwnerId(ownerId);
    transaction.setFeefired(feefired);
    transaction.setUpdatedBy(updatedBy);
    transaction.setUpdateddate(updateddate);
    transaction.setChanneltype(channeltype);
    return transaction;
  }
}
