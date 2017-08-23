package com.stixcloud.cart;

import static org.assertj.core.api.Java6Assertions.assertThat;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stixcloud.cart.api.CartLineItem;
import com.stixcloud.cart.api.DeliveryMethodsJson;
import com.stixcloud.cart.api.PaymentMethodsJson;
import com.stixcloud.cart.api.ShoppingCartJson;
import com.stixcloud.cart.repo.ShoppingCartRepository;
import com.stixcloud.common.config.multitenant.TenantContextHolder;
import com.stixcloud.domain.CartLineItemsView;
import com.stixcloud.domain.DeliveryMethod;
import com.stixcloud.domain.PaymentMethod;
import com.stixcloud.domain.ShoppingCart;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.LinkedList;
import java.util.List;
import javax.money.CurrencyUnit;
import javax.money.Monetary;
import redis.embedded.RedisExecProvider;
import redis.embedded.RedisServer;
import redis.embedded.RedisServerBuilder;
import redis.embedded.util.Architecture;
import redis.embedded.util.OS;

/**
 * Created by chongye on 7/11/2016.
 */
@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ShoppingCartJsonBuilderTest {
  private static final Logger logger = LogManager.getLogger(ShoppingCartJsonBuilderTest.class);
  private static RedisServer redisServer;

  @Autowired
  private IFeeService feeService;
  @Autowired
  private IShoppingCartService shoppingCartService;
  @Autowired
  private ShoppingCartRepository shoppingCartRepository;

  @Value("${waived.delivery.codes}")
  private String waivedDeliveryCodes;

  @Autowired
  private ObjectMapper objectMapper;
  @Autowired
  private RedisTemplate redisTemplate;

  @BeforeClass
  public static void setUp() throws Exception {
    RedisExecProvider customProvider = RedisExecProvider.defaultProvider()
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

  @Before
  public void before() {
    redisTemplate.getConnectionFactory().getConnection();
  }

  @Test
  @Sql({"/cart/sql/deliveryMethodFeeService.sql", "/cart/sql/FeeRuleFeeService.sql",
      "/cart/sql/ProductPriceFeeService.sql", "/cart/sql/getCart.sql"})
  public void build() throws Exception {
    Long profileInfoId = TenantContextHolder.getTenant().getProfileInfoId();
    CurrencyUnit currencyUnit = Monetary.getCurrency(LocaleContextHolder.getLocale());

    ShoppingCart cart = objectMapper
        .readValue(this.getClass().getResource("/cart/json/ShoppingCart.json"), ShoppingCart.class);
    cart = shoppingCartRepository.save(cart);
    assertThat(cart.getCartGuid()).isNotEmpty();

    List<DeliveryMethod>
        deliveryMethods =
        feeService.getCommonDeliveryMethods(cart.getCartGuid(), profileInfoId);
    List<PaymentMethod> paymentMethods =
        shoppingCartService.getCommonPaymentMethods(cart.getCartGuid(), profileInfoId);
    LinkedList<List<CartLineItemsView>> cartLineItems =
        shoppingCartService.getCartLineItems(cart.getCartGuid(), currencyUnit, profileInfoId);

    ShoppingCartJsonBuilder builder =
        new ShoppingCartJsonBuilder(feeService).cartLineItems(cartLineItems)
            .commonDeliveryMethods(deliveryMethods).commonPaymentMethods(paymentMethods)
            .currencyUnit(currencyUnit).waivedDeliveryCode(waivedDeliveryCodes);
    ShoppingCartJson shoppingCartJson = builder.build();

    assertThat(shoppingCartJson).isNotNull();
    assertThat(shoppingCartJson).hasNoNullFieldsOrProperties();
    logger
        .debug(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(shoppingCartJson));

    List<CartLineItem>
        lineItems = ReflectionTestUtils.invokeMethod(builder, "buildCartLineItems");
    assertThat(shoppingCartJson.getLineItemList()).isNotNull().isNotEmpty().hasSize(2)
        .doesNotContainNull().containsOnlyElementsOf(lineItems);
    logger.debug(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(lineItems));

    PaymentMethodsJson
        paymentMethodsJson =
        ReflectionTestUtils.invokeMethod(builder, "buildCommonPaymentMethods");
    assertThat(shoppingCartJson.getCommonPaymentMethod()).isNotNull()
        .isEqualToComparingFieldByFieldRecursively(paymentMethodsJson);
    logger
        .debug(
            objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(paymentMethodsJson));

    DeliveryMethodsJson
        deliveryMethodsJson =
        ReflectionTestUtils.invokeMethod(builder, "buildCommonDeliveryMethods");
    assertThat(shoppingCartJson.getCommonDeliveryMethod()).isNotNull()
        .isEqualToComparingFieldByFieldRecursively(deliveryMethodsJson);
    logger
        .debug(
            objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(deliveryMethodsJson));

    assertThat(shoppingCartJson.getUnitPrice()).isNotNull().isEqualTo(currencyUnit);
  }

  /**
   * Building cart line items will require unitPrice to be set as a CurrencyUnit is required
   * for calculating booking fees
   */
  @Test
  @Sql({"/cart/sql/deliveryMethodFeeService.sql", "/cart/sql/FeeRuleFeeService.sql",
      "/cart/sql/ProductPriceFeeService.sql", "/cart/sql/getCart.sql"})
  public void build_CartLineItemsOnly() throws Exception {
    Long profileInfoId = TenantContextHolder.getTenant().getProfileInfoId();
    CurrencyUnit currencyUnit = Monetary.getCurrency(LocaleContextHolder.getLocale());

    ShoppingCart cart = objectMapper
        .readValue(this.getClass().getResource("/cart/json/ShoppingCart.json"), ShoppingCart.class);
    cart = shoppingCartRepository.save(cart);
    assertThat(cart.getCartGuid()).isNotEmpty();

    LinkedList<List<CartLineItemsView>> cartLineItems =
        shoppingCartService.getCartLineItems(cart.getCartGuid(), currencyUnit, profileInfoId);

    ShoppingCartJsonBuilder builder =
        new ShoppingCartJsonBuilder(feeService).cartLineItems(cartLineItems)
            .currencyUnit(currencyUnit);
    ShoppingCartJson shoppingCartJson = builder.build();

    assertThat(shoppingCartJson).isNotNull();
    logger
        .debug(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(shoppingCartJson));
    List<CartLineItem>
        lineItems = ReflectionTestUtils.invokeMethod(builder, "buildCartLineItems");
    assertThat(shoppingCartJson.getLineItemList()).isNotNull().isNotEmpty().hasSize(2)
        .doesNotContainNull().containsOnlyElementsOf(lineItems);
    logger.debug(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(lineItems));
    assertThat(shoppingCartJson.getCommonPaymentMethod()).isNull();
    assertThat(shoppingCartJson.getCommonDeliveryMethod()).isNull();
    assertThat(shoppingCartJson.getUnitPrice()).isNotNull().isEqualTo(currencyUnit);
  }

  @Test
  public void build_EmptyCart() throws Exception {
    Long profileInfoId = TenantContextHolder.getTenant().getProfileInfoId();
    CurrencyUnit currencyUnit = Monetary.getCurrency(LocaleContextHolder.getLocale());

    ShoppingCart cart = objectMapper
        .readValue(this.getClass().getResource("/cart/json/ShoppingCart.json"), ShoppingCart.class);
    cart = new ShoppingCart.Builder(cart).cartItems(new LinkedList<>()).build();
    cart = shoppingCartRepository.save(cart);
    assertThat(cart.getCartGuid()).isNotEmpty();

    List<DeliveryMethod>
        deliveryMethods =
        feeService.getCommonDeliveryMethods(cart.getCartGuid(), profileInfoId);
    List<PaymentMethod> paymentMethods =
        shoppingCartService.getCommonPaymentMethods(cart.getCartGuid(), profileInfoId);
    LinkedList<List<CartLineItemsView>> cartLineItems =
        shoppingCartService.getCartLineItems(cart.getCartGuid(), currencyUnit, profileInfoId);

    ShoppingCartJson
        shoppingCartJson =
        new ShoppingCartJsonBuilder(feeService).cartLineItems(cartLineItems)
            .commonDeliveryMethods(deliveryMethods).commonPaymentMethods(paymentMethods)
            .build();

    assertThat(shoppingCartJson).isNotNull();
    logger
        .debug(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(shoppingCartJson));
    assertThat(shoppingCartJson.getLineItemList()).isNull();
    assertThat(shoppingCartJson.getCommonPaymentMethod()).isNull();
    assertThat(shoppingCartJson.getCommonDeliveryMethod()).isNull();
    assertThat(shoppingCartJson.getUnitPrice()).isNull();
  }

}