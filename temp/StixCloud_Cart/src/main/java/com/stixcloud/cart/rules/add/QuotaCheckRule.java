package com.stixcloud.cart.rules.add;

import com.stixcloud.cart.AddToCartException;
import com.stixcloud.cart.repo.ValidateCartViewRepository;
import com.stixcloud.cart.rules.BaseCartRule;
import com.stixcloud.domain.CartItem;
import com.stixcloud.domain.ShoppingCart;
import com.stixcloud.domain.ValidateCartView;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.easyrules.annotation.Action;
import org.easyrules.annotation.Condition;
import org.easyrules.annotation.Priority;
import org.easyrules.spring.SpringRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Check against quota retrieved from Price Model Template
 * The quota is evaluated against the item that is added to cart
 */
@SpringRule
public class QuotaCheckRule extends BaseCartRule {
  private static final Logger logger = LogManager.getLogger(QuotaCheckRule.class);
  @Autowired
  private MessageSource messageSource;

  @Autowired
  ValidateCartViewRepository validateCartViewRepository;

  private List<CartItem> cartItems;

  public void setCartItems(List<CartItem> cartItems) {
    this.cartItems = cartItems;
  }

  @Priority
  public int getPriority() {
    return 1;
  }

  @Condition
  public boolean when() {
    ShoppingCart cart = this.getShoppingCart();
    if (cart == null) {
      return false;
    } else {
      return isCartNotEmpty();
    }
  }

  @Action
  public void then() throws AddToCartException {
    this.setExecuted(true);

    int ticketQty =
        cartItems.stream().map(CartItem::getInventoryIdList).mapToInt(Set::size).sum();

    List<String> priceClassList =
        cartItems.stream().map(CartItem::getPriceClass).collect(Collectors.toList());

    List<ValidateCartView> cartViewList =
        validateCartViewRepository
            .findAll(cartItems.get(0).getProductId(), cartItems.get(0).getPriceCatId(),
                priceClassList);

    boolean quotaCheck = cartViewList.stream().allMatch(view -> {
      logger.debug(
          "Validate cart view : " + view.toString() + " | Ticket quantity : " + ticketQty);
      if (view.getRowquota() > 0) {
        return view.getPriceClassUnavailableSeats() + ticketQty <= view.getRowquota();
      }
      if (view.getChartquota() > 0) {
        return view.getPriceCatUnavailableSeats() + ticketQty <= view.getChartquota();
      }
      if (view.getPriceclassquota() > 0) {
        return view.getPriceClassUnavailableSeats() + ticketQty <= view.getPriceclassquota();
      }
      return true;
    });

    if (!quotaCheck) {
      throw new AddToCartException(
          messageSource.getMessage("pmt.error.quota", null, LocaleContextHolder.getLocale()));
    }
  }
}
