package com.stixcloud.cart;

import com.stixcloud.cart.api.CartLineItem;
import com.stixcloud.cart.api.DeliveryMethodJson;
import com.stixcloud.cart.api.DeliveryMethodsJson;
import com.stixcloud.cart.api.PaymentMethodJson;
import com.stixcloud.cart.api.PaymentMethodsJson;
import com.stixcloud.cart.api.Priceclass;
import com.stixcloud.cart.api.Product;
import com.stixcloud.cart.api.ShoppingCartJson;
import com.stixcloud.domain.CartLineItemsView;
import com.stixcloud.domain.DeliveryMethod;
import com.stixcloud.domain.PaymentMethod;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.javamoney.moneta.Money;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import javax.money.CurrencyUnit;
import javax.money.MonetaryAmount;

/**
 * Created by chongye on 4/11/2016.
 */
public class ShoppingCartJsonBuilder {
  private static final Logger logger = LogManager.getLogger(ShoppingCartJsonBuilder.class);

  private LinkedList<List<CartLineItemsView>> lineItemList;
  private List<PaymentMethod> commonPaymentMethods;
  private List<DeliveryMethod> commonDeliveryMethods;
  private CurrencyUnit unitPrice;
  private String waivedDeliveryCodes;
  private Set<Long> waivedBookingFeePrdtList;
  private Map<String, BigDecimal> bookingFeeMap;
  private String cartGuid;
  private IFeeService feeService;
  private IShoppingCartService shoppingCartService;


  public ShoppingCartJsonBuilder(IFeeService feeService) {
    this.feeService = feeService;
  }

  public ShoppingCartJsonBuilder cartLineItems(LinkedList<List<CartLineItemsView>> lineItemList) {
    this.lineItemList = lineItemList;
    return this;
  }

  public ShoppingCartJsonBuilder commonPaymentMethods(List<PaymentMethod> commonPaymentMethod) {
    this.commonPaymentMethods = commonPaymentMethod;
    return this;
  }

  public ShoppingCartJsonBuilder commonDeliveryMethods(List<DeliveryMethod> commonDeliveryMethod) {
    this.commonDeliveryMethods = commonDeliveryMethod;
    return this;
  }

  public ShoppingCartJsonBuilder currencyUnit(CurrencyUnit unitPrice) {
    this.unitPrice = unitPrice;
    return this;
  }

  public ShoppingCartJsonBuilder waivedDeliveryCode(String waivedDeliveryCodes) {
    this.waivedDeliveryCodes = waivedDeliveryCodes;
    return this;
  }

  public ShoppingCartJsonBuilder waivedBookingFeePrdtList(Set<Long> waivedBookingFeePrdtList) {
    this.waivedBookingFeePrdtList = waivedBookingFeePrdtList;
    return this;
  }

  public ShoppingCartJsonBuilder cartGuid(String cartGuid) {
    this.cartGuid = cartGuid;
    return this;
  }

  public ShoppingCartJsonBuilder shoppingCartService(ShoppingCartService shoppingCartService) {
    this.shoppingCartService = shoppingCartService;
    return this;
  }

  public ShoppingCartJsonBuilder bookingFeeMap(Map<String, BigDecimal> bookingFeeMap) {
    this.bookingFeeMap = bookingFeeMap;
    return this;
  }

  public ShoppingCartJson build() {
    ShoppingCartJson.Builder builder = new ShoppingCartJson.Builder();
    if (lineItemList != null && !lineItemList.isEmpty()) {
      List<CartLineItem> cartLineItems = buildCartLineItems();
      builder.cartLineItems(cartLineItems);

      builder.lineItemTotal(buildLineItemTotal(cartLineItems));
    }
    if (commonPaymentMethods != null && !commonPaymentMethods.isEmpty()) {
      PaymentMethodsJson paymentMethodsJson = buildCommonPaymentMethods();
      builder.commonPaymentMethods(paymentMethodsJson);
    }
    if (commonDeliveryMethods != null && !commonDeliveryMethods.isEmpty()) {
      DeliveryMethodsJson deliveryMethodsJson = buildCommonDeliveryMethods();
      builder.commonDeliveryMethods(deliveryMethodsJson);
    }
    if (unitPrice != null) {
      builder.currencyUnit(unitPrice);
    }

    return builder.build();
  }

  private MonetaryAmount buildLineItemTotal(List<CartLineItem> cartLineItems) {
    return cartLineItems.stream().map(cartLineItem -> cartLineItem.getSubTotal())
        .reduce(MonetaryAmount::add).get();
  }

  private List<CartLineItem> buildCartLineItems() {
    Instant start = Instant.now();
    LinkedList<CartLineItem> cartLineItems = lineItemList.stream()
        .map(cartItemList -> {
          List<Integer> seatsList =
              cartItemList.stream().map(CartLineItemsView::getSeatno).filter(Objects::nonNull)
                  .sorted().collect(Collectors.toList());

          return buildCartLineItem(cartItemList.get(0), seatsList, (long) cartItemList.size(),
              unitPrice);
        })
        .collect(Collectors.toCollection(LinkedList::new));
    logger.debug("Cart line items : " + Duration.between(start, Instant.now()));
    return cartLineItems;
  }

  private PaymentMethodsJson buildCommonPaymentMethods() {
    Instant start = Instant.now();
    List<PaymentMethodJson> paymentMethodJsons = commonPaymentMethods.stream()
        .sorted(Comparator.comparingLong(PaymentMethod::getOrdermethod))
        .map(PaymentMethod::getPaymentmethodcode)
        .map(PaymentMethodJson::new).collect(Collectors.toCollection(LinkedList::new));
    PaymentMethodsJson paymentMethodsJson = new PaymentMethodsJson(paymentMethodJsons);
    logger.debug("Payment methods : " + Duration.between(start, Instant.now()));
    return paymentMethodsJson;
  }

  private DeliveryMethodsJson buildCommonDeliveryMethods() {
    Instant start = Instant.now();
    List<String> waivedDeliveryMethods = Arrays.asList(waivedDeliveryCodes.split(","));
    List<DeliveryMethodJson> deliveryMethodJsons = commonDeliveryMethods.stream()
        .map(p -> new DeliveryMethodJson.Builder()
            .code(p.getDeliverymethodcode()).charge(p.getCharge())
            .order(p.getOrdermethod().intValue())
            .addressRequired(p.getAddressrequired())
            .feeWaived(waivedDeliveryMethods.contains(p.getDeliverymethodcode()))
            .build()).collect(Collectors.toList());
    DeliveryMethodsJson deliveryMethodsJson = new DeliveryMethodsJson(deliveryMethodJsons);
    logger.debug("Delivery methods : " + Duration.between(start, Instant.now()));
    return deliveryMethodsJson;
  }

  private CartLineItem buildCartLineItem(CartLineItemsView cli, List<Integer> seats, Long ticketQty,
                                         CurrencyUnit currencyUnit) {
    Product product =
        new Product.Builder()
            .productId(cli.getKey().getProductid())
            .productName(cli.getProductname())
            .productDate(cli.getStartdate())
            .level(StringUtils.isNotBlank(cli.getSeatlevelalias()) ? cli.getSeatlevelalias() : null)
            .section(cli.getSeatsectionalias())
            .row(StringUtils.isNotBlank(cli.getRowalias()) ? cli.getRowalias() : null)
            .seatNo(seats != null || !seats.isEmpty() ? seats : null)
            .productType(cli.getSeatsectiontype())
            .venue(cli.getVenuealias())
            .build();

    Priceclass priceclass = new Priceclass.Builder().priceClassName(cli.getPriceclassname())
        .priceClassCode(cli.getKey().getPriceclasscode()).build();

    Instant start = Instant.now();
    MonetaryAmount bookingFeePerTicket = Money.of(BigDecimal.ZERO, currencyUnit);

    String key = cli.getKey().getProductid() + "-" + cli.getKey().getPriceclasscode() + "-"
        + cli.getKey().getPricecatid();
    if (bookingFeeMap != null && bookingFeeMap.containsKey(key)) {
      bookingFeePerTicket = Money.of(bookingFeeMap.get(key), currencyUnit);
    } else if (!isExcludeBookingFee(cli.getKey().getProductid())) {
      try {
        bookingFeePerTicket =
            feeService
                .getBookingFee(cli.getKey().getProductid(), cli.getKey().getPricecatid(),
                    cli.getKey().getPriceclasscode(), Money.of(cli.getPricevalue(), currencyUnit));
      } catch (NullPointerException npe) {
        //in the case where no fee attribute is found e.g. NGS products have no booking fees outside charge
        logger.error(npe.getMessage(), npe);
      }
    }
    logger.debug("Booking fees : " + Duration.between(start, Instant.now()));

    MonetaryAmount totalBookingFees = bookingFeePerTicket.multiply(ticketQty);
    MonetaryAmount unitPrice = Money.of(cli.getPricevalue(), currencyUnit);
    MonetaryAmount subTotal = totalBookingFees.add(unitPrice.multiply(ticketQty));

    return
        new CartLineItem.Builder().cartItemId(cli.getCartItemId()).icc(cli.getIcc()).product(product)
            .priceclass(priceclass).bookingFee(bookingFeePerTicket).ticketQuantity(ticketQty)
            .unitPrice(unitPrice).subTotal(subTotal).build();
  }

  private Boolean isExcludeBookingFee(Long productId) {

    if (this.waivedBookingFeePrdtList == null || this.waivedBookingFeePrdtList.size() == 0) {
      return false;
    } else if (waivedBookingFeePrdtList.contains(productId)) {
      return true;
    }
    return false;
  }
}
