package com.stixcloud.cart.rules.precommit;

import com.stixcloud.cart.CartException;
import com.stixcloud.cart.CreditCardType;
import com.stixcloud.cart.IShoppingCartService;
import com.stixcloud.cart.ValidateCartException;
import com.stixcloud.cart.api.ShoppingCartJson;
import com.stixcloud.cart.rules.BaseCartRule;
import com.stixcloud.common.config.multitenant.TenantContextHolder;
import com.stixcloud.domain.PaymentMethod;
import com.stixcloud.domain.ShoppingCart;
import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.easyrules.annotation.Action;
import org.easyrules.annotation.Condition;
import org.easyrules.annotation.Priority;
import org.easyrules.spring.SpringRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

import java.util.List;

/**
 * Created by chongye on 29/9/2016.
 */
@SpringRule
public class PaymentMethodRule extends BaseCartRule {
  private static final Logger logger = LogManager.getLogger(PaymentMethodRule.class);
  @Autowired
  private MessageSource messageSource;
  @Autowired
  private IShoppingCartService shoppingCartService;

  private String paymentMethodCode;
  private String creditCardNo;
  private ShoppingCartJson shoppingCartJson;

  public void setPaymentMethodCode(String paymentMethodCode) {
    this.paymentMethodCode = paymentMethodCode;
  }

  public void setCreditCardNo(String creditCardNo) {
    this.creditCardNo = creditCardNo;
  }

  public void setShoppingCartJson(ShoppingCartJson shoppingCartJson) {
    this.shoppingCartJson = shoppingCartJson;
  }

  @Priority
  public int getPriority() {
    return Integer.MIN_VALUE;
  }

  @Condition
  public boolean when() {
    return isCartNotEmpty() && StringUtils.isNotBlank(creditCardNo) && StringUtils
        .isNotBlank(paymentMethodCode);
  }

  /*@Action(order = Integer.MIN_VALUE)
  public void checkRequiredFields() throws ValidateCartException {
    //if line item total is ZERO, there is no need for payment method and cc
    if (shoppingCartJson.getLineItemTotal().isPositive()) {
      if (StringUtils.isBlank(paymentMethodCode)) {
        throw new ValidateCartException(
            messageSource
                .getMessage("precommit.error.paymentMethod.required", null, LocaleContextHolder
                    .getLocale()));
      }
      if (StringUtils.isBlank(creditCardNo)) {
        throw new ValidateCartException(
            messageSource
                .getMessage("precommit.error.paymentMethod.creditcard.required", null,
                    LocaleContextHolder
                        .getLocale()));
      }
    }
  }*/

  @Action
  public void checkCreditCard() throws ValidateCartException {
    this.setExecuted(true);
    boolean validCard =
        CreditCardType.getCreditCardPattern(paymentMethodCode).isValid(creditCardNo);

    if (!validCard) {
      throw new ValidateCartException(
          messageSource
              .getMessage("precommit.error.paymentMethod.creditcard", null, LocaleContextHolder
                  .getLocale()));
    }
  }

  @Action
  public void checkCommonPaymentMethods() throws ValidateCartException {
    this.setExecuted(true);
    ShoppingCart cart = this.getShoppingCart();
    List<PaymentMethod> paymentMethodList = null;
    try {
      paymentMethodList =
          shoppingCartService.getCommonPaymentMethods(cart.getCartGuid(),
              TenantContextHolder.getTenant().getProfileInfoId());
    } catch (CartException e) {
      throw new ValidateCartException(e.getMessage(), e);
    }
    boolean
        containsPaymentMethod =
        paymentMethodList.stream()
            .anyMatch(pm -> pm.getPaymentmethodcode().equals(this.paymentMethodCode));

    if (!containsPaymentMethod) {
      throw new ValidateCartException(
          messageSource.getMessage("precommit.error.paymentMethod", null, LocaleContextHolder
              .getLocale()));
    }
  }
}
