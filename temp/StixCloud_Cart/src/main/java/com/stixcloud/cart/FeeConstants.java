package com.stixcloud.cart;


import javax.money.MonetaryAmount;

public class FeeConstants {

  public static final Integer FIXED_CHARGE_TYPE = 1;
  public static final Integer PERCENTAGE_CHARGE_TYPE = 2;
  public static final Integer USE_PRODUCT_PRICE = 1;
  public static final Integer USE_PRODUCT_STANDARD_PRICE = 0;
  public static final Integer STRIP_TAX_STATUS = 1;
  public static final Integer DO_NOT_STRIP_TAX_STATUS = 0;
  public static final Integer FLAT_RULE_TYPE = 1;
  public static final Integer RANGE_RULE_TYPE = 2;
  public static final Integer STANDARD_FEE_RULE_TABLE_CATEGORY = 1;
  public static final Integer EVENT_SPECIFIC_FEE_RULE_TABLE_CATEGORY = 2;
  public static final Integer PRODUCT_FEE_RULE_TABLE_TYPE = 1;
  public static final Integer TRANSACTION_FEE_RULE_TABLE_TYPE = 2;
  //	public static final Integer FEE_RULE_SEQUENCE_STEP = 1024;
  public static final Integer FEE_RULE_SEQUENCE_STEP = 48;
  public static final Integer AUTO_INSERT_FEE_RULE_SEQUENCE_STEP = 12;
  public static final Integer FEE_RULE_SEQUENCE_START_NO = 1024;
  public static final Integer MIN_FEE_RULE_SEQUENCE_STEP = 24;
  public static final String FEE_RULE_ALL_KEYWORD = "ALL";
  public static final Integer FEE_RULE_SEQUENCE_INITIAL_NO = 1;

  public static final long ATTR_ABLE_WAIVE_BOOKINGFEE = 134217728;

  public enum FeeClassChargeType {
    INSIDE_CHARGE_TYPE(1),
    OUTSIDE_CHARGE_TYPE(2);

    private int value;

    FeeClassChargeType(int value) {
      this.value = value;
    }

    public int getValue() {
      return value;
    }
  }

  public enum LevyBy {

    ADDRESS("Address", 1),
    OUTLET("Outlet", 2),
    SHOW("Show", 3),
    PER_TICKET("Per Ticket", 4),
    TRANSACTION("Transaction", 5);

    String name;

    int value;

    /**
     * Instantiates a new levy by.
     * @param name the name
     * @param value the value
     */
    LevyBy(String name, int value) {
      this.name = name;
      this.value = value;
    }

    /**
     * Gets the name.
     * @return the name
     */
    public String getName() {
      return name;
    }

    /**
     * Gets the value.
     * @return the value
     */
    public int getValue() {
      return value;
    }

    /**
     * Gets the levy by name.
     * @param name the name
     * @return the levy by name
     */
    public static LevyBy getLevyByName(String name) {
      for (LevyBy levyBy : LevyBy.values()) {
        if (levyBy.getName().equalsIgnoreCase(name)) {
          return levyBy;
        }
      }
      return null;
    }

  }

  public enum FeeChargeType {

    INSIDE_CHARGE("Inside Charge", 1),
    OUTSIDE_CHARGE("Outside Charge", 2),;

    String name;

    int value;

    /**
     * Instantiates a new fee charge type.
     * @param name the name
     * @param value the value
     */
    FeeChargeType(String name, int value) {
      this.name = name;
      this.value = value;
    }

    /**
     * Gets the name.
     * @return the name
     */
    public String getName() {
      return name;
    }

    /**
     * Gets the value.
     * @return the value
     */
    public int getValue() {
      return value;
    }

    /**
     * Gets the fee charge type.
     * @param value the value
     * @return the fee charge type
     */
    public static FeeChargeType getFeeChargeType(int value) {
      for (FeeChargeType feeChargeType : FeeChargeType.values()) {
        if (feeChargeType.getValue() == value) {
          return feeChargeType;
        }
      }
      return null;
    }

  }

  public enum FeeCategory {

    FLAT_BASED("Flat Based", 1),
    DELIVERY_BASED("Delivery Based", 2),
    PAYMENT_BASED("Payment Based", 3),
    TAX_BASED("Tax Based", 4);

    String name;
    int value;

    /**
     * Instantiates a new fee category.
     * @param name the name
     * @param value the value
     */
    FeeCategory(String name, int value) {
      this.name = name;
      this.value = value;
    }

    /**
     * Gets the name.
     * @return the name
     */
    public String getName() {
      return name;
    }

    /**
     * Gets the value.
     * @return the value
     */
    public int getValue() {
      return value;
    }
  }

  public enum FeeChargeRuleAttribute {

    TICKET_PRICE("Ticket Price", 1),
    TOTAL_TICKET_VALUE("Total Ticket Value", 2),
    NO_OF_TICKETS_PER_TRANSACTION("No of Tickets Per Transaction", 3),
    PRODUCT_WEIGHT("Product Weight", 4),
    TOTAL_TRANSACTION_VALUE("Total Transaction Value", 5),
    TOTAL_PRODUCT_WEIGHT("Total Product Weight", 6),;

    String name;

    int value;

    /**
     * Instantiates a new fee charge rule attribute.
     * @param name the name
     * @param value the value
     */
    FeeChargeRuleAttribute(String name, int value) {
      this.name = name;
      this.value = value;
    }

    /**
     * Gets the name.
     * @return the name
     */
    public String getName() {
      return name;
    }

    /**
     * Gets the value.
     * @return the value
     */
    public int getValue() {
      return value;
    }
  }

  public enum RuleType {

    FLAT("Flat", 1),
    RANGE("Range", 2),;

    String name;

    int value;

    /**
     * Instantiates a new rule type.
     * @param name the name
     * @param value the value
     */
    RuleType(String name, int value) {
      this.name = name;
      this.value = value;
    }

    /**
     * Gets the name.
     * @return the name
     */
    public String getName() {
      return name;
    }

    /**
     * Gets the value.
     * @return the value
     */
    public int getValue() {
      return value;
    }
  }

  public enum ChargeType {

    FIXED("Fixed", 1),
    PERCENTAGE("Percentage", 2),;

    String name;

    int value;

    /**
     * Instantiates a new charge type.
     * @param name the name
     * @param value the value
     */
    ChargeType(String name, int value) {
      this.name = name;
      this.value = value;
    }

    /**
     * Gets the name.
     * @return the name
     */
    public String getName() {
      return name;
    }

    /**
     * Gets the value.
     * @return the value
     */
    public int getValue() {
      return value;
    }
  }

  public enum ProductPrice {

    PRODUCT_UNIT_PRICE("Product Unit Price", 1),
    STANDARD_PRICE("Standard Price", 0);

    String name;

    int value;

    /**
     * Instantiates a new product price.
     * @param name the name
     * @param value the value
     */
    ProductPrice(String name, int value) {
      this.name = name;
      this.value = value;
    }

    /**
     * Gets the name.
     * @return the name
     */
    public String getName() {
      return name;
    }

    /**
     * Gets the value.
     * @return the value
     */
    public int getValue() {
      return value;
    }
  }

  public enum TaxStatus {

    STRIP_TAX("Strip Tax", 1),
    DO_NOT_STRIP_TAX("Do Not Strip Tax", 0),
    INCLUDE_TAX("Include Tax", 1),
    EXCLUDE_TAX("Exclude Tax", 2),;


    String name;

    int value;

    /**
     * Instantiates a new tax status.
     * @param name the name
     * @param value the value
     */
    TaxStatus(String name, int value) {
      this.name = name;
      this.value = value;
    }

    /**
     * Gets the name.
     * @return the name
     */
    public String getName() {
      return name;
    }

    /**
     * Gets the value.
     * @return the value
     */
    public int getValue() {
      return value;
    }
  }

  public enum TicketPrice {

    INCLUDE_TICKET_PRICE("Include Ticket Price", 1),
    ExCLUDE_TICKET_PRICE("Exclude Ticket Price", 0);

    String name;

    int value;

    /**
     * Instantiates a new ticket price.
     * @param name the name
     * @param value the value
     */
    TicketPrice(String name, int value) {
      this.name = name;
      this.value = value;
    }

    /**
     * Gets the name.
     * @return the name
     */
    public String getName() {
      return name;
    }

    /**
     * Gets the value.
     * @return the value
     */
    public int getValue() {
      return value;
    }
  }

  public class ChargedValue {

    RuleType rulteType;

    MonetaryAmount chargedValue;

    Double parameterValue;

    /**
     * Instantiates a new charged value.
     * @param ruleType the rule type
     * @param chargedValue the charged value
     */
    public ChargedValue(RuleType ruleType, MonetaryAmount chargedValue) {
      this.rulteType = ruleType;
      this.chargedValue = chargedValue;
    }

    /**
     * Instantiates a new charged value.
     * @param ruleType the rule type
     * @param chargedValue the charged value
     * @param parameterValue the parameter value
     */
    public ChargedValue(RuleType ruleType, MonetaryAmount chargedValue, Double parameterValue) {
      this(ruleType, chargedValue);
      this.parameterValue = parameterValue;
    }

    /**
     * Gets the rule type.
     * @return the rule type
     */
    public RuleType getRuleType() {
      return this.rulteType;
    }

    /**
     * Gets the charged value.
     * @return the charged value
     */
    public MonetaryAmount getChargedValue() {
      return this.chargedValue;
    }

    /**
     * Gets the parameter
     * @return Double
     */
    public Double getParameterValue() {
      return this.parameterValue;
    }
  }

  public enum PaymentMode {

    MOTO("Moto", 1),
    RETAIL("Retail", 2),;

    String name;

    int value;

    /**
     * Instantiates a new payment mode.
     * @param name the name
     * @param value the value
     */
    PaymentMode(String name, int value) {
      this.name = name;
      this.value = value;
    }

    /**
     * Gets the name.
     * @return the name
     */
    public String getName() {
      return name;
    }

    /**
     * Gets the value.
     * @return the value
     */
    public int getValue() {
      return value;
    }

    /**
     * Gets the mode by name.
     * @param name the name
     * @return the mode by name
     */
    public static PaymentMode getModeByName(String name) {
      for (PaymentMode paymentMode : PaymentMode.values()) {
        if (paymentMode.getName().equalsIgnoreCase(name)) {
          return paymentMode;
        }
      }
      return null;
    }
  }

}
