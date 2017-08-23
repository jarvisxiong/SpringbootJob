package com.stixcloud.cart.rules.add;

import java.util.List;
import java.util.Map;

import org.easyrules.api.RulesEngine;
import org.easyrules.core.RulesEngineBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.stixcloud.cart.AddToCartException;
import com.stixcloud.cart.CartException;
import com.stixcloud.cart.ISalesSeatInventoryService;
import com.stixcloud.cart.rules.CartRulesListener;
import com.stixcloud.cart.rules.ICartRulesEngine;
import com.stixcloud.cart.rules.PromoRule;
import com.stixcloud.domain.CartItem;

/**
 * Created by chongye on 24/11/2016.
 */
@Component
public class AddToCartRulesEngine extends ICartRulesEngine {

  private CartTicketsLimitRule         cartTicketsLimitRule;
  private PriceModelTemplateRule       priceModelTemplateRule;
  private InventoryCheckRule           inventoryCheckRule;
  private QuotaCheckRule               quotaCheckRule;
  private QuantityRestrictionCheckRule quantityRestrictionaCheckRule;
  private SharedQuotaCheckRule         sharedQuotaCheckRule;
  private ISalesSeatInventoryService   salesSeatInventoryService;
  private PromoRule                    promoRule;

  private List<CartItem> newCartItems;
  private Long patronProfileId;
  private Integer maxQuantity;
  private String icc;

  @Autowired
  public AddToCartRulesEngine(CartTicketsLimitRule cartTicketsLimitRule,
                              PriceModelTemplateRule priceModelTemplateRule,
                              InventoryCheckRule inventoryCheckRule,
                              QuantityRestrictionCheckRule quantityRestrictionaCheckRule,
                              QuotaCheckRule quotaCheckRule,
                              ISalesSeatInventoryService salesSeatInventoryService,
                              SharedQuotaCheckRule sharedQuotaCheckRule,
                              PromoRule promoRule) {
    this.cartTicketsLimitRule = cartTicketsLimitRule;
    this.priceModelTemplateRule = priceModelTemplateRule;
    this.inventoryCheckRule = inventoryCheckRule;
    this.quantityRestrictionaCheckRule = quantityRestrictionaCheckRule;
    this.quotaCheckRule = quotaCheckRule;
    this.salesSeatInventoryService = salesSeatInventoryService;
    this.sharedQuotaCheckRule = sharedQuotaCheckRule;
    this.promoRule = promoRule;
  }

  public void setNewCartItems(List<CartItem> newCartItems) {
    this.newCartItems = newCartItems;
  }

  public void setPatronProfileId(Long patronProfileId) {
    this.patronProfileId = patronProfileId;
  }
  
  public void setMaxQuantity(Integer maxQuantity) {
	    this.maxQuantity = maxQuantity;
 }

  public void setIcc(String icc) {
	    this.icc = icc;
  }
  
  @Override
  public void fireRules() throws AddToCartException {
    this.setCartRulesListener(new CartRulesListener());

    RulesEngine rulesEngine =
        RulesEngineBuilder.aNewRulesEngine().withSilentMode(isSilentMode)
            .withRuleListener(cartRulesListener)
            .withSkipOnFirstFailedRule(true).build();

    cartTicketsLimitRule.setShoppingCart(shoppingCart);
    cartTicketsLimitRule.setIcc(icc);
    cartTicketsLimitRule.setMaxQuantity(maxQuantity);
    priceModelTemplateRule.setShoppingCart(shoppingCart);
    inventoryCheckRule.setShoppingCart(shoppingCart);
    quotaCheckRule.setCartItems(newCartItems);
    quotaCheckRule.setShoppingCart(shoppingCart);
    quantityRestrictionaCheckRule.setPatronProfileId(patronProfileId);
    quantityRestrictionaCheckRule.setShoppingCart(shoppingCart);
    sharedQuotaCheckRule.setPatronProfileId(patronProfileId);
    sharedQuotaCheckRule.setShoppingCart(shoppingCart);
    promoRule.setShoppingCart(shoppingCart);
    promoRule.setCartItems(newCartItems);
    
    rulesEngine.registerRule(cartTicketsLimitRule);
    rulesEngine.registerRule(priceModelTemplateRule);
    rulesEngine.registerRule(inventoryCheckRule);
    rulesEngine.registerRule(quotaCheckRule);
    rulesEngine.registerRule(quantityRestrictionaCheckRule);
    rulesEngine.registerRule(sharedQuotaCheckRule);
    rulesEngine.registerRule(promoRule);
    rulesEngine.fireRules();

    handleException();
  }

  @Override
  public void handleException() throws AddToCartException {
    if (cartRulesListener.hasError()) {
      CartException exception = (CartException)
          cartRulesListener.getFailureMessageMap().entrySet().stream().map(Map.Entry::getValue)
              .findFirst().orElse(null);
      if (exception != null) {
        salesSeatInventoryService.releaseSeatsForGA(newCartItems);
        throw new AddToCartException(exception.getMessage(), exception);
      }
    }
  }
}
