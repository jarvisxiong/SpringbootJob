package com.stixcloud.cart.ticketprotector.rules.postcommit;

import com.stixcloud.cart.CartException;
import com.stixcloud.cart.ValidateCartException;
import com.stixcloud.cart.rules.PostCommitCartRule;
import com.stixcloud.cart.ticketprotector.TicketProtectorService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.easyrules.annotation.Action;
import org.easyrules.annotation.Condition;
import org.easyrules.annotation.Priority;
import org.easyrules.spring.SpringRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;

@SpringRule
public class TicketProtectorRule extends PostCommitCartRule {

  public static final Logger logger = LogManager.getLogger(TicketProtectorRule.class);

  @Autowired
  private MessageSource messageSource;
  @Autowired
  private TicketProtectorService ticketProtectorService;

  @Value("${waived.delivery.codes}")
  private String waivedDeliveryCodes;

  @Priority
  public int getPriority() {
    return 4;
  }

  @Condition
  public boolean when() {
    return isCartNotEmpty() && this.getShoppingCart().getPurchaseTp() && this.isPaymentApproved();
  }

  @Action
  public void then() throws ValidateCartException {
    this.setExecuted(true);
    logger.debug("Begin TicketProtectorRule ");
    try {
      ticketProtectorService
          .processTicketProtector(this.getPostCommitOrder(), this.getShoppingCart());
    } catch (CartException e) {
      logger.error(e.getMessage(), e);
    }
    logger.debug("End TicketProtectorRule ");
  }
}
