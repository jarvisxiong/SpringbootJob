package com.stixcloud.cart.repo;

import com.stixcloud.domain.DeliveryMethod;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

/**
 * Created by chongye on 19/10/2016.
 */
public interface DeliveryMethodRepositoryCustom {
  @Cacheable("DeliveryMethodsForProduct")
  List<DeliveryMethod> getDeliveryMethodsForProduct(Long productId, Boolean isAllowForGA,
                                                    Long profileInfoId);
                                                    
  @Cacheable("CommonDeliveryMethodsForProduct")
  List<DeliveryMethod> getDeliveryMethodsForProduct(Long productId, Boolean isAllowForGA, Long profileInfoId,
		  List<String> priceClassCodeList);                                                    
  
  List<DeliveryMethod> getCommonDeliveryMethod(Long productId, Boolean isAllowForGA,
      Long profileInfoId, String priceClass, String promoCode);
}
