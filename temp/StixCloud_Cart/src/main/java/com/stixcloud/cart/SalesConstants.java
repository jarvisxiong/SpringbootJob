package com.stixcloud.cart;

import java.io.Serializable;

public class SalesConstants implements Serializable {

  private static final long serialVersionUID = -8181735510384733566L;

  public enum LineItemType {

    PRODUCT("Product", 1),
    FEE("Fee", 2),
    //added by Devin for EVoucher Enhancement
    EVoucher("EVoucher", 3);

    String type;

    Integer value;

    /**
     * Instantiates a new line item type.
     * @param type the type
     * @param value the value
     */
    LineItemType(String type, Integer value) {
      this.type = type;
      this.value = value;
    }

    /**
     * Gets the value.
     * @return the value
     */
    public int getValue() {
      return this.value;
    }

    /**
     * Gets the name.
     * @return the name
     */
    public String getName() {
      return this.type;
    }
  }

  public enum ProductCategory {
    //[Start] 1930
    SEASONPARKING("Season Parking", 5),
    FOODANDBEVERAGE("Food & Beverage", 6),
    //[End] 1930
    EVENT("Event", 1),
    MERCHANDISE("Merchandise", 2),;

    String type;

    Integer value;

    /**
     * Instantiates a new product category.
     * @param type the type
     * @param value the value
     */
    ProductCategory(String type, Integer value) {
      this.type = type;
      this.value = value;
    }

    /**
     * Gets the value.
     * @return the value
     */
    public int getValue() {
      return this.value;
    }

    /**
     * Gets the name.
     * @return the name
     */
    public String getName() {
      return this.type;
    }
  }

  public enum FeeFiringMode {
    NOT_FIRED("Not Fired", 1),
    FIRED("Fired", 2),
    IN_PROGRESS("In Progress", 3),;

    String type;

    Integer value;

    /**
     * Instantiates a new fee firing mode.
     * @param type the type
     * @param value the value
     */
    FeeFiringMode(String type, Integer value) {
      this.type = type;
      this.value = value;
    }

    /**
     * Gets the value.
     * @return the value
     */
    public Integer getValue() {
      return this.value;
    }

    /**
     * Gets the type.
     * @return the type
     */
    public String getType() {
      return this.type;
    }

  }

  public enum PrintStatus {
    NOT_PRINTED("Not Printed", 1),
    PRINTED("Printed", 2),
    PRINTING("Printing", 3);

    String type;

    Integer value;

    /**
     * Instantiates a new prints the status.
     * @param type the type
     * @param value the value
     */
    PrintStatus(String type, Integer value) {
      this.type = type;
      this.value = value;
    }

    /**
     * Gets the value.
     * @return the value
     */
    public Integer getValue() {
      return this.value;
    }

    /**
     * Gets the type.
     * @return the type
     */
    public String getType() {
      return this.type;
    }

    /**
     * Gets the prints the status.
     * @param type the type
     * @return the prints the status
     */
    public static String getPrintStatus(int type) {
      for (PrintStatus printStatus : PrintStatus.values()) {
        if (printStatus.value == type) {
          return printStatus.type;
        }
      }
      return "";
    }

    /**
     * Gets the prints the status.
     * @param type the type
     * @return the prints the status
     */
    public static PrintStatus getPrintStatus(String type) {
      for (PrintStatus printStatus : PrintStatus.values()) {
        if (printStatus.type.equals(type)) {
          return printStatus;
        }
      }
      return null;
    }
  }

  public enum TicketType {
    PAPER_TICKET("Paper Ticket", 1),
    ETICKET("E-Ticket", 2),
    MTICKET("M-Ticket", 3),;

    String type;

    Integer value;

    /**
     * Instantiates a new ticket type.
     * @param type the type
     * @param value the value
     */
    TicketType(String type, Integer value) {
      this.type = type;
      this.value = value;
    }

    /**
     * Gets the value.
     * @return the value
     */
    public Integer getValue() {
      return this.value;
    }

    /**
     * Gets the type.
     * @return the type
     */
    public String getType() {
      return this.type;
    }

    /**
     * Gets the ticket type.
     * @param type the type
     * @return the ticket type
     */
    public static String getTicketType(int type) {
      for (TicketType ticketType : TicketType.values()) {
        if (ticketType.value == type) {
          return ticketType.type;
        }
      }
      return "";
    }

    /**
     * Gets the ticket type.
     * @param type the type
     * @return the ticket type
     */
    public static TicketType getTicketType(String type) {
      for (TicketType ticketType : TicketType.values()) {
        if (ticketType.type.equals(type)) {
          return ticketType;
        }
      }
      return null;
    }
  }

  public enum ReturnPaymentType {
    ORIGINAL_PAYMENT_METHOD("Original Payment Method", 1),
    OTHRES("Others", 2),;

    String type;

    Integer value;

    /**
     * Instantiates a new return payment type.
     * @param type the type
     * @param value the value
     */
    ReturnPaymentType(String type, Integer value) {
      this.type = type;
      this.value = value;
    }

    /**
     * Gets the value.
     * @return the value
     */
    public Integer getValue() {
      return this.value;
    }

    /**
     * Gets the type.
     * @return the type
     */
    public String getType() {
      return this.type;
    }
  }


  public enum CreditOrDebit {
    CREDIT("Credit", 1),
    DEBIT("Debit", 2),
    //EVOUCHER("EVoucher",3)
    ;

    String type;

    Integer value;

    /**
     * Instantiates a new credit or debit.
     * @param type the type
     * @param value the value
     */
    CreditOrDebit(String type, Integer value) {
      this.type = type;
      this.value = value;
    }

    /**
     * Gets the value.
     * @return the value
     */
    public Integer getValue() {
      return this.value;
    }

    /**
     * Gets the type.
     * @return the type
     */
    public String getType() {
      return this.type;
    }

    /**
     * Gets the credit or debit type.
     * @param type the type
     * @return the credit or debit type
     */
    public static CreditOrDebit getCreditOrDebitType(int type) {
      for (CreditOrDebit searchType : CreditOrDebit.values()) {
        if (searchType.value == type) {
          return searchType;
        }
      }
      return null;
    }

  }

  public enum PatronSearchType {
    TRANSACTIONS("Transaction", 1),
    MONTH("Month", 2),;

    String type;

    Integer value;

    /**
     * Instantiates a new patron search type.
     * @param type the type
     * @param value the value
     */
    PatronSearchType(String type, Integer value) {
      this.type = type;
      this.value = value;
    }

    /**
     * Gets the value.
     * @return the value
     */
    public Integer getValue() {
      return this.value;
    }

    /**
     * Gets the type.
     * @return the type
     */
    public String getType() {
      return this.type;
    }

    /**
     * Gets the ticket type.
     * @param type the type
     * @return the ticket type
     */
    public static PatronSearchType getTicketType(int type) {
      for (PatronSearchType searchType : PatronSearchType.values()) {
        if (searchType.value == type) {
          return searchType;
        }
      }
      return null;
    }
  }

  public enum PatronTxnParams {
    PRN("PRN", 1),
    CC_DETAIL("CC_Detail", 2),
    CC_HOLDER("CC_Holder", 3),
    IMMEDIATE_DELIVERY_METHOD("Immediate_Delivery", 4),;

    String type;

    Integer value;

    /**
     * Instantiates a new patron txn params.
     * @param type the type
     * @param value the value
     */
    PatronTxnParams(String type, Integer value) {
      this.type = type;
      this.value = value;
    }

    /**
     * Gets the value.
     * @return the value
     */
    public Integer getValue() {
      return this.value;
    }

    /**
     * Gets the type.
     * @return the type
     */
    public String getType() {
      return this.type;
    }

    /**
     * Gets the patron txn params type.
     * @param type the type
     * @return the patron txn params type
     */
    public static PatronTxnParams getPatronTxnParamsType(int type) {
      for (PatronTxnParams searchType : PatronTxnParams.values()) {
        if (searchType.value == type) {
          return searchType;
        }
      }
      return null;
    }
  }


  public enum TransactionMessages {

    GENERAL_ERROR("GENERAL_ERROR", -1),
    APPROVED("APPROVED", 0),
    INVALID_AMOUNT("INVALID_AMOUNT", 4),
    INVALID_REQUEST("INVALID_REQUEST", 5),
    TOO_MANY_LINE_ITEMS("TOO_MANY_LINE_ITEMS", 10),
    CLIENT_TIMEOUT("CLIENT_TIMEOUT", 11),
    DECLINED("DECLINED", 12),
    INVALID_ACCOUNT_NUMBER("INVALID_ACCOUNT_NUMBER", 23),
    INVALID_EXPIRY_DATE("INVALID_EXPIRY_DATE", 24),
    INSUFFICIENT_FUNDS("INSUFFICIENT_FUNDS", 50),
    DUPLICATE_PNREF_FOUND("DUPLICATE_PNREF_FOUND", 54),
    PROCESSOR_TIMEOUT("PROCESSOR_TIMEOUT", 104),
    SERVER_TIMEOUT("SERVER_TIMEOUT", 109),
    GATEWAY_CON_ERROR("GATEWAY_CON_ERROR", 90),
    GATEWAY_OFFLINE("GATEWAY_OFFLINE", 91),
    GATEWAY_ERROR("GATEWAY_ERROR", 92),
    REQUIRED_ACCOUNT_LOOKUP("REQUIRED_ACCOUNT_LOOKUP", 93),
    PENDING("PENDING", 94),
    GATEWAY_TIMEOUT("GATEWAY_TIMEOUT", 95),
    GATEWAY_CONFIG_ERROR("GATEWAY_CONFIG_ERROR", 96),
    BLACKLISTED_CREDITCARDS_ERROR("error_blacklisted_creditcards", 123),
    BLACKLISTED_EMAILADDRESS_ERROR("error_blacklisted_emailsAddress", 124),
    NO_REVENUE_CENTRE_FOUND("NO_REVENUE_CENTRE_FOUND", 6);;

    String type;

    Integer value;

    /**
     * Instantiates a new transaction messages.
     * @param type the type
     * @param value the value
     */
    TransactionMessages(String type, Integer value) {
      this.type = type;
      this.value = value;
    }

    /**
     * Gets the value.
     * @return the value
     */
    public Integer getValue() {
      return this.value;
    }

    /**
     * Gets the type.
     * @return the type
     */
    public String getType() {
      return this.type;
    }

    /**
     * Gets the message.
     * @param value the value
     * @return the message
     */
    public static String getMessage(Integer value) {
      String returnMessage = GENERAL_ERROR.getType();
      if (value != null) {
        for (TransactionMessages message : TransactionMessages.values()) {
          if (message.value.equals(value)) {
            returnMessage = message.getType();
          }
        }
      }
      return returnMessage;
    }


    /**
     * This method is used to get the blacklisted credit cards error message.
     * @param value the value
     * @return BLACKLISTED_CREDITCARDS_ERROR message
     */
    public static String getBLCreditCardsMessage(Integer value) {
      String returnMessage = BLACKLISTED_CREDITCARDS_ERROR.getType();
      if (value != null) {
        for (TransactionMessages message : TransactionMessages.values()) {
          if (message.value.equals(value)) {
            returnMessage = message.getType();
          }
        }
      }
      return returnMessage;
    }

    /**
     * This method is used to get the blacklisted EMAILS address error message.
     * @param value the value
     * @return the bLEMAIL message
     */
    public static String getBLEMAILMessage(Integer value) {
      String returnMessage = BLACKLISTED_EMAILADDRESS_ERROR.getType();
      if (value != null) {
        for (TransactionMessages message : TransactionMessages.values()) {
          if (message.value.equals(value)) {
            returnMessage = message.getType();
          }
        }
      }
      return returnMessage;
    }

  }


  public enum TransactionCommitStatus {
    COMMITTED("Committed", 1),
    PENDING("Pending", 2),
    NOT_COMMITTED("Not Committed", 3);

    String type;

    Integer value;

    /**
     * Instantiates a new transaction commit status.
     * @param type the type
     * @param value the value
     */
    TransactionCommitStatus(String type, Integer value) {
      this.type = type;
      this.value = value;
    }

    /**
     * Gets the value.
     * @return the value
     */
    public Integer getValue() {
      return this.value;
    }

    /**
     * Gets the type.
     * @return the type
     */
    public String getType() {
      return this.type;
    }

    /**
     * Gets the message.
     * @param type the type
     * @return the message
     */
    public static String getMessage(int type) {
      for (TransactionMessages message : TransactionMessages.values()) {
        if (message.value == type) {
          return message.getType();
        }
      }
      return "";
    }

  }

  public class SalesCommitStatus implements Serializable {

    private static final long serialVersionUID = 793676669255000895L;

    private Long orderTxnId;

    private String orderId;

    private String message;

    private boolean orderStatus;

    private Integer paymentStatus;

    private String groupBookingLink; //group booking
    private String groupBookingExpiry;

    public SalesCommitStatus(Integer paymentStatus) {
      setPaymentStatus(paymentStatus);
    }

    /**
     * Instantiates a new sales commit status.
     * @param orderId the order id
     * @param message the message
     * @param orderStatus the order status
     */
    public SalesCommitStatus(String orderId, String message, boolean orderStatus) {
      this.setOrderId(orderId);
      this.setMessage(message);
      setOrderStatus(orderStatus);
    }

    /**
     * Instantiates a new sales commit status.
     * @param orderTxnId the transaction id of the order id
     * @param orderId the order id
     * @param message the message
     * @param orderStatus the order status
     */
    public SalesCommitStatus(Long orderTxnId, String orderId, String message, boolean orderStatus) {
      this.setOrderTxnId(orderTxnId);
      this.setOrderId(orderId);
      this.setMessage(message);
      setOrderStatus(orderStatus);
    }

    /**
     * Instantiates a new sales commit status with paymentStatus.
     */
    public SalesCommitStatus(String orderId, String message, boolean orderStatus,
                             Integer paymentStatus) {
      this.setOrderId(orderId);
      this.setMessage(message);
      setOrderStatus(orderStatus);
      setPaymentStatus(paymentStatus);
    }

    /**
     * Gets the order id.
     * @return the order id
     */
    public String getOrderId() {
      return orderId;
    }

    /**
     * Sets the order id.
     * @param orderId the new order id
     */
    public void setOrderId(String orderId) {
      this.orderId = orderId;
    }

    /**
     * Gets the message.
     * @return the message
     */
    public String getMessage() {
      return message;
    }

    /**
     * Sets the message.
     * @param message the new message
     */
    public void setMessage(String message) {
      this.message = message;
    }

    /**
     * Checks if is order status.
     * @return true, if is order status
     */
    public boolean isOrderStatus() {
      return orderStatus;
    }

    /**
     * Sets the order status.
     * @param orderStatus the new order status
     */
    public void setOrderStatus(boolean orderStatus) {
      this.orderStatus = orderStatus;
    }

    /**
     * @return Integer
     */
    public Integer getPaymentStatus() {
      return paymentStatus;
    }

    /**
     * @param paymentStatus
     */
    public void setPaymentStatus(Integer paymentStatus) {
      this.paymentStatus = paymentStatus;
    }


    /**
     * @return Long
     */
    public Long getOrderTxnId() {
      return orderTxnId;
    }

    /**
     * @param orderTxnId
     */
    public void setOrderTxnId(Long orderTxnId) {
      this.orderTxnId = orderTxnId;
    }

    public String getGroupBookingLink() {
      return groupBookingLink;
    }

    public void setGroupBookingLink(String groupBookingLink) {
      this.groupBookingLink = groupBookingLink;
    }

    public String getGroupBookingExpiry() {
      return groupBookingExpiry;
    }

    public void setGroupBookingExpiry(String groupBookingExpiry) {
      this.groupBookingExpiry = groupBookingExpiry;
    }

  }

  public enum TransactionType {

    PURCHASE("Purchase", 1, true),
    RETURN("Return", 2, false),
    REPLACE("Replace", 3, true),
    RESEND("Resend", 4, false),
    CHANGE_DELIVERY_METHOD("Delivery Method Change", 5, true),
    RESET("Reset", 6, false),
    INVALIDATE("Invalidate", 7, false),;

    String type;

    Integer value;

    boolean regenerate;

    /**
     * Instantiates a new transaction type.
     * @param type the type
     * @param value the value
     */
    TransactionType(String type, Integer value, boolean regenerate) {
      this.type = type;
      this.value = value;
      this.regenerate = regenerate;
    }


    /**
     * Gets the type.
     * @return the type
     */
    public String getType() {
      return this.type;
    }

    /**
     * Gets the value.
     * @return the value
     */
    public Integer getValue() {
      return this.value;
    }

    public boolean getRegenerate() {
      return this.regenerate;
    }

  }

  public enum TicketStatus {
    PRINTED("Printed", 1),
    BOUNCED("Bounced", 2),
    SENT("Sent", 3),
    COLLECTED("Collected", 4),
    DISPATCHED("Dispatched", 5),
    NOTPRINTED("Not Printed", 6),
    PRINTING("Printing", 7),
    NOTCOLLECTED("Not Collected", 8),
    REPLACED("Replaced", 9),
    INVALIDATED("Invalidated", 10);

    String type;

    Integer value;

    /**
     * Instantiates a new ticket status.
     * @param type the type
     * @param value the value
     */
    TicketStatus(String type, Integer value) {
      this.type = type;
      this.value = value;
    }

    /**
     * Gets the value.
     * @return the value
     */
    public Integer getValue() {
      return this.value;
    }

    /**
     * Gets the type.
     * @return the type
     */
    public String getType() {
      return this.type;
    }

    /**
     * Gets the ticket status.
     * @param type the type
     * @return the ticket status
     */
    public static String getTicketStatus(int type) {
      for (TicketStatus ticketStatus : TicketStatus.values()) {
        if (ticketStatus.value == type) {
          return ticketStatus.type;
        }
      }
      return "";
    }

    /**
     * Gets the ticket status.
     * @param type the type
     * @return the ticket status
     */
    public static TicketStatus getTicketStatus(String type) {
      for (TicketStatus ticketStatus : TicketStatus.values()) {
        if (ticketStatus.type.equals(type)) {
          return ticketStatus;
        }
      }
      return null;
    }
  }

  public enum BatchTicketStatus {
    COMPLETED("Completed", 1),
    INCOMPLETE("Incomplete", 2),;

    String type;

    Integer value;

    /**
     * Instantiates a new batch ticket status.
     * @param type the type
     * @param value the value
     */
    BatchTicketStatus(String type, Integer value) {
      this.type = type;
      this.value = value;
    }

    /**
     * Gets the value.
     * @return the value
     */
    public Integer getValue() {
      return this.value;
    }

    /**
     * Gets the type.
     * @return the type
     */
    public String getType() {
      return this.type;
    }

    /**
     * Gets the batch ticket status.
     * @param type the type
     * @return the batch ticket status
     */
    public static String getBatchTicketStatus(int type) {
      for (BatchTicketStatus batchTicketStatus : BatchTicketStatus.values()) {
        if (batchTicketStatus.value == type) {
          return batchTicketStatus.type;
        }
      }
      return "";
    }

    /**
     * Gets the batch ticket status.
     * @param type the type
     * @return the batch ticket status
     */
    public static BatchTicketStatus getBatchTicketStatus(String type) {
      for (BatchTicketStatus batchTicketStatus : BatchTicketStatus.values()) {
        if (batchTicketStatus.type.equals(type)) {
          return batchTicketStatus;
        }
      }
      return null;
    }
  }

  public enum Ticketability {
    YES("Yes", new Long(1)),
    NO("No", new Long(0));

    private String type;
    private Long value;

    Ticketability(String type, Long value) {
      this.type = type;
      this.value = value;
    }

    public String getType() {
      return this.type;
    }

    public Long getValue() {
      return this.value;
    }

  }

  //added by truongbv
  public enum Regenerate {
    YES("Yes", "Y"),
    NO("No", "N");

    private String type;
    private String value;

    Regenerate(String type, String value) {
      this.type = type;
      this.value = value;
    }

    public String getType() {
      return this.type;
    }

    public String getValue() {
      return this.value;
    }

  }

}
