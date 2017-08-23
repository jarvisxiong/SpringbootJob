package com.stixcloud.cart.rules;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stixcloud.cart.CartException;
import com.stixcloud.cart.InventoryConstants;
import com.stixcloud.cart.rules.add.QuotaCheckRule;
import com.stixcloud.domain.CartItem;
import com.stixcloud.domain.ShoppingCart;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.easyrules.api.Rule;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * Created by chongye on 11/10/2016.
 */
@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest
public class QuotaCheckRuleTest extends BaseCartRuleTest {
  private static final Logger logger = LogManager.getLogger(CartTicketsLimitRuleTest.class);
  @Autowired
  private MessageSource messageSource;
  @Autowired
  private ObjectMapper objectMapper;
  @Autowired
  private QuotaCheckRule quotaCheckRule;

  @Override
  @Before
  public void setUp() throws Exception {
    super.setUp();
    ShoppingCart shoppingCart = objectMapper
        .readValue(this.getClass().getResource("/cart/json/ShoppingCart.json"), ShoppingCart.class);
    quotaCheckRule.setShoppingCart(shoppingCart);
  }

  @Test
  @Sql("/cart/sql/ValidateCartViewForRS.sql")
  public void success() throws Exception {
    CartItem newCartItem =
        new CartItem.Builder().productId(245634L)
            .priceCatId(12176L).mode(InventoryConstants.SECTION_TYPE_RS.getName())
            .inventoryIdList(new HashSet<>(Arrays.asList(121555782L)))
            .priceClass("A").build();
    fireRulesAndAssertExecuted(newCartItem);
    assertFalse(getCartRulesListener().hasError());
  }

  @Test
  @Sql(value = "/cart/sql/ValidateCartViewForRS.sql",
      statements = "update validate_cart_view set rowquota = 2, price_class_unavailable_seats = 1"
          + " where priceclasscode = 'A1';")
  public void failure_RowQuota() throws Exception {
    runTest();
  }

  @Test
  @Sql(value = "/cart/sql/ValidateCartViewForRS.sql",
      statements = "update validate_cart_view set chartquota = 2, price_cat_unavailable_seats = 1 "
          + "where priceclasscode = 'A1';")
  public void failure_ChartQuota() throws Exception {
    runTest();
  }

  @Test
  @Sql(value = "/cart/sql/ValidateCartViewForRS.sql",
      statements = "update validate_cart_view set priceclassquota = 2, "
          + "price_class_unavailable_seats = 1 where priceclasscode = 'A1';")
  public void failure_PriceClassQuota() throws Exception {
    runTest();
  }

  private void fireRulesAndAssertExecuted(CartItem... newCartItem) {
    quotaCheckRule.setCartItems(Arrays.asList(newCartItem));

    getRulesEngine().registerRule(quotaCheckRule);
    getRulesEngine().fireRules();

    assertTrue(quotaCheckRule.isExecuted());
  }

  private void runTest() throws java.io.IOException {
    Map<String, Integer> priceClassMap = new HashMap<>();
    CartItem newCartItem =
        new CartItem.Builder().productId(245634L)
            .priceCatId(12176L).mode(InventoryConstants.SECTION_TYPE_RS.getName())
            .inventoryIdList(
                new HashSet<>(Arrays.asList(121555782L, 121555783L)))
            .priceClass("A1").build();

    CartItem newCartItem2 =
        new CartItem.Builder().productId(245634L)
            .priceCatId(12176L).mode(InventoryConstants.SECTION_TYPE_RS.getName())
            .inventoryIdList(
                new HashSet<>(Arrays.asList(121555784L, 121555785L)))
            .priceClass("A").build();
    fireRulesAndAssertExecuted(newCartItem, newCartItem2);
    Map.Entry<Rule, CartException> exceptionEntry = assertListenerAndGetExceptionEntry();
    assertThat(exceptionEntry.getValue().getMessage()).isNotEmpty().isEqualTo(
        messageSource.getMessage("pmt.error.quota", null, LocaleContextHolder.getLocale()));
  }
  
  @Test
  public void fireRules() throws Exception {
	  runTest();
  }
  
}