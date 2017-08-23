package com.stixcloud.cart.rules.precommit;

import static com.stixcloud.cart.SalesStatus.AVAILABLE;

import com.stixcloud.cart.ValidateCartException;
import com.stixcloud.cart.repo.ProductLiveRepository;
import com.stixcloud.cart.rules.BaseCartRule;
import com.stixcloud.domain.CartItem;
import com.stixcloud.domain.ProductLive;
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
 * This rule is for all product related validation
 */
@SpringRule
public class ProductRule extends BaseCartRule {
  private static final Logger logger = LogManager.getLogger(ProductRule.class);
  @Autowired
  private MessageSource messageSource;

  @Autowired
  private ProductLiveRepository productLiveRepository;

  @Priority
  public int getPriority() {
    return Integer.MIN_VALUE;
  }

  @Condition
  public boolean when() {
    return isCartNotEmpty();
  }

  @Action
  public void checkProductStillOnSales() throws ValidateCartException {
    this.setExecuted(true);

    Set<Long>
        productIds =
        this.getShoppingCart().getCartItems().stream().map(CartItem::getProductId)
            .collect(Collectors.toSet());

    List<ProductLive>
        offSalesProducts =
        productLiveRepository.getOffSalesProducts(productIds, AVAILABLE.getValue());

    if (!offSalesProducts.isEmpty()) {
      logger.error(offSalesProducts.stream().map(ProductLive::toString));
      throw new ValidateCartException(
          messageSource
              .getMessage("precommit.error.product.offSales", null, LocaleContextHolder
                  .getLocale()));
    }
  }

}
