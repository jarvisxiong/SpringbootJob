package com.stixcloud.cart;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import javax.money.CurrencyUnit;
import javax.money.Monetary;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.javamoney.moneta.Money;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.stixcloud.cart.api.AddToCartRequest;
import com.stixcloud.cart.api.JsonResponse;
import com.stixcloud.cart.api.PostCommitRequest;
import com.stixcloud.cart.api.PreCommitRequest;
import com.stixcloud.cart.api.ShoppingCartJson;
import com.stixcloud.cart.repo.CartLineItemViewRepository;
import com.stixcloud.cart.repo.CreditCardRegexRepository;
import com.stixcloud.cart.repo.MasterCodeTableRepository;
import com.stixcloud.cart.repo.PaymentMethodRepository;
import com.stixcloud.cart.repo.PriceClassLiveRepository;
import com.stixcloud.cart.repo.SalesSeatInventoryRepository;
import com.stixcloud.cart.repo.ShoppingCartRepository;
import com.stixcloud.cart.repo.TransactionProductFeeRepository;
import com.stixcloud.cart.rules.add.AddToCartRulesEngine;
import com.stixcloud.cart.rules.commit.CommitOrder;
import com.stixcloud.cart.rules.commit.CommitOrderEngine;
import com.stixcloud.cart.rules.postcommit.PostCommitOrder;
import com.stixcloud.cart.rules.postcommit.PostCommitRuleEngine;
import com.stixcloud.cart.rules.precommit.PreCommitRulesEngine;
import com.stixcloud.cart.rules.promovalidation.PromoValidationRuleEngine;
import com.stixcloud.cart.util.Utils;
import com.stixcloud.common.config.multitenant.TenantContextHolder;
import com.stixcloud.domain.CartItem;
import com.stixcloud.domain.CartItemTuple;
import com.stixcloud.domain.CartLineItemsView;
import com.stixcloud.domain.CreditCardRegex;
import com.stixcloud.domain.DeliveryMethod;
import com.stixcloud.domain.PaymentMethod;
import com.stixcloud.domain.PriceClassLive;
import com.stixcloud.domain.SalesSeatInventory;
import com.stixcloud.domain.ShoppingCart;
import com.stixcloud.paymentgateway.api.PaymentGatewayRequest;
import com.stixcloud.paymentgateway.api.RequestMode;
import com.stixcloud.policyagent.util.PAUtil;

/**
 * Created by chongye on 28-Sep-16.
 */
@RefreshScope
@Service
public class ShoppingCartService implements IShoppingCartService {
  private static final Logger logger = LogManager.getLogger(ShoppingCartService.class);
  @Autowired
  private MessageSource messageSource;
  @Autowired
  private ShoppingCartRepository shoppingCartRepository;
  @Autowired
  private ISalesSeatInventoryService salesSeatInventoryService;
  @Autowired
  private PaymentMethodRepository paymentMethodRepository;
  @Autowired
  private CartLineItemViewRepository cartLineItemViewRepository;
  @Autowired
  private PriceClassLiveRepository priceClassLiveRepository;
  @Autowired
  private SalesSeatInventoryRepository salesSeatInventoryRepository;

  @Autowired
  private AddToCartRulesEngine addToCartRulesEngine;
  @Autowired
  private PreCommitRulesEngine preCommitRulesEngine;
  @Autowired
  private CommitOrderEngine commitOrderEngine;
  @Autowired
  private IPaymentGatewayAPIService paymentGatewayAPIService;
  @Autowired
  private IFeeService feeService;
  @Autowired
  private PostCommitRuleEngine postCommitRuleEngine;
  @Autowired
  private MasterCodeTableRepository masterCodeTableRepository;
  @Autowired
  private CreditCardRegexRepository creditCardRegexRepository;
  @Autowired
  private PromoValidationRuleEngine promoValidationRuleEngine;
  @Autowired
  private TransactionProductFeeRepository transactionProductFeeRepository;
  @Value("${waived.delivery.codes}")
  private String waivedDeliveryCodes;

  @Value("${cart.default.timeout}")
  private Long cartTimeout;
  
  public Long getCartTimeout() {
    Long tenantCartTimeout = TenantContextHolder.getTenant().getCartTimeout();
    if(null != tenantCartTimeout & !Long.valueOf(0l).equals(tenantCartTimeout)) {
      cartTimeout = tenantCartTimeout;
    }
    
    return cartTimeout;
  }
  
  /*private ShoppingCart cart;

  @Profile("add_cart")
  @PostConstruct
  public void init() {
    try {
      logger.info("Trying to load shopping cart");
      Resource resource = resourceLoader.getResource("classpath:cart.json");
      URL jsonAsFile = resource.getURL();

      cart = objectMapper.readValue(jsonAsFile, ShoppingCart.class);
      if (!shoppingCartRepository.exists(cart.getCartGuid())) {
        shoppingCartRepository.save(cart);
        logger.info("Shopping cart was loaded successfully.");
      }

    } catch (IOException | NullPointerException e) {
      logger.error("Database reader cound not be initialized. ", e);
    }
  }*/

  @Override
  public ShoppingCart addToCart(AddToCartRequest addToCart, String promoCode) throws AddToCartException {
	  logger.info("AddToCartRequest "+addToCart);
    ShoppingCart shoppingCart = new ShoppingCart.Builder().build();
    List<CartItem> newCartItems;
    try {
      //newCartItems = createCartItems(addToCart);
      newCartItems = createSplitCartItems(addToCart, promoCode);
    } catch (RuntimeException e) {
      //unwrap exception as streams can only throw RuntimeException
      throw new AddToCartException(e.getMessage(), e);
    }

    if (StringUtils.isNotEmpty(addToCart.getCartGuid()) && shoppingCartRepository
        .exists(addToCart.getCartGuid())) {
      shoppingCart = shoppingCartRepository.findOne(addToCart.getCartGuid());
    }

    shoppingCart = new ShoppingCart.Builder(shoppingCart).timeout(getCartTimeout()).build();
    shoppingCart.addToCart(newCartItems);

    logger.debug("Shopping cart : " + shoppingCart.toString());

    Long patronProfileId = PAUtil.getPatronId();

    long startTime = System.nanoTime();

    addToCartRulesEngine.setShoppingCart(shoppingCart);
    addToCartRulesEngine.setPatronProfileId(patronProfileId);
    addToCartRulesEngine.setNewCartItems(newCartItems);
    addToCartRulesEngine.setIcc(addToCart.getIcc());
    addToCartRulesEngine.setMaxQuantity(addToCart.getMaxQuantity());
    addToCartRulesEngine.fireRules();

    shoppingCart = shoppingCartRepository.save(shoppingCart);
    updateInventoriesReserveExpiry(shoppingCart);

    List<Long> seatInvIds = newCartItems.stream()
        .map(CartItem::getInventoryIdList).flatMap(Set::stream).collect(Collectors.toList());

    /*OffsetDateTime reservedTime = salesSeatInventoryService.getEarliestReservedTime(seatInvIds);
    if (reservedTime != null) {
      shoppingCart =
          new ShoppingCart.Builder(shoppingCart).reservedTime(reservedTime.toString()).build();
    }*/
    OffsetDateTime
        reservedStartTime =
        salesSeatInventoryService.getEarliestReservedStartTime(seatInvIds);
    if (reservedStartTime != null) {
      shoppingCart =
          new ShoppingCart.Builder(shoppingCart).reservedTime(reservedStartTime.toString()).build();
    }

    logger.debug("Validation took " + TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - startTime));
    logger.info("AddToCartResponse Shopping cart "+shoppingCart);
    return shoppingCart;
  }

  /**
   * Update the seat reservation expiry timer for all inventory in cart
   */
  private void updateInventoriesReserveExpiry(ShoppingCart cart) {
    List<Long> inventoriesToBeUpdated = new ArrayList<>();
    cart.getCartItems().stream().map(CartItem::getInventoryIdList)
        .forEach(inventoriesToBeUpdated::addAll);
    salesSeatInventoryService.updateSeatsReserveExpiry(inventoriesToBeUpdated);
  }

  private Set<Long> createInventoryList(Long productId, Long priceCatId, Long seatSectionId,
                                        int quantity) throws CartException {
    /*int quantity =
        newCartItem.getPriceClassMap().entrySet().stream().mapToInt(Map.Entry::getValue).sum();*/
    List<SalesSeatInventory> inventories = salesSeatInventoryService
        .findSeatsAndReserveForGA(productId, priceCatId, seatSectionId, quantity);

    return inventories.stream().map(SalesSeatInventory::getSalesseatinventoryid)
        .collect(Collectors.toSet());
  }

  private List<CartItem> createCartItems(AddToCartRequest addToCart) {
    Map<String, String> priceClassMap =
        Arrays.stream(addToCart.getPriceClassMap().split(",")).map(s -> s.split(":"))
            .collect(Collectors.toMap(o -> o[0], e -> e[1] + ":" + e[2]));

    return priceClassMap.entrySet().stream().map(entry -> {
      try {
        String[] priceClassStr = entry.getValue().split(":");
        int ticketQty = Integer.parseInt(priceClassStr[0]);
        Set<Long> invList;

        if (ticketQty <= 0) {
          throw new AddToCartException(messageSource
              .getMessage("addtocart.quantity.error", null, LocaleContextHolder.getLocale()));
        }

        if (addToCart.getMode().equals(InventoryConstants.SECTION_TYPE_GA.getName())) {
          invList =
              createInventoryList(addToCart.getProductId(), addToCart.getPriceCatId(),
                  addToCart.getSeatSectionId(), ticketQty);
        } else {
          invList =
              addToCart.getInventoryList().stream().limit(ticketQty).collect(Collectors.toSet());
          addToCart.getInventoryList().removeAll(invList);
        }

        PriceClassLive
            priceClassLive =
            priceClassLiveRepository.getPriceClassLive(addToCart.getProductId(), entry.getKey());

        Long
            scbPrdtId =
            masterCodeTableRepository.retrieveSCBWaiverProduct(addToCart.getProductId());

        return new CartItem.Builder().cartItemId(UUID.randomUUID().toString())
            .productId(addToCart.getProductId()).priceCatId(addToCart.getPriceCatId())
            .seatSectionId(addToCart.getSeatSectionId()).mode(addToCart.getMode())
            .inventoryIdList(invList).priceClass(entry.getKey())
            .priceClassId(priceClassLive.getPriceclassid()).quantity(ticketQty)
            .sCBWaiverFlag(scbPrdtId == null || scbPrdtId == 0 ? false : true)
            .promoPassword(priceClassStr[1])
            .build();
      } catch (CartException e) {
        logger.error(e.getMessage(), e);
        throw new RuntimeException(e);
      }
    }).collect(Collectors.toList());
  }

  /**
   * 1) Retaining createCartItems method in case need to revert back
   * 2) createSplitCartItems will spilt cartItems if the same ticket type
   *    but it is on different row
   */
  private List<CartItem> createSplitCartItems(AddToCartRequest addToCart, String promoCode) {
    Map<String, String> priceClassMap =
        Arrays.stream(addToCart.getPriceClassMap().split(",")).map(s -> s.split(":"))
            .collect(Collectors.toMap(o -> o[0], e -> e[1]));

    List<CartItem> cartItemList = new ArrayList<CartItem>();

    priceClassMap.entrySet().forEach(entry -> {
      try {
        String[] priceClassStr = entry.getValue().split("\\|");
        int ticketQty = Integer.parseInt(priceClassStr[0]);
        String promoPassword = priceClassStr.length == 1 ? null : priceClassStr[1];
        Set<Long> invList;

        if (ticketQty <= 0) {
          throw new AddToCartException(messageSource
              .getMessage("addtocart.quantity.error", null, LocaleContextHolder.getLocale()));
        }

        PriceClassLive
            priceClassLive =
            priceClassLiveRepository.getPriceClassLive(addToCart.getProductId(), entry.getKey());

        Long
            scbPrdtId =
            masterCodeTableRepository.retrieveSCBWaiverProduct(addToCart.getProductId());

        if (addToCart.getMode().equals(InventoryConstants.SECTION_TYPE_GA.getName())) {
          invList =
              createInventoryList(addToCart.getProductId(), addToCart.getPriceCatId(),
                  addToCart.getSeatSectionId(), ticketQty);

          cartItemList.add(new CartItem.Builder().cartItemId(UUID.randomUUID().toString())
              .productId(addToCart.getProductId()).priceCatId(addToCart.getPriceCatId())
              .seatSectionId(addToCart.getSeatSectionId()).mode(addToCart.getMode())
              .inventoryIdList(invList).priceClass(entry.getKey())
              .priceClassId(priceClassLive.getPriceclassid()).quantity(ticketQty)
              .sCBWaiverFlag(scbPrdtId == null || scbPrdtId == 0 ? false : true)
              .icc(addToCart.getIcc())
              .promoPassword(promoPassword).promoCode(promoCode)
              .build());
        } else {
          invList =
              addToCart.getInventoryList().stream().limit(ticketQty).collect(Collectors.toSet());
          addToCart.getInventoryList().removeAll(invList);

          HashMap<String, List<Long>>
              spiltCartItemList =
              spiltInvListInCartItem(invList, addToCart.getProductId());

          spiltCartItemList.forEach((s, longs) -> {
            cartItemList.add(new CartItem.Builder().cartItemId(UUID.randomUUID().toString())
                .productId(addToCart.getProductId()).priceCatId(addToCart.getPriceCatId())
                .seatSectionId(addToCart.getSeatSectionId()).mode(addToCart.getMode())
                .inventoryIdList(new HashSet(longs)).priceClass(entry.getKey())
                .priceClassId(priceClassLive.getPriceclassid()).quantity(longs.size())
                .sCBWaiverFlag(scbPrdtId == null || scbPrdtId == 0 ? false : true)
                .icc(addToCart.getIcc())
                .promoPassword(promoPassword).promoCode(promoCode)
                .build());
          });
        }

      } catch (CartException e) {
        logger.error(e.getMessage(), e);
        throw new RuntimeException(e);
      }
    });

    return cartItemList;
  }

  private HashMap<String, List<Long>> spiltInvListInCartItem(Set<Long> invList, Long productId) {
    List rowAliasList = salesSeatInventoryRepository.findRowAlias(invList, productId);

    HashMap<String, List<Long>> spiltInvListInCartItemList = new HashMap<String, List<Long>>();
    rowAliasList.forEach(o -> {
      Object[] map = (Object[]) o;
      Long prodId = (Long) map[0];
      String rowAlias = (String) map[1];

      if (!spiltInvListInCartItemList.containsKey(rowAlias)) {
        spiltInvListInCartItemList.put(rowAlias, Arrays.asList(new Long[]{prodId}));
      } else {
        List<Long> prodIdsList = new LinkedList<Long>(spiltInvListInCartItemList.get(rowAlias));
        prodIdsList.add(prodId);
        spiltInvListInCartItemList.put(rowAlias, prodIdsList);
      }
    });

    return spiltInvListInCartItemList;
  }

  @Override
  public ShoppingCart getCart(String cartGuid) throws CartException {
    if (!shoppingCartRepository.exists(cartGuid)) {
      throw new CartException(messageSource.getMessage("cart.error.not.found", null,
          LocaleContextHolder.getLocale()));
    }
    return shoppingCartRepository.findOne(cartGuid);
  }

  @Override
  public ShoppingCartJson getShoppingCartJson(
      String cartGuid, CurrencyUnit currencyUnit, String waivedDeliveryCodes, Long profileInfoId,
      Set<Long> waivedBookingFeePrdtList)
      throws CartException {
    List<DeliveryMethod>
        deliveryMethods =
        feeService.getCommonDeliveryMethods(cartGuid, profileInfoId);
    List<PaymentMethod> paymentMethods =
        this.getCommonPaymentMethods(cartGuid, profileInfoId);
    LinkedList<List<CartLineItemsView>> cartLineItems =
        this.getCartLineItems(cartGuid, currencyUnit, profileInfoId);

    return new ShoppingCartJsonBuilder(feeService)
        .cartGuid(cartGuid)
        .waivedBookingFeePrdtList(waivedBookingFeePrdtList)
        .shoppingCartService(this)
        .cartLineItems(cartLineItems)
        .commonDeliveryMethods(deliveryMethods).waivedDeliveryCode(waivedDeliveryCodes)
        .commonPaymentMethods(paymentMethods).currencyUnit(currencyUnit).build();
  }

  @Override
  public ShoppingCartJson getShoppingCartJsonPostCommit(
      String cartGuid, CurrencyUnit currencyUnit, String waivedDeliveryCodes, Long profileInfoId,
      Set<Long> waivedBookingFeePrdtList)
      throws CartException {
    ShoppingCart cart = this.getCart(cartGuid);
    List<Object[]>
        bookingFeeList =
        transactionProductFeeRepository
            .findBookingFeeListByTransactionRefNumber(cart.getTransactionReferenceNumber());
    Map<String, BigDecimal> bookingFeeMap = new HashMap<>();
    bookingFeeList.forEach(e -> bookingFeeMap.put(e[0] + "-" + e[1] + "-" + e[2], (BigDecimal) e[3]));
   /* List<DeliveryMethod>
        deliveryMethods =
        feeService.getCommonDeliveryMethods(cartGuid, profileInfoId);
    List<PaymentMethod> paymentMethods =
        this.getCommonPaymentMethods(cartGuid, profileInfoId);*/
    LinkedList<List<CartLineItemsView>> cartLineItems =
        this.getCartLineItems(cartGuid, currencyUnit, profileInfoId);

    return new ShoppingCartJsonBuilder(feeService)
        .cartGuid(cartGuid)
        .waivedBookingFeePrdtList(waivedBookingFeePrdtList)
        .shoppingCartService(this)
        .cartLineItems(cartLineItems)
        .waivedDeliveryCode(waivedDeliveryCodes)
        .currencyUnit(currencyUnit)
        .bookingFeeMap(bookingFeeMap)
        .build();
    // .commonDeliveryMethods(deliveryMethods).waivedDeliveryCode(waivedDeliveryCodes)
    //  .commonPaymentMethods(paymentMethods).currencyUnit(currencyUnit).build();
  }

  @Override
  public ShoppingCart removeFromCart(String cartGuid, String cartItemId,
                                     String priceClassCode) throws CartException {
    ShoppingCart cart = this.getCart(cartGuid);

    boolean cartItemIdExists = cart.getCartItems().stream().anyMatch(
        ci -> ci.getCartItemId().equals(cartItemId));
    if (!cartItemIdExists) {
      throw new CartException(messageSource
          .getMessage("delete.cart.item.error.cartGuid", new Object[]{cartGuid},
              LocaleContextHolder.getLocale()));
    }

    boolean priceClassCodeExists = cart.getCartItems().stream().anyMatch(
        ci -> ci.getCartItemId().equals(cartItemId) && ci.getPriceClass().equals(priceClassCode));
    if (!priceClassCodeExists) {
      throw new CartException(messageSource
          .getMessage("delete.cart.item.error.priceClassCode", new Object[]{cartGuid},
              LocaleContextHolder.getLocale()));
    }

    cart.getCartItems().stream()
        .filter(ci -> ci.getCartItemId().equals(cartItemId)
            && ci.getPriceClass().equals(priceClassCode))
        .forEach(ci -> {
              List<Long> seatsList = new ArrayList<>(ci.getInventoryIdList());
              ci.getInventoryIdList().removeAll(seatsList);
              salesSeatInventoryService.releaseSeats(seatsList);
            }
        );

    CartItem cartItemToBeRemoved = cart.getCartItems().stream()
        .filter(ci -> ci.getCartItemId().equals(cartItemId)
            && ci.getPriceClass().equals(priceClassCode))
        .findFirst().orElse(null);
    if (cartItemToBeRemoved != null) {
      cart.getCartItems().remove(cartItemToBeRemoved);
    }
    return shoppingCartRepository.save(cart);
  }

  @Override
  public void removeCart(String cartGuid) throws CartException {
    ShoppingCart cart = this.getCart(cartGuid);
    List<Long> seatsList = cart.getCartItems().stream().map(CartItem::getInventoryIdList)
        .flatMap(l -> l.stream()).collect(Collectors.toList());
    salesSeatInventoryService.releaseSeats(seatsList);
    shoppingCartRepository.delete(cartGuid);
  }

  @Override
  public List<PaymentMethod> getCommonPaymentMethods(String cartGuid, Long profileInfoId)
      throws CartException {
    ShoppingCart cart = this.getCart(cartGuid);
    List<PaymentMethod> paymentMethodList = cart
        .getCartItems().stream().map(entry -> paymentMethodRepository
            .getCommonPaymentMethod(entry.getProductId(), profileInfoId, entry.getPromoCode()))
        .reduce((o1, o2) -> {
          o1.retainAll(o2);
          return o1;
        }).orElse(new ArrayList<>());

    return paymentMethodList;
  }

  @Override
  public LinkedList<List<CartLineItemsView>> getCartLineItems(String cartGuid,
          CurrencyUnit currencyUnit,
          Long profileInfoId) 
        throws CartException{
	  return getCartLineItems(cartGuid, currencyUnit, profileInfoId, RuleConstants.ISPACKAGECLASS_F);	  
  }
  @Override
  public LinkedList<List<CartLineItemsView>> getCartLineItems(String cartGuid,
                                                              CurrencyUnit currencyUnit,
                                                              Long profileInfoId,
                                                              String isPackageClass)
      throws CartException {
    ShoppingCart cart = this.getCart(cartGuid);
    //collect CartLineItemsView into a flat list
    LinkedList<List<CartLineItemsView>> retList = cart.getCartItems().stream().map(cartItem ->
        cartItem.getInventoryIdList().stream().map(invId -> {
          CartLineItemsView cartLineItemsView = cartLineItemViewRepository
              .findCartLineItem(cartItem.getProductId(), cartItem.getPriceCatId(),
                  cartItem.getSeatSectionId(),
                  (!cartItem.getMode().equals("GA") ? "RS" : "GA"),
                  cartItem.getPriceClass(), invId, profileInfoId);

          if (cartLineItemsView != null) {
            cartLineItemsView.setCartItemId(cartItem.getCartItemId());
            cartLineItemsView.setIcc(cartItem.getIcc());
          }

          return cartLineItemsView;
        }).filter(Objects::nonNull).collect(Collectors.toCollection(LinkedList::new))
    ).collect(Collectors.toCollection(LinkedList::new));
    retList.removeIf(List::isEmpty);
    return retList;
  }

  @Override
  public List<Long> getProductIdsByCartGuid(String cartGuid) {
    List<Long> productIds = new ArrayList<>();
    ShoppingCart shoppingCart = shoppingCartRepository.findOne(cartGuid);
    if (!shoppingCart.getCartItems().isEmpty()) {
      for (CartItem cartItem : shoppingCart.getCartItems()) {
        CartItemTuple item = cartItem.getTuple();
        if (item != null) {
          productIds.add((Long) item.getProductId());
        }
      }
    }
    return productIds;
  }

  @Override
  @Transactional
  public JsonResponse precommit(String cartGuid, PreCommitRequest preCommitRequest)
      throws CartException {
    JsonResponse.Builder builder = new JsonResponse.Builder();
    ShoppingCart shoppingCart = this.getCart(cartGuid);
    logger.info("PreCommit Api called ");
    logger.info("PreCommitRequest Evoucher List "+preCommitRequest.getEvoucherIdList());
    Long profileInfoId = TenantContextHolder.getTenant().getProfileInfoId();//11L
    Long userProfileId = TenantContextHolder.getTenant().getUserInfoId();//59L
    Long patronProfileId = PAUtil.getPatronId();
    //Long patronProfileId = 1922246L; //TODO: Remove this after OAuth is fixed
    CurrencyUnit currencyUnit = Monetary.getCurrency(LocaleContextHolder.getLocale());
    ShoppingCartJson
        shoppingCartJson =
        this.getShoppingCartJson(shoppingCart.getCartGuid(), currencyUnit, waivedDeliveryCodes,
            profileInfoId, null);

    //pre-commit validation
    preCommitRulesEngine.setShoppingCartJson(shoppingCartJson);
    preCommitRulesEngine.setShoppingCart(shoppingCart);
    preCommitRulesEngine.setPreCommitRequest(preCommitRequest);
    preCommitRulesEngine.setPatronProfileId(patronProfileId);
    preCommitRulesEngine.setUpdatedBy(userProfileId);
    preCommitRulesEngine.fireRules();

    shoppingCart = new ShoppingCart.Builder(shoppingCart)
        .paymentMethodCode(preCommitRequest.getPaymentMethodCode())
        .deliveryMethodCode(preCommitRequest.getDeliveryMethodCode())
        .patronSolicitation(preCommitRequest.getPatronSolicitation())
        .addressList(preCommitRequest.getAddress())
        .evoucherIdList(preCommitRequest.getEvoucherIdList())
        .creditCardNo(preCommitRequest.getCreditCardNo())
        .creditCardExpiryMM(preCommitRequest.getCreditCardMonth())
        .creditCardExpiryYY(preCommitRequest.getCreditCardYear())
        .creditCardCSC(preCommitRequest.getCreditCardCSC())
        .purchaseTp(preCommitRequest.getPurchaseTp())
        .build();

    //commit order
    updateInventoriesReserveExpiry(shoppingCart);

    CommitOrder commitOrder = new CommitOrder();
    commitOrder.setShoppingCartJson(shoppingCartJson);
    commitOrder.setProfileInfoId(profileInfoId);
    commitOrder.setUserProfileId(userProfileId);
    commitOrder.setPatronProfileId(patronProfileId);
    commitOrderEngine.setCommitOrder(commitOrder);
    commitOrderEngine.setShoppingCart(shoppingCart);
    commitOrderEngine.fireRules();

    ResponseEntity<String> queueResponse = null;
    if (!commitOrder.getIsFullyRedeemed()) {
      PaymentGatewayRequest paymentGatewayRequest =
          new PaymentGatewayRequest.Builder()
              .paymentMethodCode(preCommitRequest.getPaymentMethodCode())
              .cartGuid(shoppingCart.getCartGuid())
              .mode(RequestMode.PRECOMMIT).build();

      queueResponse =
          paymentGatewayAPIService
              .sendMessage(TenantContextHolder.getTenant().getName(), Utils.getToken(), paymentGatewayRequest);
      logger.debug(queueResponse);
      builder.statusMessage(
          messageSource.getMessage("precommit.success", null, LocaleContextHolder.getLocale()))
          .messageId(queueResponse.getBody())
          .payableAmount(Money.of(commitOrder.getPayableAmount(), currencyUnit));
    } else {
      builder.payableAmount(Money.of(commitOrder.getPayableAmount(), currencyUnit))
          .isFullyRedeemed(true);
    }
    shoppingCart = new ShoppingCart.Builder(shoppingCart)
        .transactionReferenceNumber(commitOrder.getTransaction().getTransactionrefnumber())
        .isFullyRedeemed(commitOrder.getIsFullyRedeemed())
        .messageUuid(queueResponse != null ? queueResponse.getBody() : null)
        .timeout(getCartTimeout())
        .payableAmount(commitOrder.getPayableAmount())
        .build();
    shoppingCart = shoppingCartRepository.save(shoppingCart);

    Boolean popUp = false;
    if (commitOrder.getSCBJsonResponse() != null
        && commitOrder.getSCBJsonResponse().getAdditionalProperties().get("popUp") != null) {
      popUp = (Boolean) commitOrder.getSCBJsonResponse().getAdditionalProperties().get("popUp");
    }
    logger.info("PreCommit Api Excecuted Succesfully");
    return builder.httpStatus(HttpStatus.OK.toString())
        .transactionRefNumber(shoppingCart.getTransactionReferenceNumber())
        .popUp(popUp)
        .popUpMessage(commitOrder.getSCBJsonResponse().getPopUpMessage())
        .build();
  }

  @Override
  @Transactional(propagation = Propagation.REQUIRED)
  public PostCommitOrder postcommit(String cartGuid, PostCommitRequest postCommitRequest)
      throws CartException {
	  logger.info("Post Commit API Called ");
    Long profileInfoId = TenantContextHolder.getTenant().getProfileInfoId();//11L
    Long userProfileId = TenantContextHolder.getTenant().getUserInfoId();//59L
    Long patronProfileId = PAUtil.getPatronId();
    //Long patronProfileId = 1922246L; //TODO: Remove this after OAuth is fixed
    ShoppingCart shoppingCart = this.getCart(cartGuid);
    PostCommitOrder postCommitOrder = new PostCommitOrder();
    postCommitOrder.setPatronProfileId(patronProfileId);
    postCommitOrder.setUserProfileId(userProfileId);
    postCommitOrder.setProfileInfoId(profileInfoId);
    postCommitOrder.setPaymentMethodCode(shoppingCart.getPaymentMethodCode());
    postCommitOrder.setTransactionReferenceNumber(shoppingCart.getTransactionReferenceNumber());
    postCommitRuleEngine.setPostCommitRequest(postCommitRequest);
    postCommitRuleEngine.setShoppingCart(shoppingCart);
    postCommitRuleEngine.setPostCommitOrder(postCommitOrder);
    postCommitRuleEngine.fireRules();
    logger.info("Post Commit rules fired succesfully");
    CurrencyUnit currencyUnit = Monetary.getCurrency(LocaleContextHolder.getLocale());
    ShoppingCartJson
        shoppingCartJson =
        getShoppingCartJsonPostCommit(cartGuid, currencyUnit, cartGuid, profileInfoId, null);
    postCommitOrder.setShoppingCartJson(shoppingCartJson);
    logger.info("Deleting shoppingCart");
    if (postCommitOrder.getPaymentResponse().getOrderStatus() == 0) {//on success
      shoppingCartRepository.delete(shoppingCart.getCartGuid());
    }
    logger.info("Finished Deleting Shopping cart");
    logger.info("Post Commit API Ended Succesfully");
    return postCommitOrder;
  }

  public ShoppingCart updateCartExpirationTTL(String cartGuid, Long ttl) throws CartException {
	  logger.info("updateCartExpirationTTL  called");
    ShoppingCart shoppingCart = new ShoppingCart.Builder().build();
    
    if (StringUtils.isNotEmpty(cartGuid) && shoppingCartRepository.exists(cartGuid)) {
      shoppingCart = shoppingCartRepository.findOne(cartGuid);

      shoppingCart = new ShoppingCart.Builder(shoppingCart).timeout(ttl).build();

      shoppingCart = shoppingCartRepository.save(shoppingCart);
      updateInventoriesReserveExpiry(shoppingCart);
    }
    logger.info("updateCartExpirationTTL  ended succesfully");

    return shoppingCart;
  }

  public Boolean isValidSCBWaiverCreditCard(String ccNum) throws CartException {
    List<CreditCardRegex>
        listCreditCardRegex =
        creditCardRegexRepository
            .getCreditCardRegexesByCreditCardName("SCB Waiver of Booking Fees");
    boolean res = false;
    if (listCreditCardRegex != null && !listCreditCardRegex.isEmpty() && StringUtils
        .isNotEmpty(ccNum)) {
      for (CreditCardRegex ccRegex : listCreditCardRegex) {
        res |= ccNum.matches(ccRegex.getRegularExpression());
        if (res) {
          //break the loop
          break;
        }
      }
    }

    return res;
  }

  public Set<Long> getValidSCBWaiverShows(ShoppingCart shoppingCart) throws CartException {
    return shoppingCart.getCartItems().stream()
        .filter(cartItem -> cartItem.isSCBWaiverFlag() != null && cartItem.isSCBWaiverFlag())
        .map(CartItem::getProductId).collect(Collectors.toSet());
  }

  @Override
  public JsonResponse promoValidation(ShoppingCart shoppingCart)
      throws CartException {
    CommitOrder commitOrder = new CommitOrder();

    Long profileInfoId = TenantContextHolder.getTenant().getProfileInfoId();//11L
    CurrencyUnit currencyUnit = Monetary.getCurrency(LocaleContextHolder.getLocale());
    ShoppingCartJson
        shoppingCartJson =
        this.getShoppingCartJson(shoppingCart.getCartGuid(), currencyUnit, waivedDeliveryCodes,
            profileInfoId, null);
    promoValidationRuleEngine.setCommitOrder(commitOrder);
    promoValidationRuleEngine.setShoppingCart(shoppingCart);
    commitOrder.setShoppingCartJson(shoppingCartJson);
    promoValidationRuleEngine.fireRules();
    return commitOrder.getSCBJsonResponse();
  }

  public void removeQueueMessage(String messageUuid) throws CartException {
    paymentGatewayAPIService
        .removeMessage(TenantContextHolder.getTenant().getName(), Utils.getToken(), messageUuid);
  }
}
