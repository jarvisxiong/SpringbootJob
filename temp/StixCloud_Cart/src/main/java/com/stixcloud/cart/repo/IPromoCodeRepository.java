package com.stixcloud.cart.repo;

import java.util.List;
import java.util.Set;

import com.stixcloud.dto.PromoCodeDTO;

/**
 * @author dbthan created 04/05/2017
 */
public interface IPromoCodeRepository {
  
  List<PromoCodeDTO> getConfiguredPromos(Set<Long> productIds, Set<Long> priceClassIds, Set<String> promoCodes, List<Long> holdCodes);
  List<PromoCodeDTO> getConfiguredPromos(Long productId, Long priceClassId, String promoCode);
}
