package com.stixcloud.cart.rules;

import static org.junit.Assert.assertFalse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stixcloud.cart.InventoryConstants;
import com.stixcloud.cart.rules.add.PriceModelTemplateRule;
import com.stixcloud.domain.CartItem;
import com.stixcloud.domain.ShoppingCart;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
import java.util.HashSet;

/**
 * Created by chongye on 11/10/2016.
 */
@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest
public class PriceModelTemplateRuleTest extends BaseCartRuleTest {
  private static final Logger logger = LogManager.getLogger(CartTicketsLimitRuleTest.class);
  @Autowired
  private MessageSource messageSource;
  @Autowired
  private ObjectMapper objectMapper;
  @Autowired
  private PriceModelTemplateRule priceModelTemplateRule;
  private ShoppingCart shoppingCart;

  @Override
  @Before
  public void setUp() throws Exception {
    super.setUp();
    shoppingCart = objectMapper
        .readValue(this.getClass().getResource("/cart/json/ShoppingCart.json"), ShoppingCart.class);
  }

  @Test
  @Sql("/cart/sql/ValidateCartViewForRS.sql")
  public void success() throws Exception {
    fireRulesAndAssertExecuted(this.priceModelTemplateRule, shoppingCart);
    assertFalse(getCartRulesListener().hasError());
  }

  @Test
  @Sql(value = "/cart/sql/ValidateCartViewForRS.sql",
      statements = "update validate_cart_view set quantitystatus = 1, quantity = 2"
          + " where priceclasscode = 'A1';")
  public void failure_AllocationCheck_ExactClause() throws Exception {
    runAllocationCheckTest();
  }

  @Test
  @Sql(value = "/cart/sql/ValidateCartViewForRS.sql",
      statements = "update validate_cart_view set quantitystatus = 2, quantity = 2"
          + " where priceclasscode = 'A1';")
  public void failure_AllocationCheck_MinimumClause() throws Exception {
    runAllocationCheckTest();
  }

  private void runAllocationCheckTest() throws java.io.IOException {
    fireRulesAndAssertException(this.priceModelTemplateRule, shoppingCart,
        messageSource.getMessage("pmt.error.allocation", null, LocaleContextHolder.getLocale()));
  }

  @Test
  @Sql(value = "/cart/sql/ValidateCartViewForRS.sql",
      statements = "update validate_cart_view set maximumquantity = 2 where priceclasscode = 'A1';")
  public void failure_MaxQuantityCheck() throws Exception {
    ShoppingCart shoppingCart = new ShoppingCart();
    CartItem
        cartItem =
        new CartItem.Builder().productId(245634L)
            .priceCatId(12176L).mode(InventoryConstants.SECTION_TYPE_RS.getName())
            .inventoryIdList(new HashSet<>(Arrays.asList(121555782L, 121555783L, 121555784L)))
            .priceClass("A1").build();
    shoppingCart.addToCart(Arrays.asList(cartItem));

    fireRulesAndAssertException(this.priceModelTemplateRule, shoppingCart,
        messageSource.getMessage("pmt.error.quantity", null, LocaleContextHolder.getLocale()));
  }

  @Test
  @Sql(value = "/cart/sql/ValidateCartViewForRS.sql",
      statements =
          "update validate_cart_view set MAXIMUMQUANTITY = 1, quantitystatus = 1, quantity = 3"
              + " where priceclasscode = 'A1';")
  public void failure_Generic() throws Exception {
    ShoppingCart shoppingCart = new ShoppingCart();
    CartItem cartItem =
        new CartItem.Builder().productId(245634L)
            .priceCatId(12176L).mode(InventoryConstants.SECTION_TYPE_RS.getName())
            .inventoryIdList(new HashSet<>(Arrays.asList(121555782L, 121555783L)))
            .priceClass("A1").build();
    shoppingCart.addToCart(Arrays.asList(cartItem));

    fireRulesAndAssertException(this.priceModelTemplateRule, shoppingCart,
        messageSource.getMessage("pmt.error.generic", null, LocaleContextHolder.getLocale()));
  }
}