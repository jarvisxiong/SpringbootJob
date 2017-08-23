package com.stixcloud.evoucher.rules;

import org.easyrules.api.Rule;
import org.easyrules.api.RuleListener;

import java.lang.reflect.InvocationTargetException;

/**
 * Created by dbthan on 13/10/2016.
 */

public class EVoucherRulesListener implements RuleListener {

  private EVoucherValidationException eVoucherException;
  private boolean hasError;

  public EVoucherRulesListener() {
    hasError = false;
  }

  /**
   * @return the eVoucherException
   */
  public EVoucherValidationException geteVoucherException() {
    return eVoucherException;
  }

  public boolean hasError() {
    return hasError;
  }


  public void setHasError(boolean hasError) {
    this.hasError = hasError;
  }

  @Override
  public boolean beforeEvaluate(Rule arg0) {
    return true;
  }

  @Override
  public void beforeExecute(Rule arg0) {
  }

  @Override
  public void onFailure(Rule rule, Exception exception) {
    EVoucherValidationException targetException =
        (EVoucherValidationException) ((InvocationTargetException) exception).getTargetException();
    this.eVoucherException = targetException;
    hasError = true;
  }

  @Override
  public void onSuccess(Rule arg0) {
  }

}
