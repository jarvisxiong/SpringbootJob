package com.stixcloud.cart.rules.postcommit;

import com.stixcloud.cart.INotificationService;
import com.stixcloud.cart.ValidateCartException;
import com.stixcloud.cart.rules.PostCommitCartRule;
import com.stixcloud.domain.ShoppingCart;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.easyrules.annotation.Action;
import org.easyrules.annotation.Condition;
import org.easyrules.annotation.Priority;
import org.easyrules.spring.SpringRule;
import org.springframework.beans.factory.annotation.Autowired;

@SpringRule
public class InsertNotification extends PostCommitCartRule {

  @Autowired
  private INotificationService notificationService;
  private static final Logger logger = LogManager.getLogger(InsertNotification.class);

  @Priority
  public int getPriority() {
    return 5;
  }

  @Condition
  public boolean when() {
    return isCartNotEmpty() && isPaymentApproved();
  }

  @Action
  public void then() throws ValidateCartException {
    this.setExecuted(true);
    logger.debug("Begin InsertNotification ");
    this.getPostCommitOrder().getProfileInfoId();
    ShoppingCart cart = this.getShoppingCart();
    try {
      notificationService.insertNotification(cart);
    } catch (Exception e) {
      logger.error(e);
    }
    logger.debug("End InsertNotification ");
  }

}
