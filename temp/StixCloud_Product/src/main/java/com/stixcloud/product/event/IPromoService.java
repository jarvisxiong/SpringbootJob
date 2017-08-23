package com.stixcloud.product.event;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.tuple.Pair;

import com.stixcloud.dto.PromoCodeDTO;

public interface IPromoService {
  /**
   * Retrieve promo state by internet content code and promo code
   * 
   * @param contentCode
   * @param promoCode
   * @return
   */
  public int getPromoState(String contentCode, String promoCode);

  /**
   * Retrieve promo state by product id and promo code
   * 
   * @param productId
   * @param promoCode
   * @return
   */
  public int getPromoState(Long productId, String promoCode);

  /**
   * Get product availability by content code and restricted by promo code configuration, there are
   * two cases as below: <br />
   * 1. If promo code is given, it means promo code is valid, then return promo products with its
   * availability status with restriction by promo code configuration. </br>
   * 2. Else, return promo products with restriction by promo code configuration.
   * 
   * @param contentCode
   * @param promoCode optional
   * @return
   */
  public Map<Long, Boolean> getProductAvailability(String contentCode, String... promoCode);

  /**
   * Key is combination of price cat id and section id Value is section availability (true: section
   * is available, false: section is unavailable)
   * 
   * @param productId
   * @param promoCode
   * @return
   */

  public List<PromoCodeDTO> getPromoCodes(Long productId, String... promoCode);

  public Set<Long> getPromoPriceClassIds(Long productId, String... promoCode);

  public Set<Long> getHoldIds(Long productId, String promoCode);

  public Set<Long> getAvailableSeats(Long productId, Long priceCatId, Long sectionId,
      Set<Long> holdIds);
  
}
