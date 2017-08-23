package com.stixcloud.domain;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import java.io.Serializable;
import java.time.OffsetDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name = "NOTIFICATION")
public class Notification implements Serializable {

  private Long notificationID;
  private String txnID;
  private OffsetDateTime timeInserted;
  private OffsetDateTime timeSent;
  private String status;
  private String content;
  private String errorReason;
  private String remarks;
  private String recipient;
  private String subject;
  private Integer numRetry;
  private String config;

  /**
   * Create a new Notification instance
   */
  public Notification() {

  }

  /**
   * Create a new Notification instance
   */
  public Notification(String txnId, String recipient, String subject, String content,
                      String status) {
    this.txnID = txnId;
    this.recipient = recipient;
    this.subject = subject;
    this.content = content;
    this.status = status;
    this.timeInserted = OffsetDateTime.now();
  }


  /**
   * Gets the notification id.
   * @return the notification id
   */
  @Id
  @Column(name = "NOTIFICATIONID", unique = true, nullable = false, precision = 10, scale = 0)
  @GeneratedValue(strategy = GenerationType.AUTO, generator = "notificationIDSeq")
  @GenericGenerator(name = "notificationIDSeq", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
      parameters = {
          @Parameter(name = "sequence_name", value = "NOTIFICATION_ID_SEQ"),
          @Parameter(name = "optimizer", value = "none")
      })
  public Long getNotificationID() {
    return notificationID;
  }

  /**
   * Sets the notification id.
   * @param notificationID the new notification id
   */
  public void setNotificationID(Long notificationID) {
    this.notificationID = notificationID;
  }

  /**
   * Gets the txn id.
   * @return the txn id
   */
  @Column(name = "TRANSACTIONREFNUMBER", nullable = false)
  public String getTxnID() {
    return txnID;
  }

  /**
   * Sets the txn id.
   * @param txnID the new txn id
   */
  public void setTxnID(String txnID) {
    this.txnID = txnID;
  }

  /**
   * Gets the time inserted.
   * @return the time inserted
   */
  @Column(name = "TIMEINSERTED", nullable = true)
  public OffsetDateTime getTimeInserted() {
    return timeInserted;
  }

  /**
   * Sets the time inserted.
   * @param timeInserted the new time inserted
   */
  public void setTimeInserted(OffsetDateTime timeInserted) {
    this.timeInserted = timeInserted;
  }

  /**
   * Gets the time sent.
   * @return the time sent
   */
  @Column(name = "TIMESENT", nullable = true)
  public OffsetDateTime getTimeSent() {
    return timeSent;
  }

  /**
   * Sets the time sent.
   * @param timeSent the new time sent
   */
  public void setTimeSent(OffsetDateTime timeSent) {
    this.timeSent = timeSent;
  }

  /**
   * Gets the status.
   * @return the status
   */
  @Column(name = "STATUS", nullable = true)
  public String getStatus() {
    return status;
  }

  /**
   * Sets the status.
   * @param status the new status
   */
  public void setStatus(String status) {
    this.status = status;
  }

  /**
   * Gets the content.
   * @return the content
   */
  @Lob
  @Column(name = "CONTENT", nullable = true)
  public String getContent() {
    return content;
  }

  /**
   * Sets the content.
   * @param content the new content
   */
  public void setContent(String content) {
    this.content = content;
  }

  /**
   * Gets the error reason.
   * @return the error reason
   */
  @Column(name = "ERRORREASON", nullable = true)
  public String getErrorReason() {
    return errorReason;
  }

  /**
   * Sets the error reason.
   * @param errorReason the new error reason
   */
  public void setErrorReason(String errorReason) {
    this.errorReason = errorReason;
  }

  /**
   * Gets the remarks.
   * @return the remarks
   */
  @Column(name = "REMARKS", nullable = true)
  public String getRemarks() {
    return remarks;
  }

  /**
   * Sets the remarks.
   * @param remarks the new remarks
   */
  public void setRemarks(String remarks) {
    this.remarks = remarks;
  }

  /**
   * Gets the recipient.
   * @return the recipient
   */
  @Column(name = "RECIPIENT", nullable = true)
  public String getRecipient() {
    return recipient;
  }

  /**
   * Sets the recipient.
   * @param recipient the new recipient
   */
  public void setRecipient(String recipient) {
    this.recipient = recipient;
  }

  /**
   * Gets the subject.
   * @return the subject
   */
  @Column(name = "SUBJECT", nullable = true)
  public String getSubject() {
    return subject;
  }

  /**
   * Sets the subject.
   * @param subject the new subject
   */
  public void setSubject(String subject) {
    this.subject = subject;
  }

  /**
   * Gets the num retry.
   * @return the num retry
   */
  @Column(name = "NUMRETRY", nullable = true)
  public Integer getNumRetry() {
    return numRetry;
  }

  /**
   * Sets the num retry.
   * @param numRetry the new num retry
   */
  public void setNumRetry(Integer numRetry) {
    this.numRetry = numRetry;
  }

  /**
   * Gets the config.
   * @return the config
   */
  @Column(name = "CONFIG", nullable = true)
  public String getConfig() {
    return config;
  }

  /**
   * Sets the config.
   * @param config the new config
   */
  public void setConfig(String config) {
    this.config = config;
  }


}
