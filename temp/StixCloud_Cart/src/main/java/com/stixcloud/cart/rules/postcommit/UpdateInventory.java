package com.stixcloud.cart.rules.postcommit;

import com.stixcloud.cart.ISalesSeatInventoryService;
import com.stixcloud.cart.ValidateCartException;
import com.stixcloud.cart.rules.PostCommitCartRule;
import com.stixcloud.domain.CartItem;
import com.stixcloud.domain.SalesSeatInventory;
import org.easyrules.annotation.Action;
import org.easyrules.annotation.Condition;
import org.easyrules.annotation.Priority;
import org.easyrules.spring.SpringRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@SpringRule
public class UpdateInventory extends PostCommitCartRule {
  @Autowired
  private ISalesSeatInventoryService seatInventoryService;
  @Autowired
  private MessageSource messageSource;

  @Priority
  public int getPriority() {
    return 1;
  }

  @Condition
  public boolean when() {
    return isCartNotEmpty() && isPaymentApproved();
  }

  @Action
  public void then() throws ValidateCartException {
    this.setExecuted(true);
    List<SalesSeatInventory> inventoryList = null;
    List<Long> invIdList =
        this.getShoppingCart().getCartItems().stream()
            .map(CartItem::getInventoryIdList)
            .flatMap(Collection::stream)
            .collect(Collectors.toList());

      inventoryList = seatInventoryService.updateSeatsToSold(invIdList);

    if (inventoryList == null) {
      throw new ValidateCartException(
          messageSource
              .getMessage("postcommit.error.inventory.unavailable", null, LocaleContextHolder
                  .getLocale()));
    }
  }
}
