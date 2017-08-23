package com.stixcloud.cart.rules.commit;

import com.stixcloud.cart.CartException;
import com.stixcloud.cart.ShoppingCartService;
import com.stixcloud.cart.repo.TransactionCodeRepository;
import com.stixcloud.cart.rules.CartRulesListener;
import com.stixcloud.cart.rules.ICartRulesEngine;
import com.stixcloud.domain.TransactionCode;
import org.easyrules.api.RulesEngine;
import org.easyrules.core.RulesEngineBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Created by chongye on 24/11/2016.
 */
@Component
public class CommitOrderEngine extends ICartRulesEngine {
  private CreateTransaction createTransaction;
  private CreatePayment createPayment;
  private CreateLineItems createLineItems;
  private CreateFees createFees;
  private SCBValidation sCBValidation;
  private ShoppingCartService shoppingCartService;
  private TransactionCodeRepository transactionCodeRepository;
  private CommitOrder commitOrder;

  @Autowired
  public CommitOrderEngine(CreateTransaction createTransaction,
                           CreatePayment createPayment,
                           CreateLineItems createLineItems,
                           CreateFees createFees,
                           ShoppingCartService shoppingCartService,
                           SCBValidation sCBValidation,
                           TransactionCodeRepository transactionCodeRepository) {
    this.createTransaction = createTransaction;
    this.createPayment = createPayment;
    this.createLineItems = createLineItems;
    this.createFees = createFees;
    this.shoppingCartService = shoppingCartService;
    this.sCBValidation = sCBValidation;
    this.transactionCodeRepository = transactionCodeRepository;
  }

  public CommitOrder getCommitOrder() {
    return commitOrder;
  }

  public void setCommitOrder(CommitOrder commitOrder) {
    this.commitOrder = commitOrder;
  }

  @Override
//  @Transactional
  public void fireRules() throws CartException {
    this.cartRulesListener = new CartRulesListener();

    RulesEngine rulesEngine =
        RulesEngineBuilder.aNewRulesEngine().withSilentMode(isSilentMode)
            .withRuleListener(cartRulesListener)
            .withSkipOnFirstFailedRule(true).build();

    TransactionCode
        transactionCode =
        transactionCodeRepository.getTransactionCode("Sales", "Purchase");
    commitOrder.setTransactionCode(transactionCode);

    sCBValidation.setShoppingCart(shoppingCart);
    sCBValidation.setCommitOrder(commitOrder);
    rulesEngine.registerRule(sCBValidation);

    createTransaction.setShoppingCart(shoppingCart);
    createTransaction.setCommitOrder(commitOrder);
    rulesEngine.registerRule(createTransaction);

    createPayment.setShoppingCart(shoppingCart);
    createPayment.setCommitOrder(commitOrder);
    rulesEngine.registerRule(createPayment);

    createLineItems.setShoppingCart(shoppingCart);
    createLineItems.setCommitOrder(commitOrder);
    rulesEngine.registerRule(createLineItems);

    createFees.setShoppingCart(shoppingCart);
    createFees.setCommitOrder(commitOrder);
    rulesEngine.registerRule(createFees);

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
