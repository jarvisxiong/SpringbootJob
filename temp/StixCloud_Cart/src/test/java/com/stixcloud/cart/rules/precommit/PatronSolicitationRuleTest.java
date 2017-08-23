package com.stixcloud.cart.rules.precommit;

import static org.junit.Assert.assertFalse;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stixcloud.cart.IPatronSolicitationService;
import com.stixcloud.cart.api.AddressJson;
import com.stixcloud.cart.api.PatronSolicitation;
import com.stixcloud.cart.rules.BaseCartRuleTest;
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
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * Created by sengkai on 12/29/2016.
 */
@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest
public class PatronSolicitationRuleTest extends BaseCartRuleTest {
  private static final Logger logger = LogManager.getLogger(PatronSolicitationRuleTest.class);
  @Autowired
  IPatronSolicitationService iPatronSolicitationService;
  @Autowired
  private MessageSource messageSource;
  @Autowired
  private ObjectMapper objectMapper;
  @Autowired
  private PatronSolicitationRule patronSolicitationRule;
  private ShoppingCart shoppingCart;

  @Test
  @Sql({"/cart/sql/patronAddresses.sql", "/cart/sql/patronSolicitation.sql"})
  public void success() throws Exception {
    Long patronProfileId = 540711L;
    Long updatedBy = 59L;

    this.shoppingCart = objectMapper
        .readValue(this.getClass().getResource("/cart/json/ShoppingCart.json"), ShoppingCart.class);

    List<AddressJson> addressJson = objectMapper
        .readValue(this.getClass().getResource("/cart/json/getPatronAddress.json"),
            new TypeReference<List<AddressJson>>() {
            });

    PatronSolicitation patronSolicitationJson = objectMapper
        .readValue(this.getClass().getResource("/cart/json/getPatronSolicitationRule.json"),
            PatronSolicitation.class);

    addressJson.stream().forEach(System.out::println);

    this.shoppingCart =
        new ShoppingCart.Builder(shoppingCart).addressList(addressJson)
            .patronSolicitation(patronSolicitationJson).build();
    logger.debug("Show only getPatronSolicitation >>> " + this.shoppingCart.getPatronSolicitation()
        .toString());
    logger.debug(
        "Size of this array >> " + this.shoppingCart.getPatronSolicitation().getSolicitationList()
            .size());

    this.patronSolicitationRule.setExecuted(true);
    this.patronSolicitationRule.setPatronProfileId(patronProfileId);
    this.patronSolicitationRule.setUpdatedBy(updatedBy);

    fireRulesAndAssertExecuted(this.patronSolicitationRule, this.shoppingCart);
    assertFalse(getCartRulesListener().hasError());
  }

  @Test
  @Sql({"/cart/sql/patronAddresses.sql", "/cart/sql/patronSolicitation.sql"})
  public void failureToFindLocalAddress() throws Exception {
    Long patronProfileId = 1921979L;
    Long updatedBy = 59L;

    this.shoppingCart = objectMapper
        .readValue(this.getClass().getResource("/cart/json/ShoppingCart.json"), ShoppingCart.class);

    List<AddressJson> addressJson = objectMapper
        .readValue(this.getClass().getResource("/cart/json/getPatronAddress.json"),
            new TypeReference<List<AddressJson>>() {
            });
    addressJson.stream().forEach(System.out::println);

    PatronSolicitation patronSolicitationJson = objectMapper
        .readValue(
            this.getClass().getResource("/cart/json/getPatronSolicitationInvalidAddressRule.json"),
            PatronSolicitation.class);

    this.shoppingCart =
        new ShoppingCart.Builder(shoppingCart)
            .addressList(addressJson)
            .patronSolicitation(patronSolicitationJson).build();
    logger.debug("Show only getPatronSolicitation >>> " + this.shoppingCart.getPatronSolicitation()
        .toString());
    logger.debug(
        "Size of this array >> " + this.shoppingCart.getPatronSolicitation().getSolicitationList()
            .size());

    this.patronSolicitationRule.setExecuted(true);
    this.patronSolicitationRule.setPatronProfileId(patronProfileId);
    this.patronSolicitationRule.setUpdatedBy(updatedBy);
    this.patronSolicitationRule.setAddressJsonList(addressJson);
    this.patronSolicitationRule.setPatronSolicitation(patronSolicitationJson);
    this.patronSolicitationRule.setShoppingCart(this.shoppingCart);

    fireRulesAndAssertException(this.patronSolicitationRule, this.shoppingCart,
        messageSource.getMessage("precommit.error.patronSolicitation.invalidMailingCountry", null,
            LocaleContextHolder.getLocale()));
  }
}
