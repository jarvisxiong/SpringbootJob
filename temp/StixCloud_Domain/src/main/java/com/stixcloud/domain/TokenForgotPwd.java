package com.stixcloud.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "PATRON_TOKEN_FORGOT_PWD")
public class TokenForgotPwd {
  private Long tokenID;
  private String tokenHash;
  private Long patron;
  private String patronEmail;
  private Date expiryTime;
  private Date requestOn;
  private Long requestBy;
  private String requestByUser;
  private Date updateOn;
  private Integer activeStatus;
  private String origin;

  /**
   * Return TokenID
   * 
   * @return
   */
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO, generator = "patronTokenSeq")
  @SequenceGenerator(name = "patronTokenSeq", sequenceName = "PATRON_TOKEN_SEQ", allocationSize = 1)
  @Column(name = "TOKENID", unique = true, nullable = false, precision = 10, scale = 0)
  public Long getTokenID() {
    return tokenID;
  }


  /**
   * Set TokenID()
   * 
   * @param tokenID
   */
  public void setTokenID(Long tokenID) {
    this.tokenID = tokenID;
  }

  /**
   * Gets the Token Hash
   * 
   * @return
   */
  @Column(name = "TOKENHASH", nullable = false)
  public String getTokenHash() {
    return tokenHash;
  }

  public void setTokenHash(String tokenHash) {
    this.tokenHash = tokenHash;
  }

  /**
   * @return the patron
   */
  @Column(name = "PATRON", length = 10)
  public Long getPatron() {
    return patron;
  }

  /**
   * @param patron the patron to set
   */
  public void setPatron(Long patron) {
    this.patron = patron;
  }

  /**
   * Gets the patron email
   * 
   * @return
   */
  @Column(name = "PATRONEMAIL", nullable = false)
  public String getPatronEmail() {
    return patronEmail;
  }



  public void setPatronEmail(String patronEmail) {
    this.patronEmail = patronEmail;
  }


  /**
   * Gets the expiry time of the token
   * 
   * @return
   */
  @Column(name = "EXPIRYTIME", nullable = true)
  public Date getExpiryTime() {
    return expiryTime;
  }


  /**
   * Sets the expiry time of the token
   */
  public void setExpiryTime(Date expiryTime) {
    this.expiryTime = expiryTime;
  }


  /**
   * Gets the time when password token was requested
   * 
   * @return
   */
  @Column(name = "REQUESTON", nullable = true)
  public Date getRequestOn() {
    return requestOn;
  }


  /**
   * Sets the time when password token was requested
   * 
   * @param requestOn
   */
  public void setRequestOn(Date requestOn) {
    this.requestOn = requestOn;
  }


  /**
   * Gets the requested by, null means user himself pressed the forgot pwd button.
   * 
   * @return
   */
  @Column(name = "REQUESTBY", nullable = true)
  public Long getRequestBy() {
    return requestBy;
  }

  /**
   * Sets the requested By, more like in behalf on the patron/user, someone else clicked the reset
   * button
   * 
   * @param requestBy
   */
  public void setRequestBy(Long requestBy) {
    this.requestBy = requestBy;
  }

  /**
   * Gets the user type for request, Patron and User
   * 
   * @return
   */
  @Column(name = "REQUESTBYUSER", nullable = true)
  public String getRequestByUser() {
    return requestByUser;
  }

  public void setRequestByUser(String requestByUser) {
    this.requestByUser = requestByUser;
  }

  /**
   * gets the date when the token was used
   * 
   * @return
   */
  @Column(name = "UPDATEDON", nullable = true)
  public Date getUpdateOn() {
    return updateOn;
  }

  public void setUpdateOn(Date updateOn) {
    this.updateOn = updateOn;
  }

  /**
   * Gets the active status of the token. 0 - inactive , 1 - active.
   * 
   * @return
   */
  @Column(name = "ACTIVESTATUS", nullable = false)
  public Integer getActiveStatus() {
    return activeStatus;
  }


  /**
   * Sets the active status of the token
   * 
   * @param activeStatus
   */
  public void setActiveStatus(Integer activeStatus) {
    this.activeStatus = activeStatus;
  }

  /**
   * Gets the request origin, where it was requested. booking enginer, stix or stix cloud
   * 
   * @return
   */
  @Column(name = "ORIGIN", nullable = false)
  public String getOrigin() {
    return origin;
  }

  /**
   * Set the origin.
   * 
   * @param origin
   */
  public void setOrigin(String origin) {
    this.origin = origin;
  }
}
