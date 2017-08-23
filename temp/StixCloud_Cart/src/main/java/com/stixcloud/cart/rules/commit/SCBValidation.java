package com.stixcloud.cart.rules.commit;

import com.stixcloud.cart.CartException;
import com.stixcloud.cart.ShoppingCartService;
import com.stixcloud.cart.ValidateCartException;
import com.stixcloud.cart.api.JsonResponse;
import com.stixcloud.cart.api.PopUpMessageJson;
import com.stixcloud.cart.api.ShoppingCartJson;
import com.stixcloud.cart.rules.CommitCartRule;
import com.stixcloud.common.config.multitenant.TenantContextHolder;
import com.stixcloud.domain.ShoppingCart;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.easyrules.annotation.Action;
import org.easyrules.annotation.Condition;
import org.easyrules.annotation.Priority;
import org.easyrules.spring.SpringRule;
import org.javamoney.moneta.Money;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;

import java.util.Set;
import java.util.stream.Collectors;
import javax.money.CurrencyUnit;
import javax.money.Monetary;

/**
 * This rule is for all product related validation
 */
@SpringRule
public class SCBValidation extends CommitCartRule {
  private static final Logger logger = LogManager.getLogger(SCBValidation.class);

  @Autowired
  ShoppingCartService shoppingCartService;
  @Value("${waived.delivery.codes}")
  private String waivedDeliveryCodes;

  @Priority
  public int getPriority() {
    return 0;
  }

  @Condition
  public boolean when() {
    return isCartNotEmpty();
  }

  @Action
  public void then() throws ValidateCartException, CartException {
    this.setExecuted(true);
    logger.info("SCBValidation start time "+System.currentTimeMillis());
    CommitOrder commitOrder = this.getCommitOrder();
    ShoppingCartJson shoppingCartJson = commitOrder.getShoppingCartJson();
    JsonResponse.Builder builder = new JsonResponse.Builder();
    Boolean isValidSCBWaiverCreditCard = false;
    Long validSCBWaiverShowCount = 0l;
    ShoppingCart shoppingCart = this.getShoppingCart();
    CurrencyUnit currencyUnit = Monetary.getCurrency(LocaleContextHolder.getLocale());
    isValidSCBWaiverCreditCard =
        shoppingCartService.isValidSCBWaiverCreditCard(shoppingCart.getCreditCardNo());
    Set<Long> scbWaiveProducts = shoppingCartService.getValidSCBWaiverShows(shoppingCart);
    JsonResponse jsonResponse = null;
    if (!isValidSCBWaiverCreditCard) {
      jsonResponse = builder.httpStatus(HttpStatus.OK.toString())
          .popUp(false).build();
    } else {
      String message = null;

      if (scbWaiveProducts != null) {
        validSCBWaiverShowCount = Long.valueOf(scbWaiveProducts.size());
      }
      Integer
          productCount =
          shoppingCartService.getProductIdsByCartGuid(shoppingCart.getCartGuid()).stream()
              .collect(Collectors.toSet()).size();
      Double bookingFeeTotal = 0d;
      bookingFeeTotal = shoppingCartJson.getLineItemList().stream()
          .filter(cartLineItem -> scbWaiveProducts != null
              && scbWaiveProducts.contains(cartLineItem.getProduct().getProductId()))
          .mapToDouble(
              cartLineItem -> cartLineItem.getQuantity() * (cartLineItem.getBookingFee().getNumber()
                  .doubleValue())).sum();
      if ((productCount == validSCBWaiverShowCount.intValue()) && isValidSCBWaiverCreditCard) {
        message = "message.scb.total.booking.fee.waived";
      } else if ((validSCBWaiverShowCount > 0) && isValidSCBWaiverCreditCard) {
        message = "message.scb.partial.booking.fee.waived";
      } else if (validSCBWaiverShowCount == 0 && isValidSCBWaiverCreditCard) {
        message = "message.scb.not.participating.scb.booking.fee.waiver";
        bookingFeeTotal = shoppingCartJson.getLineItemList().stream()
            .mapToDouble(cartLineItem -> cartLineItem.getQuantity() * (cartLineItem.getBookingFee()
                .getNumber().doubleValue())).sum();
      } else {
        jsonResponse = builder.httpStatus(HttpStatus.OK.toString())
            .popUp(false).build();
      }
      if (message != null) {
        PopUpMessageJson
            popUpMessageJson =
            new PopUpMessageJson(Money.of(bookingFeeTotal, currencyUnit),
                message);
        jsonResponse =
            builder.httpStatus(HttpStatus.OK.toString()).popUp(true).popUpMessage(popUpMessageJson)
                .build();
        ShoppingCartJson
            shoppingCartJsonNew =
            shoppingCartService.getShoppingCartJson(shoppingCart.getCartGuid(),
                currencyUnit, waivedDeliveryCodes,
                TenantContextHolder.getTenant().getProfileInfoId(),
                scbWaiveProducts);
        commitOrder.setShoppingCartJson(shoppingCartJsonNew);
      }
    }
    commitOrder.setSCBJsonResponse(jsonResponse);
    logger.info("SCBValidation end time "+System.currentTimeMillis());
    logger.debug("SCB Booking Fee Waiver response " + jsonResponse);

  }
}
