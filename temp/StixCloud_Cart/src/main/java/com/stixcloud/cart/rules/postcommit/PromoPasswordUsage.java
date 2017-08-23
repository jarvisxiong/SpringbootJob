package com.stixcloud.cart.rules.postcommit;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.easyrules.annotation.Action;
import org.easyrules.annotation.Condition;
import org.easyrules.annotation.Priority;
import org.easyrules.spring.SpringRule;
import org.springframework.beans.factory.annotation.Autowired;

import com.stixcloud.cart.PromoPasswordService;
import com.stixcloud.cart.rules.PostCommitCartRule;

@SpringRule
public class PromoPasswordUsage extends PostCommitCartRule {

  private static final Logger logger = LogManager.getLogger(PromoPasswordUsage.class);
  @Autowired
  private PromoPasswordService promoPasswordService;
  
  @Priority
  public int getPriority() {
    return 6;
  }

  @Condition
  public boolean when() {
    boolean hasPromoPassword = this.getShoppingCart().getCartItems().stream()
        .filter(p -> !StringUtils.isBlank(p.getPromoPassword())).findAny().isPresent();
    return isCartNotEmpty() && isPaymentApproved() && hasPromoPassword;
  }
  
  @Action
  public void then() {
    this.setExecuted(true);
    logger.debug("updating Promotion Password ");
    promoPasswordService.updateNumberUsage(this.getShoppingCart());
    logger.debug("updating Promotion Password was success");
  }
  
}
