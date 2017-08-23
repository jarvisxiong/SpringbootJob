package com.stixcloud.cart.builder;

import com.stixcloud.domain.TransactionHash;

/**
 * Created by chongye on 16/12/2016.
 */
public final class TransactionHashBuilder {
  private Long txnpaymentid;
  private String hash;
  private String salt;
  private String fingerprint;

  private TransactionHashBuilder() {
  }

  public static TransactionHashBuilder aTransactionHash() {
    return new TransactionHashBuilder();
  }

  public TransactionHashBuilder txnpaymentid(Long txnpaymentid) {
    this.txnpaymentid = txnpaymentid;
    return this;
  }

  public TransactionHashBuilder hash(String hash) {
    this.hash = hash;
    return this;
  }

  public TransactionHashBuilder salt(String salt) {
    this.salt = salt;
    return this;
  }

  public TransactionHashBuilder fingerprint(String fingerprint) {
    this.fingerprint = fingerprint;
    return this;
  }

  public TransactionHash build() {
    TransactionHash transactionHash = new TransactionHash();
    transactionHash.setTxnpaymentid(txnpaymentid);
    transactionHash.setHash(hash);
    transactionHash.setSalt(salt);
    transactionHash.setFingerprint(fingerprint);
    return transactionHash;
  }
}
