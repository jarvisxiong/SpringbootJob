package com.stixcloud.cart.rules;

import static org.junit.Assert.assertFalse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stixcloud.cart.InventoryConstants;
import com.stixcloud.cart.repo.SalesSeatInventoryRepository;
import com.stixcloud.cart.rules.add.InventoryCheckRule;
import com.stixcloud.domain.CartItem;
import com.stixcloud.domain.SalesSeatInventory;
import com.stixcloud.domain.ShoppingCart;
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
import java.util.LinkedList;
import java.util.stream.Collectors;

/**
 * Created by chongye on 11/10/2016.
 */
@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest
public class InventoryCheckRuleTest extends BaseCartRuleTest {
  @Autowired
  private MessageSource messageSource;
  @Autowired
  private ObjectMapper objectMapper;
  @Autowired
  private InventoryCheckRule inventoryCheckRule;
  @Autowired
  SalesSeatInventoryRepository salesSeatInventoryRepository;
  private ShoppingCart shoppingCart;

  @Override
  @Before
  public void setUp() throws Exception {
    super.setUp();
    shoppingCart = objectMapper
        .readValue(this.getClass().getResource("/cart/json/ShoppingCart.json"), ShoppingCart.class);
  }

  @Test
  @Sql("/cart/sql/reservedInvForRS.sql")
  public void success() throws Exception {
    fireRulesAndAssertExecuted(this.inventoryCheckRule, shoppingCart);
    assertFalse(getCartRulesListener().hasError());
  }

  @Test
  @Sql(statements = "truncate table sales_seat_inventory;")
  public void checkEmpty() throws Exception {
    //empty the inventory list
    shoppingCart =
        new ShoppingCart.Builder(shoppingCart).cartItems(shoppingCart.getCartItems().stream().map(
            i -> new CartItem.Builder(i).inventoryIdList(new HashSet<>()).build())
            .collect(Collectors.toCollection(LinkedList::new))).build();
    fireRulesAndAssertException(this.inventoryCheckRule, shoppingCart,
        messageSource.getMessage("inventory.error.empty", null, LocaleContextHolder.getLocale()));
  }

  @Test
  @Sql(statements = "truncate table sales_seat_inventory;")
  public void checkDuplicates() throws Exception {
    shoppingCart =
        new ShoppingCart.Builder(shoppingCart).cartItems(shoppingCart.getCartItems().stream().map(
            i -> new CartItem.Builder(i).inventoryIdList(new HashSet<>(Arrays.asList(121555781L)))
                .build())
            .collect(Collectors.toCollection(LinkedList::new))).build();
    fireRulesAndAssertException(this.inventoryCheckRule, shoppingCart,
        messageSource
            .getMessage("inventory.error.duplicates", null, LocaleContextHolder.getLocale()));
  }

  @Test
  public void checkInventoriesAndPriceClassMapTally() throws Exception {
    //change price class map quantity to an erroneous value
    CartItem cartItem = new CartItem.Builder(shoppingCart.getCartItems().get(0))
        .inventoryIdList(new HashSet<>()).quantity(1).build();
    shoppingCart.getCartItems().set(0, cartItem);
    fireRulesAndAssertException(this.inventoryCheckRule, shoppingCart,
        messageSource
            .getMessage("inventory.error.quantity.error", null, LocaleContextHolder.getLocale()));
  }

  /**
   * When database does not contain the inventory ids
   */
  @Test
  @Sql(statements = "truncate table sales_seat_inventory;")
  public void checkAgainstDatabase_NotExists() throws Exception {
    inventoryCheckRule.setShoppingCart(shoppingCart);

    fireRulesAndAssertException(this.inventoryCheckRule, shoppingCart,
        messageSource.getMessage("inventory.error.check", null, LocaleContextHolder.getLocale()));
  }

  /**
   * When inventory ids pull from database does not tally with inventory list
   */
  @Test
  @Sql("/cart/sql/reservedInvForRS.sql")
  public void checkAgainstDatabase_NotTally() throws Exception {
    CartItem cartItem = new CartItem.Builder(shoppingCart.getCartItems().get(0))
        .quantity(2).build();
    cartItem.getInventoryIdList().add(121555776L);
    shoppingCart.getCartItems().set(0, cartItem);
    inventoryCheckRule.setShoppingCart(shoppingCart);

    //update one of the seats to available
    SalesSeatInventory inventory = salesSeatInventoryRepository.findOne(121555775L);
    inventory.setReserveexpirydate(null);
    inventory.setSeatsalesstatus(InventoryConstants.SALES_STATUS_AVAILABLE.getValue());
    salesSeatInventoryRepository.save(inventory);

    fireRulesAndAssertException(this.inventoryCheckRule, shoppingCart,
        messageSource.getMessage("inventory.error.check", null, LocaleContextHolder.getLocale()));
  }
}