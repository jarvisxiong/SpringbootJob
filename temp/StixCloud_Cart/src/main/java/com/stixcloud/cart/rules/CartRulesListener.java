package com.stixcloud.cart.rules;

import com.stixcloud.cart.CartException;
import org.easyrules.api.Rule;
import org.easyrules.api.RuleListener;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by chongye on 4/10/2016.
 */
public class CartRulesListener implements RuleListener {
  private Map<Rule, ? super CartException> failureMessageMap;
  private boolean hasError;

  public CartRulesListener() {
    failureMessageMap = new HashMap<>();
    hasError = false;
  }

  public Map<Rule, ? super CartException> getFailureMessageMap() {
    return failureMessageMap;
  }

  public void setFailureMessageMap(Map<Rule, ? super CartException> failureMessageMap) {
    this.failureMessageMap = failureMessageMap;
  }

  public boolean hasError() {
    return hasError;
  }

  public void setHasError(boolean hasError) {
    this.hasError = hasError;
  }

  @Override
  public boolean beforeEvaluate(Rule rule) {
    return true;
  }

  @Override
  public void beforeExecute(Rule rule) {
  }

  @Override
  public void onSuccess(Rule rule) {
  }

  @Override
  public void onFailure(Rule rule, Exception exception) {
    Throwable throwable = ((InvocationTargetException) exception).getTargetException();
    CartException targetException = new CartException(throwable.getMessage(), throwable);
    failureMessageMap.put(rule, targetException);
    hasError = true;
  }
}
