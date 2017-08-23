package com.stixcloud.cart.repo;

import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.stixcloud.cart.PromoConstants;
import com.stixcloud.dto.PromoCodeDTO;

/**
 * @author dbthan created 04/05/2017
 */
@Repository
public class PromoCodeRepository implements IPromoCodeRepository {

  @Autowired
  private EntityManager em;
  
  @Override
  public List<PromoCodeDTO> getConfiguredPromos(Set<Long> productIds, Set<Long> priceClassIds, Set<String> promoCodes, List<Long> holdCodes){
    StringBuilder query = new StringBuilder()
        .append("SELECT rownum, ops.product_id, ")
        .append("  opp.price_class_id, ")
        .append("  oph.hold_code_id, ")
        .append("  opp.exclusived, ")
        .append("  opi.promotion_code")
        .append(" FROM online_promotion_info opi ")
        .append(" JOIN online_promotion_show ops ")
        .append(" ON opi.onlinepromotioninfoid = ops.online_promotion_info_id ")
        .append(" JOIN online_promotion_price_class opp ")
        .append(" ON opi.onlinepromotioninfoid = opp.online_promotion_info_id ")
        .append(" AND ops.product_id           = opp.product_id ")
        .append(" LEFT JOIN online_promotion_hold_code oph ")
        .append(" ON opi.onlinepromotioninfoid = oph.online_promotion_info_id ")
        .append(" WHERE opi.STATUS               = ")
        .append(PromoConstants.PROMO_CODE_STATE_ACTIVE);
    
    if(productIds != null && !productIds.isEmpty()){
      query.append(" AND ops.product_id           IN :productIds ");
    }
    if(priceClassIds != null && !priceClassIds.isEmpty()){
      query.append(" AND opp.price_class_id         IN :priceClassIds ");
    }
    if(promoCodes != null && !promoCodes.isEmpty()){
      query.append(" AND opi.promotion_code     IN :promoCodes ");
    }
    if(holdCodes != null && !holdCodes.isEmpty()){
      query.append(" AND oph.hold_code_id     IN :holdCodes ");
    }
        
    Query squery = em.createNativeQuery(query.toString(), PromoCodeDTO.class);
    if(productIds != null && !productIds.isEmpty()){
      squery.setParameter("productIds", productIds);
    }
    if(priceClassIds != null && !priceClassIds.isEmpty()){
      squery.setParameter("priceClassIds", priceClassIds);
    }
    if(promoCodes != null && !promoCodes.isEmpty()){
      squery.setParameter("promoCodes", promoCodes);
    }
    if(holdCodes != null && !holdCodes.isEmpty()){
      squery.setParameter("holdCodes", holdCodes);
    }

    return squery.getResultList();
  }

  @Override
  public List<PromoCodeDTO> getConfiguredPromos(Long productId, Long priceClassId,
      String promoCode) {

    StringBuilder query = new StringBuilder()
        .append("SELECT rownum, ops.product_id, ")
        .append("  opp.price_class_id, ")
        .append("  oph.hold_code_id, ")
        .append("  opp.exclusived, ")
        .append("  opi.promotion_code")
        .append(" FROM online_promotion_info opi ")
        .append(" JOIN online_promotion_show ops ")
        .append(" ON opi.onlinepromotioninfoid = ops.online_promotion_info_id ")
        .append(" JOIN online_promotion_price_class opp ")
        .append(" ON opi.onlinepromotioninfoid = opp.online_promotion_info_id ")
        .append(" AND ops.product_id           = opp.product_id ")
        .append(" LEFT JOIN online_promotion_hold_code oph ")
        .append(" ON opi.onlinepromotioninfoid = oph.online_promotion_info_id ")
        .append(" WHERE opi.STATUS               = ")
        .append(PromoConstants.PROMO_CODE_STATE_ACTIVE);
    
    if(productId != null){
      query.append(" AND ops.product_id           = :productIds ");
    }
    if(priceClassId != null){
      query.append(" AND opp.price_class_id         = :priceClassIds ");
    }
    if(promoCode != null){
      query.append(" AND opi.promotion_code     = :promoCodes ");
    }
    
        
    Query squery = em.createNativeQuery(query.toString(), PromoCodeDTO.class);
    if(productId != null){
      squery.setParameter("productIds", productId);
    }
    if(priceClassId != null){
      squery.setParameter("priceClassIds", priceClassId);
    }
    if(promoCode != null){
      squery.setParameter("promoCodes", promoCode);
    }
    return squery.getResultList();
  
  }
}
