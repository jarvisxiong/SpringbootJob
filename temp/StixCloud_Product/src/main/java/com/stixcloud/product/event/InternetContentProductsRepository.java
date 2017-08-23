/**
 *
 */
package com.stixcloud.product.event;

import com.stixcloud.common.config.multitenant.TenantContextHolder;
import com.stixcloud.domain.InternetContentProducts;

import org.apache.commons.collections.CollectionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

@Repository
@Transactional(readOnly = true)
public class InternetContentProductsRepository implements IInternetContentProductsRepository {
  private final Logger logger = LogManager.getLogger(IInternetContentProductsRepository.class);

  @Autowired
  EntityManager em;

  @Override
  public List<InternetContentProducts> getProductsByInternetContentCode(
      String internetContentCode, LocalDate startDate, LocalDate endDate, Collection<Long> productIds) {

    StringBuffer queryBuf = new StringBuffer();
    queryBuf.append("SELECT icp.* ")
    .append(" FROM INTERNET_CONTENT_PRODUCTS icp ")
    .append(" WHERE icp.code = :internetContentCode ");
    
    if (endDate != null) {
      queryBuf.append(" AND (icp.startdate between :startDate and :endDate)");
    } else {
      queryBuf.append(" AND (icp.startdate between icp.startdate and icp.startdate + 31)");
    }
    
    // PROMO: only get products
    if(CollectionUtils.isNotEmpty(productIds)){
      queryBuf.append(" AND icp.productid In :productids");
    }
    
    queryBuf.append(" AND icp.profile_info_id = ")
        .append(TenantContextHolder.getTenant().getProfileInfoId())
        .append(" ORDER BY icp.startdate");

    //for now we will use the system default timezone, but this will change as multitenant comes into play
    //will have to retrieve timezone based off tenant id
    Query query = em.createNativeQuery(queryBuf.toString(), InternetContentProducts.class);
    query.setParameter("internetContentCode", internetContentCode);
    if (endDate != null) {
      OffsetDateTime now = OffsetDateTime.now();
      ZoneOffset zoneOffset = now.getOffset();
      query.setParameter("startDate", OffsetDateTime.of(startDate.atStartOfDay(), zoneOffset))
          .setParameter("endDate", OffsetDateTime.of(endDate.atStartOfDay(), zoneOffset));
    }
    
    if(CollectionUtils.isNotEmpty(productIds)){
      query.setParameter("productids", productIds);
    }

    return query.getResultList();
  }

  @Override
  public List<InternetContentProducts> getProductsByInternetContentCode(String code,
      LocalDate startDate, LocalDate endDate) {
    return getProductsByInternetContentCode(code, startDate, endDate, null);
  }
}
