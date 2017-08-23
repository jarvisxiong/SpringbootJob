package com.stixcloud.cart.rules;

import static io.github.benas.randombeans.api.EnhancedRandom.randomListOf;
import static io.github.benas.randombeans.api.EnhancedRandom.randomSetOf;
import static org.junit.Assert.assertFalse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stixcloud.cart.rules.add.CartTicketsLimitRule;
import com.stixcloud.domain.CartItem;
import com.stixcloud.domain.ShoppingCart;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.LinkedList;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

/**
 * Created by chongye on 11/10/2016.
 */
@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest
public class CartTicketsLimitRuleTest extends BaseCartRuleTest {
  private static final Logger logger = LogManager.getLogger(CartTicketsLimitRuleTest.class);
  @Autowired
  private MessageSource messageSource;
  @Autowired
  private ObjectMapper objectMapper;
  @Autowired
  private CartTicketsLimitRule cartTicketsLimitRule;

  @Test
  public void success() throws Exception {
    ShoppingCart shoppingCart = new ShoppingCart.Builder()
        .cartItems(new LinkedList<>(
            randomListOf(ThreadLocalRandom.current().nextInt(1, 10), CartItem.class,
                "inventoryIdList"))).build();
    shoppingCart = new ShoppingCart.Builder(shoppingCart)
        .cartItems(shoppingCart.getCartItems().stream().map(
            i -> i = new CartItem.Builder(i).inventoryIdList(randomSetOf(1, Long.class)).build())
            .collect(Collectors.toCollection(LinkedList::new))).build();
    logger.debug(shoppingCart);
    fireRulesAndAssertExecuted(this.cartTicketsLimitRule, shoppingCart);
    assertFalse(getCartRulesListener().hasError());
  }

  @Test
  public void failure() throws Exception {
    ShoppingCart shoppingCart = objectMapper
        .readValue(this.getClass().getResource("/cart/json/ShoppingCart.json"), ShoppingCart.class);
    //empty the inventory list
    shoppingCart = new ShoppingCart.Builder(shoppingCart)
        .cartItems(new LinkedList<>(
            randomListOf(ThreadLocalRandom.current().nextInt(11, 20), CartItem.class))).build();
    fireRulesAndAssertException(this.cartTicketsLimitRule, shoppingCart,
        messageSource.getMessage("cart.error.limit", null, LocaleContextHolder.getLocale()));
  }
}