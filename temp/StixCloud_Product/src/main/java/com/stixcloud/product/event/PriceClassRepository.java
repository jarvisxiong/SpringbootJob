package com.stixcloud.product.event;

import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.stixcloud.domain.RetrievePriceTypeView;

/**
 * Created by mhviet on 25-Aug-16.
 */
@Repository
public class PriceClassRepository implements IPriceClassRepository {

  @Autowired
  private EntityManager em;

  /**
   * Retrieve list RetrievePriceTypeView from a view in database
   * 
   * @return List RetrievePriceTypeView
   */
  @Override
  @Transactional(readOnly = true)
  public List<RetrievePriceTypeView> getPriceClassList(Long productId, Long priceCatId,
      Set<Long> priceClassIds, Long userInfoId, Long profileInfoId,
      String isPackageClass, boolean exclusive) {

    StringBuilder queryStr = new StringBuilder();
    queryStr.append(" SELECT DISTINCT priceTypeView.* ")
        .append(" FROM {h-schema}retrieve_price_type_view priceTypeView ")
        .append(" WHERE product_id = :productId ").append(" AND user_info_id = :userInfoId ")
        .append(" AND profile_info_id = :profileInfoId ")
        .append(" AND is_package_class = :isPackageClass ");

    if (priceCatId != null) {
      queryStr.append(" AND price_category_id= :priceCategoryId ");
    }
    
    if(CollectionUtils.isNotEmpty(priceClassIds)){// if promo -> get price classes belong to promo code only. else get price classes not being held by promo codes
      queryStr.append(" AND price_class_id ").append(exclusive ? " NOT " : "")
          .append(" IN :priceClassIds ");
    }
    
    queryStr.append(" ORDER BY ORDERING, PRICE_CLASS_CODE ");

    Query query = em.createNativeQuery(queryStr.toString(), RetrievePriceTypeView.class)
        .setParameter("productId", productId).setParameter("profileInfoId", profileInfoId)
        .setParameter("userInfoId", userInfoId).setParameter("isPackageClass", isPackageClass);
    if (priceCatId != null) {
      query.setParameter("priceCategoryId", priceCatId);
    }
    
    if(CollectionUtils.isNotEmpty(priceClassIds)){
      query.setParameter("priceClassIds", priceClassIds);
    }

    return query.getResultList();
  }

}
