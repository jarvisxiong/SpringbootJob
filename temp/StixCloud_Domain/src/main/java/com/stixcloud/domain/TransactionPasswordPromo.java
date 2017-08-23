package com.stixcloud.domain;

import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

/**
 * 
 * @author dbthan
 *
 */
@Entity
@Table(name = "TRANSACTION_PASSWORD_PROMO")
public class TransactionPasswordPromo implements Serializable {
  private static final long serialVersionUID = 3727493111451914163L;
  private Long transactionPasswordPromoId;
  private Long promotionPasswordId;
  private String password;
  private String transactionRefNumber;
  private OffsetDateTime createdDate;
  private OffsetDateTime updatedDate;
  private Long createdBy;
  private Long updatedBy;

  public TransactionPasswordPromo() {
    super();
  }

  /**
   * @param transactionPasswordPromoId
   * @param promotionPasswordId
   * @param password
   * @param transactionRefNumber
   * @param createdDate
   * @param updatedDate
   * @param createdBy
   * @param updatedBy
   */
  public TransactionPasswordPromo(Long transactionPasswordPromoId, Long promotionPasswordId,
      String password, String transactionRefNumber, OffsetDateTime createdDate,
      OffsetDateTime updatedDate, Long createdBy, Long updatedBy) {
    super();
    this.transactionPasswordPromoId = transactionPasswordPromoId;
    this.promotionPasswordId = promotionPasswordId;
    this.password = password;
    this.transactionRefNumber = transactionRefNumber;
    this.createdDate = createdDate;
    this.updatedDate = updatedDate;
    this.createdBy = createdBy;
    this.updatedBy = updatedBy;
  }

  /**
   * @param promotionPasswordId
   * @param password
   * @param transactionRefNumber
   * @param createdDate
   * @param updatedDate
   * @param createdBy
   * @param updatedBy
   */
  public TransactionPasswordPromo(Long promotionPasswordId, String password,
      String transactionRefNumber, OffsetDateTime createdDate, OffsetDateTime updatedDate,
      Long createdBy, Long updatedBy) {
    super();
    this.promotionPasswordId = promotionPasswordId;
    this.password = password;
    this.transactionRefNumber = transactionRefNumber;
    this.createdDate = createdDate;
    this.updatedDate = updatedDate;
    this.createdBy = createdBy;
    this.updatedBy = updatedBy;
  }

  @Id
  @Column(name = "TRANSACTIONPASSWORDPROMOID", nullable = false, precision = 0)
  @GeneratedValue(strategy = GenerationType.AUTO, generator = "TxnPasswordPromoSeq")
  @GenericGenerator(name = "TxnPasswordPromoSeq",
      strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
      parameters = {@Parameter(name = "sequence_name", value = "TRANSACTION_PASSWORD_PROMO_SEQ"),
          @Parameter(name = "optimizer", value = "none")})
  public Long getTransactionPasswordPromoId() {
    return transactionPasswordPromoId;
  }

  /**
   * @param transactionPasswordPromoId the transactionPasswordPromoId to set
   */
  public void setTransactionPasswordPromoId(Long transactionPasswordPromoId) {
    this.transactionPasswordPromoId = transactionPasswordPromoId;
  }

  /**
   * @return the promotionPasswordId
   */
  @Column(name = "PROMOTION_PASSWORD_ID", nullable = false, precision = 0)
  public Long getPromotionPasswordId() {
    return promotionPasswordId;
  }

  /**
   * @param promotionPasswordId the promotionPasswordId to set
   */
  public void setPromotionPasswordId(Long promotionPasswordId) {
    this.promotionPasswordId = promotionPasswordId;
  }

  /**
   * @return the password
   */
  @Column(name = "PASSWORD", nullable = false, length = 50)
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
   * gets the transactionRefNumber.
   *
   */
  @Column(name = "TRANSACTIONREFNUMBER", nullable = false, length = 40)
  public String getTransactionRefNumber() {
    return transactionRefNumber;
  }

  /**
   * Sets the transactionRefNumber.
   *
   * @param transactionRefNumber the new transactionRefNumber
   */
  public void setTransactionRefNumber(String transactionRefNumber) {
    this.transactionRefNumber = transactionRefNumber;
  }

  @Column(name = "CREATED_BY", nullable = false, precision = 0)
  public Long getCreatedBy() {
    return createdBy;
  }

  public void setCreatedBy(Long createdBy) {
    this.createdBy = createdBy;
  }

  @Column(name = "CREATEDDATE", nullable = false)
  public OffsetDateTime getCreatedDate() {
    return createdDate;
  }

  public void setCreatedDate(OffsetDateTime createdDate) {
    this.createdDate = createdDate;
  }

  @Column(name = "UPDATED_BY", nullable = false, precision = 0)
  public Long getUpdatedBy() {
    return updatedBy;
  }

  public void setUpdatedBy(Long updatedBy) {
    this.updatedBy = updatedBy;
  }

  @Column(name = "UPDATEDDATE", nullable = true)
  public OffsetDateTime getUpdatedDate() {
    return updatedDate;
  }

  public void setUpdatedDate(OffsetDateTime updatedDate) {
    this.updatedDate = updatedDate;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TransactionPasswordPromo that = (TransactionPasswordPromo) o;
    return Objects.equals(transactionPasswordPromoId, that.transactionPasswordPromoId)
        && Objects.equals(promotionPasswordId, that.promotionPasswordId)
        && Objects.equals(password, that.password)
        && Objects.equals(transactionRefNumber, that.transactionRefNumber)
        && Objects.equals(createdDate, that.createdDate)
        && Objects.equals(updatedDate, that.updatedDate)
        && Objects.equals(createdBy, that.createdBy) && Objects.equals(updatedBy, that.updatedBy);
  }

  @Override
  public int hashCode() {
    return Objects.hash(transactionPasswordPromoId, promotionPasswordId, password,
        transactionRefNumber, createdDate, updatedDate, createdBy, updatedBy);
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.JSON_STYLE)
        .append("transactionPasswordPromoId", transactionPasswordPromoId)
        .append("promotionPasswordId", promotionPasswordId).append("password", password)
        .append("transactionRefNumber", transactionRefNumber).append("createdDate", createdDate)
        .append("updatedDate", updatedDate).append("createdBy", createdBy)
        .append("updatedBy", updatedBy).toString();
  }
}
