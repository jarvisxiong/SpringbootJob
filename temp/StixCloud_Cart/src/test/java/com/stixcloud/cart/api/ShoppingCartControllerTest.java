package com.stixcloud.cart.api;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stixcloud.cart.IPaymentGatewayAPIService;
import com.stixcloud.cart.InventoryConstants;
import com.stixcloud.cart.TenantPropertiesTest;
import com.stixcloud.cart.util.Utils;
import com.stixcloud.paymentgateway.api.PaymentGatewayRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import java.util.UUID;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import javax.money.Monetary;
import redis.embedded.RedisExecProvider;
import redis.embedded.RedisServer;
import redis.embedded.RedisServerBuilder;
import redis.embedded.util.Architecture;
import redis.embedded.util.OS;

/**
 * Created by chongye on 10/10/2016.
 */
@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ShoppingCartControllerTest {
  private static RedisServer redisServer;
  private final Logger logger = LogManager.getLogger(ShoppingCartControllerTest.class);
  private MockMvc mockMvc;
  @Autowired
  private ShoppingCartController controller;
  @Autowired
  private RedisTemplate redisTemplate;

  @Autowired
  private MessageSource messageSource;
  @Autowired
  private ObjectMapper objectMapper;
  @Autowired
  private TestRestTemplate restTemplate;
  @Autowired
  private IPaymentGatewayAPIService queueService;
  @Value("${spring.mvc.locale}")
  private Locale locale;

  private HttpHeaders httpHeaders;

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

  @Before
  public void setup() throws Exception {
    HttpHeaders httpHeaders = new HttpHeaders();
    MockitoAnnotations.initMocks(this);
    mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    Locale.setDefault(locale);
    objectMapper.setTimeZone(TimeZone.getTimeZone("SGT"));
    objectMapper.setLocale(locale);

    httpHeaders = new HttpHeaders();
    httpHeaders.setContentType(MediaType.APPLICATION_JSON);
    //redisTemplate.getConnectionFactory().getConnection();
    TenantPropertiesTest.setUp();
  }

  @Test
  @Sql({"/cart/sql/availableInvForGA.sql", "/cart/sql/ValidateCartViewForGA.sql"})
  public void addToCartForGA_NewCart() throws Exception {
    AddToCartRequest addToCartRequest = objectMapper.readValue(
        this.getClass().getResource("/cart/json/addToCart_GA.json"),
        AddToCartRequest.class);

    addToCart_Success(addToCartRequest);
  }

  @Test
  @Sql({"/cart/sql/reservedInvForRS.sql", "/cart/sql/ValidateCartViewForRS.sql"})
  public void addToCartForRS_NewCart() throws Exception {
    redisTemplate.getConnectionFactory().getConnection();
    AddToCartRequest addToCartRequest = objectMapper.readValue(
        this.getClass().getResource("/cart/json/addToCart_RS.json"),
        AddToCartRequest.class);

    addToCart_Success(addToCartRequest);
  }

  @Test
  @Sql({"/cart/sql/availableInvForGA.sql", "/cart/sql/ValidateCartViewForGA.sql"})
  public void addToCartForGA_ExistingCart() throws Exception {
    AddToCartRequest addToCartRequest = objectMapper.readValue(
        this.getClass().getResource("/cart/json/addToCart_GA.json"),
        AddToCartRequest.class);

    String cartGuid = addToCart_Success(addToCartRequest);
    addToCartRequest = new AddToCartRequest.Builder(addToCartRequest).cartGuid(cartGuid).build();
    addToCart_Success(addToCartRequest);
  }

  @Test
  @Sql({"/cart/sql/reservedInvForRS.sql", "/cart/sql/ValidateCartViewForRS.sql"})
  public void addToCartForRS_ExistingCart() throws Exception {
    AddToCartRequest addToCartRequest = objectMapper.readValue(
        this.getClass().getResource("/cart/json/addToCart_RS.json"),
        AddToCartRequest.class);

    String cartGuid = addToCart_Success(addToCartRequest);
    addToCartRequest = new AddToCartRequest.Builder(addToCartRequest)
        .priceClassMap("A:2").cartGuid(cartGuid)
        .inventoryList(new HashSet<>(Arrays.asList(121555777L, 121555778L))).build();
    addToCart_Success(addToCartRequest);
  }

  private String addToCart_Success(AddToCartRequest addToCartRequest)
      throws JsonProcessingException {
    ResponseEntity<JsonResponse> cartResponse = addToCart(addToCartRequest);

    logger
        .debug(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(addToCartRequest));
    assertThat(cartResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
    assertThat(cartResponse.getBody())
//        .hasNoNullFieldsOrProperties()
        .hasFieldOrPropertyWithValue("httpStatus", HttpStatus.OK.toString())
        .hasFieldOrPropertyWithValue("statusMessage", messageSource
            .getMessage("addtocart.success", null, LocaleContextHolder.getLocale()));
    return (String) cartResponse.getBody().getAdditionalProperties().get("cartGuid");
  }

  @Test
  @Sql({"/cart/sql/deliveryMethodFeeService.sql", "/cart/sql/FeeRuleFeeService.sql",
      "/cart/sql/ProductPriceFeeService.sql", "/cart/sql/getCart.sql"})
  public void getCartExpirationTTL() throws Exception {
    AddToCartRequest firstRequest =
        new AddToCartRequest.Builder().productId(245634L).priceCatId(12176L).priceClassMap("A1:1")
            .mode(InventoryConstants.SECTION_TYPE_RS.getName())
            .inventoryList(new HashSet<>(Arrays.asList(121555775L))).build();

    String cartGuid = addToCart_Success(firstRequest);
    assertThat(cartGuid).isNotEmpty();

    Long ttl = 999L;
    ResponseEntity<JsonResponse> responseEntity = restTemplate.getForEntity(
        "/SISTIC/cart/" + cartGuid + "/expiration/" + ttl, JsonResponse.class
    );

    logger.debug(responseEntity.toString());
    assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
    assertThat(responseEntity.getBody().getHttpStatus())
        .isEqualTo(HttpStatus.OK.toString());
    assertThat(responseEntity.getBody().getStatusMessage())
        .isEqualTo(messageSource.getMessage("updatecartttl.success", null, locale));
    assertThat(responseEntity.getBody().getAdditionalProperties().get("cartGuid"))
        .isEqualTo(cartGuid);
  }

  @Test
  @Sql({"/cart/sql/deliveryMethodFeeService.sql", "/cart/sql/FeeRuleFeeService.sql",
      "/cart/sql/ProductPriceFeeService.sql", "/cart/sql/getCart.sql"})
  public void getCartExpirationTTL_failed() throws Exception {
    AddToCartRequest firstRequest =
        new AddToCartRequest.Builder().productId(245634L).priceCatId(12176L).priceClassMap("A1:1")
            .mode(InventoryConstants.SECTION_TYPE_RS.getName())
            .inventoryList(new HashSet<>(Arrays.asList(121555775L))).build();

    String cartGuid = addToCart_Success(firstRequest);
    assertThat(cartGuid).isNotEmpty();

    Long ttl = 9999L;
    cartGuid += "a";//make the cartGuid fail to be found
    ResponseEntity<JsonResponse> responseEntity = restTemplate.getForEntity(
        "/SISTIC/cart/" + cartGuid + "/expiration/" + ttl, JsonResponse.class
    );

    logger.debug(responseEntity.toString());
    assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    assertThat(responseEntity.getBody().getHttpStatus())
        .isEqualTo(HttpStatus.BAD_REQUEST.toString());
    assertThat(responseEntity.getBody().getStatusMessage())
        .isEqualTo(messageSource.getMessage("updatecartttl.failure", null, locale));
  }

  @Test
  @Sql({"/cart/sql/deliveryMethodFeeService.sql", "/cart/sql/FeeRuleFeeService.sql",
      "/cart/sql/ProductPriceFeeService.sql", "/cart/sql/getCart.sql"})
  public void getCart() throws Exception {
    AddToCartRequest firstRequest =
        new AddToCartRequest.Builder().productId(245634L).priceCatId(12176L).priceClassMap("A1:1")
            .mode(InventoryConstants.SECTION_TYPE_RS.getName())
            .inventoryList(new HashSet<>(Arrays.asList(121555775L))).build();

    String cartGuid = addToCart_Success(firstRequest);
    assertThat(cartGuid).isNotEmpty();

    AddToCartRequest secondRequest =
        new AddToCartRequest.Builder(firstRequest).priceClassMap("A:2").cartGuid(cartGuid)
            .inventoryList(new HashSet<>(Arrays.asList(121555776L, 121555777L))).build();
    cartGuid = addToCart_Success(secondRequest);
    assertThat(cartGuid).isNotEmpty();

    ResponseEntity<ShoppingCartJson> responseEntity =
        restTemplate.getForEntity("/SISTIC/cart/" + cartGuid, ShoppingCartJson.class);
    ShoppingCartJson shoppingCartJson = responseEntity.getBody();
    logger
        .debug(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(shoppingCartJson));

    //assertThat(shoppingCartJson).hasNoNullFieldsOrProperties();
    assertThat(shoppingCartJson.getLineItemList()).isNotEmpty().hasSize(2).allMatch(li ->
        li.getSubTotal()
            .isEqualTo(li.getUnitPrice().multiply(li.getQuantity()).add(li.getBookingFee())));
    assertThat(shoppingCartJson.getCommonDeliveryMethod()).isNotNull()
        .hasNoNullFieldsOrProperties();
    assertThat(shoppingCartJson.getCommonDeliveryMethod().getDeliveryMethodJsons()).isNotEmpty()
        .hasSize(7);
    assertThat(shoppingCartJson.getCommonPaymentMethod()).isNotNull().hasNoNullFieldsOrProperties();
    assertThat(shoppingCartJson.getCommonPaymentMethod().getPaymentMethodJsons()).isNotEmpty()
        .hasSize(10);
    assertThat(shoppingCartJson.getUnitPrice())
        .isEqualTo(Monetary.getCurrency(LocaleContextHolder.getLocale()));

    ShoppingCartJson cartFromJsonFile = objectMapper
        .readValue(this.getClass().getResource("/cart/json/getCart.json"), ShoppingCartJson.class);
    List<Product> products =
        cartFromJsonFile.getLineItemList().stream().map(CartLineItem::getProduct)
            .collect(Collectors.toList());
    assertThat(shoppingCartJson.getLineItemList()).extracting(CartLineItem::getProduct)
        .usingRecursiveFieldByFieldElementComparator()
        .isEqualTo(products);
    assertThat(shoppingCartJson).isEqualToIgnoringGivenFields(cartFromJsonFile, "lineItemList");
  }


  @Test
  public void getCart_CartNotFound() throws Exception {
    ResponseEntity<JsonResponse> responseEntity =
        restTemplate.getForEntity("/SISTIC/cart/1234", JsonResponse.class);
    logger.debug(responseEntity.toString());
    assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    assertThat(responseEntity.getBody().getHttpStatus())
        .isEqualTo(HttpStatus.BAD_REQUEST.toString());
    assertThat(responseEntity.getBody().getStatusMessage())
        .isEqualTo(messageSource.getMessage("cart.error.not.found", null, locale));
  }

  /**
   * Test to assert that adding same productid, pricecat and priceclass will maintain the cart order
   */
  @Test
  @Sql({"/cart/sql/deliveryMethodFeeService.sql", "/cart/sql/FeeRuleFeeService.sql",
      "/cart/sql/ProductPriceFeeService.sql", "/cart/sql/getCart.sql"})
  public void getCart_DuplicateProductId() throws Exception {
    AddToCartRequest firstRequest =
        new AddToCartRequest.Builder().productId(245634L).priceCatId(12176L).priceClassMap("A:1")
            .inventoryList(new HashSet<>(Arrays.asList(121555775L)))
            .mode(InventoryConstants.SECTION_TYPE_RS.getName()).build();

    String cartGuid = addToCart_Success(firstRequest);
    assertThat(cartGuid).isNotEmpty();

    AddToCartRequest gaRequest =
        new AddToCartRequest.Builder(firstRequest).cartGuid(cartGuid)
            .inventoryList(new HashSet<>(Arrays.asList(121555776L))).build();
    cartGuid = addToCart_Success(gaRequest);
    assertThat(cartGuid).isNotEmpty();

    ResponseEntity<ShoppingCartJson> responseEntity =
        restTemplate.getForEntity("/SISTIC/cart/" + cartGuid, ShoppingCartJson.class);
    ShoppingCartJson shoppingCartJson = responseEntity.getBody();
    logger
        .debug(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(shoppingCartJson));

    //assertThat(shoppingCartJson).hasNoNullFieldsOrProperties();
    assertThat(shoppingCartJson.getLineItemList()).isNotEmpty().hasSize(2).allMatch(li ->
        li.getSubTotal()
            .isEqualTo(li.getUnitPrice().multiply(li.getQuantity()).add(li.getBookingFee())));
    //check both cart items are exactly the same
    assertThat(shoppingCartJson.getLineItemList())
        .usingComparatorForElementFieldsWithType((o1, o2) -> (o1.equals(o2) ? 0 : -1),
            Product.class);

    assertThat(shoppingCartJson.getCommonDeliveryMethod()).isNotNull()
        .hasNoNullFieldsOrProperties();
    assertThat(shoppingCartJson.getCommonDeliveryMethod().getDeliveryMethodJsons()).isNotEmpty()
        .hasSize(7);

    assertThat(shoppingCartJson.getCommonPaymentMethod()).isNotNull().hasNoNullFieldsOrProperties();
    assertThat(shoppingCartJson.getCommonPaymentMethod().getPaymentMethodJsons()).isNotEmpty()
        .hasSize(10);
    assertThat(shoppingCartJson.getUnitPrice())
        .isEqualTo(Monetary.getCurrency(LocaleContextHolder.getLocale()));

    ShoppingCartJson cartFromJsonFile = objectMapper
        .readValue(this.getClass().getResource("/cart/json/getCart_Duplicate.json"),
            ShoppingCartJson.class);
    List<Product> products =
        cartFromJsonFile.getLineItemList().stream().map(CartLineItem::getProduct)
            .collect(Collectors.toList());
    assertThat(shoppingCartJson.getLineItemList()).extracting(CartLineItem::getProduct)
        .usingRecursiveFieldByFieldElementComparator()
        .usingElementComparatorIgnoringFields("cartItemId")
        .isEqualTo(products);
    assertThat(shoppingCartJson).isEqualToIgnoringGivenFields(cartFromJsonFile, "lineItemList");
  }

  @Test
  @Sql({"/cart/sql/deliveryMethodFeeService.sql", "/cart/sql/FeeRuleFeeService.sql",
      "/cart/sql/ProductPriceFeeService.sql", "/cart/sql/getCart.sql",
      "/cart/sql/createTransactionData.sql", "/cart/sql/barcodeFieldValuesData.sql"})
  public void precommit() throws Exception {
    TenantPropertiesTest.setUp();
    AddToCartRequest firstRequest =
        new AddToCartRequest.Builder().productId(245634L).priceCatId(12176L).priceClassMap("A1:1")
            .mode(InventoryConstants.SECTION_TYPE_RS.getName())
            .inventoryList(new HashSet<>(Arrays.asList(121555775L))).build();

    String cartGuid = addToCart_Success(firstRequest);
    assertThat(cartGuid).isNotEmpty();

    AddToCartRequest secondRequest =
        new AddToCartRequest.Builder(firstRequest).priceClassMap("A:2").cartGuid(cartGuid)
            .inventoryList(new HashSet<>(Arrays.asList(121555776L, 121555777L))).build();
    cartGuid = addToCart_Success(secondRequest);
    assertThat(cartGuid).isNotEmpty();
    List<String> evoucherIdList = new ArrayList<>();
    evoucherIdList.add("MC4s2Bfj4e");
    evoucherIdList.add("MC77K0r72N");
    String jsonContent = new String(Files.readAllBytes(
        Paths.get(this.getClass().getResource("/cart/json/preCommitRequest.json").toURI())));
    PreCommitRequest
        preCommitRequest = new PreCommitRequest.Builder().creditCardNo("5123456789012346")
        .deliveryMethodCode("MASTERCARD_PICKUP").paymentMethodCode("MASTER").purchaseTp(false)
        .sameAsMailingAddrFlag(false).evoucherIdList(evoucherIdList).build();
    logger
        .debug(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(preCommitRequest));

    Mockito.when(queueService.sendMessage(anyString(), Utils.getToken(), any(PaymentGatewayRequest.class)))
        .thenReturn(ResponseEntity.ok(UUID.randomUUID().toString()));

    mockMvc
        .perform(post("/SISTIC/cart/" + cartGuid + "/precommit").content(jsonContent).locale(locale)
            .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
        .andExpect(jsonPath("$.httpStatus", is("200"))).andExpect(jsonPath("$.statusMessage", is(
        messageSource.getMessage("precommit.success", null, LocaleContextHolder.getLocale()))));
  }

  @Test
  @Sql({"/cart/sql/deliveryMethodFeeService.sql", "/cart/sql/FeeRuleFeeService.sql",
      "/cart/sql/ProductPriceFeeService.sql", "/cart/sql/getCart.sql"})
  public void removeFromCart_LineItemOnly() throws Exception {
    AddToCartRequest firstRequest =
        new AddToCartRequest.Builder().productId(245634L).priceCatId(12176L).priceClassMap("A1:1")
            .mode(InventoryConstants.SECTION_TYPE_RS.getName())
            .inventoryList(new HashSet<>(Arrays.asList(121555775L))).build();

    String cartGuid = addToCart_Success(firstRequest);
    assertThat(cartGuid).isNotEmpty();

    AddToCartRequest secondRequest =
        new AddToCartRequest.Builder(firstRequest).priceClassMap("A:2").cartGuid(cartGuid)
            .inventoryList(new HashSet<>(Arrays.asList(121555776L, 121555777L))).build();
    cartGuid = addToCart_Success(secondRequest);
    assertThat(cartGuid).isNotEmpty();

    ResponseEntity<ShoppingCartJson> cartResponse =
        restTemplate.getForEntity("/SISTIC/cart/" + cartGuid, ShoppingCartJson.class);
    ShoppingCartJson shoppingCartJson = cartResponse.getBody();
    logger
        .debug(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(shoppingCartJson));

    CartLineItem firstCartLineItem = shoppingCartJson.getLineItemList().get(0);
    DeleteCartItemRequest deleteCartItemRequest = new DeleteCartItemRequest.Builder()
        .cartItemId(firstCartLineItem.getCartItemId())
        .priceClassCode(firstCartLineItem.getPriceclass().getPriceClassCode())
        .build();

    HttpEntity<DeleteCartItemRequest>
        deleteCartItemRequestHttpEntity =
        new HttpEntity<>(deleteCartItemRequest, httpHeaders);
    ResponseEntity<JsonResponse>
        deleteResponse =
        restTemplate.exchange("/SISTIC/cart/" + cartGuid, HttpMethod.DELETE,
            deleteCartItemRequestHttpEntity, JsonResponse.class);
    logger
        .debug(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(deleteResponse));
    assertThat(deleteResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
    assertThat(deleteResponse.getBody())
        .hasFieldOrPropertyWithValue("httpStatus", HttpStatus.OK.toString());

    cartResponse =
        restTemplate.getForEntity("/SISTIC/cart/" + cartGuid, ShoppingCartJson.class);
    shoppingCartJson = cartResponse.getBody();
    logger
        .debug(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(shoppingCartJson));
    assertThat(shoppingCartJson.getLineItemList()).isNotEmpty().hasSize(1);
  }

  @Ignore
  @Test
  @Sql({"/cart/sql/deliveryMethodFeeService.sql", "/cart/sql/FeeRuleFeeService.sql",
      "/cart/sql/ProductPriceFeeService.sql", "/cart/sql/getCart.sql"})
  public void removeFromCart_Cart() throws Exception {
    AddToCartRequest firstRequest =
        new AddToCartRequest.Builder().productId(245634L).priceCatId(12176L).priceClassMap("A1:1")
            .mode(InventoryConstants.SECTION_TYPE_RS.getName())
            .inventoryList(new HashSet<>(Arrays.asList(121555775L))).build();

    String cartGuid = addToCart_Success(firstRequest);
    assertThat(cartGuid).isNotEmpty();

    ResponseEntity<ShoppingCartJson> cartResponse =
        restTemplate.getForEntity("/SISTIC/cart/" + cartGuid, ShoppingCartJson.class);
    ShoppingCartJson shoppingCartJson = cartResponse.getBody();
    logger
        .debug(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(shoppingCartJson));

    ResponseEntity<JsonResponse>
        deleteResponse =
        restTemplate.exchange("/SISTIC/cart/" + cartGuid, HttpMethod.DELETE,
            null, JsonResponse.class);
    logger
        .debug(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(deleteResponse));
    assertThat(deleteResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
    assertThat(deleteResponse.getBody())
        .hasFieldOrPropertyWithValue("httpStatus", HttpStatus.OK.toString())
        .hasFieldOrPropertyWithValue("statusMessage",
            messageSource.getMessage("delete.cart.success", new Object[]{cartGuid}, locale));

    ResponseEntity<JsonResponse> noCartResponse =
        restTemplate.getForEntity("/SISTIC/cart/" + cartGuid, JsonResponse.class);
    logger
        .debug(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(noCartResponse));
    assertThat(noCartResponse.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    assertThat(noCartResponse.getBody())
        .hasFieldOrPropertyWithValue("httpStatus", HttpStatus.BAD_REQUEST.toString())
        .hasFieldOrPropertyWithValue("statusMessage",
            messageSource.getMessage("cart.error.not.found", null, locale));
  }

  @Ignore
  @Test
  public void handleMissingParameterException() throws Exception {

  }

  /**
   * Error messages can be found in messages_en.properties
   */
  @Test
  public void handleValidationException() throws Exception {
    Locale locale = LocaleContextHolder.getLocale();
    AddToCartRequest addToCartRequest = new AddToCartRequest.Builder().build();
    ResponseEntity<JsonResponse> cartResponse = addToCart(addToCartRequest);
    assertThat(cartResponse.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);

    logger
        .debug(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(cartResponse));
    List<String> errorMsg = Arrays.asList(
        messageSource.getMessage("addToCartRequest.error.productId", null, locale),
        messageSource.getMessage("addToCartRequest.error.priceCatId", null, locale),
        messageSource.getMessage("addToCartRequest.error.mode", null, locale));
    assertFailureResponseWithErrorMsgs(cartResponse.getBody(), errorMsg);

    addToCartRequest =
        new AddToCartRequest.Builder().mode(InventoryConstants.SECTION_TYPE_GA.getName()).build();
    cartResponse = addToCart(addToCartRequest);
    logger
        .debug(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(cartResponse));
    assertFailureResponseWithErrorMsgs(cartResponse.getBody(), Arrays.asList(
        messageSource.getMessage("addToCartRequest.error.productId", null, locale),
        messageSource.getMessage("addToCartRequest.error.priceCatId", null, locale),
        messageSource.getMessage("addToCartRequest.error.seatSectionId", null, locale)));

    addToCartRequest =
        new AddToCartRequest.Builder().mode(InventoryConstants.SECTION_TYPE_RS.getName()).build();
    cartResponse = addToCart(addToCartRequest);
    logger
        .debug(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(cartResponse));
    assertFailureResponseWithErrorMsgs(cartResponse.getBody(), Arrays.asList(
        messageSource.getMessage("addToCartRequest.error.productId", null, locale),
        messageSource.getMessage("addToCartRequest.error.priceCatId", null, locale),
        messageSource.getMessage("addToCartRequest.error.priceClassMap", null, locale),
        messageSource.getMessage("addToCartRequest.error.inventoryList", null, locale)));
  }

  private void assertFailureResponseWithErrorMsgs(JsonResponse cartResponse,
                                                  List<String> errorMsg) {
    assertThat(cartResponse)
        //.hasFieldOrPropertyWithValue("cartGuid", null)
        .hasFieldOrPropertyWithValue("httpStatus", HttpStatus.BAD_REQUEST.toString())
        .matches(response -> {
          List<String> statusMsg = Pattern.compile(", ").splitAsStream(response.getStatusMessage())
              .collect(Collectors.toList());
          return statusMsg.containsAll(errorMsg);
        }, "Status message does not match");
  }

  private ResponseEntity<JsonResponse> addToCart(AddToCartRequest addToCartRequest)
      throws JsonProcessingException {
    String uri = "/SISTIC/cart";
    HttpEntity<AddToCartRequest>
        addToCartRequestHttpEntity =
        new HttpEntity<>(addToCartRequest, httpHeaders);

    return restTemplate
        .postForEntity(uri, addToCartRequestHttpEntity, JsonResponse.class);
  }
}