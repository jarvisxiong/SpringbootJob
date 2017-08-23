package com.stixcloud.cart.rules.postcommit;

import com.stixcloud.cart.rules.PostCommitCartRule;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.easyrules.annotation.Action;
import org.easyrules.annotation.Condition;
import org.easyrules.annotation.Priority;
import org.easyrules.spring.SpringRule;
import org.javamoney.moneta.Money;
import org.springframework.context.i18n.LocaleContextHolder;

import java.math.BigDecimal;
import javax.money.CurrencyUnit;
import javax.money.Monetary;

@SpringRule
public class RedeemedPurchase extends PostCommitCartRule {
  private static final Logger logger = LogManager.getLogger(RedeemedPurchase.class);
  private CurrencyUnit currencyUnit;

  @Priority
  public int getPriority() {
    return Integer.MIN_VALUE;
  }

  @Condition
  public boolean when() {
    return isCartNotEmpty() && this.getShoppingCart().getIsFullyRedeemed();
  }

  @Action
  public void then() {
    if (this.getShoppingCart().getIsFullyRedeemed()) {
      currencyUnit = Monetary.getCurrency(LocaleContextHolder.getLocale());

      PaymentResponse
          paymentResponse =
          new PaymentResponse.Builder()
              .totalPaymentAmount(Money.of(BigDecimal.ZERO, currencyUnit))
              .orderStatus(0).build();
      this.getPostCommitOrder().setPaymentResponse(paymentResponse);
    }
  }
}
