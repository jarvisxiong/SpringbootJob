package com.stixcloud.cart;

import static org.assertj.core.api.Assertions.assertThat;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stixcloud.cart.repo.ShoppingCartRepository;
import com.stixcloud.common.config.multitenant.TenantContextHolder;
import com.stixcloud.domain.DeliveryMethod;
import com.stixcloud.domain.ShoppingCart;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.javamoney.moneta.Money;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import javax.money.CurrencyUnit;
import javax.money.Monetary;
import javax.money.MonetaryAmount;
import redis.embedded.RedisExecProvider;
import redis.embedded.RedisServer;
import redis.embedded.RedisServerBuilder;
import redis.embedded.util.Architecture;
import redis.embedded.util.OS;

/**
 * Created by chongye on 24/10/2016.
 */
@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest
public class FeeServiceTest {
  private static final Logger logger = LogManager.getLogger(FeeServiceTest.class);

  private static RedisServer redisServer;
  @Autowired
  IFeeService feeService;

  @Autowired
  ShoppingCartRepository shoppingCartRepository;
  @Autowired
  private ObjectMapper objectMapper;

  @BeforeClass
  public static void setUp() throws Exception {
    RedisExecProvider
        customProvider =
        RedisExecProvider.defaultProvider()
            .override(OS.WINDOWS, Architecture.x86, "redis/redis-server-3.2.1.exe")
            .override(OS.WINDOWS, Architecture.x86_64, "redis/redis-server-3.2.1.exe");
    redisServer = new RedisServerBuilder().redisExecProvider(customProvider).build();
    redisServer.start();
    TenantPropertiesTest.setUp();
  }

  @AfterClass
  public static void tearDown() {
    redisServer.stop();
  }

  @Test
  @Sql({"/cart/sql/deliveryMethodFeeService.sql", "/cart/sql/FeeRuleFeeService.sql",
      "/cart/sql/ProductPriceFeeService.sql"})
  public void getCommonDeliveryMethods() throws Exception {
    ShoppingCart shoppingCart = objectMapper
        .readValue(this.getClass().getResource("/cart/json/ShoppingCart.json"), ShoppingCart.class);
    shoppingCart = shoppingCartRepository.save(shoppingCart);
    assertThat(shoppingCart.getCartGuid()).isNotEmpty();

    List<DeliveryMethod> commonDeliveryMethod =
        feeService.getCommonDeliveryMethods(shoppingCart.getCartGuid(),
            TenantContextHolder.getTenant().getProfileInfoId());
    assertThat(commonDeliveryMethod).isNotEmpty().hasSize(7);
  }

  @Test
  @Sql({"/cart/sql/FeeRuleFeeService.sql", "/cart/sql/ProductPriceFeeService.sql"})
  public void getBookingFee() throws Exception {
    CurrencyUnit currencyUnit = Monetary.getCurrency(LocaleContextHolder.getLocale());
    MonetaryAmount monetaryAmount =
        feeService.getBookingFee(245634L, 12176L, "A",
            Money.of(ThreadLocalRandom.current().nextDouble(0.1, 20), currencyUnit));
    assertThat(monetaryAmount).isEqualByComparingTo(Money.of(1, currencyUnit));

    monetaryAmount =
        feeService.getBookingFee(245634L, 12176L, "A",
            Money.of(ThreadLocalRandom.current().nextDouble(20.01, 40), currencyUnit));
    assertThat(monetaryAmount).isEqualByComparingTo(Money.of(3, currencyUnit));

    monetaryAmount =
        feeService.getBookingFee(245634L, 12176L, "A",
            Money
                .of(ThreadLocalRandom.current().nextDouble(40.01, Double.MAX_VALUE), currencyUnit));
    assertThat(monetaryAmount).isEqualByComparingTo(Money.of(4, currencyUnit));
  }

  //Test if fees are also retrieved if using standard fee rule table
  @Test
  @Sql(value = {"/cart/sql/deliveryMethodFeeService.sql", "/cart/sql/FeeRuleFeeService.sql",
      "/cart/sql/ProductPriceFeeService.sql"},
      statements = "delete from FEE_RULE_TABLE where FEERULETABLEID = 25")
  public void testStandardFeeRuleTable() throws Exception {
    ShoppingCart shoppingCart = objectMapper
        .readValue(this.getClass().getResource("/cart/json/ShoppingCart.json"), ShoppingCart.class);
    shoppingCart = shoppingCartRepository.save(shoppingCart);
    assertThat(shoppingCart.getCartGuid()).isNotEmpty();

    List<DeliveryMethod> commonDeliveryMethod =
        feeService.getCommonDeliveryMethods(shoppingCart.getCartGuid(),
            TenantContextHolder.getTenant().getProfileInfoId());
    assertThat(commonDeliveryMethod).isNotEmpty().hasSize(7);

    CurrencyUnit currencyUnit = Monetary.getCurrency(LocaleContextHolder.getLocale());
    MonetaryAmount monetaryAmount =
        feeService.getBookingFee(245634L, 12176L, "A",
            Money.of(ThreadLocalRandom.current().nextDouble(0.1, 20), currencyUnit));
    assertThat(monetaryAmount).isEqualByComparingTo(Money.of(1, currencyUnit));

    monetaryAmount =
        feeService.getBookingFee(245634L, 12176L, "A",
            Money.of(ThreadLocalRandom.current().nextDouble(20.01, 40), currencyUnit));
    assertThat(monetaryAmount).isEqualByComparingTo(Money.of(3, currencyUnit));

    monetaryAmount =
        feeService.getBookingFee(245634L, 12176L, "A",
            Money
                .of(ThreadLocalRandom.current().nextDouble(40.01, Double.MAX_VALUE), currencyUnit));
    assertThat(monetaryAmount).isEqualByComparingTo(Money.of(4, currencyUnit));
  }
}