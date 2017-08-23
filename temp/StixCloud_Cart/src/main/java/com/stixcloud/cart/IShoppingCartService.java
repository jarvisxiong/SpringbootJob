package com.stixcloud.cart;

import com.stixcloud.cart.api.AddToCartRequest;
import com.stixcloud.cart.api.JsonResponse;
import com.stixcloud.cart.api.PostCommitRequest;
import com.stixcloud.cart.api.PreCommitRequest;
import com.stixcloud.cart.api.ShoppingCartJson;
import com.stixcloud.cart.rules.postcommit.PostCommitOrder;
import com.stixcloud.domain.CartLineItemsView;
import com.stixcloud.domain.PaymentMethod;
import com.stixcloud.domain.ShoppingCart;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import javax.money.CurrencyUnit;

/**
 * Created by chongye on 27-Sep-16.
 */
public interface IShoppingCartService {
  ShoppingCart addToCart(AddToCartRequest addToCart, String promoCode) throws AddToCartException;

  ShoppingCart getCart(String cartGuid) throws CartException;

  ShoppingCartJson getShoppingCartJson(String cartGuid, CurrencyUnit currencyUnit,
                                       String waivedDeliveryCodes, Long profileInfoId,
                                       Set<Long> waivedBookingFeePrdtList)
      throws CartException;

  ShoppingCart removeFromCart(String cartGuid, String cartItemId, String priceClassCode)
      throws CartException;

  void removeCart(String cartGuid) throws CartException;

  List<PaymentMethod> getCommonPaymentMethods(String cartGuid, Long profileInfoId)
      throws CartException;

  LinkedList<List<CartLineItemsView>> getCartLineItems(String cartGuid, CurrencyUnit currencyUnit,
                                                       Long profileInfoId) throws CartException;
  LinkedList<List<CartLineItemsView>> getCartLineItems(String cartGuid, CurrencyUnit currencyUnit,
          Long profileInfoId, String isPackageClass) throws CartException;

  List<Long> getProductIdsByCartGuid(String cartGuid);

  JsonResponse precommit(String cartGuid, PreCommitRequest preCommitRequest)
      throws CartException;

  PostCommitOrder postcommit(String cartGuid, PostCommitRequest postCommitRequest)
      throws CartException;

  ShoppingCart updateCartExpirationTTL(String cartGuid, Long ttl) throws CartException;

  Boolean isValidSCBWaiverCreditCard(String ccNum) throws CartException;

  Set<Long> getValidSCBWaiverShows(ShoppingCart shoppingCart) throws CartException;

  JsonResponse promoValidation(ShoppingCart shoppingCart) throws CartException;

  ShoppingCartJson getShoppingCartJsonPostCommit(String cartGuid, CurrencyUnit currencyUnit,
                                                 String waivedDeliveryCodes,
                                                 Long profileInfoId,
                                                 Set<Long> waivedBookingFeePrdtList)
      throws CartException;

  void removeQueueMessage(String messageUuid) throws CartException;
}
