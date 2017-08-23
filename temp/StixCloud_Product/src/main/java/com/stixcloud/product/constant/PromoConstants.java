package com.stixcloud.product.constant;

public interface PromoConstants {
  /**
   * No private link is configured for the internet content
   */
  public static final int PROMO_CODE_UNCONFIGURED = 0;
  /**
   * 1. There is a private link configured for the internet content <br />
   * 2. Promo code is correct <br />
   * 3. It is in promo time
   */
  public static final int PROMO_CODE_VALID = 1;
  /**
   * 1. There is a private link configured for the internet content <br />
   * 2. Promo code is incorrect <br />
   * 3. It is in promo time
   */
  public static final int PROMO_CODE_INVALID = 2;
  /**
   * 1. There is a private link configured for the internet content <br />
   * 2. It is not in promo time
   */
  public static final int PROMO_CODE_UNAVAILABLE = 3;

  /**
   * Price class is configured in private link with exclusive status is T
   */
  public static final String EXCLUSIVE = "T";
  /**
   * Price class is configured in private link with exclusive status is F
   */
  public static final String UNEXCLUSIVE = "F";
  
}
