package com.stixcloud.cart.rules.precommit;

import com.stixcloud.cart.CartException;
import com.stixcloud.cart.IFeeService;
import com.stixcloud.cart.ValidateCartException;
import com.stixcloud.cart.rules.BaseCartRule;
import com.stixcloud.common.config.multitenant.TenantContextHolder;
import com.stixcloud.domain.DeliveryMethod;
import com.stixcloud.domain.ShoppingCart;
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
 * Created by sengkai on 11/28/2016.
 */
@SpringRule
public class DeliveryMethodRule extends BaseCartRule {
  private static final Logger logger = LogManager.getLogger(DeliveryMethodRule.class);
  @Autowired
  private MessageSource messageSource;

  @Autowired
  private IFeeService feeService;

  private String deliveryMethodCode;

  public void setDeliveryMethodCode(String deliveryMethodCode) {
    this.deliveryMethodCode = deliveryMethodCode;
  }

  @Priority
  public int getPriority() {
    return Integer.MIN_VALUE;
  }

  @Condition
  public boolean when() {
    return isCartNotEmpty();
  }

  @Action
  public void then() throws ValidateCartException {
    this.setExecuted(true);
    ShoppingCart cart = this.getShoppingCart();

    List<DeliveryMethod> deliveryMethodList = null;
    try {
      deliveryMethodList =
          feeService.getCommonDeliveryMethods(cart.getCartGuid(), TenantContextHolder
              .getTenant().getProfileInfoId());
    } catch (CartException e) {
      throw new ValidateCartException(e.getMessage(), e);
    }
    boolean
        containsDeliveryMethod =
        deliveryMethodList.stream()
            .anyMatch(dm -> dm.getDeliverymethodcode().equals(this.deliveryMethodCode));

    if (!containsDeliveryMethod) {
      throw new ValidateCartException(
          messageSource.getMessage("precommit.error.deliveryMethod", null, LocaleContextHolder
              .getLocale()));
    }
  }

}
