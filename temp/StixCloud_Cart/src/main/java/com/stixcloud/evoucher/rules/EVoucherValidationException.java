package com.stixcloud.evoucher.rules;

/**
 * Created by dbthan on 13/10/2016.
 */

public class EVoucherValidationException extends Exception {

  private static final long serialVersionUID = -1262480780385675913L;

  private String invalidValidationConstant;

  public EVoucherValidationException(String message) {
    super(message);
  }

  public EVoucherValidationException(String message, Throwable cause) {
    super(message, cause);
  }

  public EVoucherValidationException(String message, String invalidValidationConstant) {
    super(message);
    this.invalidValidationConstant = invalidValidationConstant;
  }

  /**
   * @return the invalidValidationConstant
   */
  public String getInvalidValidationConstant() {
    return invalidValidationConstant;
  }

  /**
   * @param invalidValidationConstant the invalidValidationConstant to set
   */
  public void setInvalidValidationConstant(String invalidValidationConstant) {
    this.invalidValidationConstant = invalidValidationConstant;
  }
}
