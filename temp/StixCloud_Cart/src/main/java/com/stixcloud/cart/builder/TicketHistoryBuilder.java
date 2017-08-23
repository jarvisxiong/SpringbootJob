package com.stixcloud.cart.builder;

import com.stixcloud.domain.TicketHistory;

import java.time.OffsetDateTime;

/**
 * Created by chongye on 27/12/2016.
 */
public final class TicketHistoryBuilder {
  private Long tickethistoryid;
  private String transactioncode;
  private String transactionrefnumber;
  private Long transactionproductId;
  private String remarks;
  private Long updatedBy;
  private OffsetDateTime updateddate;

  private TicketHistoryBuilder() {
  }

  public static TicketHistoryBuilder aTicketHistory() {
    return new TicketHistoryBuilder();
  }

  public TicketHistoryBuilder tickethistoryid(Long tickethistoryid) {
    this.tickethistoryid = tickethistoryid;
    return this;
  }

  public TicketHistoryBuilder transactioncode(String transactioncode) {
    this.transactioncode = transactioncode;
    return this;
  }

  public TicketHistoryBuilder transactionrefnumber(String transactionrefnumber) {
    this.transactionrefnumber = transactionrefnumber;
    return this;
  }

  public TicketHistoryBuilder transactionproductId(Long transactionproductId) {
    this.transactionproductId = transactionproductId;
    return this;
  }

  public TicketHistoryBuilder remarks(String remarks) {
    this.remarks = remarks;
    return this;
  }

  public TicketHistoryBuilder updatedBy(Long updatedBy) {
    this.updatedBy = updatedBy;
    return this;
  }

  public TicketHistoryBuilder updateddate(OffsetDateTime updateddate) {
    this.updateddate = updateddate;
    return this;
  }

  public TicketHistory build() {
    TicketHistory ticketHistory = new TicketHistory();
    ticketHistory.setTickethistoryid(tickethistoryid);
    ticketHistory.setTransactioncode(transactioncode);
    ticketHistory.setTransactionrefnumber(transactionrefnumber);
    ticketHistory.setTransactionproductId(transactionproductId);
    ticketHistory.setRemarks(remarks);
    ticketHistory.setUpdatedBy(updatedBy);
    ticketHistory.setUpdateddate(updateddate);
    return ticketHistory;
  }
}
