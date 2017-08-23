package com.stixcloud.cart.rules.promovalidation;

import com.stixcloud.cart.CartException;
import com.stixcloud.cart.ShoppingCartService;
import com.stixcloud.cart.api.ShoppingCartJson;
import com.stixcloud.cart.rules.CartRulesListener;
import com.stixcloud.cart.rules.ICartRulesEngine;
import com.stixcloud.cart.rules.commit.CommitOrder;
import com.stixcloud.cart.rules.commit.SCBValidation;
import org.easyrules.api.RulesEngine;
import org.easyrules.core.RulesEngineBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Created by chongye on 24/11/2016.
 */
@Component
public class PromoValidationRuleEngine extends ICartRulesEngine {

  private SCBValidation sCBValidation;
  private ShoppingCartService shoppingCartService;
  private CommitOrder commitOrder;
  private ShoppingCartJson shoppingCartJson;

  @Autowired
  public PromoValidationRuleEngine(
      ShoppingCartService shoppingCartService,
      SCBValidation sCBValidation) {
    this.shoppingCartService = shoppingCartService;
    this.sCBValidation = sCBValidation;
  }

  public CommitOrder getCommitOrder() {
    return commitOrder;
  }

  public void setCommitOrder(CommitOrder commitOrder) {
    this.commitOrder = commitOrder;
  }

  public ShoppingCartJson getShoppingCartJson() {
    return this.shoppingCartJson;
  }

  public void setShoppingCartJson(ShoppingCartJson shoppingCartJson) {
    this.shoppingCartJson = shoppingCartJson;
  }

  @Override
//  @Transactional
  public void fireRules() throws CartException {
    this.cartRulesListener = new CartRulesListener();

    RulesEngine rulesEngine =
        RulesEngineBuilder.aNewRulesEngine().withSilentMode(isSilentMode)
            .withRuleListener(cartRulesListener)
            .withSkipOnFirstFailedRule(true).build();
    sCBValidation.setShoppingCart(shoppingCart);
    sCBValidation.setCommitOrder(commitOrder);
    rulesEngine.registerRule(sCBValidation);
    rulesEngine.fireRules();
    handleException();
  }

  @Override
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
