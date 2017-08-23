package com.stixcloud.cart;

import static org.assertj.core.api.Assertions.assertThat;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stixcloud.cart.api.AddToCartRequest;
import com.stixcloud.cart.repo.SalesSeatInventoryRepository;
import com.stixcloud.cart.repo.ShoppingCartRepository;
import com.stixcloud.domain.CartItem;
import com.stixcloud.domain.SalesSeatInventory;
import com.stixcloud.domain.ShoppingCart;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import redis.embedded.RedisExecProvider;
import redis.embedded.RedisServer;
import redis.embedded.RedisServerBuilder;
import redis.embedded.util.Architecture;
import redis.embedded.util.OS;

/**
 * Created by chongye on 7/10/2016.
 */
@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest
public class ShoppingCartServiceTest {
  private static RedisServer redisServer;
  private static final Logger logger = LogManager.getLogger(ShoppingCartServiceTest.class);

  @Autowired
  ShoppingCartService shoppingCartService;
  @Autowired
  ShoppingCartRepository shoppingCartRepository;
  @Autowired
  SalesSeatInventoryRepository salesSeatInventoryRepository;
  @Autowired
  private ObjectMapper objectMapper;
  @Autowired
  RedisTemplate redisTemplate;

  @BeforeClass
  public static void setUp() throws Exception {
    RedisExecProvider
        customProvider =
        RedisExecProvider.defaultProvider()
            .override(OS.WINDOWS, Architecture.x86, "redis/redis-server-3.2.1.exe")
            .override(OS.WINDOWS, Architecture.x86_64, "redis/redis-server-3.2.1.exe");
    redisServer = new RedisServerBuilder().redisExecProvider(customProvider).build();
    redisServer.start();
  }

  @AfterClass
  public static void tearDown() {
    redisServer.stop();
  }

  @Before
  public void setup() throws Exception {
    objectMapper.setTimeZone(TimeZone.getTimeZone("SGT"));
    objectMapper.setLocale(new Locale("en", "SG"));
    redisTemplate.getConnectionFactory().getConnection();
    TenantPropertiesTest.setUp();
  }

  @Test
  @Sql({"/cart/sql/availableInvForGA.sql", "/cart/sql/ValidateCartViewForGA.sql"})
  public void addToCartForGA() throws Exception {
    //new cart portion
    AddToCartRequest firstRequest =
        new AddToCartRequest.Builder().productId(245632L).priceClassMap("A:3").priceCatId(12176L)
            .seatSectionId(356880L).mode(InventoryConstants.SECTION_TYPE_GA.getName()).build();

    String cartGuid = shoppingCartService.addToCart(firstRequest, null).getCartGuid();
    assertThat(cartGuid).isNotEmpty();

    List<CartItem> firstCartItem =
        ReflectionTestUtils.invokeMethod(shoppingCartService, "createCartItems", firstRequest);

    //existing cart portion
    AddToCartRequest secondRequest =
        new AddToCartRequest.Builder(firstRequest).priceClassMap("A:2").cartGuid(cartGuid).build();
    cartGuid = shoppingCartService.addToCart(secondRequest, null).getCartGuid();
    assertThat(cartGuid).isNotEmpty();

    ShoppingCart cart = shoppingCartRepository.findOne(cartGuid);
    assertThat(cart).isNotNull();

    List<CartItem> secondCartItem =
        ReflectionTestUtils.invokeMethod(shoppingCartService, "createCartItems", secondRequest);

    assertThat(cart.getCartItems()).isNotEmpty().hasSize(2);
    assertThat(cart.getCartItems()).extracting("tuple")
        .containsOnly(firstCartItem.get(0).getTuple(), secondCartItem.get(0).getTuple());
    assertThat(cart.getCartItems()).extracting("priceClass")
        .containsOnly(firstCartItem.get(0).getPriceClass(), secondCartItem.get(0).getPriceClass());
    assertThat(cart.getCartItems()).extracting("mode")
        .containsOnly(InventoryConstants.SECTION_TYPE_GA.getName());
    assertThat(cart.getCartItems()).flatExtracting("inventoryIdList").hasSize(5);

    //check all seats are reserved
    List<Long> reservedSeats = new ArrayList<>();
    cart.getCartItems().stream().map(CartItem::getInventoryIdList).forEach(reservedSeats::addAll);

    Iterable<SalesSeatInventory>
        seatInventories =
        salesSeatInventoryRepository.findAll(reservedSeats);
    assertThat(seatInventories).isNotEmpty().extracting("seatsalesstatus")
        .containsOnly(InventoryConstants.SALES_STATUS_RESERVED.getValue());
  }

  /**
   * This test will throw a com.stixcloud.cart.AddToCartException: Exceeded allocation for cart
   * due to the price class map having 0 for it's quantity
   */
  @Test(expected = AddToCartException.class)
  @Sql({"/cart/sql/availableInvForGA.sql", "/cart/sql/ValidateCartViewForGA.sql"})
  public void addToCart_Exception() throws Exception {
    AddToCartRequest addToCartRequestGA =
        new AddToCartRequest.Builder().productId(245632L).priceClassMap("A:0").priceCatId(12176L)
            .seatSectionId(356880L).mode(InventoryConstants.SECTION_TYPE_GA.getName()).build();
    shoppingCartService.addToCart(addToCartRequestGA, null);
  }

  @Test
  @Sql({"/cart/sql/reservedInvForRS.sql", "/cart/sql/ValidateCartViewForRS.sql"})
  public void addToCartForRS() throws Exception {
    //new cart portion
    AddToCartRequest firstRequest =
        new AddToCartRequest.Builder().productId(245634L).priceClassMap("A1:1").priceCatId(12176L)
            .inventoryList(new HashSet<>(Arrays.asList(121555775L)))
            .mode(InventoryConstants.SECTION_TYPE_RS.getName()).build();

    String cartGuid = shoppingCartService.addToCart(firstRequest, null).getCartGuid();
    assertThat(cartGuid).isNotEmpty();

    CartItem firstCartItem = shoppingCartRepository.findOne(cartGuid).getCartItems().getFirst();
//    firstCartItem = new CartItem.Builder(firstCartItem).seatSectionId(356882L).build();

    //existing cart portion
    AddToCartRequest secondRequest =
        new AddToCartRequest.Builder(firstRequest).cartGuid(cartGuid)
            .inventoryList(new HashSet<>(Arrays.asList(121555776L))).build();

    cartGuid = shoppingCartService.addToCart(secondRequest, null).getCartGuid();
    assertThat(cartGuid).isNotEmpty();

    ShoppingCart cart = shoppingCartRepository.findOne(cartGuid);
    CartItem secondCartItem = shoppingCartRepository.findOne(cartGuid).getCartItems().getLast();
//        ReflectionTestUtils.invokeMethod(shoppingCartService, "createCartItems", secondRequest);
//    secondCartItem = new CartItem.Builder(secondCartItem).seatSectionId(356882L).build();

    assertThat(cart.getCartItems()).isNotEmpty().hasSize(2);
    assertThat(cart.getCartItems()).extracting("tuple")
        .containsOnly(firstCartItem.getTuple(), secondCartItem.getTuple());
    assertThat(cart.getCartItems()).extracting("priceClass")
        .containsOnly(firstCartItem.getPriceClass(), secondCartItem.getPriceClass());
    assertThat(cart.getCartItems()).extracting("mode")
        .containsOnly(InventoryConstants.SECTION_TYPE_RS.getName());
    assertThat(cart.getCartItems()).flatExtracting("inventoryIdList").hasSize(2);
    assertThat(cart.getCartItems()).flatExtracting("inventoryIdList")
        .containsOnly(121555775L, 121555776L);

    //check all seats are reserved
    List<Long> reservedSeats = new ArrayList<>();
    cart.getCartItems().stream().map(CartItem::getInventoryIdList).forEach(reservedSeats::addAll);

    Iterable<SalesSeatInventory>
        seatInventories =
        salesSeatInventoryRepository.findAll(reservedSeats);
    assertThat(seatInventories).isNotEmpty().extracting("seatsalesstatus")
        .containsOnly(InventoryConstants.SALES_STATUS_RESERVED.getValue());
  }

  @Test
  @Sql({"/cart/sql/reservedInvForRS.sql", "/cart/sql/ValidateCartViewForRS.sql"})
  public void getCart() throws Exception {
    AddToCartRequest request =
        new AddToCartRequest.Builder().productId(245634L).priceClassMap("A1:1").priceCatId(12176L)
            .inventoryList(new HashSet<>(Arrays.asList(121555776L))).seatSectionId(356882L)
            .mode(InventoryConstants.SECTION_TYPE_RS.getName()).build();

    String cartGuid = shoppingCartService.addToCart(request, null).getCartGuid();
    assertThat(cartGuid).isNotEmpty();

    ShoppingCart cart = shoppingCartService.getCart(cartGuid);
    assertThat(cart).isNotNull();
    //assertThat(cart).hasNoNullFieldsOrProperties();
    assertThat(cart).hasFieldOrPropertyWithValue("cartGuid", cartGuid);
    assertThat(cart.getCartItems()).isNotEmpty().hasSize(1);
    assertThat(cart.getCartItems().getFirst()).hasFieldOrPropertyWithValue("productId", 245634L);
    assertThat(cart.getCartItems().getFirst()).hasFieldOrPropertyWithValue("priceCatId", 12176L);
    assertThat(cart.getCartItems().getFirst())
        .hasFieldOrPropertyWithValue("seatSectionId", 356882L);
    assertThat(cart.getCartItems().getFirst())
        .hasFieldOrPropertyWithValue("mode", InventoryConstants.SECTION_TYPE_RS.getName());
    assertThat(cart.getCartItems().getFirst())
        .hasFieldOrPropertyWithValue("inventoryIdList", new HashSet<>(Arrays.asList(121555776L)));
    assertThat(cart.getCartItems().getFirst())
        .hasFieldOrPropertyWithValue("priceClass", "A1");
  }

  @Test
  public void getCommonPaymentMethods() throws Exception {

  }
}