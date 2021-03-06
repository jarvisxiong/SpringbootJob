package com.stixcloud.evoucher.constant;

/**
 * Created by dbthan on 13/10/2016.
 */

public enum EVoucherEnum {

  INACTIVE("0"), ACTIVE("1"), REDEEMED("2"),

  VALIDATION_RULE_USAGE_WITH_OTHER_VOUCHERS_FAIL("VALIDATION_RULE_USAGE_WITH_OTHER_VOUCHERS_FAIL"),
  VALIDATION_RULE_MAX_QTY_FAIL("VALIDATION_RULE_MAX_QTY_FAIL"),
  VALIDATION_RULE_ORG_FAIL("VALIDATION_RULE_ORG_FAIL"),
  VALIDATION_RULE_VENUE_FAIL("VALIDATION_RULE_VENUE_FAIL"),
  VALIDATION_RULE_CC_FAIL("VALIDATION_RULE_CC_FAIL"),
  VALIDATION_RULE_ALLOWED_PM_FAIL("VALIDATION_RULE_ALLOWED_PM_FAIL"),
  VALIDATION_RULE_REDEEM_RESTRICTIONS_WITHIN_FAIL(
      "VALIDATION_RULE_REDEEM_RESTRICTIONS_WITHIN_FAIL"),
  VALIDATION_RULE_REDEEM_RESTRICTIONS_OUTSIDE_FAIL(
      "VALIDATION_RULE_REDEEM_RESTRICTIONS_OUTSIDE_FAIL"),
  VALIDATION_RULE_REDEEM_RESTRICTIONS_ALL_FAIL("VALIDATION_RULE_REDEEM_RESTRICTIONS_ALL_FAIL"),
  VALIDATION_VIOLATE_OWNERSHIP("VALIDATION_VIOLATE_OWNERSHIP"),
  VALIDATION_VIOLATE_MIX_CARD("VALIDATION_VIOLATE_MIX_CARD"),
  VALIDATION_RULE_PASS("VALIDATION_RULE_PASS"),
  VALIDATION_RULE_FAILED("VALIDATION_RULE_FAILED"),
  VALIDATION_BASIC_FAIL("VALIDATION_BASIC_FAIL"),
  VALIDATION_BASIC_PASS("VALIDATION_BASIC_PASS"),
  VALIDATION_BASIC_EXPIRED("VALIDATION_BASIC_EXPIRED"),
  VALIDATION_BASIC_INACTIVE("VALIDATION_BASIC_INACTIVE"),
  VALIDATION_BASIC_REDEEMED("VALIDATION_BASIC_REDEEMED"),
  VALIDATION_BASIC_DUPLICATE("VALIDATION_BASIC_DUPLICATE"),

  ORGANIZATION_RULE("OrganizationRule"),
  BASIC_RULE("BasicRule"),
  MAX_QUANTITY_RULE("MaxQuantityRule"),
  MEMBERSHIP_PATRON_RULE("MembershipPatronRule"),
  MULTI_USAGE_RULE("MultiUsageRule"),
  VENUE_RULE("VenueRule"),;

  private String value;

  EVoucherEnum(String value) {
    this.value = value;
  }

  public String value() {
    return value;
  }
}
