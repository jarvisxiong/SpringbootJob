package com.stixcloud.cart.rules;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.stixcloud.cart.IShoppingCartService;
import com.stixcloud.cart.TenantPropertiesTest;
import com.stixcloud.cart.rules.postcommit.PostCommitOrder;
import com.stixcloud.cart.ticketprotector.rules.postcommit.TicketProtectorRule;
import com.stixcloud.common.config.multitenant.TenantContextHolder;
import com.stixcloud.domain.ShoppingCart;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.core.io.ResourceLoader;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import javax.money.CurrencyUnit;
import javax.money.Monetary;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest
public class TicketProtectorRuleTest extends BaseCartRuleTest {

  private static final Logger logger = LogManager.getLogger(CartTicketsLimitRuleTest.class);
  @Autowired
  private MessageSource messageSource;
  @Autowired
  private ResourceLoader resourceLoader;
  @Autowired
  private ObjectMapper objectMapper;
  @Autowired
  private TicketProtectorRule ticketProtectorRule;
  @Autowired
  private IShoppingCartService shoppingCartService;

  private ShoppingCart shoppingCart;
  private PostCommitOrder postCommitOrder;
  private CurrencyUnit currencyUnit;

  @Value("${waived.delivery.codes}")
  private String waivedDeliveryCodes;

  @Override
  @Before
  public void setUp() throws Exception {
    super.setUp();
    TenantPropertiesTest.setUp();

    currencyUnit = Monetary.getCurrency(LocaleContextHolder.getLocale());

    shoppingCart = objectMapper
        .readValue(this.getClass().getResource("/cart/json/ShoppingCart1.json"),
            ShoppingCart.class);

//    Resource resource = resourceLoader.getResource("classpath:cart1.json");
//    URL jsonAsFile = resource.getURL();
//
//    shoppingCart = objectMapper.readValue(jsonAsFile, ShoppingCart.class);

    postCommitOrder = new PostCommitOrder();
    postCommitOrder.setProfileInfoId(TenantContextHolder.getTenant().getProfileInfoId()); // 11L
    postCommitOrder.setUserProfileId(TenantContextHolder.getTenant().getUserInfoId());    // 59L
    postCommitOrder.setPatronProfileId(1922404L);
  }

  @Test
  @Sql({"/cart/sql/patronPhone.sql", "/cart/sql/patronAddresses.sql",
      "/cart/sql/RetrieveCartLineItems.sql", "/cart/sql/deliveryMethodFeeService.sql",
      "/cart/sql/FeeRuleFeeService.sql", "/cart/sql/ProductPriceFeeService.sql",
      "/cart/sql/getCart.sql"})
  public void runTicketProtectorCommit() throws Exception {
    ticketProtectorRule.setExecuted(true);
    ticketProtectorRule.setPostCommitOrder(postCommitOrder);
    ticketProtectorRule.setShoppingCart(shoppingCart);
    fireRulesAndAssertExecuted(ticketProtectorRule, shoppingCart);

  }

}
