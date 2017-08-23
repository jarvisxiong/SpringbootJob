package com.stixcloud.cart.rules.add;

import com.stixcloud.cart.AddToCartException;
import com.stixcloud.cart.QuantityStatus;
import com.stixcloud.cart.repo.ValidateCartViewRepository;
import com.stixcloud.cart.rules.BaseCartRule;
import com.stixcloud.domain.CartItem;
import com.stixcloud.domain.CartItemTuple;
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
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Check against rules retrieved from the Price Model Template of the product
 * - Allocation check (for price class)
 * - Max quantity check (for price class)
 */
@SpringRule
public class PriceModelTemplateRule extends BaseCartRule {
  private static final Logger logger = LogManager.getLogger(PriceModelTemplateRule.class);
  @Autowired
  private MessageSource messageSource;

  @Autowired
  ValidateCartViewRepository validateCartViewRepository;

  private Map<CartItemTuple, List<CartItem>> mergedCart;
  private boolean allocationCheck;
  private boolean maxQuantityCheck;

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
      if (isCartNotEmpty()) {
        this.mergedCart =
            cart.getCartItems().stream().collect(Collectors.groupingBy(CartItem::getTuple));
        logger.debug(mergedCart);

        return true;
      }
    }
    return false;
  }

  @Action
  public void then() throws AddToCartException {
    this.setExecuted(true);
    boolean match = mergedCart.entrySet().stream().allMatch(entrySet -> {
      CartItemTuple tuple = entrySet.getKey();
      Map<String, Integer> mergedPriceClassMap = getPriceClassMap(entrySet.getValue());

      List<String> priceClassList =
          mergedPriceClassMap.entrySet().stream().map(Map.Entry::getKey).collect(
              Collectors.toList());

      List<ValidateCartView> cartViewList =
          validateCartViewRepository
              .findAll((Long) tuple.getProductId(), (Long) tuple.getPriceCatId(), priceClassList);

      return !cartViewList.isEmpty() && cartViewList.stream().allMatch(view -> {
        Integer ticketQty = mergedPriceClassMap.get(view.getPriceclasscode());

        allocationCheck = checkAllocation(view, ticketQty);
        maxQuantityCheck = checkMaxQuantity(view, ticketQty);

        if (!allocationCheck || !maxQuantityCheck) {
          logger.error(
              "Validate cart view : " + view.toString() + " | Ticket quantity : " + ticketQty);
        }

        return allocationCheck && maxQuantityCheck;
      });
    });

    if (!match) {
      if (!allocationCheck && !maxQuantityCheck) {
        throw new AddToCartException(
            messageSource.getMessage("pmt.error.generic", null, LocaleContextHolder.getLocale()));
      } else if (!allocationCheck) {
        throw new AddToCartException(
            messageSource
                .getMessage("pmt.error.allocation", null, LocaleContextHolder.getLocale()));
      } else if (!maxQuantityCheck) {
        throw new AddToCartException(
            messageSource.getMessage("pmt.error.quantity", null, LocaleContextHolder.getLocale()));
      } else {
        throw new AddToCartException(
            messageSource.getMessage("pmt.error.generic", null, LocaleContextHolder.getLocale()));
      }
    }
  }

  private boolean checkAllocation(ValidateCartView view, Integer ticketQty) {
    if (view.getQuantitystatus().equals(QuantityStatus.EXACT.getValue())) {
      logger.debug("Exact clause : " + (ticketQty % view.getQuantity() == 0));
      return ticketQty % view.getQuantity() == 0;
    } else if (view.getQuantitystatus().equals(QuantityStatus.MINIMUM.getValue())) {
      logger.debug("Minimum clause : " + (ticketQty >= view.getQuantity()));
      return ticketQty >= view.getQuantity();
    }
    return true;
  }

  private boolean checkMaxQuantity(ValidateCartView view, Integer ticketQty) {
    int maxQuantity = view.getMaximumquantity();
    logger.debug("Max quantity = " + maxQuantity);
    return (maxQuantity == 0 || ticketQty <= maxQuantity);
  }

  /**
   * Retrieves a merged price class map with quantity of each price class
   */
  private Map<String, Integer> getPriceClassMap(List<CartItem> cartItems) {
    //merge all price class maps into 1 map
    return cartItems.stream()
        .collect(Collectors
            .groupingBy(CartItem::getPriceClass,
                Collectors.summingInt(ci -> ci.getInventoryIdList().size())));
  }
}
