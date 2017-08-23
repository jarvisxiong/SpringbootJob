package com.stixcloud.product.event;

import java.util.List;

import com.stixcloud.domain.PaymentMethod;

public interface IPaymentMethodRepository {
	List<PaymentMethod> getCommonPaymentMethod(Long productId, Long profileInfoId, String promoCode);
}
