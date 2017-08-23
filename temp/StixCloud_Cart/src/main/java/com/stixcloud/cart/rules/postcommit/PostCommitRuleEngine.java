package com.stixcloud.cart.rules.postcommit;

import com.stixcloud.cart.CartException;
import com.stixcloud.cart.api.PostCommitRequest;
import com.stixcloud.cart.rules.CartRulesListener;
import com.stixcloud.cart.rules.ICartRulesEngine;
import com.stixcloud.cart.ticketprotector.rules.postcommit.TicketProtectorRule;
import org.easyrules.api.RulesEngine;
import org.easyrules.core.RulesEngineBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Created by chetan on 01/01/2017.
 */

@Component
public class PostCommitRuleEngine extends ICartRulesEngine {
  private RedeemedPurchase redeemedPurchase;
  private Payment payment;
  private UpdatePayment updatePayment;
  private UpdateInventory updateInventory;
  private UpdateTransaction updateTransaction;
  private TicketProtectorRule ticketProtectorRule;
  private InsertNotification insertNotification;
  private PostCommitRequest postCommitRequest;
  private PostCommitOrder postCommitOrder;
  private EvoucherRedemptionRule evoucherRedemptionRule;
  private PromoPasswordUsage promoPasswordUsage;
  @Autowired
  public PostCommitRuleEngine(RedeemedPurchase redeemedPurchase,
                              Payment payment,
                              UpdatePayment updatePayment,
                              UpdateInventory updateInventory,
                              UpdateTransaction updateTransaction,
                              TicketProtectorRule ticketProtectorRule,
                              InsertNotification insertNotification,
                              EvoucherRedemptionRule evoucherRedemptionRule,
                              PromoPasswordUsage promoPasswordUsage) {
    this.redeemedPurchase = redeemedPurchase;
    this.payment = payment;
    this.updatePayment = updatePayment;
    this.updateInventory = updateInventory;
    this.updateTransaction = updateTransaction;
    this.ticketProtectorRule = ticketProtectorRule;
    this.insertNotification = insertNotification;
    this.evoucherRedemptionRule=evoucherRedemptionRule;
    this.promoPasswordUsage = promoPasswordUsage;
  }

  @Override
  // @Transactional
  public void fireRules() throws CartException {
    this.cartRulesListener = new CartRulesListener();

    RulesEngine
        rulesEngine =
        RulesEngineBuilder.aNewRulesEngine().withSilentMode(isSilentMode)
            .withRuleListener(cartRulesListener)
            .withSkipOnFirstFailedRule(true).build();
    redeemedPurchase.setShoppingCart(shoppingCart);
    redeemedPurchase.setPostCommitOrder(postCommitOrder);
    rulesEngine.registerRule(redeemedPurchase);
    
    evoucherRedemptionRule.setShoppingCart(shoppingCart);
    evoucherRedemptionRule.setPostCommitOrder(postCommitOrder);
    rulesEngine.registerRule(evoucherRedemptionRule);
    
    payment.setShoppingCart(shoppingCart);
    payment.setPostCommitRequest(postCommitRequest);
    payment.setPostCommitOrder(postCommitOrder);
    rulesEngine.registerRule(payment);
    updateTransaction.setShoppingCart(shoppingCart);
    updateTransaction.setPostCommitOrder(postCommitOrder);
    rulesEngine.registerRule(updateTransaction);
    updatePayment.setShoppingCart(shoppingCart);
    updatePayment.setPostCommitOrder(postCommitOrder);
    rulesEngine.registerRule(updatePayment);
    updateInventory.setShoppingCart(shoppingCart);
    updateInventory.setPostCommitOrder(postCommitOrder);
    rulesEngine.registerRule(updateInventory);
    insertNotification.setShoppingCart(shoppingCart);
    insertNotification.setPostCommitOrder(postCommitOrder);
    rulesEngine.registerRule(insertNotification);
    ticketProtectorRule.setShoppingCart(shoppingCart);
    ticketProtectorRule.setPostCommitOrder(postCommitOrder);
    rulesEngine.registerRule(ticketProtectorRule);
    promoPasswordUsage.setShoppingCart(shoppingCart);
    promoPasswordUsage.setPostCommitOrder(postCommitOrder);
    rulesEngine.registerRule(promoPasswordUsage);
    rulesEngine.fireRules();
    handleException();
  }

  @Override
  public void handleException() throws CartException {
    if (cartRulesListener.hasError()) {
      CartException
          exception =
          (CartException) cartRulesListener.getFailureMessageMap().entrySet().stream()
              .map(Map.Entry::getValue).findFirst().orElse(null);
      if (exception != null) {
        throw exception;
      }
    }
  }

  public void setPostCommitRequest(PostCommitRequest postCommitRequest) {
    this.postCommitRequest = postCommitRequest;
  }

  public void setPostCommitOrder(PostCommitOrder postCommitOrder) {
    this.postCommitOrder = postCommitOrder;
  }
}
