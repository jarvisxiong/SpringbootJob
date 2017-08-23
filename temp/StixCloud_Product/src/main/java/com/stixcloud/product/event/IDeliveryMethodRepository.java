package com.stixcloud.product.event;

import java.util.List;

import com.stixcloud.domain.DeliveryMethod;

public interface IDeliveryMethodRepository {
  List<DeliveryMethod> getCommonDeliveryMethod(Long productId, Boolean isAllowForGA,
      Long profileInfoId, String promoCode);
}
