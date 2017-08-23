package com.stixcloud.domain;

import com.stixcloud.domain.DomainConstants.CREDIT_CARD_REGEX_TYPE;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.time.OffsetDateTime;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * Created by chongye on 28/11/2016.
 */
@Entity
@Table(name = "CREDIT_CARD_REGEX")
public class CreditCardRegex {
  private Long creditcardregexid;
  private Integer exptype;
  private String startdigits;
  private String remainingdigits;
  private Long creditCardId;
  private Long fileInfoId;
  private OffsetDateTime createddate;
  private Long createdBy;
  private OffsetDateTime updateddate;
  private Long updatedBy;

  @Id
  @Column(name = "CREDITCARDREGEXID", nullable = false, precision = 0)
  public Long getCreditcardregexid() {
    return creditcardregexid;
  }

  public void setCreditcardregexid(Long creditcardregexid) {
    this.creditcardregexid = creditcardregexid;
  }

  @Column(name = "EXPTYPE", nullable = false, precision = 0)
  public Integer getExptype() {
    return exptype;
  }

  public void setExptype(Integer exptype) {
    this.exptype = exptype;
  }

  @Column(name = "STARTDIGITS", nullable = false, length = 20)
  public String getStartdigits() {
    return startdigits;
  }

  public void setStartdigits(String startdigits) {
    this.startdigits = startdigits;
  }

  @Column(name = "REMAININGDIGITS", nullable = false, precision = 0)
  public String getRemainingdigits() {
    return remainingdigits;
  }

  public void setRemainingdigits(String remainingdigits) {
    this.remainingdigits = remainingdigits;
  }

  @Column(name = "CREDIT_CARD_ID", nullable = false, precision = 0)
  public Long getCreditCardId() {
    return creditCardId;
  }

  public void setCreditCardId(Long creditCardId) {
    this.creditCardId = creditCardId;
  }

  @Column(name = "FILE_INFO_ID", nullable = true, precision = 0)
  public Long getFileInfoId() {
    return fileInfoId;
  }

  public void setFileInfoId(Long fileInfoId) {
    this.fileInfoId = fileInfoId;
  }

  @Column(name = "CREATEDDATE", nullable = false)
  public OffsetDateTime getCreateddate() {
    return createddate;
  }

  public void setCreateddate(OffsetDateTime createddate) {
    this.createddate = createddate;
  }

  @Column(name = "CREATED_BY", nullable = false, precision = 0)
  public Long getCreatedBy() {
    return createdBy;
  }

  public void setCreatedBy(Long createdBy) {
    this.createdBy = createdBy;
  }

  @Column(name = "UPDATEDDATE", nullable = true)
  public OffsetDateTime getUpdateddate() {
    return updateddate;
  }

  public void setUpdateddate(OffsetDateTime updateddate) {
    this.updateddate = updateddate;
  }

  @Column(name = "UPDATED_BY", nullable = false, precision = 0)
  public Long getUpdatedBy() {
    return updatedBy;
  }

  public void setUpdatedBy(Long updatedBy) {
    this.updatedBy = updatedBy;
  }

  @Transient
  public String getRegularExpression() {
    return "^" + getStartdigits() + "\\" + CREDIT_CARD_REGEX_TYPE.getRegexType(getExptype()) + "{"
        + getRemainingdigits() + "}$";
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    CreditCardRegex that = (CreditCardRegex) o;

    return Objects.equals(this.creditcardregexid, that.creditcardregexid) &&
        Objects.equals(this.exptype, that.exptype) &&
        Objects.equals(this.startdigits, that.startdigits) &&
        Objects.equals(this.remainingdigits, that.remainingdigits) &&
        Objects.equals(this.creditCardId, that.creditCardId) &&
        Objects.equals(this.fileInfoId, that.fileInfoId) &&
        Objects.equals(this.createddate, that.createddate) &&
        Objects.equals(this.createdBy, that.createdBy) &&
        Objects.equals(this.updateddate, that.updateddate) &&
        Objects.equals(this.updatedBy, that.updatedBy);
  }

  @Override
  public int hashCode() {
    return Objects
        .hash(creditcardregexid, exptype, startdigits, remainingdigits, creditCardId, fileInfoId,
            createddate, createdBy, updateddate, updatedBy);
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.JSON_STYLE)
        .append("creditcardregexid", creditcardregexid)
        .append("exptype", exptype)
        .append("startdigits", startdigits)
        .append("remainingdigits", remainingdigits)
        .append("creditCardId", creditCardId)
        .append("fileInfoId", fileInfoId)
        .append("createddate", createddate)
        .append("createdBy", createdBy)
        .append("updateddate", updateddate)
        .append("updatedBy", updatedBy)
        .toString();
  }
}
