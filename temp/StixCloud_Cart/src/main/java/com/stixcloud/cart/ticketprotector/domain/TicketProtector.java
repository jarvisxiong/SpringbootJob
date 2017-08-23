package com.stixcloud.cart.ticketprotector.domain;

import com.google.common.base.Objects;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name = "TICKET_PROTECTOR")
public class TicketProtector {

  private Long ticketProtectorId;
  private String mondialPolicyId;
  private BigDecimal premiumPaid;
  private String requestXml;
  private String responseXml;
  private int status;
  private Long transactionId;
  private Long showId;

  public TicketProtector() {
  }

  public TicketProtector(String mondialPolicyId,
                         BigDecimal premiumPaid, String requestXml, String responseXml, int status,
                         long transactionId, long showNumber) {
    this.mondialPolicyId = mondialPolicyId;
    this.premiumPaid = premiumPaid;
    this.requestXml = requestXml;
    this.responseXml = responseXml;
    this.status = status;
    this.transactionId = transactionId;
    this.showId = showNumber;
  }

  @Id
  @Column(name = "TICKETPROTECTORID", unique = true, nullable = false)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TICKET_PROTECTOR_ID_SEQ")
  @GenericGenerator(name = "TICKET_PROTECTOR_ID_SEQ", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
      parameters = {
          @Parameter(name = "sequence_name", value = "TICKET_PROTECTOR_ID_SEQ"),
          @Parameter(name = "optimizer", value = "none")
      })
  public Long getTicketProtectorId() {
    return ticketProtectorId;
  }

  public void setTicketProtectorId(Long ticketProtectorId) {
    this.ticketProtectorId = ticketProtectorId;
  }

  @Column(name = "MONDIALPOLICYID", nullable = false)
  public String getMondialPolicyId() {
    return mondialPolicyId;
  }

  public void setMondialPolicyId(String mondialPolicyId) {
    this.mondialPolicyId = mondialPolicyId;
  }

  @Column(name = "PREMIUMPAID", nullable = false, precision = 20, scale = 10)
  public BigDecimal getPremiumPaid() {
    return premiumPaid;
  }

  public void setPremiumPaid(BigDecimal premiumPaid) {
    this.premiumPaid = premiumPaid;
  }

  @Column(name = "REQUESTXML", nullable = false)
  @Lob
  public String getRequestXml() {
    return requestXml;
  }

  public void setRequestXml(String requestXml) {
    this.requestXml = requestXml;
  }

  @Column(name = "RESPONSEXML", nullable = false)
  @Lob
  public String getResponseXml() {
    return responseXml;
  }

  public void setResponseXml(String responseXml) {
    this.responseXml = responseXml;
  }

  @Column(name = "STATUS", nullable = false, length = 1)
  public int getStatus() {
    return status;
  }

  public void setStatus(int status) {
    this.status = status;
  }

  @Column(name = "TRANSACTION_ID", nullable = false, length = 10)
  public Long getTransactionId() {
    return transactionId;
  }

  public void setTransactionId(Long transactionId) {
    this.transactionId = transactionId;
  }

  @Column(name = "SHOW_ID", nullable = false, length = 10)
  public Long getShowId() {
    return showId;
  }

  public void setShowId(Long showId) {
    this.showId = showId;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TicketProtector that = (TicketProtector) o;
    return status == that.status &&
        Objects.equal(ticketProtectorId, that.ticketProtectorId) &&
        Objects.equal(mondialPolicyId, that.mondialPolicyId) &&
        Objects.equal(premiumPaid, that.premiumPaid) &&
        Objects.equal(requestXml, that.requestXml) &&
        Objects.equal(responseXml, that.responseXml) &&
        Objects.equal(transactionId, that.transactionId) &&
        Objects.equal(showId, that.showId);
  }

  @Override
  public int hashCode() {
    return Objects
        .hashCode(ticketProtectorId, mondialPolicyId, premiumPaid, requestXml, responseXml, status,
            transactionId, showId);
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this)
        .append("ticketProtectorId", ticketProtectorId)
        .append("mondialPolicyId", mondialPolicyId)
        .append("premiumPaid", premiumPaid)
        .append("requestXml", requestXml)
        .append("responseXml", responseXml)
        .append("status", status)
        .append("transactionId", transactionId)
        .append("showId", showId)
        .toString();
  }
}
