package com.stixcloud.cart.api;

import com.stixcloud.cart.AddToCartException;
import com.stixcloud.cart.CartException;
import com.stixcloud.cart.IShoppingCartService;
import com.stixcloud.cart.NotificationService;
import com.stixcloud.cart.PostCommitCartException;
import com.stixcloud.cart.PreCommitCartException;
import com.stixcloud.cart.TransactionPaymentDetails;
import com.stixcloud.cart.rules.postcommit.PostCommitOrder;
import com.stixcloud.common.config.multitenant.TenantContextHolder;
import com.stixcloud.domain.ShoppingCart;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.money.CurrencyUnit;
import javax.money.Monetary;
import javax.money.MonetaryAmount;
import javax.validation.Valid;

@RestController
@CrossOrigin
@RequestMapping("/{tenant}")
public class ShoppingCartController {
  private static final Logger logger = LogManager.getLogger(ShoppingCartController.class);
  @Autowired
  NotificationService notificationService;
  private MessageSource messageSource;
  private IShoppingCartService shoppingCartService;
  @Value("${waived.delivery.codes}")
  private String waivedDeliveryCodes;

  @Autowired
  public ShoppingCartController(MessageSource messageSource,
                                IShoppingCartService shoppingCartService) {
    this.messageSource = messageSource;
    this.shoppingCartService = shoppingCartService;
  }
  
  @RequestMapping("/ready")
  public String ready() {
    return "true";
  }

  @InitBinder("addToCartRequest")
  protected void initBinder(WebDataBinder binder) {
    binder.setValidator(new AddToCartRequestValidator());
  }

  @RequestMapping(value = "/cart", method = RequestMethod.POST)
  @ResponseBody
  public JsonResponse addToCart(@Valid @RequestBody AddToCartRequest addToCartRequest,
                                @RequestParam(value = "promocode", defaultValue = "", required = false) String promoCode,
                                Locale locale) throws AddToCartException {
    JsonResponse.Builder builder = new JsonResponse.Builder();
    ShoppingCart shoppingCart = shoppingCartService.addToCart(addToCartRequest, promoCode);
    if (shoppingCart != null && shoppingCart.getCartItems().size() > 0 && StringUtils.isNotEmpty(shoppingCart.getCartGuid())) {
      return builder.httpStatus(HttpStatus.OK.toString()).cartGuid(shoppingCart.getCartGuid())
          .statusMessage(messageSource.getMessage("addtocart.success", null, locale))
          .reservedTime(shoppingCart.getReservedTime())
          .build();
    } else {
      throw new AddToCartException(messageSource.getMessage("addtocart.failure", null, locale));
    }
  }

  @RequestMapping(value = "/cart/{cartGuid}/expiration/{ttl}", method = RequestMethod.GET)
  @ResponseBody
  public JsonResponse getCartExpirationTTL(@PathVariable String cartGuid,
                                           @PathVariable Long ttl,
                                           Locale locale)
      throws CartException {
    JsonResponse.Builder builder = new JsonResponse.Builder();

    ShoppingCart shoppingCart = shoppingCartService.updateCartExpirationTTL(cartGuid, ttl);
    if (shoppingCart.getCartItems() != null && StringUtils.isNotEmpty(shoppingCart.getCartGuid())) {
      return builder.httpStatus(HttpStatus.OK.toString()).cartGuid(shoppingCart.getCartGuid())
          .statusMessage(messageSource.getMessage("updatecartttl.success", null, locale))
          .build();
    } else {
      throw new CartException(messageSource.getMessage("updatecartttl.failure", null, locale));
    }
  }

  @RequestMapping(value = "/cart/{cartGuid}", method = RequestMethod.GET)
  @ResponseBody
  public ShoppingCartJson getCart(@PathVariable String cartGuid, Locale locale)
      throws CartException {
    CurrencyUnit currencyUnit = Monetary.getCurrency(locale);

    return shoppingCartService
        .getShoppingCartJson(cartGuid, currencyUnit, waivedDeliveryCodes,
            TenantContextHolder.getTenant().getProfileInfoId(), null);
  }

  @RequestMapping(value = "/cart/{cartGuid}", method = RequestMethod.DELETE)
  @ResponseBody
  public JsonResponse removeFromCart(@PathVariable String cartGuid,
                                     @RequestBody(required = false) DeleteCartItemRequest deleteCartItemRequest,
                                     Locale locale)
      throws CartException {
    JsonResponse.Builder builder = new JsonResponse.Builder();
    logger.info(
            "removeFromCart api called with request "+deleteCartItemRequest);
    if (deleteCartItemRequest == null) {
      shoppingCartService.removeCart(cartGuid);
      builder.httpStatus(HttpStatus.OK.toString())
          .statusMessage(
              messageSource.getMessage("delete.cart.success", null, locale));
    } else {
      shoppingCartService.removeFromCart(cartGuid, deleteCartItemRequest.getCartItemId(),
          deleteCartItemRequest.getPriceClassCode());
      builder.httpStatus(HttpStatus.OK.toString())
          .statusMessage(
              messageSource.getMessage("delete.cart.item.success",
                  new Object[]{deleteCartItemRequest.getCartItemId()}, locale));
    }
    logger.info(
            "removeFromCart api ended succesfully");
    return builder.build();
  }

  @RequestMapping(value = "/cart/{cartGuid}/precommit", method = RequestMethod.POST)
  @ResponseBody
  public Object precommit(@PathVariable String cartGuid,
                          @Valid @RequestBody PreCommitRequest preCommitRequest,
                          Locale locale) throws CartException {
    ShoppingCart cart = shoppingCartService.getCart(cartGuid);
    if (cart == null) {
      throw new CartException(messageSource.getMessage("cart.error.not.found", null, locale));
    }

    try {
      return shoppingCartService.precommit(cartGuid, preCommitRequest);
    } catch (CartException e) {
      logger.error(e.getMessage(), e);
      throw new PreCommitCartException(e.getMessage(), e);
    }
  }

  @RequestMapping(value = "/cart/{cartGuid}", method = RequestMethod.POST)
  @ResponseBody
  public Object postcommit(@PathVariable String cartGuid,
                           @Valid @RequestBody PostCommitRequest postCommitRequest,
                           Locale locale) throws CartException {
    JsonResponse.Builder builder = new JsonResponse.Builder();
    ShoppingCart cart = shoppingCartService.getCart(cartGuid);
    if (cart == null) {
      throw new CartException(messageSource.getMessage("cart.error.not.found", null, locale));
    }
    String messageUuid = cart.getMessageUuid();
    logger.info("messageUuid "+messageUuid);
    Instant start = Instant.now();
    logger.info(
        "Post Commit Start Time for transaction " + cart.getTransactionReferenceNumber() + ":"
            + start.toString());
    logger.debug("postcommit transrefnumber " + cart.getTransactionReferenceNumber());
    logger.debug("postcommit cartguiid " + cart.getCartGuid());
    try {
      PostCommitOrder postCommitOrder = shoppingCartService.postcommit(cartGuid, postCommitRequest);
      logger.info(
          "Post Commit Total Time for transaction " + cart.getTransactionReferenceNumber() + ":"
              + Duration.between(start, Instant.now()));
      if(StringUtils.isNotEmpty(messageUuid)){
    	  shoppingCartService.removeQueueMessage(messageUuid);
      }
      if (postCommitOrder.getPaymentResponse().getOrderStatus() == 0) {//SUCCESS
    	  logger.info(
    	            "Post Commit ended succesfully");
    	  MonetaryAmount totalPaymentAmount=postCommitOrder.getPaymentResponse().getTotalPaymentAmount(); // deafault for complimentary it will be 0
    	  if(postCommitOrder.getPaymentList()!=null && !postCommitOrder.getPaymentList().isEmpty()){
    		  totalPaymentAmount= postCommitOrder.getPaymentList().stream()
      	            .map(TransactionPaymentDetails::getSubAmount).reduce(MonetaryAmount::add).get();
    	  }
    	  
        return new PostCommitOrderJson.Builder()
            .orderId(postCommitOrder.getPaymentResponse().getOrderId())
            .confirmOrderStatus("1")
            .orderStatus(
                (postCommitOrder.getPaymentResponse().getOrderStatus() == 0) ? "true" : "false")
            .transactionRefNumber(postCommitOrder.getTransactionReferenceNumber())
            .transactionPaymentDetailsList(postCommitOrder.getPaymentList())
            .totalPayment(totalPaymentAmount)
            .shoppingCart(postCommitOrder.getShoppingCartJson())
            .transactionTime(postCommitOrder.getTransaction().getTransactedtime().toString())
            .build();
      } else {
        JsonResponse jsonResponse = builder.httpStatus(HttpStatus.INTERNAL_SERVER_ERROR.toString())
            .transactionRefNumber(postCommitOrder.getTransactionReferenceNumber())
            .status("payment.status.card.failure")
            .statusMessage(messageSource.getMessage("postcommit.error.confirm.order", null, locale))
            .build();
        logger.info("Error in postcommit returning INTERNAL_SERVER_ERROR  " + jsonResponse);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(jsonResponse);
      }
    } catch (CartException e) {
    	if(StringUtils.isNotEmpty(messageUuid)){
    		shoppingCartService.removeQueueMessage(messageUuid);
    	}
      logger.error(e.getMessage(), e);

      throw new PostCommitCartException(e.getMessage(), e);
    }
    
    catch (Exception e) {
    	if(StringUtils.isNotEmpty(messageUuid)){
    		shoppingCartService.removeQueueMessage(messageUuid);
    	}
      logger.error(e.getMessage(), e);

      throw new PostCommitCartException(e.getMessage(), e);
    }
    
  }

  @RequestMapping(value = "/cart/{cartGuid}/promotion/{ccNum}", method = RequestMethod.GET)
  @ResponseBody
  public JsonResponse promotion(@PathVariable String cartGuid,
                                @PathVariable String ccNum,
                                Locale locale)
      throws CartException {
    ShoppingCart shoppingCart = shoppingCartService.getCart(cartGuid);
    if (shoppingCart == null) {
      throw new CartException(messageSource.getMessage("updatecartttl.failure", null, locale));
    }
    shoppingCart = new ShoppingCart.Builder(shoppingCart).creditCardNo(ccNum).build();
    JsonResponse scbJsonResponse = shoppingCartService.promoValidation(shoppingCart);
    return scbJsonResponse;
  }

  @RequestMapping(value = "/cart/{cartGuid}/checkcart", method = RequestMethod.GET)
  @ResponseBody
  public CheckCartResponse CheckCart(@PathVariable String cartGuid, Locale locale)
      throws CartException {
	  logger.info("CheckCart API called ");
	  ShoppingCart cart = shoppingCartService.getCart(cartGuid);
	    if (cart == null) {
	      throw new CartException(messageSource.getMessage("cart.error.not.found", null, locale));
	    }
    CurrencyUnit currencyUnit = Monetary.getCurrency(locale);
    CheckCartResponse.Builder builder = new CheckCartResponse.Builder();
    builder = builder.cartLineItems(null);
    ShoppingCartJson shoppingCartJson = shoppingCartService
        .getShoppingCartJson(cartGuid, currencyUnit, waivedDeliveryCodes,
            TenantContextHolder.getTenant().getProfileInfoId(), null);

    List<CheckCartItem> lineItemList = new ArrayList<CheckCartItem>();
    if(shoppingCartJson!=null && shoppingCartJson.getLineItemList()!=null) {
    	shoppingCartJson.getLineItemList().forEach(item -> {
    		lineItemList.add(new CheckCartItem.Builder().product(
    				new CheckCartProduct.Builder().productDate(item.getProduct().getProductDate())
    				.productId(item.getProduct().getProductId())
    				.productName(item.getProduct().getProductName())
    				.venue(item.getProduct().getVenue()).build())
    				.quantity(item.getQuantity())
    				.build());
    	});
    }
    logger.info("CheckCart API ended succesfully ");

    return new CheckCartResponse.Builder().cartLineItems(lineItemList).build();

  }

  @ExceptionHandler(MissingServletRequestParameterException.class)
  @ResponseBody
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public JsonResponse handleMissingParameterException(MissingServletRequestParameterException e,
                                                      Locale locale) {
	  logger.info("handleMissingParameterException API ");
    logger.error(e.getMessage(), e);
    JsonResponse.Builder builder =
        new JsonResponse.Builder().httpStatus(HttpStatus.BAD_REQUEST.toString());
    switch (e.getParameterName()) {
      case "contentCode":
        builder
            .statusMessage(messageSource.getMessage("missing.param.contentCode", null, locale));
      default:
        builder.statusMessage(e.getMessage());
        break;
    }
    return builder.build();
  }

  @RequestMapping(value = "/cart/emailTest/{emailAddress}", method = RequestMethod.GET)
  @ResponseBody
  public JsonResponse testEmail(
      @PathVariable String emailAddress,
      Locale locale)
      throws CartException {
    logger.info("begin testEmail");
    try {
      notificationService.testEmail(emailAddress);
    } catch (Exception e) {
      logger.error(e);
    }
    logger.info("begin testEmail");
    JsonResponse.Builder builder =
        new JsonResponse.Builder().statusMessage("success");
    return builder.build();
  }

}
