package com.stixcloud.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

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
@Table(name = "TICKET_HISTORY")
public class TicketHistory {
  private Long tickethistoryid;
  private String transactioncode;
  private String transactionrefnumber;
  private Long transactionproductId;
  private String remarks;
  private Long updatedBy;
  private OffsetDateTime updateddate;

  public TicketHistory() {
  }

  public TicketHistory(Long tickethistoryid, String transactioncode,
                       String transactionrefnumber, Long transactionproductId,
                       String remarks, Long updatedBy, OffsetDateTime updateddate) {
    this.tickethistoryid = tickethistoryid;
    this.transactioncode = transactioncode;
    this.transactionrefnumber = transactionrefnumber;
    this.transactionproductId = transactionproductId;
    this.remarks = remarks;
    this.updatedBy = updatedBy;
    this.updateddate = updateddate;
  }

  @Id
  @Column(name = "TICKETHISTORYID", nullable = false, precision = 0)
  @GeneratedValue(strategy = GenerationType.AUTO, generator = "ticketHistoryIdSeq")
  @GenericGenerator(name = "ticketHistoryIdSeq",
      strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
      parameters = {
          @Parameter(name = "sequence_name", value = "TICKET_HISTORY_ID_SEQ"),
          @Parameter(name = "optimizer", value = "none")
      })
  public Long getTickethistoryid() {
    return tickethistoryid;
  }

  public void setTickethistoryid(Long tickethistoryid) {
    this.tickethistoryid = tickethistoryid;
  }

  @Column(name = "TRANSACTIONCODE", nullable = true, length = 40)
  public String getTransactioncode() {
    return transactioncode;
  }

  public void setTransactioncode(String transactioncode) {
    this.transactioncode = transactioncode;
  }

  @Column(name = "TRANSACTIONREFNUMBER", nullable = true, length = 40)
  public String getTransactionrefnumber() {
    return transactionrefnumber;
  }

  public void setTransactionrefnumber(String transactionrefnumber) {
    this.transactionrefnumber = transactionrefnumber;
  }

  @Column(name = "TRANSACTIONPRODUCT_ID", nullable = false, precision = 0)
  public Long getTransactionproductId() {
    return transactionproductId;
  }

  public void setTransactionproductId(Long transactionproductId) {
    this.transactionproductId = transactionproductId;
  }

  @Column(name = "REMARKS", nullable = true, length = 255)
  public String getRemarks() {
    return remarks;
  }

  public void setRemarks(String remarks) {
    this.remarks = remarks;
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

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TicketHistory that = (TicketHistory) o;
    return Objects.equals(tickethistoryid, that.tickethistoryid) &&
        Objects.equals(transactioncode, that.transactioncode) &&
        Objects.equals(transactionrefnumber, that.transactionrefnumber) &&
        Objects.equals(transactionproductId, that.transactionproductId) &&
        Objects.equals(remarks, that.remarks) &&
        Objects.equals(updatedBy, that.updatedBy) &&
        Objects.equals(updateddate, that.updateddate);
  }

  @Override
  public int hashCode() {
    return Objects
        .hash(tickethistoryid, transactioncode, transactionrefnumber, transactionproductId, remarks,
            updatedBy, updateddate);
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.JSON_STYLE)
        .append("tickethistoryid", tickethistoryid)
        .append("transactioncode", transactioncode)
        .append("transactionrefnumber", transactionrefnumber)
        .append("transactionproductId", transactionproductId)
        .append("remarks", remarks)
        .append("updatedBy", updatedBy)
        .append("updateddate", updateddate)
        .toString();
  }

}
