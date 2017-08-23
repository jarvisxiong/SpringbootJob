package com.stixcloud.paymentgateway.api;

/**
 * This interface contains all the constants used by
 * Sinopay in their payment messages.cupv
 * @author chongye
 */
public interface CupConstants {

  // Payment gateway configuration parameters label
  String PG_LABEL_PURCHASE_URL = "sales.url";
  String PG_LABEL_REFUND_URL = "refund.url";
  String PG_LABEL_QUERY_URL = "query.url";
  String PG_LABEL_FRONTEND_URL = "frontend.url";
  String PG_LABEL_BACKEND_URL = "backend.url";

  String PG_LABEL_PURCHASE_CON_TIMEOUT = "sales.con.timeout";
  String PG_LABEL_REFUND_CON_TIMEOUT = "refund.con.timeout";

  String PG_LABEL_CUP_VERSION = "cup.version";
  String PG_LABEL_CUP_ENCODE = "cup.encode";
  String PG_LABEL_CUP_SIGN_METHOD = "cup.sign.method";
  String PG_LABEL_MERCHANT_NAME = "merchant.name";

  String PG_LABEL_NOTIFY_EMAIL_RECEP = "ne.recipient";
  String PG_LABEL_NOTIFY_EMAIL_SENDER = "ne.sender";
  String PG_LABEL_NOTIFY_EMAIL_TPL_FOLDER_PATH = "ne.template.folder.path";
  String PG_LABEL_NOTIFY_EMAIL_SUBJECT = "ne.subject";

  // Notification email contents keys
  String NE_LABEL_PG_NAME = "pgName";
  String NE_LABEL_PG_ID = "pgId";
  String NE_LABEL_PG_MERCHANT_ID = "pgMerchantId";
  String NE_LABEL_TXN_ID = "txnId";
  String NE_LABEL_TXN_DATE = "txnDate";
  String NE_LABEL_TXN_TYPE = "txnType";
  String NE_LABEL_TXN_AMOUNT = "txnAmount";


  // Revenue center configuration parameters label
  String RC_LABEL_MERCHANT_ID = "merchant.id";
  String RC_LABEL_MERCHANT_USER_ID = "merchant.user.id";
  String RC_LABEL_MERCHANT_PASSWORD = "merchant.password";
  String RC_LABEL_POS_ID = "pos.id";
  String RC_VALUE_DEFAULT_POS_ID = "default";
  String COM_FAILURE_EMAIL_TPL_NAME = "comFailureEmailTpl.vm";

  // Action Type
  String ACTION_PURCHASE = "01";
  String ACTION_REFUND = "04";

  int DEFAULT_CONNECTION_TIMEOUT = 90000;

} // end public interface CupConstants