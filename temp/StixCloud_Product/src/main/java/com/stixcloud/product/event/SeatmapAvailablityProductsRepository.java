/**
 *
 */
package com.stixcloud.product.event;

import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.apache.commons.collections.CollectionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.stixcloud.common.config.multitenant.TenantContextHolder;
import com.stixcloud.domain.SeatmapAvailablityProducts;

@Repository
@Transactional(readOnly = true)
public class SeatmapAvailablityProductsRepository implements ISeatmapAvailablityProductsRepository {
  private final Logger logger = LogManager.getLogger(SeatmapAvailablityProductsRepository.class);

  @Autowired
  EntityManager em;

  @Override
  public List<SeatmapAvailablityProducts> getSeatmapAvailability(Long productId, Set<Long> priceClassIds, Set<Long> holdCodeIds, boolean exclusive) {
boolean isHold = CollectionUtils.isNotEmpty(holdCodeIds);
    StringBuilder sb = new StringBuilder();
    sb.append("SELECT seatAvail.PRODUCTID, seatAvail.SEATSECTIONID, seatAvail.PRICE_CAT_ID, seatAvail.PROFILE_INFO_ID, ")
    .append(" MAX (");
    if(CollectionUtils.isEmpty(priceClassIds)) {
      sb.append(" seatAvail.seat_section_availability ");
    } else {
        sb.append(" CASE ")
        .append("    WHEN seatAvail.PRICE_CLASS_ID " + (exclusive ? "NOT" : "") + " IN :priceClassIds ");

        if(!exclusive && isHold) {
            sb.append("    AND seatSum.SYSTEM_SALE_CODE_ID IN :holdCodeIds ")
            .append("    THEN seatSum.HOLD_AVAILABILITY_STATUS ");
        } else {
            sb.append("    THEN seatAvail.seat_section_availability ");
        }
        sb.append("  ELSE 0 ")
                          .append("  END ");
    }

    sb.append(" ) AS seat_section_availability ").append("FROM SEATMAP_AVAILABLITY_PRODUCTS seatAvail ");

    if(!exclusive && isHold) {
        sb.append(" JOIN SALES_SEAT_INVENTORY_SUMMARY seatSum ")
        .append(" ON seatAvail.PRODUCTID       = seatSum.PRODUCTID ")
        .append(" AND seatAvail.PRICE_CAT_ID    = seatSum.PRICE_CAT_ID ")
        .append(" AND seatAvail.SEATSECTIONID   = seatSum.SEATSECTIONID ")
        .append(" AND seatSum.PRODUCTID         = :productId ");
    }

    sb.append(" WHERE seatAvail.productid        = :productId ")
    .append("  AND seatAvail.PROFILE_INFO_ID = " + TenantContextHolder.getTenant().getProfileInfoId())
    .append(" GROUP BY seatAvail.PRODUCTID, seatAvail.SEATSECTIONID, seatAvail.PRICE_CAT_ID, seatAvail.PROFILE_INFO_ID HAVING COUNT(1) > 0");

    Query query =
        em.createNativeQuery(sb.toString(), SeatmapAvailablityProducts.class)
            .setParameter("productId", productId);

    if(priceClassIds != null && priceClassIds.size() > 0){
      query.setParameter("priceClassIds", priceClassIds);
    }
    
    if(!exclusive && isHold) {
      query.setParameter("holdCodeIds", holdCodeIds);
    }
    
    return query.getResultList();
  }
}
