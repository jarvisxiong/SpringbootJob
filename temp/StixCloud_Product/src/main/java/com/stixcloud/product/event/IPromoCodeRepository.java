package com.stixcloud.product.event;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.tuple.Pair;

import com.stixcloud.dto.PromoCodeDTO;

public interface IPromoCodeRepository {

  /**
   * Retrieve promo info by product id and promo code
   * 
   * @param productId
   * @return
   */
  public List<PromoCodeDTO> getPromoCodes(Long productId, String promoCode);

  /**
   * Retrieve promo info by internet content code and promo code
   * 
   * @param contentCode
   * @return
   */
  public List<PromoCodeDTO> getPromoCodes(String contentCode, String promoCode);

  /**
   * Retrieve exclusive promo info by internet content code and promo code
   * 
   * @param contentCode
   * @return
   */
  public List<PromoCodeDTO> getExclusivePromoCodes(String contentCode);

  /**
   * Retrieve exclusive promo info by product id and promo code
   * 
   * @param productId
   * @return
   */
  public List<PromoCodeDTO> getExclusivePromoCodes(Long productId);

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

  public Map<Long, Boolean> getProductAvailability(Set<Long> productIds,
      Set<Long> holdCodeIds, boolean isPromo);

  /**
   * Get available seats
   * 
   * @param productIds
   * @param holdCodeIds
   * @return
   */
  public List<BigDecimal> getAvailableSeats(Long productId, Long priceCatId, Long sectionId,
      Set<Long> holdCodeIds, boolean isPromo);

  public Map<Pair<Long, Long>, Boolean> getSectionAvailability(Long productId,
      Set<Long> priceClassIds, Set<Long> holdCodeIds, boolean isPromo);
}
