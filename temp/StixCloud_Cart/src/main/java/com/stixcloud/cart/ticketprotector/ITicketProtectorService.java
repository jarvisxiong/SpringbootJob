package com.stixcloud.cart.ticketprotector;

import com.stixcloud.cart.CartException;
import com.stixcloud.cart.api.CartLineItem;
import com.stixcloud.cart.rules.postcommit.PostCommitOrder;
import com.stixcloud.domain.ShoppingCart;

import java.util.List;
import javax.money.MonetaryAmount;

public interface ITicketProtectorService {

  List<CartLineItem> getCartLineItemList(String cartGuid) throws CartException;

  MonetaryAmount getTicketProtectorAmount(String cartGuid) throws CartException;

  String processTicketProtector(PostCommitOrder postCommitOrder, ShoppingCart shoppingCart)
      throws CartException;
}
