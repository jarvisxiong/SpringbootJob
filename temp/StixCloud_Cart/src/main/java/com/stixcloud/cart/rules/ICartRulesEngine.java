package com.stixcloud.cart.rules;

import com.stixcloud.cart.CartException;
import com.stixcloud.domain.ShoppingCart;
import org.springframework.beans.factory.annotation.Value;

import java.util.Map;

/**
 * Created by chongye on 24/11/2016.
 */
public abstract class ICartRulesEngine {
  protected ShoppingCart shoppingCart;
  protected CartRulesListener cartRulesListener;
  @Value("${easyrule.silent.mode:false}")
  protected boolean isSilentMode;

  public void setCartRulesListener(CartRulesListener cartRulesListener) {
    this.cartRulesListener = cartRulesListener;
  }

  public void setShoppingCart(ShoppingCart shoppingCart) {
    this.shoppingCart = shoppingCart;
  }

  public void fireRules() throws CartException {
  }

  public void handleException() throws CartException {
    if (cartRulesListener.hasError()) {
      CartException exception = (CartException)
          cartRulesListener.getFailureMessageMap().entrySet().stream().map(Map.Entry::getValue)
              .findFirst().orElse(null);
      if (exception != null) {
        throw exception;
      }
    }
  }
}
