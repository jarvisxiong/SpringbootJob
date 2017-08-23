package com.stixcloud.cart;

import com.stixcloud.domain.ShoppingCart;

public interface INotificationService {
  public void insertNotification(ShoppingCart cart) throws Exception;
}
