package com.stixcloud.cart;

import java.util.List;

import com.stixcloud.domain.CartItem;

public interface ITransactionPasswordPromoService {
  void cre(List<CartItem> cartItems);
}
