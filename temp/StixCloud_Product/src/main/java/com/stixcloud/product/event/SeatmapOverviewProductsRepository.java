/**
 *
 */
package com.stixcloud.product.event;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.stixcloud.common.config.multitenant.TenantContextHolder;
import com.stixcloud.domain.SeatmapOverviewProducts;

@Repository
@Transactional(readOnly = true)
public class SeatmapOverviewProductsRepository implements ISeatmapOverviewProductsRepository {
  private final Logger logger = LogManager.getLogger(SeatmapOverviewProductsRepository.class);

  @Autowired
  EntityManager em;

  @SuppressWarnings("unchecked")
  @Override
  public List<SeatmapOverviewProducts> getSeatmapOverview(Long productId) {

    StringBuilder queryStringBuilder = new StringBuilder();
    queryStringBuilder.append(" SELECT sop.* ")
        .append(" FROM {h-schema}seatmap_overview_products sop ")
        .append(" WHERE productid = :productId ")
        .append("     AND sop.profile_info_id =  ")
        .append(TenantContextHolder.getTenant().getProfileInfoId());

    queryStringBuilder.append(" ORDER BY sop.pricecatnum, sop.priceclasscode ASC, ")
        .append(" sop.ranking ASC, sop.isstandard DESC, sop.pricevalue ASC ");

    return em.createNativeQuery(queryStringBuilder.toString(), SeatmapOverviewProducts.class)
        .setParameter("productId", productId).getResultList();
  }
  
  @Override
  public List<SeatmapOverviewProducts> getSeatmapOverview(List<Long> productIds) {

    StringBuilder queryStringBuilder = new StringBuilder();
    queryStringBuilder.append(" SELECT sop.* ")
        .append(" FROM {h-schema}seatmap_overview_products sop ")
        .append(" WHERE productid in :productIds ")
        .append("     AND sop.profile_info_id =  ")
        .append(TenantContextHolder.getTenant().getProfileInfoId());

    queryStringBuilder.append(" ORDER BY sop.pricecatnum, sop.priceclasscode ASC, ")
        .append(" sop.ranking ASC, sop.isstandard DESC, sop.pricevalue ASC ");

    Query query = em.createNativeQuery(queryStringBuilder.toString(), SeatmapOverviewProducts.class)
        .setParameter("productIds", productIds);

    return query.getResultList();
  }
}
