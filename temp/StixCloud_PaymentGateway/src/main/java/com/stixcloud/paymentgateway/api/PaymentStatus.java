package com.stixcloud.paymentgateway.api;

public class PaymentStatus {

  public static final int GENERAL_ERROR = -1;

  public static final int APPROVED = 0;
  public static final int INVALID_AMOUNT = 4;

  // Added by Pei Yong 20100706
  public static final int INVALID_REQUEST = 5;

  public static final int NO_REVENUE_CENTRE_FOUND = 6;

  public static final int TOO_MANY_LINE_ITEMS = 10;

  public static final int CLIENT_TIMEOUT = 11;

  public static final int DECLINED = 12;

  public static final int INVALID_ACCOUNT_NUMBER = 23;
  public static final int INVALID_EXPIRY_DATE = 24;

  public static final int INSUFFICIENT_FUNDS = 50;

  public static final int DUPLICATE_PNREF_FOUND = 54;

  public static final int PROCESSOR_TIMEOUT = 104;
  public static final int SERVER_TIMEOUT = 109;

  // Added by Pei Yong 20100408
  public static final int GATEWAY_CON_ERROR = 90;
  public static final int GATEWAY_OFFLINE = 91;
  public static final int GATEWAY_ERROR = 92;
  public static final int REQUIRED_ACCOUNT_LOOKUP = 93;

  // Added by Jac 20130411
  public static final int PENDING = 94;

  public static final int GATEWAY_TIMEOUT = 95;
  public static final int GATEWAY_CONFIG_ERROR = 96;

  // Added by babu  20130622 for BlackListed credit card and EMAIL
  public static final int BLACKLISTED_CREDITCARDS_ERROR = 123;
  public static final int BLACKLISTED_EMAILADDRESS_ERROR = 124;

  private int status = -1;
  public static final int APPROVED_EMAILSENDFAIL = 7;

  //added by Devin for EVoucher Enhancement
  public static final int EVOUCHER_VALIDATE_REGEX_FAIL = 125;
  public static final int CREDIT_CARD_DISC_NO_EVOUCHER = 126;


  /**
   * Instantiates a new payment status.
   * @param status the status
   * @param msg the msg
   */
  public PaymentStatus(int status, String msg) {
    this.status = status;
  }

  /**
   * Success.
   * @return true, if successful
   */
  public boolean success() {
    return (status == APPROVED);
  }

}
