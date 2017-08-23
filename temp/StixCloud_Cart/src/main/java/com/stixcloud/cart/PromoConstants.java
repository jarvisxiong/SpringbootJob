package com.stixcloud.cart;

public class PromoConstants {
  
  public static final int PROMO_CODE_STATE_ACTIVE = 1;
  
  public static final int PROMO_PWD_STATE_ACTIVE = 1;
  
  public static final int PROMO_PWD_VALIDATION_VALID = 0;
  
  public static final int PROMO_PWD_VALIDATION_INACTIVE = 1;
  
  public static final int PROMO_PWD_VALIDATION_USAGE_EXCEEDED = 2;
  
  public static final int PROMO_PWD_VALIDATION_INVALID = 3;
  
  /**
   * Price class is configured in private link with exclusive status is T
   */
  public static final String PROMO_CODE_EXCLUSIVE = "T";
  /**
   * Price class is configured in private link with exclusive status is F
   */
  public static final String PROMO_CODE_UNEXCLUSIVE = "F";
  
  public static final int PROMO_PWD_UNLIMITED_USAGE = 0;

}
