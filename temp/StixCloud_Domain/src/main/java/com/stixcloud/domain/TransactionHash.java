package com.stixcloud.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by sengkai on 12/14/2016.
 */
@Entity
@Table(name = "TRANSACTION_HASH")
public class TransactionHash {
  private Long txnpaymentid;
  private String hash;
  private String salt;
  private String fingerprint;

  public TransactionHash() {
  }

  public TransactionHash(Long txnpaymentid, String hash, String salt, String fingerprint) {

    this.txnpaymentid = txnpaymentid;
    this.hash = hash;
    this.salt = salt;
    this.fingerprint = fingerprint;
  }

  @Id
  @Column(name = "TXNPAYMENTID", nullable = false, precision = 0)
  public Long getTxnpaymentid() {
    return txnpaymentid;
  }

  public void setTxnpaymentid(Long txnpaymentid) {
    this.txnpaymentid = txnpaymentid;
  }

  @Column(name = "HASH", nullable = false, length = 128)
  public String getHash() {
    return hash;
  }

  public void setHash(String hash) {
    this.hash = hash;
  }

  @Column(name = "SALT", nullable = false, length = 128)
  public String getSalt() {
    return salt;
  }

  public void setSalt(String salt) {
    this.salt = salt;
  }

  @Column(name = "FINGERPRINT", nullable = false, length = 128)
  public String getFingerprint() {
    return fingerprint;
  }

  public void setFingerprint(String fingerprint) {
    this.fingerprint = fingerprint;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TransactionHash that = (TransactionHash) o;
    return Objects.equals(txnpaymentid, that.txnpaymentid) &&
        Objects.equals(hash, that.hash) &&
        Objects.equals(salt, that.salt) &&
        Objects.equals(fingerprint, that.fingerprint);
  }

  @Override
  public int hashCode() {
    return Objects.hash(txnpaymentid, hash, salt, fingerprint);
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.JSON_STYLE)
        .append("txnpaymentid", txnpaymentid)
        .append("hash", hash)
        .append("salt", salt)
        .append("fingerprint", fingerprint)
        .toString();
  }
}
