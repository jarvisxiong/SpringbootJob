package com.stixcloud.cart.rules.precommit;

import com.stixcloud.cart.CreditCardRegexType;
import com.stixcloud.cart.IShoppingCartService;
import com.stixcloud.cart.ValidateCartException;
import com.stixcloud.cart.repo.CreditCardRegexRepository;
import com.stixcloud.cart.rules.BaseCartRule;
import com.stixcloud.domain.CreditCardRegex;
import com.stixcloud.domain.ShoppingCart;
import org.apache.commons.lang3.StringUtils;
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
import java.util.stream.Collectors;

/**
 * Created by chongye on 29/9/2016.
 */
@SpringRule
public class CreditCardRangeRule extends BaseCartRule {
  private static final Logger logger = LogManager.getLogger(CreditCardRangeRule.class);
  @Autowired
  private MessageSource messageSource;
  @Autowired
  private IShoppingCartService shoppingCartService;
  @Autowired
  private CreditCardRegexRepository creditCardRegexRepository;

  private String creditCard;
  private String deliveryMethodCode;

  public void setCreditCard(String creditCard) {
    this.creditCard = creditCard;
  }

  public void setDeliveryMethodCode(String deliveryMethodCode) {
    this.deliveryMethodCode = deliveryMethodCode;
  }

  @Priority
  public int getPriority() {
    return 1;
  }

  @Condition
  public boolean when() {
    return isCartNotEmpty()
        && StringUtils.isNotBlank(creditCard)
        && StringUtils.isNotBlank(deliveryMethodCode);
  }

  @Action
  public void checkDeliveryMethod() throws ValidateCartException {
    this.setExecuted(true);

    List<CreditCardRegex>
        creditCardRegexes =
        creditCardRegexRepository.getCreditCardRegexesByDeliveryMethodCode(deliveryMethodCode);

    if (!creditCardRegexes.isEmpty()) {
      boolean
          creditCardMatch =
          creditCardRegexes.stream().map(this::getRegularExpression)
              .anyMatch(creditCard::matches);
      if (!creditCardMatch) {
        throw new ValidateCartException(
            messageSource.getMessage("precommit.error.creditcardregex.deliverymethod", null,
                LocaleContextHolder
                    .getLocale()));
      }
    }
  }

  @Action
  public void checkPriceClass() throws ValidateCartException {
    this.setExecuted(true);
    ShoppingCart cart = this.getShoppingCart();

    List<String> creditCardRegexes = cart.getCartItems().stream().flatMap(cartItem ->
        creditCardRegexRepository
            .getCreditCardRegexesByProductIdAndPriceClassCode(cartItem.getProductId(),
                cartItem.getPriceClass())
            .stream().map(this::getRegularExpression)
    ).collect(Collectors.toList());

    boolean allRegexesMatch = creditCardRegexes.stream().allMatch(creditCard::matches);
    if (!allRegexesMatch) {
      throw new ValidateCartException(
          messageSource
              .getMessage("precommit.error.creditcardregex.priceclass", null, LocaleContextHolder
                  .getLocale()));
    }
  }

  private String getRegularExpression(CreditCardRegex creditCardRegex) {
    return "^" + creditCardRegex.getStartdigits() + "\\" + CreditCardRegexType
        .getRegexType(creditCardRegex.getExptype()) + "{"
        + creditCardRegex.getRemainingdigits() + "}$";
  }

}
