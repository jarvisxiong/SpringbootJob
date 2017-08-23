package com.stixcloud.paymentgateway.api;

public interface TelemoneyConstants {

  // Currencies
  public static final String CURRENCY_SGD = "SGD"; // Singapore Dollars
  public static final String CURRENCY_MYR = "MYR"; // Malaysian Ringgit
  public static final String CURRENCY_USD = "USD"; // US Dollar
  public static final String CURRENCY_AUD = "AUD"; // Australian Dollar
  public static final String CURRENCY_JPY = "JPY"; // Japanese Yen
  public static final String CURRENCY_THB = "THB"; // Thai Baht
  public static final String CURRENCY_CNY = "CNY"; // Chinese Yuan
  public static final String CURRENCY_BND = "BND"; // Brunei Dollars
  public static final String CURRENCY_VND = "VND"; // Vietnam Dong

  // Payment Types
  public static final String PT_AMEX = "5";
  public static final String PT_DINERS = "22";
  //public static final String PT_JCB = "23";
  public static final String PT_CUP = "25";

  // Added for Bugs #2028: Unable to return Amex transactions
  public static final String RECURRENT_INIT = "INIT";

  // Payment gateway constants
  public static final String PG_LABEL_PURCHASE_URL = "sales.url";
  public static final String PG_LABEL_REFUND_URL = "refund.url";
  public static final String PG_LABEL_VOID_URL = "void.url";

  public static final String PG_LABEL_PURCHASE_CON_TIMEOUT = "sales.con.timeout";
  public static final String PG_LABEL_REFUND_CON_TIMEOUT = "refund.con.timeout";
  public static final String PG_LABEL_VOID_CON_TIMEOUT = "void.con.timeout";

  public static final String ACTION_SALE = "sale";
  public static final String ACTION_REFUND = "refund";
  public static final String ACTION_VOID = "void";

  // Sequence ids for hash sequence for generating signature
  public static final String SQ_ID_MID = "%mid%";
  public static final String SQ_ID_PAYTYPE = "%paytype%";
  public static final String SQ_ID_REF = "%ref%";
  public static final String SQ_ID_AMT = "%amt%";
  public static final String SQ_ID_CUR = "%cur%";

  // Revenue Centre constants
  public static final String RC_LABEL_POS_ID = "pos.id";
  public static final String RC_LABEL_MERCHANT_ID = "merchant.id";
  public static final String RC_LABEL_REFUND_AUTH_CODE = "refund.auth.code";
  public static final String RC_LABEL_PROCESS_MODE = "process.mode";

  public static final String RC_VALUE_DEFAULT_POS_ID = "default";
  public static final String RC_VALUE_MOTO_PROCESS_MODE = "MOTO";
  public static final String RC_VALUE_ECOMMERCE_PROCESS_MODE = "ECOMMERCE";

  public static final String PG_LABEL_NOTIFY_EMAIL_RECEP = "ne.recipient";
  public static final String PG_LABEL_NOTIFY_EMAIL_SENDER = "ne.sender";
  public static final String PG_LABEL_NOTIFY_EMAIL_TPL_FOLDER_PATH = "ne.template.folder.path";
  public static final String PG_LABEL_NOTIFY_EMAIL_SUBJECT = "ne.subject";

  public static final String COM_FAILURE_EMAIL_TPL_NAME = "comFailureEmailTpl.vm";

  //Notification email contents keys
  public static final String NE_LABEL_PG_NAME = "pgName";
  public static final String NE_LABEL_PG_ID = "pgId";
  public static final String NE_LABEL_PG_MERCHANT_ID = "pgMerchantId";
  public static final String NE_LABEL_TXN_ID = "txnId";
  public static final String NE_LABEL_TXN_DATE = "txnDate";
  public static final String NE_LABEL_TXN_TYPE = "txnType";
  public static final String NE_LABEL_TXN_AMOUNT = "txnAmount";


  public static int DEFAULT_CONNECTION_TIMEOUT = 60000;

} // end public interface ITelemoneyConstants