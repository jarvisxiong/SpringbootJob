package com.stixcloud.evoucher.api;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stixcloud.cart.TenantPropertiesTest;
import com.stixcloud.cart.api.AddToCartRequest;
import com.stixcloud.cart.repo.ShoppingCartRepository;
import com.stixcloud.domain.CartItem;
import com.stixcloud.domain.ShoppingCart;

import redis.embedded.RedisExecProvider;
import redis.embedded.RedisServer;
import redis.embedded.RedisServerBuilder;
import redis.embedded.util.Architecture;
import redis.embedded.util.OS;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class EvoucherValidationControllerTest {
  private final Logger logger = LogManager.getLogger(EvoucherValidationControllerTest.class);

  private MockMvc mockMvc;
  private static RedisServer redisServer;

  @Autowired
  private WebApplicationContext wac;

  @Autowired
  private ObjectMapper objectMapper;
  @Autowired
  private TestRestTemplate restTemplate;

  @Autowired
  private ShoppingCartRepository shoppingCartRepository;

  private JacksonTester<EvoucherValidationResponse> evoucherValidationResJacksonTester;
  @Value("${spring.mvc.locale}")
  private Locale locale;

  @BeforeClass
  public static void setUp() throws Exception {
    RedisExecProvider customProvider = RedisExecProvider.defaultProvider()
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
  public void before() throws Exception {
    JacksonTester.initFields(this, objectMapper);
    MockitoAnnotations.initMocks(this);
    mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    setUpRedisData();
    TenantPropertiesTest.setUp();
  }

  @After
  public void after() {
    shoppingCartRepository.delete("afabb997-928e-4da3-92eb-fb4e3a400cbe");
  }

  public void setUpRedisData() throws Exception {
    ShoppingCart shoppingCart =
        new ShoppingCart.Builder().cartGuid("afabb997-928e-4da3-92eb-fb4e3a400cbe").build();
    AddToCartRequest addToCartRequest = objectMapper.readValue(
        this.getClass().getResource("/evoucher/json/addToCart.json"), AddToCartRequest.class);

    List<CartItem> newCartItem = new ArrayList<>();
    CartItem cartItem = new CartItem.Builder().cartItemId(UUID.randomUUID().toString())
        .productId(addToCartRequest.getProductId()).priceCatId(addToCartRequest.getPriceCatId())
        .seatSectionId(addToCartRequest.getSeatSectionId()).mode(addToCartRequest.getMode())
        .build();
    // ReflectionTestUtils.invokeMethod(shoppingCartService, "createCartItems", addToCartRequest);
    newCartItem.add(cartItem);
    shoppingCart.addToCart(newCartItem);
    shoppingCartRepository.save(shoppingCart);
  }

  @Test
  @Sql({"/evoucher/sql/evoucherData_pass.sql", "/evoucher/sql/productPromoterVenue.sql",
      "/evoucher/sql/paymentMethodsData.sql", "/cart/sql/availableInvForGA.sql"})
  public void runDbsEvoucherPass() throws Exception {
    assertResult("/evoucher/json/evoucherValidationRequest_dbsEvoucher.json",
        "/evoucher/json/evoucherValidationResponse_dbsEvoucher_Pass.json");
  }

  @Test
  @Sql({"/evoucher/sql/evoucherData_Venue_Failed.sql", "/evoucher/sql/productPromoterVenue.sql",
      "/evoucher/sql/paymentMethodsData.sql", "/cart/sql/availableInvForGA.sql"})
  public void runDbsEvoucherVenueFailed() throws Exception {
    assertResult("/evoucher/json/evoucherValidationRequest_dbsEvoucher.json",
        "/evoucher/json/evoucherValidationResponse_dbsEvoucher_Venue_Failed.json");

  }

  @Test
  @Sql({"/evoucher/sql/evoucherData_Redeem_Within_Failed.sql",
      "/evoucher/sql/productPromoterVenue.sql",
      "/evoucher/sql/paymentMethodsData_Evoucher_restrictionsIn_FAIL.sql",
      "/cart/sql/availableInvForGA.sql"})
  public void runDbsEvoucherRedeemWithinFailed() throws Exception {
    assertResult("/evoucher/json/evoucherValidationRequest_dbsEvoucher.json",
        "/evoucher/json/evoucherValidationResponse_dbsEvoucher_Redeem_Within_Failed.json");
  }

  @Test
  @Sql({"/evoucher/sql/evoucherData_Redeem_Within_Pass.sql",
      "/evoucher/sql/productPromoterVenue.sql",
      "/evoucher/sql/paymentMethodsData_Evoucher_restrictionsIn_Pass.sql",
      "/cart/sql/availableInvForGA.sql"})
  public void runDbsEvoucherRedeemWithinPass() throws Exception {
    assertResult("/evoucher/json/evoucherValidationRequest_dbsEvoucher.json",
        "/evoucher/json/evoucherValidationResponse_dbsEvoucher_Pass.json");
  }

  @Test
  @Sql({"/evoucher/sql/evoucherData_Redeem_Without_Failed.sql",
      "/evoucher/sql/productPromoterVenue.sql",
      "/evoucher/sql/paymentMethodsData_Evoucher_restrictionsOut_FAIL.sql",
      "/cart/sql/availableInvForGA.sql"})
  public void runDbsEvoucherRedeemWithoutFailed() throws Exception {
    assertResult("/evoucher/json/evoucherValidationRequest_dbsEvoucher.json",
        "/evoucher/json/evoucherValidationResponse_dbsEvoucher_Redeem_Without_Failed.json");
  }

  @Test
  @Sql({"/evoucher/sql/evoucherData_Redeem_Without_Pass.sql",
      "/evoucher/sql/productPromoterVenue.sql",
      "/evoucher/sql/paymentMethodsData_Evoucher_restrictionsOut_Pass.sql",
      "/cart/sql/availableInvForGA.sql"})
  public void runDbsEvoucherRedeemWithoutPass() throws Exception {
    assertResult("/evoucher/json/evoucherValidationRequest_dbsEvoucher.json",
        "/evoucher/json/evoucherValidationResponse_dbsEvoucher_Pass.json");
  }

  @Test
  @Sql({"/evoucher/sql/evoucherData_Redeem_All_Failed.sql",
      "/evoucher/sql/productPromoterVenue.sql",
      "/evoucher/sql/paymentMethodsData_Evoucher_restrictionsOut_FAIL.sql",
      "/cart/sql/availableInvForGA.sql"})
  public void runDbsEvoucherRedeemAllFailed() throws Exception {
    assertResult("/evoucher/json/evoucherValidationRequest_dbsEvoucher.json",
        "/evoucher/json/evoucherValidationResponse_dbsEvoucher_Redeem_All_Failed.json");
  }

  @Test
  @Sql({"/evoucher/sql/evoucherData_Redeem_None.sql", "/evoucher/sql/productPromoterVenue.sql",
      "/evoucher/sql/paymentMethodsData_Evoucher_restrictionsOut_FAIL.sql",
      "/cart/sql/availableInvForGA.sql"})
  public void runDbsEvoucherRedeemNone() throws Exception {
    assertResult("/evoucher/json/evoucherValidationRequest_dbsEvoucher.json",
        "/evoucher/json/evoucherValidationResponse_dbsEvoucher_Pass.json");
  }

  @Test
  @Sql({"/evoucher/sql/evoucherData_pass.sql", "/evoucher/sql/productPromoterVenue.sql",
      "/evoucher/sql/paymentMethodsData.sql", "/evoucher/sql/creditCardData.sql",
      "/cart/sql/availableInvForGA.sql"})
  public void runDbsEvoucherCreditCardFailed() throws Exception {
    assertResult("/evoucher/json/evoucherValidationRequest_creditCard_invalid.json",
        "/evoucher/json/evoucherValidationResponse_dbsEvoucher_CreditCard_Failed.json");
  }

  @Test
  @Sql({"/evoucher/sql/evoucherData_MaxQuantity_Failed.sql",
      "/evoucher/sql/productPromoterVenue.sql", "/evoucher/sql/paymentMethodsData.sql",
      "/cart/sql/availableInvForGA.sql"})
  public void runDbsEvoucherMaxQuantityFailed() throws Exception {
    assertResult("/evoucher/json/evoucherValidationRequest_maxQty.json",
        "/evoucher/json/evoucherValidationResponse_dbsEvoucher_MaxQuantity_Failed.json");
  }

  @Test
  @Sql({"/evoucher/sql/evoucherData_MultiUsage_Failed.sql",
      "/evoucher/sql/productPromoterVenue.sql", "/evoucher/sql/paymentMethodsData.sql",
      "/cart/sql/availableInvForGA.sql"})
  public void runDbsEvoucherMultiUsageFailed() throws Exception {
    assertResult("/evoucher/json/evoucherValidationRequest_maxQty.json",
        "/evoucher/json/evoucherValidationResponse_dbsEvoucher_MultiUsage_Failed.json");
  }

  @Test
  @Sql({"/evoucher/sql/evoucherData_Basic_Expired.sql", "/evoucher/sql/productPromoterVenue.sql",
      "/evoucher/sql/paymentMethodsData.sql", "/cart/sql/availableInvForGA.sql"})
  public void runDbsEvoucherBasicExpired() throws Exception {
    assertResult("/evoucher/json/evoucherValidationRequest_dbsEvoucher.json",
        "/evoucher/json/evoucherValidationResponse_dbsEvoucher_Basic_Expired.json");
  }

  @Test
  @Sql({"/evoucher/sql/evoucherData_Basic_Inactive.sql", "/evoucher/sql/productPromoterVenue.sql",
      "/evoucher/sql/paymentMethodsData.sql", "/cart/sql/availableInvForGA.sql"})
  public void runDbsEvoucherBasicInactive() throws Exception {
    assertResult("/evoucher/json/evoucherValidationRequest_dbsEvoucher.json",
        "/evoucher/json/evoucherValidationResponse_dbsEvoucher_Basic_Inactive.json");
  }

  @Test
  @Sql({"/evoucher/sql/evoucherData_Basic_Redeemed.sql", "/evoucher/sql/productPromoterVenue.sql",
      "/evoucher/sql/paymentMethodsData.sql", "/cart/sql/availableInvForGA.sql"})
  public void runDbsEvoucherBasicRedeemed() throws Exception {
    assertResult("/evoucher/json/evoucherValidationRequest_dbsEvoucher.json",
        "/evoucher/json/evoucherValidationResponse_dbsEvoucher_Basic_Redeemed.json");
  }

  @Test
  @Sql({"/evoucher/sql/evoucherData_Organization_Failed.sql",
      "/evoucher/sql/productPromoterVenue.sql", "/evoucher/sql/paymentMethodsData.sql",
      "/cart/sql/availableInvForGA.sql"})
  public void runDbsEvoucherOrganizationFailed() throws Exception {
    assertResult("/evoucher/json/evoucherValidationRequest_dbsEvoucher.json",
        "/evoucher/json/evoucherValidationResponse_dbsEvoucher_Organization_Failed.json");
  }

  @Test
  @Sql({"/evoucher/sql/evoucherData_pass.sql", "/evoucher/sql/productPromoterVenue.sql",
      "/evoucher/sql/paymentMethodsData.sql", "/cart/sql/availableInvForGA.sql"})
  public void runEsplanadeEvoucherPass() throws Exception {
    assertResult("/evoucher/json/evoucherValidationRequest_esplanadeEvoucher.json",
        "/evoucher/json/evoucherValidationResponse_esplanadeEvoucher_Pass.json");
  }

  @Test
  @Sql({"/evoucher/sql/evoucherData_Venue_Failed.sql", "/evoucher/sql/productPromoterVenue.sql",
      "/evoucher/sql/paymentMethodsData.sql", "/cart/sql/availableInvForGA.sql"})
  public void runEsplanadeEvoucherVenueFailed() throws Exception {
    assertResult("/evoucher/json/evoucherValidationRequest_esplanadeEvoucher.json",
        "/evoucher/json/evoucherValidationResponse_esplanadeEvoucher_Venue_Failed.json");
  }

  @Test
  @Sql({"/evoucher/sql/evoucherData_Basic_Expired.sql", "/evoucher/sql/productPromoterVenue.sql",
      "/evoucher/sql/paymentMethodsData.sql", "/cart/sql/availableInvForGA.sql"})
  public void runEsplanadeEvoucherBasicExpired() throws Exception {
    assertResult("/evoucher/json/evoucherValidationRequest_esplanadeEvoucher.json",
        "/evoucher/json/evoucherValidationResponse_esplanadeEvoucher_Basic_Expired.json");
  }

  @Test
  @Sql({"/evoucher/sql/evoucherData_Basic_Inactive.sql", "/evoucher/sql/productPromoterVenue.sql",
      "/evoucher/sql/paymentMethodsData.sql", "/cart/sql/availableInvForGA.sql"})
  public void runEsplanadeEvoucherBasicInactive() throws Exception {
    assertResult("/evoucher/json/evoucherValidationRequest_esplanadeEvoucher.json",
        "/evoucher/json/evoucherValidationResponse_esplanadeEvoucher_Basic_Inactive.json");

  }

  @Test
  @Sql({"/evoucher/sql/evoucherData_Basic_Redeemed.sql", "/evoucher/sql/productPromoterVenue.sql",
      "/evoucher/sql/paymentMethodsData.sql", "/cart/sql/availableInvForGA.sql"})
  public void runEsplanadeEvoucherBasicRedeemed() throws Exception {
    assertResult("/evoucher/json/evoucherValidationRequest_esplanadeEvoucher.json",
        "/evoucher/json/evoucherValidationResponse_esplanadeEvoucher_Basic_Redeemed.json");
  }

  @Test
  @Sql({"/evoucher/sql/evoucherData_Redeem_Within_Pass.sql",
      "/evoucher/sql/productPromoterVenue.sql",
      "/evoucher/sql/paymentMethodsData_Evoucher_restrictionsIn_Pass.sql",
      "/cart/sql/availableInvForGA.sql"})
  public void runEsplanadeEvoucherRedeemWithinPass() throws Exception {
    assertResult("/evoucher/json/evoucherValidationRequest_esplanadeEvoucher.json",
        "/evoucher/json/evoucherValidationResponse_esplanadeEvoucher_Pass.json");
  }

  @Test
  @Sql({"/evoucher/sql/evoucherData_Redeem_Within_Failed.sql",
      "/evoucher/sql/productPromoterVenue.sql",
      "/evoucher/sql/paymentMethodsData_Evoucher_restrictionsIn_FAIL.sql",
      "/cart/sql/availableInvForGA.sql"})
  public void runEsplanadeEvoucherRedeemWithinFailed() throws Exception {
    assertResult("/evoucher/json/evoucherValidationRequest_esplanadeEvoucher.json",
        "/evoucher/json/evoucherValidationResponse_esplanadeEvoucher_Redeem_Within_Failed.json");
  }

  @Test
  @Sql({"/evoucher/sql/evoucherData_Redeem_Without_Pass.sql",
      "/evoucher/sql/productPromoterVenue.sql",
      "/evoucher/sql/paymentMethodsData_Evoucher_restrictionsOut_Pass.sql",
      "/cart/sql/availableInvForGA.sql"})
  public void runEsplanadeEvoucherRedeemWithoutPass() throws Exception {
    assertResult("/evoucher/json/evoucherValidationRequest_esplanadeEvoucher.json",
        "/evoucher/json/evoucherValidationResponse_esplanadeEvoucher_Pass.json");
  }


  @Test
  @Sql({"/evoucher/sql/evoucherData_Redeem_Without_Failed.sql",
      "/evoucher/sql/productPromoterVenue.sql",
      "/evoucher/sql/paymentMethodsData_Evoucher_restrictionsOut_FAIL.sql",
      "/cart/sql/availableInvForGA.sql"})
  public void runEsplanadeEvoucherRedeemWithoutFailed() throws Exception {
    assertResult("/evoucher/json/evoucherValidationRequest_esplanadeEvoucher.json",
        "/evoucher/json/evoucherValidationResponse_esplanadeEvoucher_Redeem_Without_Failed.json");
  }

  @Test
  @Sql({"/evoucher/sql/evoucherData_Redeem_All_Failed.sql",
      "/evoucher/sql/productPromoterVenue.sql",
      "/evoucher/sql/paymentMethodsData_Evoucher_restrictionsOut_FAIL.sql",
      "/cart/sql/availableInvForGA.sql"})
  public void runEsplanadeEvoucherRedeemAllFailed() throws Exception {
    assertResult("/evoucher/json/evoucherValidationRequest_esplanadeEvoucher.json",
        "/evoucher/json/evoucherValidationResponse_esplanadeEvoucher_Redeem_All_Failed.json");
  }

  @Test
  @Sql({"/evoucher/sql/evoucherData_Redeem_None.sql", "/evoucher/sql/productPromoterVenue.sql",
      "/evoucher/sql/paymentMethodsData_Evoucher_restrictionsOut_FAIL.sql",
      "/cart/sql/availableInvForGA.sql"})
  public void runEsplanadeEvoucherRedeemNone() throws Exception {
    assertResult("/evoucher/json/evoucherValidationRequest_esplanadeEvoucher.json",
        "/evoucher/json/evoucherValidationResponse_esplanadeEvoucher_Pass.json");
  }

  @Test
  @Sql({"/evoucher/sql/evoucherData_Membership_Patron_Failed.sql",
      "/evoucher/sql/productPromoterVenue.sql", "/evoucher/sql/paymentMethodsData.sql",
      "/cart/sql/availableInvForGA.sql"})
  public void runEsplanadeEvoucherMembershipPatronFailed() throws Exception {

    assertResult("/evoucher/json/evoucherValidationRequest_esplanadeEvoucher.json",
        "/evoucher/json/evoucherValidationResponse_esplanadeEvoucher_membership_patron_Failed.json");
  }

  @Test
  @Sql({"/evoucher/sql/evoucherData_MaxQuantity_Failed.sql",
      "/evoucher/sql/productPromoterVenue.sql", "/evoucher/sql/paymentMethodsData.sql",
      "/cart/sql/availableInvForGA.sql"})
  public void runEsplanadeEvoucherMaxQuantityFailed() throws Exception {

    assertResult("/evoucher/json/evoucherValidationRequest_esplanade_evoucher_maxQty.json",
        "/evoucher/json/evoucherValidationResponse_esplanadeEvoucher_MaxQuantity_Failed.json");
  }

  @Test
  @Sql({"/evoucher/sql/evoucherData_MultiUsage_Failed.sql",
      "/evoucher/sql/productPromoterVenue.sql", "/evoucher/sql/paymentMethodsData.sql",
      "/cart/sql/availableInvForGA.sql"})
  public void runEsplanadeEvoucherMultiUsageFailed() throws Exception {

    assertResult("/evoucher/json/evoucherValidationRequest_esplanade_evoucher_maxQty.json",
        "/evoucher/json/evoucherValidationResponse_esplanadeEvoucher_MultiUsage_Failed.json");
  }

  @Test
  @Sql({"/evoucher/sql/evoucherData_Organization_Failed.sql",
      "/evoucher/sql/productPromoterVenue.sql", "/evoucher/sql/paymentMethodsData.sql",
      "/cart/sql/availableInvForGA.sql"})
  public void runEsplanadeEvoucherOrganizationFailed() throws Exception {

    assertResult("/evoucher/json/evoucherValidationRequest_esplanadeEvoucher.json",
        "/evoucher/json/evoucherValidationResponse_esplanadeEvoucher_Organization_Failed.json");
  }

  @Test
  @Sql({"/evoucher/sql/evoucherData_pass.sql", "/evoucher/sql/productPromoterVenue.sql",
      "/evoucher/sql/paymentMethodsData.sql", "/evoucher/sql/creditCardData.sql",
      "/cart/sql/availableInvForGA.sql"})
  public void runMasterEvoucherPass() throws Exception {

    assertResult("/evoucher/json/evoucherValidationRequest_masterEvoucher.json",
        "/evoucher/json/evoucherValidationResponse_masterEvoucher_Pass.json");
  }

  @Test
  @Sql({"/evoucher/sql/evoucherData_Redeem_Within_Pass.sql",
      "/evoucher/sql/productPromoterVenue.sql",
      "/evoucher/sql/paymentMethodsData_Evoucher_restrictionsIn_Pass.sql",
      "/cart/sql/availableInvForGA.sql"})
  public void runMasterEvoucherRedeemWithinPass() throws Exception {
    assertResult("/evoucher/json/evoucherValidationRequest_masterEvoucher.json",
        "/evoucher/json/evoucherValidationResponse_masterEvoucher_Pass.json");
  }

  @Test
  @Sql({"/evoucher/sql/evoucherData_Redeem_Within_Failed.sql",
      "/evoucher/sql/productPromoterVenue.sql",
      "/evoucher/sql/paymentMethodsData_Evoucher_restrictionsIn_FAIL.sql",
      "/cart/sql/availableInvForGA.sql"})
  public void runMasterEvoucherRedeemWithinFailed() throws Exception {
    assertResult("/evoucher/json/evoucherValidationRequest_masterEvoucher.json",
        "/evoucher/json/evoucherValidationResponse_masterEvoucher_Redeem_Within_Failed.json");

  }

  @Test
  @Sql({"/evoucher/sql/evoucherData_Redeem_Without_Pass.sql",
      "/evoucher/sql/productPromoterVenue.sql",
      "/evoucher/sql/paymentMethodsData_Evoucher_restrictionsOut_Pass.sql",
      "/cart/sql/availableInvForGA.sql"})
  public void runMasterEvoucherRedeemWithoutPass() throws Exception {
    assertResult("/evoucher/json/evoucherValidationRequest_masterEvoucher.json",
        "/evoucher/json/evoucherValidationResponse_masterEvoucher_Pass.json");
  }


  @Test
  @Sql({"/evoucher/sql/evoucherData_Redeem_Without_Failed.sql",
      "/evoucher/sql/productPromoterVenue.sql",
      "/evoucher/sql/paymentMethodsData_Evoucher_restrictionsOut_FAIL.sql",
      "/cart/sql/availableInvForGA.sql"})
  public void runMasterEvoucherRedeemWithoutFailed() throws Exception {
    assertResult("/evoucher/json/evoucherValidationRequest_masterEvoucher.json",
        "/evoucher/json/evoucherValidationResponse_masterEvoucher_Redeem_Without_Failed.json");

  }

  @Test
  @Sql({"/evoucher/sql/evoucherData_Redeem_All_Failed.sql",
      "/evoucher/sql/productPromoterVenue.sql",
      "/evoucher/sql/paymentMethodsData_Evoucher_restrictionsOut_FAIL.sql",
      "/cart/sql/availableInvForGA.sql"})
  public void runMasterEvoucherRedeemAllFailed() throws Exception {
    assertResult("/evoucher/json/evoucherValidationRequest_masterEvoucher.json",
        "/evoucher/json/evoucherValidationResponse_masterEvoucher_Redeem_All_Failed.json");

  }

  @Test
  @Sql({"/evoucher/sql/evoucherData_Redeem_None.sql", "/evoucher/sql/productPromoterVenue.sql",
      "/evoucher/sql/paymentMethodsData_Evoucher_restrictionsOut_FAIL.sql",
      "/cart/sql/availableInvForGA.sql"})
  public void runMasterEvoucherRedeemNone() throws Exception {
    assertResult("/evoucher/json/evoucherValidationRequest_masterEvoucher.json",
        "/evoucher/json/evoucherValidationResponse_masterEvoucher_Pass.json");

  }

  @Test
  @Sql({"/evoucher/sql/evoucherData_pass.sql", "/evoucher/sql/productPromoterVenue.sql",
      "/evoucher/sql/paymentMethodsData.sql", "/evoucher/sql/creditCardData.sql",
      "/cart/sql/availableInvForGA.sql"})
  public void runMasterEvoucherCreditCardFailed() throws Exception {
    assertResult("/evoucher/json/evoucherValidationRequest_master_creditCard_invalid.json",
        "/evoucher/json/evoucherValidationResponse_masterEvoucher_CreditCard_Failed.json");

  }

  @Test
  @Sql({"/evoucher/sql/evoucherData_MultiUsage_Failed.sql",
      "/evoucher/sql/productPromoterVenue.sql", "/evoucher/sql/paymentMethodsData.sql",
      "/cart/sql/availableInvForGA.sql"})
  public void runMasterEvoucherMultiUsageFailed() throws Exception {
    assertResult("/evoucher/json/evoucherValidationRequest_master_evoucher_maxQty.json",
        "/evoucher/json/evoucherValidationResponse_masterEvoucher_MultiUsage_Failed.json");

  }

  @Test
  @Sql({"/evoucher/sql/evoucherData_MaxQuantity_Failed.sql",
      "/evoucher/sql/productPromoterVenue.sql", "/evoucher/sql/paymentMethodsData.sql",
      "/cart/sql/availableInvForGA.sql"})
  public void runMasterEvoucherMaxQuantityFailed() throws Exception {
    assertResult("/evoucher/json/evoucherValidationRequest_master_evoucher_maxQty.json",
        "/evoucher/json/evoucherValidationResponse_masterEvoucher_MaxQuantity_Failed.json");

  }

  @Test
  @Sql({"/evoucher/sql/evoucherData_Basic_Expired.sql", "/evoucher/sql/productPromoterVenue.sql",
      "/evoucher/sql/paymentMethodsData.sql", "/cart/sql/availableInvForGA.sql"})
  public void runMasterEvoucherBasicExpired() throws Exception {
    assertResult("/evoucher/json/evoucherValidationRequest_masterEvoucher.json",
        "/evoucher/json/evoucherValidationResponse_masterEvoucher_Basic_Expired.json");

  }

  @Test
  @Sql({"/evoucher/sql/evoucherData_Basic_Inactive.sql", "/evoucher/sql/productPromoterVenue.sql",
      "/evoucher/sql/paymentMethodsData.sql", "/cart/sql/availableInvForGA.sql"})
  public void runMasterEvoucherBasicInactive() throws Exception {
    assertResult("/evoucher/json/evoucherValidationRequest_masterEvoucher.json",
        "/evoucher/json/evoucherValidationResponse_masterEvoucher_Basic_Inactive.json");

  }

  @Test
  @Sql({"/evoucher/sql/evoucherData_Basic_Redeemed.sql", "/evoucher/sql/productPromoterVenue.sql",
      "/evoucher/sql/paymentMethodsData.sql", "/cart/sql/availableInvForGA.sql"})
  public void runMasterEvoucherBasicRedeemed() throws Exception {
    assertResult("/evoucher/json/evoucherValidationRequest_masterEvoucher.json",
        "/evoucher/json/evoucherValidationResponse_masterEvoucher_Basic_Redeemed.json");

  }

  @Test
  @Sql({"/evoucher/sql/evoucherData_pass.sql", "/evoucher/sql/productPromoterVenue.sql",
      "/evoucher/sql/paymentMethodsData.sql", "/cart/sql/availableInvForGA.sql"})
  public void runOcbcEvoucherPass() throws Exception {
    assertResult("/evoucher/json/evoucherValidationRequest_ocbcEvoucher.json",
        "/evoucher/json/evoucherValidationResponse_ocbcEvoucher_Pass.json");

  }

  @Test
  @Sql({"/evoucher/sql/evoucherData_Redeem_Within_Pass.sql",
      "/evoucher/sql/productPromoterVenue.sql",
      "/evoucher/sql/paymentMethodsData_Evoucher_restrictionsIn_Pass.sql",
      "/cart/sql/availableInvForGA.sql"})
  public void runOcbcEvoucherRedeemWithinPass() throws Exception {
    assertResult("/evoucher/json/evoucherValidationRequest_ocbcEvoucher.json",
        "/evoucher/json/evoucherValidationResponse_ocbcEvoucher_Pass.json");
  }

  @Test
  @Sql({"/evoucher/sql/evoucherData_Redeem_Within_Failed.sql",
      "/evoucher/sql/productPromoterVenue.sql",
      "/evoucher/sql/paymentMethodsData_Evoucher_restrictionsIn_FAIL.sql",
      "/cart/sql/availableInvForGA.sql"})
  public void runOcbcEvoucherRedeemWithinFailed() throws Exception {
    assertResult("/evoucher/json/evoucherValidationRequest_ocbcEvoucher.json",
        "/evoucher/json/evoucherValidationResponse_ocbcEvoucher_Redeem_Within_Failed.json");

  }

  @Test
  @Sql({"/evoucher/sql/evoucherData_Redeem_Without_Pass.sql",
      "/evoucher/sql/productPromoterVenue.sql",
      "/evoucher/sql/paymentMethodsData_Evoucher_restrictionsOut_Pass.sql",
      "/cart/sql/availableInvForGA.sql"})
  public void runOcbcEvoucherRedeemWithoutPass() throws Exception {
    assertResult("/evoucher/json/evoucherValidationRequest_ocbcEvoucher.json",
        "/evoucher/json/evoucherValidationResponse_ocbcEvoucher_Pass.json");
  }


  @Test
  @Sql({"/evoucher/sql/evoucherData_Redeem_Without_Failed.sql",
      "/evoucher/sql/productPromoterVenue.sql",
      "/evoucher/sql/paymentMethodsData_Evoucher_restrictionsOut_FAIL.sql",
      "/cart/sql/availableInvForGA.sql"})
  public void runOcbcEvoucherRedeemWithoutFailed() throws Exception {
    assertResult("/evoucher/json/evoucherValidationRequest_ocbcEvoucher.json",
        "/evoucher/json/evoucherValidationResponse_ocbcEvoucher_Redeem_Without_Failed.json");

  }

  @Test
  @Sql({"/evoucher/sql/evoucherData_Redeem_All_Failed.sql",
      "/evoucher/sql/productPromoterVenue.sql",
      "/evoucher/sql/paymentMethodsData_Evoucher_restrictionsOut_FAIL.sql",
      "/cart/sql/availableInvForGA.sql"})
  public void runOcbcEvoucherRedeemAllFailed() throws Exception {
    assertResult("/evoucher/json/evoucherValidationRequest_ocbcEvoucher.json",
        "/evoucher/json/evoucherValidationResponse_ocbcEvoucher_Redeem_All_Failed.json");

  }

  @Test
  @Sql({"/evoucher/sql/evoucherData_Redeem_None.sql", "/evoucher/sql/productPromoterVenue.sql",
      "/evoucher/sql/paymentMethodsData_Evoucher_restrictionsOut_FAIL.sql",
      "/cart/sql/availableInvForGA.sql"})
  public void runOcbcEvoucherRedeemNone() throws Exception {
    assertResult("/evoucher/json/evoucherValidationRequest_ocbcEvoucher.json",
        "/evoucher/json/evoucherValidationResponse_ocbcEvoucher_Pass.json");

  }

  @Test
  @Sql({"/evoucher/sql/evoucherData_pass.sql", "/evoucher/sql/productPromoterVenue.sql",
      "/evoucher/sql/paymentMethodsData.sql", "/evoucher/sql/creditCardData.sql",
      "/cart/sql/availableInvForGA.sql"})
  public void runOcbcEvoucherCreditCardFailed() throws Exception {
    assertResult("/evoucher/json/evoucherValidationRequest_ocbc_creditCard_invalid.json",
        "/evoucher/json/evoucherValidationResponse_ocbcEvoucher_CreditCard_Failed.json");

  }

  @Test
  @Sql({"/evoucher/sql/evoucherData_MultiUsage_Failed.sql",
      "/evoucher/sql/productPromoterVenue.sql", "/evoucher/sql/paymentMethodsData.sql",
      "/cart/sql/availableInvForGA.sql"})
  public void runOcbcEvoucherMultiUsageFailed() throws Exception {
    assertResult("/evoucher/json/evoucherValidationRequest_ocbc_evoucher_maxQty.json",
        "/evoucher/json/evoucherValidationResponse_ocbcEvoucher_MultiUsage_Failed.json");

  }

  @Test
  @Sql({"/evoucher/sql/evoucherData_MaxQuantity_Failed.sql",
      "/evoucher/sql/productPromoterVenue.sql", "/evoucher/sql/paymentMethodsData.sql",
      "/cart/sql/availableInvForGA.sql"})
  public void runOcbcEvoucherMaxQuantityFailed() throws Exception {
    assertResult("/evoucher/json/evoucherValidationRequest_ocbc_evoucher_maxQty.json",
        "/evoucher/json/evoucherValidationResponse_ocbcEvoucher_MaxQuantity_Failed.json");

  }

  @Test
  @Sql({"/evoucher/sql/evoucherData_Basic_Expired.sql", "/evoucher/sql/productPromoterVenue.sql",
      "/evoucher/sql/paymentMethodsData.sql", "/cart/sql/availableInvForGA.sql"})
  public void runOcbcEvoucherBasicExpired() throws Exception {
    assertResult("/evoucher/json/evoucherValidationRequest_ocbcEvoucher.json",
        "/evoucher/json/evoucherValidationResponse_ocbcEvoucher_Basic_Expired.json");

  }

  @Test
  @Sql({"/evoucher/sql/evoucherData_Basic_Inactive.sql", "/evoucher/sql/productPromoterVenue.sql",
      "/evoucher/sql/paymentMethodsData.sql", "/cart/sql/availableInvForGA.sql"})
  public void runOcbcEvoucherBasicInactive() throws Exception {
    assertResult("/evoucher/json/evoucherValidationRequest_ocbcEvoucher.json",
        "/evoucher/json/evoucherValidationResponse_ocbcEvoucher_Basic_Inactive.json");

  }

  @Test
  @Sql({"/evoucher/sql/evoucherData_Basic_Redeemed.sql", "/evoucher/sql/productPromoterVenue.sql",
      "/evoucher/sql/paymentMethodsData.sql", "/cart/sql/availableInvForGA.sql"})
  public void runOcbcEvoucherBasicRedeemed() throws Exception {
    assertResult("/evoucher/json/evoucherValidationRequest_ocbcEvoucher.json",
        "/evoucher/json/evoucherValidationResponse_ocbcEvoucher_Basic_Redeemed.json");

  }

  @Test
  @Sql({"/evoucher/sql/evoucherData_pass.sql", "/evoucher/sql/productPromoterVenue.sql",
      "/evoucher/sql/paymentMethodsData.sql", "/cart/sql/availableInvForGA.sql"})
  public void runNgRenewalEvoucherPass() throws Exception {
    assertResult("/evoucher/json/evoucherValidationRequest_ngRenewalEvoucher.json",
        "/evoucher/json/evoucherValidationResponse_ngRenewalEvoucher_Pass.json");

  }

  @Test
  @Sql({"/evoucher/sql/evoucherData_Redeem_Within_Pass.sql",
      "/evoucher/sql/productPromoterVenue.sql",
      "/evoucher/sql/paymentMethodsData_Evoucher_restrictionsIn_Pass.sql",
      "/cart/sql/availableInvForGA.sql"})
  public void runNgRenewalEvoucherRedeemWithinPass() throws Exception {
    assertResult("/evoucher/json/evoucherValidationRequest_ngRenewalEvoucher.json",
        "/evoucher/json/evoucherValidationResponse_ngRenewalEvoucher_Pass.json");
  }

  @Test
  @Sql({"/evoucher/sql/evoucherData_Redeem_Within_Failed.sql",
      "/evoucher/sql/productPromoterVenue.sql",
      "/evoucher/sql/paymentMethodsData_Evoucher_restrictionsIn_FAIL.sql",
      "/cart/sql/availableInvForGA.sql"})
  public void runNgRenewalEvoucherRedeemWithinFailed() throws Exception {
    assertResult("/evoucher/json/evoucherValidationRequest_ngRenewalEvoucher.json",
        "/evoucher/json/evoucherValidationResponse_ngRenewalEvoucher_Redeem_Within_Failed.json");

  }

  @Test
  @Sql({"/evoucher/sql/evoucherData_Redeem_Without_Pass.sql",
      "/evoucher/sql/productPromoterVenue.sql",
      "/evoucher/sql/paymentMethodsData_Evoucher_restrictionsOut_Pass.sql",
      "/cart/sql/availableInvForGA.sql"})
  public void runNgRenewalEvoucherRedeemWithoutPass() throws Exception {
    assertResult("/evoucher/json/evoucherValidationRequest_ngRenewalEvoucher.json",
        "/evoucher/json/evoucherValidationResponse_ngRenewalEvoucher_Pass.json");
  }

  @Test
  @Sql({"/evoucher/sql/evoucherData_Redeem_Without_Failed.sql",
      "/evoucher/sql/productPromoterVenue.sql",
      "/evoucher/sql/paymentMethodsData_Evoucher_restrictionsOut_FAIL.sql",
      "/cart/sql/availableInvForGA.sql"})
  public void runNgRenewalEvoucherRedeemWithoutFailed() throws Exception {
    assertResult("/evoucher/json/evoucherValidationRequest_ngRenewalEvoucher.json",
        "/evoucher/json/evoucherValidationResponse_ngRenewalEvoucher_Redeem_Without_Failed.json");

  }

  @Test
  @Sql({"/evoucher/sql/evoucherData_Redeem_All_Failed.sql",
      "/evoucher/sql/productPromoterVenue.sql",
      "/evoucher/sql/paymentMethodsData_Evoucher_restrictionsOut_FAIL.sql",
      "/cart/sql/availableInvForGA.sql"})
  public void runNgRenewalEvoucherRedeemAllFailed() throws Exception {
    assertResult("/evoucher/json/evoucherValidationRequest_ngRenewalEvoucher.json",
        "/evoucher/json/evoucherValidationResponse_ngRenewalEvoucher_Redeem_All_Failed.json");

  }

  @Test
  @Sql({"/evoucher/sql/evoucherData_Redeem_None.sql", "/evoucher/sql/productPromoterVenue.sql",
      "/evoucher/sql/paymentMethodsData_Evoucher_restrictionsOut_FAIL.sql",
      "/cart/sql/availableInvForGA.sql"})
  public void runNgRenewalEvoucherRedeemNone() throws Exception {
    assertResult("/evoucher/json/evoucherValidationRequest_ngRenewalEvoucher.json",
        "/evoucher/json/evoucherValidationResponse_ngRenewalEvoucher_Pass.json");

  }

  @Test
  @Sql({"/evoucher/sql/evoucherData_Basic_Expired.sql", "/evoucher/sql/productPromoterVenue.sql",
      "/evoucher/sql/paymentMethodsData.sql", "/cart/sql/availableInvForGA.sql"})
  public void runNgRenewalEvoucherBasicExpired() throws Exception {
    assertResult("/evoucher/json/evoucherValidationRequest_ngRenewalEvoucher.json",
        "/evoucher/json/evoucherValidationResponse_ngRenewalEvoucher_Basic_Expired.json");

  }

  @Test
  @Sql({"/evoucher/sql/evoucherData_Basic_Inactive.sql", "/evoucher/sql/productPromoterVenue.sql",
      "/evoucher/sql/paymentMethodsData.sql", "/cart/sql/availableInvForGA.sql"})
  public void runNgRenewalEvoucherBasicInactive() throws Exception {
    assertResult("/evoucher/json/evoucherValidationRequest_ngRenewalEvoucher.json",
        "/evoucher/json/evoucherValidationResponse_ngRenewalEvoucher_Basic_Inactive.json");

  }

  @Test
  @Sql({"/evoucher/sql/evoucherData_Basic_Redeemed.sql", "/evoucher/sql/productPromoterVenue.sql",
      "/evoucher/sql/paymentMethodsData.sql", "/cart/sql/availableInvForGA.sql"})
  public void runNgRenewalEvoucherBasicRedeemed() throws Exception {
    assertResult("/evoucher/json/evoucherValidationRequest_ngRenewalEvoucher.json",
        "/evoucher/json/evoucherValidationResponse_ngRenewalEvoucher_Basic_Redeemed.json");

  }

  @Test
  @Sql({"/evoucher/sql/evoucherData_pass.sql", "/evoucher/sql/productPromoterVenue.sql",
      "/evoucher/sql/paymentMethodsData.sql", "/cart/sql/availableInvForGA.sql"})
  public void runNgsEvoucherPass() throws Exception {
    assertResult("/evoucher/json/evoucherValidationRequest_ngsEvoucher.json",
        "/evoucher/json/evoucherValidationResponse_ngsEvoucher_Pass.json");

  }

  @Test
  @Sql({"/evoucher/sql/evoucherData_Redeem_Within_Pass.sql",
      "/evoucher/sql/productPromoterVenue.sql",
      "/evoucher/sql/paymentMethodsData_Evoucher_restrictionsIn_Pass.sql",
      "/cart/sql/availableInvForGA.sql"})
  public void runNgsEvoucherRedeemWithinPass() throws Exception {
    assertResult("/evoucher/json/evoucherValidationRequest_ngsEvoucher.json",
        "/evoucher/json/evoucherValidationResponse_ngsEvoucher_Pass.json");
  }

  @Test
  @Sql({"/evoucher/sql/evoucherData_Redeem_Within_Failed.sql",
      "/evoucher/sql/productPromoterVenue.sql",
      "/evoucher/sql/paymentMethodsData_Evoucher_restrictionsIn_FAIL.sql",
      "/cart/sql/availableInvForGA.sql"})
  public void runNgsEvoucherRedeemWithinFailed() throws Exception {
    assertResult("/evoucher/json/evoucherValidationRequest_ngsEvoucher.json",
        "/evoucher/json/evoucherValidationResponse_ngsEvoucher_Redeem_Within_Failed.json");

  }

  @Test
  @Sql({"/evoucher/sql/evoucherData_Redeem_Without_Pass.sql",
      "/evoucher/sql/productPromoterVenue.sql",
      "/evoucher/sql/paymentMethodsData_Evoucher_restrictionsOut_Pass.sql",
      "/cart/sql/availableInvForGA.sql"})
  public void runNgsEvoucherRedeemWithoutPass() throws Exception {
    assertResult("/evoucher/json/evoucherValidationRequest_ngsEvoucher.json",
        "/evoucher/json/evoucherValidationResponse_ngsEvoucher_Pass.json");
  }


  @Test
  @Sql({"/evoucher/sql/evoucherData_Redeem_Without_Failed.sql",
      "/evoucher/sql/productPromoterVenue.sql",
      "/evoucher/sql/paymentMethodsData_Evoucher_restrictionsOut_FAIL.sql",
      "/cart/sql/availableInvForGA.sql"})
  public void runNgsEvoucherRedeemWithoutFailed() throws Exception {
    assertResult("/evoucher/json/evoucherValidationRequest_ngsEvoucher.json",
        "/evoucher/json/evoucherValidationResponse_ngsEvoucher_Redeem_Without_Failed.json");

  }

  @Test
  @Sql({"/evoucher/sql/evoucherData_Redeem_All_Failed.sql",
      "/evoucher/sql/productPromoterVenue.sql",
      "/evoucher/sql/paymentMethodsData_Evoucher_restrictionsOut_FAIL.sql",
      "/cart/sql/availableInvForGA.sql"})
  public void runNgsEvoucherRedeemAllFailed() throws Exception {
    assertResult("/evoucher/json/evoucherValidationRequest_ngsEvoucher.json",
        "/evoucher/json/evoucherValidationResponse_ngsEvoucher_Redeem_All_Failed.json");

  }

  @Test
  @Sql({"/evoucher/sql/evoucherData_Redeem_None.sql", "/evoucher/sql/productPromoterVenue.sql",
      "/evoucher/sql/paymentMethodsData_Evoucher_restrictionsOut_FAIL.sql",
      "/cart/sql/availableInvForGA.sql"})
  public void runNgsEvoucherRedeemNone() throws Exception {
    assertResult("/evoucher/json/evoucherValidationRequest_ngsEvoucher.json",
        "/evoucher/json/evoucherValidationResponse_ngsEvoucher_Pass.json");

  }

  @Test
  @Sql({"/evoucher/sql/evoucherData_Basic_Expired.sql", "/evoucher/sql/productPromoterVenue.sql",
      "/evoucher/sql/paymentMethodsData.sql", "/cart/sql/availableInvForGA.sql",
      "/cart/sql/availableInvForGA.sql"})
  public void runNgsEvoucherBasicExpired() throws Exception {
    assertResult("/evoucher/json/evoucherValidationRequest_ngsEvoucher.json",
        "/evoucher/json/evoucherValidationResponse_ngsEvoucher_Basic_Expired.json");

  }

  @Test
  @Sql({"/evoucher/sql/evoucherData_Basic_Inactive.sql", "/evoucher/sql/productPromoterVenue.sql",
      "/evoucher/sql/paymentMethodsData.sql", "/cart/sql/availableInvForGA.sql",
      "/cart/sql/availableInvForGA.sql"})
  public void runNgsEvoucherBasicInactive() throws Exception {
    assertResult("/evoucher/json/evoucherValidationRequest_ngsEvoucher.json",
        "/evoucher/json/evoucherValidationResponse_ngsEvoucher_Basic_Inactive.json");

  }

  @Test
  @Sql({"/evoucher/sql/evoucherData_Basic_Redeemed.sql", "/evoucher/sql/productPromoterVenue.sql",
      "/evoucher/sql/paymentMethodsData.sql", "/cart/sql/availableInvForGA.sql"})
  public void runNgsEvoucherBasicRedeemed() throws Exception {
    assertResult("/evoucher/json/evoucherValidationRequest_ngsEvoucher.json",
        "/evoucher/json/evoucherValidationResponse_ngsEvoucher_Basic_Redeemed.json");

  }

  @Test
  @Sql({"/evoucher/sql/evoucherData_pass.sql", "/evoucher/sql/productPromoterVenue.sql",
      "/evoucher/sql/paymentMethodsData.sql", "/cart/sql/availableInvForGA.sql"})
  public void runSisticEvoucherPass() throws Exception {
    assertResult("/evoucher/json/evoucherValidationRequest_sisticEvoucher.json",
        "/evoucher/json/evoucherValidationResponse_sisticEvoucher_Pass.json");

  }

  @Test
  @Sql({"/evoucher/sql/evoucherData_Redeem_Within_Pass.sql",
      "/evoucher/sql/productPromoterVenue.sql",
      "/evoucher/sql/paymentMethodsData_Evoucher_restrictionsIn_Pass.sql",
      "/cart/sql/availableInvForGA.sql"})
  public void runSisticEvoucherRedeemWithinPass() throws Exception {
    assertResult("/evoucher/json/evoucherValidationRequest_sisticEvoucher.json",
        "/evoucher/json/evoucherValidationResponse_sisticEvoucher_Pass.json");
  }

  @Test
  @Sql({"/evoucher/sql/evoucherData_Redeem_Within_Failed.sql",
      "/evoucher/sql/productPromoterVenue.sql",
      "/evoucher/sql/paymentMethodsData_Evoucher_restrictionsIn_FAIL.sql",
      "/cart/sql/availableInvForGA.sql"})
  public void runSisticEvoucherRedeemWithinFailed() throws Exception {
    assertResult("/evoucher/json/evoucherValidationRequest_sisticEvoucher.json",
        "/evoucher/json/evoucherValidationResponse_sisticEvoucher_Redeem_Within_Failed.json");

  }

  @Test
  @Sql({"/evoucher/sql/evoucherData_Redeem_Without_Pass.sql",
      "/evoucher/sql/productPromoterVenue.sql",
      "/evoucher/sql/paymentMethodsData_Evoucher_restrictionsOut_Pass.sql",
      "/cart/sql/availableInvForGA.sql"})
  public void runSisticEvoucherRedeemWithoutPass() throws Exception {
    assertResult("/evoucher/json/evoucherValidationRequest_sisticEvoucher.json",
        "/evoucher/json/evoucherValidationResponse_sisticEvoucher_Pass.json");
  }


  @Test
  @Sql({"/evoucher/sql/evoucherData_Redeem_Without_Failed.sql",
      "/evoucher/sql/productPromoterVenue.sql",
      "/evoucher/sql/paymentMethodsData_Evoucher_restrictionsOut_FAIL.sql",
      "/cart/sql/availableInvForGA.sql"})
  public void runSisticEvoucherRedeemWithoutFailed() throws Exception {
    assertResult("/evoucher/json/evoucherValidationRequest_sisticEvoucher.json",
        "/evoucher/json/evoucherValidationResponse_sisticEvoucher_Redeem_Without_Failed.json");

  }

  @Test
  @Sql({"/evoucher/sql/evoucherData_Redeem_All_Failed.sql",
      "/evoucher/sql/productPromoterVenue.sql",
      "/evoucher/sql/paymentMethodsData_Evoucher_restrictionsOut_FAIL.sql",
      "/cart/sql/availableInvForGA.sql"})
  public void runSisticEvoucherRedeemAllFailed() throws Exception {
    assertResult("/evoucher/json/evoucherValidationRequest_sisticEvoucher.json",
        "/evoucher/json/evoucherValidationResponse_sisticEvoucher_Redeem_All_Failed.json");

  }

  @Test
  @Sql({"/evoucher/sql/evoucherData_Redeem_None.sql", "/evoucher/sql/productPromoterVenue.sql",
      "/evoucher/sql/paymentMethodsData_Evoucher_restrictionsOut_FAIL.sql",
      "/cart/sql/availableInvForGA.sql"})
  public void runSisticEvoucherRedeemNone() throws Exception {
    assertResult("/evoucher/json/evoucherValidationRequest_sisticEvoucher.json",
        "/evoucher/json/evoucherValidationResponse_sisticEvoucher_Pass.json");

  }

  @Test
  @Sql({"/evoucher/sql/evoucherData_Basic_Expired.sql", "/evoucher/sql/productPromoterVenue.sql",
      "/evoucher/sql/paymentMethodsData.sql", "/cart/sql/availableInvForGA.sql"})
  public void runSisticEvoucherBasicExpired() throws Exception {
    assertResult("/evoucher/json/evoucherValidationRequest_sisticEvoucher.json",
        "/evoucher/json/evoucherValidationResponse_sisticEvoucher_Basic_Expired.json");

  }

  @Test
  @Sql({"/evoucher/sql/evoucherData_Basic_Inactive.sql", "/evoucher/sql/productPromoterVenue.sql",
      "/evoucher/sql/paymentMethodsData.sql", "/cart/sql/availableInvForGA.sql"})
  public void runSisticEvoucherBasicInactive() throws Exception {
    assertResult("/evoucher/json/evoucherValidationRequest_sisticEvoucher.json",
        "/evoucher/json/evoucherValidationResponse_sisticEvoucher_Basic_Inactive.json");

  }

  @Test
  @Sql({"/evoucher/sql/evoucherData_Basic_Redeemed.sql", "/evoucher/sql/productPromoterVenue.sql",
      "/evoucher/sql/paymentMethodsData.sql", "/cart/sql/availableInvForGA.sql"})
  public void runSisticEvoucherBasicRedeemed() throws Exception {
    assertResult("/evoucher/json/evoucherValidationRequest_sisticEvoucher.json",
        "/evoucher/json/evoucherValidationResponse_sisticEvoucher_Basic_Redeemed.json");

  }

  @Test
  @Sql({"/evoucher/sql/evoucherData.sql", "/evoucher/sql/productPromoterVenue.sql",
      "/evoucher/sql/paymentMethodsData.sql", "/cart/sql/availableInvForGA.sql"})
  public void validateExceedMaxQty() throws Exception {
    String uri = "/SISTIC/evoucher/validation/afabb997-928e-4da3-92eb-fb4e3a400cbe";

    EVoucherValidationRequest eVoucherValidationRequest = objectMapper.readValue(
        this.getClass().getResource("/evoucher/json/evoucherValidationRequest_exceedMaxQty.json"),
        EVoucherValidationRequest.class);

    HttpEntity<EVoucherValidationRequest> eVoucherValidationRequestHttpEntity =
        getHttpEntityForEVoucherValidationRequest(eVoucherValidationRequest);

    ResponseEntity<EvoucherValidationResponse> entity = restTemplate.exchange(uri, HttpMethod.POST,
        eVoucherValidationRequestHttpEntity, EvoucherValidationResponse.class);
    EvoucherValidationResponse evoucherValidationResponse = entity.getBody();

    logger.debug(evoucherValidationResponse);
    assertThat(evoucherValidationResponse).hasFieldOrPropertyWithValue("exceedMaxQtyAllowed", true);
  }

  @Test
  @Sql({"/evoucher/sql/evoucherData.sql", "/evoucher/sql/productPromoterVenue.sql",
      "/evoucher/sql/paymentMethodsData.sql", "/cart/sql/availableInvForGA.sql"})
  public void validateEqualMaxQty() throws Exception {
    EvoucherValidationResponse evoucherValidationResponse =
        validate("/evoucher/json/evoucherValidationRequest_equalMaxQty.json");

    logger.debug(evoucherValidationResponse);
    assertThat(evoucherValidationResponse).hasFieldOrPropertyWithValue("exceedMaxQtyAllowed",
        false);
  }

  @Test
  @Sql({"/evoucher/sql/evoucherData.sql", "/evoucher/sql/productPromoterVenue.sql",
      "/evoucher/sql/paymentMethodsData.sql", "/cart/sql/availableInvForGA.sql"})
  public void validateLessThanMaxQty() throws Exception {
    EvoucherValidationResponse evoucherValidationResponse =
        validate("/evoucher/json/evoucherValidationRequest_lessThanMaxQty.json");

    logger.debug(evoucherValidationResponse);
    assertThat(evoucherValidationResponse).hasFieldOrPropertyWithValue("exceedMaxQtyAllowed",
        false);
  }

  @Test
  @Sql({"/evoucher/sql/evoucherData_Redeem_None.sql", "/evoucher/sql/productPromoterVenue.sql",
      "/evoucher/sql/paymentMethodsData.sql", "/cart/sql/availableInvForGA.sql"})
  public void runMultiRulesPass() throws Exception {
    assertResult("/evoucher/json/evoucherValidationRequest_multiEvouchers_allPass.json",
        "/evoucher/json/evoucherValidationResponse_multiEvouchers_allPass.json");
  }

  @Test
  @Sql({"/evoucher/sql/evoucherData_Basic_Expired.sql", "/evoucher/sql/productPromoterVenue.sql",
      "/evoucher/sql/paymentMethodsData.sql", "/cart/sql/availableInvForGA.sql"})
  public void runMultiRulesFailed() throws Exception {
    assertResult("/evoucher/json/evoucherValidationRequest_multiEvouchers_allFailed.json",
        "/evoucher/json/evoucherValidationResponse_multiEvouchers_allFailed.json");
  }

  @Test
  @Sql({"/evoucher/sql/evoucherData_MultiRule.sql", "/evoucher/sql/productPromoterVenue.sql",
      "/evoucher/sql/paymentMethodsData.sql", "/cart/sql/availableInvForGA.sql"})
  public void runMultiRulesOnePassOneFailed() throws Exception {
    assertResult("/evoucher/json/evoucherValidationRequest_multiEvouchers_onePass_oneFailed.json",
        "/evoucher/json/evoucherValidationResponse_multiEvouchers_onePass_oneFailed.json");
  }

  private EvoucherValidationResponse validate(String jsonRequestPath) throws Exception {
    String uri = "/SISTIC/evoucher/validation/afabb997-928e-4da3-92eb-fb4e3a400cbe";

    EVoucherValidationRequest eVoucherValidationRequest = objectMapper
        .readValue(this.getClass().getResource(jsonRequestPath), EVoucherValidationRequest.class);

    HttpEntity<EVoucherValidationRequest> eVoucherValidationRequestHttpEntity =
        getHttpEntityForEVoucherValidationRequest(eVoucherValidationRequest);

    ResponseEntity<EvoucherValidationResponse> entity = restTemplate.exchange(uri, HttpMethod.POST,
        eVoucherValidationRequestHttpEntity, EvoucherValidationResponse.class);
    return entity.getBody();
  }

  private HttpEntity<EVoucherValidationRequest> getHttpEntityForEVoucherValidationRequest(
      EVoucherValidationRequest eVoucherValidationRequest) {
    HttpHeaders httpHeaders = new HttpHeaders();
    httpHeaders.setContentType(MediaType.APPLICATION_JSON);

    return new HttpEntity<>(eVoucherValidationRequest, httpHeaders);
  }

  private void assertResult(String jsonRequestPath, String expectedResultPath) throws Exception {
    String jsonContent = new String(
        Files.readAllBytes(Paths.get(this.getClass().getResource(jsonRequestPath).toURI())));
    String jsonExpected = new String(
        Files.readAllBytes(Paths.get(this.getClass().getResource(expectedResultPath).toURI())));
    mockMvc
        .perform(post("/SISTIC/evoucher/validation/afabb997-928e-4da3-92eb-fb4e3a400cbe")
            .content(jsonContent).locale(locale).contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
        .andExpect(content().json(jsonExpected));
  }
}
