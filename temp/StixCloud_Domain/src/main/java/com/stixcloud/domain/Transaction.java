package com.stixcloud.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

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
@Table(name = "TRANSACTION")
public class Transaction {
  private Long transactionid;
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

  public Transaction() {
  }

  public Transaction(String transactionrefnumber, Long txnCodeId, Long patronId,
                     String patronname, String patronidentitynum, Long transactedBy,
                     Long transactedProfile, Long ownerProfile, Long posId,
                     Integer orderstatus, BigDecimal totalPayable, Integer totalTickets,
                     OffsetDateTime transactedtime, Integer paymentmode, String remarks,
                     String externaltxnid, Long ownerId, Integer feefired, Long updatedBy,
                     OffsetDateTime updateddate, String channeltype) {
    this.transactionrefnumber = transactionrefnumber;
    this.txnCodeId = txnCodeId;
    this.patronId = patronId;
    this.patronname = patronname;
    this.patronidentitynum = patronidentitynum;
    this.transactedBy = transactedBy;
    this.transactedProfile = transactedProfile;
    this.ownerProfile = ownerProfile;
    this.posId = posId;
    this.orderstatus = orderstatus;
    this.totalPayable = totalPayable;
    this.totalTickets = totalTickets;
    this.transactedtime = transactedtime;
    this.paymentmode = paymentmode;
    this.remarks = remarks;
    this.externaltxnid = externaltxnid;
    this.ownerId = ownerId;
    this.feefired = feefired;
    this.updatedBy = updatedBy;
    this.updateddate = updateddate;
    this.channeltype = channeltype;
  }

  @Id
  @Column(name = "TRANSACTIONID", nullable = false, precision = 0)
  @GeneratedValue(strategy = GenerationType.AUTO, generator = "transactionIdSeq")
  @GenericGenerator(name = "transactionIdSeq", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
      parameters = {
          @Parameter(name = "sequence_name", value = "TRANSACTION_ID_SEQ"),
          @Parameter(name = "optimizer", value = "none")
      })
  public Long getTransactionid() {
    return transactionid;
  }

  public void setTransactionid(Long transactionid) {
    this.transactionid = transactionid;
  }

  @Column(name = "TRANSACTIONREFNUMBER", nullable = false, length = 40)
  public String getTransactionrefnumber() {
    return transactionrefnumber;
  }

  public void setTransactionrefnumber(String transactionrefnumber) {
    this.transactionrefnumber = transactionrefnumber;
  }

  @Column(name = "TXN_CODE_ID", nullable = false, precision = 0)
  public Long getTxnCodeId() {
    return txnCodeId;
  }

  public void setTxnCodeId(Long txnCodeId) {
    this.txnCodeId = txnCodeId;
  }

  @Column(name = "PATRON_ID", nullable = false, precision = 0)
  public Long getPatronId() {
    return patronId;
  }

  public void setPatronId(Long patronId) {
    this.patronId = patronId;
  }

  @Column(name = "PATRONNAME", nullable = false, length = 255)
  public String getPatronname() {
    return patronname;
  }

  public void setPatronname(String patronname) {
    this.patronname = patronname;
  }

  @Column(name = "PATRONIDENTITYNUM", nullable = true, length = 50)
  public String getPatronidentitynum() {
    return patronidentitynum;
  }

  public void setPatronidentitynum(String patronidentitynum) {
    this.patronidentitynum = patronidentitynum;
  }

  @Column(name = "TRANSACTED_BY", nullable = false, precision = 0)
  public Long getTransactedBy() {
    return transactedBy;
  }

  public void setTransactedBy(Long transactedBy) {
    this.transactedBy = transactedBy;
  }

  @Column(name = "TRANSACTED_PROFILE", nullable = false, precision = 0)
  public Long getTransactedProfile() {
    return transactedProfile;
  }

  public void setTransactedProfile(Long transactedProfile) {
    this.transactedProfile = transactedProfile;
  }

  @Column(name = "OWNER_PROFILE", nullable = true, precision = 0)
  public Long getOwnerProfile() {
    return ownerProfile;
  }

  public void setOwnerProfile(Long ownerProfile) {
    this.ownerProfile = ownerProfile;
  }

  @Column(name = "POS_ID", nullable = false, precision = 0)
  public Long getPosId() {
    return posId;
  }

  public void setPosId(Long posId) {
    this.posId = posId;
  }

  @Column(name = "ORDERSTATUS", nullable = false, precision = 0)
  public Integer getOrderstatus() {
    return orderstatus;
  }

  public void setOrderstatus(Integer orderstatus) {
    this.orderstatus = orderstatus;
  }

  @Column(name = "TOTAL_PAYABLE", nullable = true, precision = 10)
  public BigDecimal getTotalPayable() {
    return totalPayable;
  }

  public void setTotalPayable(BigDecimal totalPayable) {
    this.totalPayable = totalPayable;
  }

  @Column(name = "TOTAL_TICKETS", nullable = true, precision = 0)
  public Integer getTotalTickets() {
    return totalTickets;
  }

  public void setTotalTickets(Integer totalTickets) {
    this.totalTickets = totalTickets;
  }

  @Column(name = "TRANSACTEDTIME", nullable = true)
  public OffsetDateTime getTransactedtime() {
    return transactedtime;
  }

  public void setTransactedtime(OffsetDateTime transactedtime) {
    this.transactedtime = transactedtime;
  }

  @Column(name = "PAYMENTMODE", nullable = false, precision = 0)
  public Integer getPaymentmode() {
    return paymentmode;
  }

  public void setPaymentmode(Integer paymentmode) {
    this.paymentmode = paymentmode;
  }

  @Column(name = "REMARKS", nullable = true, length = 255)
  public String getRemarks() {
    return remarks;
  }

  public void setRemarks(String remarks) {
    this.remarks = remarks;
  }

  @Column(name = "EXTERNALTXNID", nullable = true, length = 40)
  public String getExternaltxnid() {
    return externaltxnid;
  }

  public void setExternaltxnid(String externaltxnid) {
    this.externaltxnid = externaltxnid;
  }

  @Column(name = "OWNER_ID", nullable = false, precision = 0)
  public Long getOwnerId() {
    return ownerId;
  }

  public void setOwnerId(Long ownerId) {
    this.ownerId = ownerId;
  }

  @Column(name = "FEEFIRED", nullable = false, precision = 0)
  public Integer getFeefired() {
    return feefired;
  }

  public void setFeefired(Integer feefired) {
    this.feefired = feefired;
  }

  @Column(name = "UPDATED_BY", nullable = false, precision = 0)
  public Long getUpdatedBy() {
    return updatedBy;
  }

  public void setUpdatedBy(Long updatedBy) {
    this.updatedBy = updatedBy;
  }

  @Column(name = "UPDATEDDATE", nullable = false)
  public OffsetDateTime getUpdateddate() {
    return updateddate;
  }

  public void setUpdateddate(OffsetDateTime updateddate) {
    this.updateddate = updateddate;
  }

  @Column(name = "CHANNELTYPE", nullable = true, length = 25)
  public String getChanneltype() {
    return channeltype;
  }

  public void setChanneltype(String channeltype) {
    this.channeltype = channeltype;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Transaction that = (Transaction) o;
    return Objects.equals(transactionid, that.transactionid) &&
        Objects.equals(transactionrefnumber, that.transactionrefnumber) &&
        Objects.equals(txnCodeId, that.txnCodeId) &&
        Objects.equals(patronId, that.patronId) &&
        Objects.equals(patronname, that.patronname) &&
        Objects.equals(patronidentitynum, that.patronidentitynum) &&
        Objects.equals(transactedBy, that.transactedBy) &&
        Objects.equals(transactedProfile, that.transactedProfile) &&
        Objects.equals(ownerProfile, that.ownerProfile) &&
        Objects.equals(posId, that.posId) &&
        Objects.equals(orderstatus, that.orderstatus) &&
        Objects.equals(totalPayable, that.totalPayable) &&
        Objects.equals(totalTickets, that.totalTickets) &&
        Objects.equals(transactedtime, that.transactedtime) &&
        Objects.equals(paymentmode, that.paymentmode) &&
        Objects.equals(remarks, that.remarks) &&
        Objects.equals(externaltxnid, that.externaltxnid) &&
        Objects.equals(ownerId, that.ownerId) &&
        Objects.equals(feefired, that.feefired) &&
        Objects.equals(updatedBy, that.updatedBy) &&
        Objects.equals(updateddate, that.updateddate) &&
        Objects.equals(channeltype, that.channeltype);
  }

  @Override
  public int hashCode() {
    return Objects
        .hash(transactionid, transactionrefnumber, txnCodeId, patronId, patronname,
            patronidentitynum,
            transactedBy, transactedProfile, ownerProfile, posId, orderstatus, totalPayable,
            totalTickets, transactedtime, paymentmode, remarks, externaltxnid, ownerId, feefired,
            updatedBy, updateddate, channeltype);
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.JSON_STYLE)
        .append("transactionid", transactionid)
        .append("transactionrefnumber", transactionrefnumber)
        .append("txnCodeId", txnCodeId)
        .append("patronId", patronId)
        .append("patronname", patronname)
        .append("patronidentitynum", patronidentitynum)
        .append("transactedBy", transactedBy)
        .append("transactedProfile", transactedProfile)
        .append("ownerProfile", ownerProfile)
        .append("posId", posId)
        .append("orderstatus", orderstatus)
        .append("totalPayable", totalPayable)
        .append("totalTickets", totalTickets)
        .append("transactedtime", transactedtime)
        .append("paymentmode", paymentmode)
        .append("remarks", remarks)
        .append("externaltxnid", externaltxnid)
        .append("ownerId", ownerId)
        .append("feefired", feefired)
        .append("updatedBy", updatedBy)
        .append("updateddate", updateddate)
        .append("channeltype", channeltype)
        .toString();
  }
}
