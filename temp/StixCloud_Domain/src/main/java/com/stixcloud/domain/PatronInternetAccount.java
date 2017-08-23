package com.stixcloud.domain;

import java.time.OffsetDateTime;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Table(name = "PATRON_INTERNET_ACCOUNT")
public class PatronInternetAccount {

  private Long patroninternetaccountid;
  private String userid;
  private String password;
  private OffsetDateTime lastlogindate;
  private Long patronProfileId;
  private Integer loginfailedcount;

  public PatronInternetAccount() {

  }

  public PatronInternetAccount(Long patroninternetaccountid, String userid, String password,
      OffsetDateTime lastlogindate, Long patronProfileId, Integer loginfailedcount) {
    this.patroninternetaccountid = patroninternetaccountid;
    this.userid = userid;
    this.password = password;
    this.lastlogindate = lastlogindate;
    this.patronProfileId = patronProfileId;
    this.loginfailedcount = loginfailedcount;
  }
  
  public PatronInternetAccount(String userid, String password,
      OffsetDateTime lastlogindate, Long patronProfileId, Integer loginfailedcount) {
    this.userid = userid;
    this.password = password;
    this.lastlogindate = lastlogindate;
    this.patronProfileId = patronProfileId;
    this.loginfailedcount = loginfailedcount;
  }

  /**
   * @return the patroninternetaccountid
   */
  @Id
  @Column(name = "PATRONINTERNETACCOUNTID", nullable = false, precision = 0)
  @GeneratedValue(strategy = GenerationType.AUTO, generator = "patronInternetAccountIdSeq")
  @GenericGenerator(name = "patronInternetAccountIdSeq",
      strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
      parameters = {@Parameter(name = "sequence_name", value = "PATRON_INTERNET_ACCOUNT_ID_SEQ"),
          @Parameter(name = "optimizer", value = "none")})
  public Long getPatroninternetaccountid() {
    return patroninternetaccountid;
  }

  /**
   * @param patroninternetaccountid the patroninternetaccountid to set
   */
  public void setPatroninternetaccountid(Long patroninternetaccountid) {
    this.patroninternetaccountid = patroninternetaccountid;
  }

  /**
   * @return the userid
   */
  @Column(name = "USERID", nullable = false, length = 75)
  public String getUserid() {
    return userid;
  }

  /**
   * @param userid the userid to set
   */
  public void setUserid(String userid) {
    this.userid = userid;
  }

  /**
   * @return the password
   */
  @Column(name = "PASSWORD", nullable = false, length = 255)
  public String getPassword() {
    return password;
  }

  /**
   * @param password the password to set
   */
  public void setPassword(String password) {
    this.password = password;
  }

  /**
   * @return the lastlogindate
   */
  @Column(name = "LASTLOGINDATE", nullable = true)
  public OffsetDateTime getLastlogindate() {
    return lastlogindate;
  }

  /**
   * @param lastlogindate the lastlogindate to set
   */
  public void setLastlogindate(OffsetDateTime lastlogindate) {
    this.lastlogindate = lastlogindate;
  }

  /**
   * @return the patronProfileId
   */
  @Column(name = "PATRON_PROFILE_ID", nullable = false, precision = 0)
  public Long getPatronProfileId() {
    return patronProfileId;
  }

  /**
   * @param patronProfileId the patronProfileId to set
   */
  public void setPatronProfileId(Long patronProfileId) {
    this.patronProfileId = patronProfileId;
  }

  /**
   * @return the loginfailedcount
   */
  @Column(name = "LOGINFAILEDCOUNT", nullable = true)
  public Integer getLoginfailedcount() {
    return loginfailedcount;
  }

  /**
   * @param loginfailedcount the loginfailedcount to set
   */
  public void setLoginfailedcount(Integer loginfailedcount) {
    this.loginfailedcount = loginfailedcount;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    PatronInternetAccount that = (PatronInternetAccount) o;
    return Objects.equals(patroninternetaccountid, that.getPatroninternetaccountid())
        && Objects.equals(userid, that.getUserid()) && Objects.equals(password, that.getPassword())
        && Objects.equals(lastlogindate, that.getLastlogindate())
        && Objects.equals(patronProfileId, that.getPatronProfileId())
        && Objects.equals(loginfailedcount, that.getLoginfailedcount());
  }


  @Override
  public int hashCode() {
    return Objects.hash(patroninternetaccountid, userid, password, lastlogindate, patronProfileId,
        loginfailedcount);
  }
}
