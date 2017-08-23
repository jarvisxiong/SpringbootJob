/**
 * Created for EVoucher enhancement
 * @author devinlim
 */

package com.stixcloud.cart;

public enum EVoucherConstants {

  //EVOUCHER_TYPE
  SISTIC("SISTIC_E_VOUCHER"),
  SHOW_RWS("SHOW_VOUCHER_RWS"),
  SRT("SRT_E_VOUCHER"),
  OCBC("OCBC_E_VOUCHER"),
  DBS("DBS_E_VOUCHER"),
  //Membership Tier - Membership Sales Via Box Office - added by webster
  ESPLANADE("ESPLANADE_E_VOUCHER"),
  NATIONALGALLERY("NGS_E_VOUCHER"),
  NATIONALGALLERY_GIFTVOUCHER("NGS_GIFT_VOUCHER"),

  //STATUS
  INACTIVE("0"),
  ACTIVE("1"),
  REDEEMED("2"),

  //ISVOUCHER PAYMENT
  ISEVOUCHERPAYMENT("TRUE"),
  NOTEVOUCHERPAYMENT("FALSE"),

  //misc
  VOUCHER_ID_LENGTH("8"),
  OCBC_VOUCHER_PREFIX("BC"),
  OCBC_TXN_TYPE_REDEEM("REDEEM"),

  APP_CONFIG_TYPE("EVOUCHER_CREDIT_CARD"),
  EMPTY(""),
  DEFAULT_QTY("1"),
  STRING_SPLIT(";"),
  STRING_SPLIT_BE(","),

  WITHIN("RESTRICT_WITHIN"),
  OUTSIDE("RESTRICT_OUTSIDE"),
  RESTRICT_ALL("RESTRICT_ALL"),
  RESTRICT_NONE("RESTRICT_NONE"),

  BOOKING_ENGINE_DATE_FORMAT("dd-MM-yyyy HH:mm"),

  //template attributes
  QTY("evoucher.qty"),
  REDEEM_VALUE("evoucher.redeemvalue"),
  TYPE("evoucher.type"),
  DATE_FORMAT("evoucher.date.format"),
  START_DATE("evoucher.date.date_start"),
  END_DATE("evoucher.date.date_end"),
  WHO_CREATED("evoucher.whocreated"),
  E_VOUCHER_PREFIX("evoucher.prefix"),

  E_VOUCHER_PAYMENT_TYPE("eVoucher"),

  ORGANIZATION("ORGANIZATION"),
  VENUE("VENUE"),
  USAGE_WITH_OTHER_VOUCHER("USAGE_WITH_OTHER_VOUCHER"),
  MAXIMUM_QTY("MAXIMUM_QTY"),
  CREDIT_CARD("CREDIT_CARD"),
  ALLOWED_PAYMENT_METHOD("ALLOWED_PAYMENT_METHOD"),
  REDEEM_RESTRICTIONS("REDEEM_RESTRICTIONS"),
  MEMBERSHIP_PATRON_ACCT_NO("MEMBERSHIP_PATRON_ACCT_NO"),

  //rule validation attributes
  VALIDATION_BASIC_FAIL(
      "Generic basic validation Failure. Please Contact the SISTIC Helpdesk for more Info"),
  VALIDATION_BASIC_PASS("Pass"),
  VALIDATION_BASIC_EXPIRED("eVoucher has expired."),
  VALIDATION_BASIC_INACTIVE("eVoucher does not exist."),
  VALIDATION_BASIC_NOT_FOUND("eVoucher does not exist."),
  VALIDATION_BASIC_REDEEMED("eVoucher has already been redeemed, please use another eVoucher."),
  VALIDATION_BASIC_DUPLICATE(
      "eVoucher duplicates is found. Selected eVoucher has already been redeemed and added into the shopping cart."),

  VALIDATION_BASIC_REVALIDATE("Please re-apply the EVoucher for validation."),

  VALIDATION_RULE_FAIL(
      "Generic validation Failure. Please Contact the SISTIC Helpdesk for more Info"),
  VALIDATION_RULE_PASS("Pass"),

  VALIDATION_RULE_EX_NUMBERFORMAT("Invalid number format."),
  VALIDATION_RULE_EX_NULLPOINTER("eVoucher is null."),

  VALIDATION_RULE_ORG_FAIL(
      "eVoucher cannot be used for this promoter's event, please use the correct eVoucher for the selected event."),
  VALIDATION_RULE_VENUE_FAIL(
      "eVoucher cannot be used for this event's venue, please use the correct eVoucher for the selected event."),
  VALIDATION_RULE_USAGE_WITH_OTHER_VOUCHERS_FAIL(
      "eVoucher cannot be used with other types of eVouchers, please use the correct eVoucher for the selected event."),
  VALIDATION_RULE_MAX_QTY_FAIL(
      "Maximum redeem quantity for this type of eVoucher has been reached, please use the correct amount of eVoucher for the selected event."),
  VALIDATION_RULE_CC_FAIL(
      "eVoucher cannot be used with this Credit Card, please use the correct eVoucher for the selected event."),
  VALIDATION_RULE_ALLOWED_PM_FAIL(
      "eVoucher cannot be used with this Credit Card, Please use the correct payment method to complete the purchase."),
  VALIDATION_RULE_REDEEM_RESTRICTIONS_WITHIN_FAIL(
      "eVoucher cannot be used, E-Voucher Payment method is not configured in Event/Product and User Profile. Please use a different eVoucher."),
  VALIDATION_RULE_REDEEM_RESTRICTIONS_OUTSIDE_FAIL(
      "eVoucher cannot be used, Only E-Voucher Payment method that is not found in Event/Product and User Profile can be used. Please use a different eVoucher."),
  VALIDATION_RULE_REDEEM_RESTRICTIONS_ALL_FAIL(
      "eVoucher cannot be used or redeemed due the Restrict-All rules applied to it. Please check with your IT guy/ Manager regarding this issue."),

  //Membership Tier - Membership E-Voucher - added by webster
  VALIDATION_VIOLATE_OWNERSHIP("This eVoucher does not belong to this patron."),

  VALIDATE_AT_CHECKOUTCONFIRMATION("checkoutConfirmation"),
  VALIDATE_AT_CHECKOUT("checkout"),
  VALIDATE_AT_ALL("all"),
  VALIDATION_VIOLATE_MIX_CARD("White card members can't use eVoucher to redeem non esplanade show"),
  PROMOTER_ESPLANADE("The Esplanade Co Ltd"),;

  private String value;

  EVoucherConstants(String value) {
    this.value = value;
  }

  public String value() {
    return value;
  }

}
