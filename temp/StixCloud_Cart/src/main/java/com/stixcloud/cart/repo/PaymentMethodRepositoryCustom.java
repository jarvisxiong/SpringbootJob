package com.stixcloud.cart.repo;

import java.util.List;

import com.stixcloud.domain.PaymentMethod;

/**
 * @author dbthan created 04/05/2017
 */
public interface PaymentMethodRepositoryCustom {
  List<PaymentMethod> getCommonPaymentMethod(Long productId, Long profileInfoId, String promoCode);
}
