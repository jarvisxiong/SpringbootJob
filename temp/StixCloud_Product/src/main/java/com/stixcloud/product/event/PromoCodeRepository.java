package com.stixcloud.product.event;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.stixcloud.dto.PromoCodeDTO;
import com.stixcloud.product.constant.InventoryConstants;
import com.stixcloud.product.constant.PromoConstants;

@Repository
public class PromoCodeRepository implements IPromoCodeRepository {

  @Autowired
  private EntityManager em;

  @Override
  public List<PromoCodeDTO> getPromoCodes(Long productId, String promoCode) {
    StringBuilder query = new StringBuilder()
        .append("SELECT rownum, ops.product_id, ")
        .append("  opp.price_class_id, ")
        .append("  oph.hold_code_id, ")
        .append("  opp.exclusived, ")
        .append("  opi.promotion_code ")
        .append("FROM online_promotion_info opi ")
        .append("JOIN online_promotion_show ops ")
        .append("ON opi.onlinepromotioninfoid = ops.online_promotion_info_id ")
        .append("JOIN online_promotion_price_class opp ")
        .append("ON opi.onlinepromotioninfoid = opp.online_promotion_info_id ")
        .append("AND ops.product_id           = opp.product_id ")
        .append("LEFT JOIN online_promotion_hold_code oph ")
        .append("ON opi.onlinepromotioninfoid = oph.online_promotion_info_id ")
        .append("WHERE opi.promotion_code     = :promoCode ")
        .append("AND ops.product_id           = :productId ")
        .append("AND opi.STATUS               = 1 ");
    
    return em.createNativeQuery(query.toString(), PromoCodeDTO.class)
        .setParameter("promoCode", promoCode)
        .setParameter("productId", productId)
        .getResultList();
  }

  @Override
  public List<PromoCodeDTO> getPromoCodes(String contentCode, String promoCode) {
    StringBuilder query = new StringBuilder()
        .append("SELECT rownum, ops.product_id, ")
        .append("  opp.price_class_id, ")
        .append("  oph.hold_code_id, ")
        .append("  opp.exclusived, ")
        .append("  opi.promotion_code ")
        .append("FROM online_promotion_info opi ")
        .append("JOIN internet_content_live ic ")
        .append("ON opi.internet_content_id = ic.internetcontentid ")
        .append("JOIN online_promotion_show ops ")
        .append("ON opi.onlinepromotioninfoid = ops.online_promotion_info_id ")
        .append("JOIN online_promotion_price_class opp ")
        .append("ON opi.onlinepromotioninfoid = opp.online_promotion_info_id ")
        .append("AND ops.product_id           = opp.product_id ")
        .append("LEFT JOIN online_promotion_hold_code oph ")
        .append("ON opi.onlinepromotioninfoid = oph.online_promotion_info_id ")
        .append("WHERE opi.PROMOTION_CODE     = :promoCode ")
        .append("AND opi.STATUS               = 1 ")
        .append("AND ic.code                  = :contentCode ");
    
    return em.createNativeQuery(query.toString(), PromoCodeDTO.class)
        .setParameter("promoCode", promoCode)
        .setParameter("contentCode", contentCode)
        .getResultList();
  }

  @Override
  public List<PromoCodeDTO> getExclusivePromoCodes(String contentCode) {
    StringBuilder query = new StringBuilder()
        .append("SELECT rownum, ops.product_id, ")
        .append("  opp.price_class_id, ")
        .append("  oph.hold_code_id, ")
        .append("  opp.exclusived, ")
        .append("  opi.promotion_code ")
        .append("FROM online_promotion_info opi ")
        .append("JOIN internet_content_live ic ")
        .append("ON opi.internet_content_id = ic.internetcontentid ")
        .append("JOIN online_promotion_show ops ")
        .append("ON opi.onlinepromotioninfoid = ops.online_promotion_info_id ")
        .append("JOIN online_promotion_price_class opp ")
        .append("ON opi.onlinepromotioninfoid = opp.online_promotion_info_id ")
        .append("AND ops.product_id           = opp.product_id ")
        .append("LEFT JOIN online_promotion_hold_code oph ")
        .append("ON opi.onlinepromotioninfoid = oph.online_promotion_info_id ")
        .append("WHERE ic.CODE                = :contentCode ")
        .append("AND opi.STATUS               = 1 ")
        .append("AND opp.EXCLUSIVED           = 'T' ");
    
    return em.createNativeQuery(query.toString(), PromoCodeDTO.class)
        .setParameter("contentCode", contentCode)
        .getResultList();
  }

  @Override
  public List<PromoCodeDTO> getExclusivePromoCodes(Long productId) {
    StringBuilder query = new StringBuilder()
        .append("SELECT rownum, ops.product_id, ")
        .append("  opp.price_class_id, ")
        .append("  oph.hold_code_id, ")
        .append("  opp.exclusived, ")
        .append("  opi.promotion_code ")
        .append("FROM online_promotion_info opi ")
        .append("JOIN online_promotion_show ops ")
        .append("ON opi.onlinepromotioninfoid = ops.online_promotion_info_id ")
        .append("JOIN online_promotion_price_class opp ")
        .append("ON opi.onlinepromotioninfoid = opp.online_promotion_info_id ")
        .append("AND ops.product_id           = opp.product_id ")
        .append("LEFT JOIN online_promotion_hold_code oph ")
        .append("ON opi.onlinepromotioninfoid = oph.online_promotion_info_id ")
        .append("WHERE ops.product_id         = :productId ")
        .append("AND opi.STATUS               = 1 ")
        .append("AND opp.EXCLUSIVED           = 'T' ");
    
    return em.createNativeQuery(query.toString(), PromoCodeDTO.class)
        .setParameter("productId", productId)
        .getResultList();
  }

  @Override
  public int getPromoState(String contentCode, String promoCode) {
    StringBuilder query = new StringBuilder()
        .append("SELECT COALESCE( ")
        .append("  (SELECT MIN (PROMO_STATE) ")
        .append("  FROM ")
        .append("    (SELECT ( ")
        .append("      CASE ")
        .append("        WHEN opi.PROMOTION_CODE = :promoCode ")
        .append("        AND (opi.STARTDATE     IS NULL ")
        .append("        OR opi.STARTDATE       <= SYSDATE) ")
        .append("        AND (opi.ENDDATE       IS NULL ")
        .append("        OR opi.ENDDATE         >= SYSDATE) ")
        .append("        THEN ")
        .append(PromoConstants.PROMO_CODE_VALID)
        .append("        WHEN opi.PROMOTION_CODE IS NOT NULL ")
        .append("        AND (opi.STARTDATE      IS NULL ")
        .append("        OR opi.STARTDATE        <= SYSDATE) ")
        .append("        AND (opi.ENDDATE        IS NULL ")
        .append("        OR opi.ENDDATE          >= SYSDATE) ")
        .append("        THEN ")
        .append(PromoConstants.PROMO_CODE_INVALID)
        .append("        ELSE ")
        .append(PromoConstants.PROMO_CODE_UNAVAILABLE)
        .append("      END) AS PROMO_STATE ")
        .append("    FROM ONLINE_PROMOTION_INFO opi ")
        .append("    JOIN INTERNET_CONTENT_LIVE ic ")
        .append("    ON opi.INTERNET_CONTENT_ID = ic.INTERNETCONTENTID ")
        .append("    WHERE opi.STATUS             = 1 ")
        .append("     AND ic.CODE = :contentCode ")
        .append("    ) ")
        .append("  ), ")
        .append(PromoConstants.PROMO_CODE_UNCONFIGURED)
        .append(") AS PROMO_STATE ")
        .append("FROM dual ");

    return ((Number) em.createNativeQuery(query.toString())
        .setParameter("promoCode", promoCode)
        .setParameter("contentCode", contentCode)
        .getResultList().get(0)).intValue();
  }

  @Override
  public int getPromoState(Long productId, String promoCode) {
    StringBuilder query = new StringBuilder()
        .append("SELECT COALESCE( ")
        .append("  (SELECT MIN (PROMO_STATE) ")
        .append("  FROM ")
        .append("    (SELECT ( ")
        .append("      CASE ")
        .append("        WHEN opi.PROMOTION_CODE = :promoCode ")
        .append("        AND (opi.STARTDATE     IS NULL ")
        .append("        OR opi.STARTDATE       <= SYSDATE) ")
        .append("        AND (opi.ENDDATE       IS NULL ")
        .append("        OR opi.ENDDATE         >= SYSDATE) ")
        .append("        THEN ")
        .append(PromoConstants.PROMO_CODE_VALID)
        .append("        WHEN opi.PROMOTION_CODE IS NOT NULL ")
        .append("        AND (opi.STARTDATE      IS NULL ")
        .append("        OR opi.STARTDATE        <= SYSDATE) ")
        .append("        AND (opi.ENDDATE        IS NULL ")
        .append("        OR opi.ENDDATE          >= SYSDATE) ")
        .append("        THEN ")
        .append(PromoConstants.PROMO_CODE_INVALID)
        .append("        ELSE ")
        .append(PromoConstants.PROMO_CODE_UNAVAILABLE)
        .append("      END) AS PROMO_STATE ")
        .append("    FROM ONLINE_PROMOTION_INFO opi ")
        .append("    JOIN ONLINE_PROMOTION_SHOW ops ")
        .append("    ON opi.onlinepromotioninfoid = ops.ONLINE_PROMOTION_INFO_ID ")
        .append("    WHERE opi.STATUS             = 1 ")
        .append("    AND ops.PRODUCT_ID           = :productId ")
        .append("  )), ")
        .append(PromoConstants.PROMO_CODE_UNCONFIGURED)
        .append(") AS PROMO_STATE ")
        .append("FROM dual ");

    return ((Number) em.createNativeQuery(query.toString())
        .setParameter("promoCode", promoCode)
        .setParameter("productId", productId)
        .getSingleResult()).intValue();
  }

  @Override
  public Map<Long,Boolean> getProductAvailability(Set<Long> productIds,
      Set<Long> holdCodeIds, boolean isPromo) {
    Boolean isHold = isPromo && CollectionUtils.isNotEmpty(holdCodeIds);
    StringBuilder queryStr = new StringBuilder()
        .append("SELECT DISTINCT PRODUCT_ID, ")
        .append("  (SELECT COALESCE( ")
        .append("    (SELECT 1 ")
        .append("    FROM SALES_SEAT_INVENTORY s2 ")
        .append("    WHERE s1.PRODUCT_ID      = s2.PRODUCT_ID ")
        .append("    AND TICKETABLE           = ")
        .append(InventoryConstants.TICKETABLE_TRUE.getValue())
        .append("    AND SEATSALESSTATUS     = ")
        .append(isHold ? InventoryConstants.SALES_STATUS_HOLD.getValue()
            : InventoryConstants.SALES_STATUS_AVAILABLE.getValue())
        .append(isHold ? " AND SYSTEM_SALE_CODE_ID IN :holdCodeIds "
            : "      AND SYSTEM_SALE_CODE_ID IS NULL ")
        .append("    AND ROWNUM               = 1 ")
        .append("    ), 0) ")
        .append("  FROM DUAL ")
        .append("  ) AS IS_AVAIL ")
        .append("FROM SALES_SEAT_INVENTORY s1 ")
        .append("WHERE PRODUCT_ID        IN :productIds ")
        .append("AND TICKETABLE           = ")
        .append(InventoryConstants.TICKETABLE_TRUE.getValue())
        .append(" AND SEATSALESSTATUS     = ")
        .append(isHold ? InventoryConstants.SALES_STATUS_HOLD.getValue()
            : InventoryConstants.SALES_STATUS_AVAILABLE.getValue())
        .append(isHold ? " AND SYSTEM_SALE_CODE_ID IN :holdCodeIds "
            : " AND SYSTEM_SALE_CODE_ID IS NULL ");

    Query query = em.createNativeQuery(queryStr.toString())
    .setParameter("productIds", productIds);
    
    if(isHold) {
      query.setParameter("holdCodeIds", holdCodeIds);
    }
    
    List<Object[]> records = query.getResultList();
    if(CollectionUtils.isEmpty(records)) {
      return new HashMap<Long, Boolean>();
    }
    
    return records.stream().collect(Collectors.toMap(record -> ((BigDecimal) record[0]).longValue(),
        record -> ((BigDecimal) record[0]).intValue() == 0 ? Boolean.FALSE : Boolean.TRUE));
  }

  @Override
  public List<BigDecimal> getAvailableSeats(Long productId, Long priceCatId, Long sectionId,
      Set<Long> holdCodeIds, boolean isPromo) {
    Boolean isHold = isPromo && CollectionUtils.isNotEmpty(holdCodeIds);
    StringBuilder queryStr = new StringBuilder()
        .append("SELECT SALESSEATINVENTORYID ")
        .append("FROM SALES_SEAT_INVENTORY ")
        .append("WHERE PRODUCT_ID           = :productId ")
        .append("AND PRICE_CAT_ID           = :priceCatId ")
        .append("AND VENUE_SECTION_LC_ID    = :sectionId ")
        .append("AND TICKETABLE             = ")
        .append(InventoryConstants.TICKETABLE_TRUE.getValue())
        .append(" AND SEATSALESSTATUS       = ")
        .append(isHold ? InventoryConstants.SALES_STATUS_HOLD.getValue()
            : InventoryConstants.SALES_STATUS_AVAILABLE.getValue())
        .append(isHold ? "    AND SYSTEM_SALE_CODE_ID IN :holdCodeIds "
            : "    AND SYSTEM_SALE_CODE_ID IS NULL ");
    
    Query query = em.createNativeQuery(queryStr.toString())
        .setParameter("productId", productId)
        .setParameter("priceCatId", priceCatId)
        .setParameter("sectionId", sectionId);
    
    if(isHold) {
      query.setParameter("holdCodeIds", holdCodeIds);
    }
    
    return query.getResultList();
  }

  @Override
  public Map<Pair<Long, Long>, Boolean> getSectionAvailability(Long productId,
      Set<Long> priceClassIds, Set<Long> holdCodeIds, boolean isPromo) {
    Boolean isHold = isPromo && CollectionUtils.isNotEmpty(holdCodeIds);
    StringBuilder queryStr = new StringBuilder()
        .append("SELECT DISTINCT PRICE_CAT_ID, VENUE_SECTION_LC_ID, ")
        .append("  (SELECT COALESCE( ")
        .append("    (SELECT 1 ")
        .append("    FROM SALES_SEAT_INVENTORY s2 ")
        .append("    WHERE PRODUCT_ID           = :productId ")
        .append("    AND s2.PRICE_CAT_ID        = s1.PRICE_CAT_ID ")
        .append("    AND s2.VENUE_SECTION_LC_ID = s1.VENUE_SECTION_LC_ID ")
        .append("    AND TICKETABLE             = ")
        .append(InventoryConstants.TICKETABLE_TRUE.getValue())
        .append("    AND SEATSALESSTATUS        = ")
        .append(isHold ? InventoryConstants.SALES_STATUS_HOLD.getValue()
            : InventoryConstants.SALES_STATUS_AVAILABLE.getValue())
        .append(isHold ? "    AND SYSTEM_SALE_CODE_ID IN :holdCodeIds "
            : "      AND SYSTEM_SALE_CODE_ID IS NULL ")
        .append("    AND ROWNUM                 = 1 ")
        .append("    ), 0) ")
        .append("  FROM DUAL ")
        .append("  ) AS IS_AVAIL ")
        .append("FROM SALES_SEAT_INVENTORY s1 ")
        .append("JOIN PMT_COLUMN_LIVE pcol ")
        .append("ON s1.PRICE_CAT_ID = pcol.PRICECATEGORY_ID ")
        .append("JOIN PMT_CHART_LIVE pc ")
        .append("ON pcol.PMTCOLUMNID = pc.PMTCOLUMN_ID ")
        .append("JOIN PMT_ROW_LIVE pr ")
        .append("ON pc.PMTROW_ID          = pr.PMTROWID ")
        .append("WHERE PRODUCT_ID         = :productId ")
        .append("AND pc.ISAPPLICABLE      = 'T' ")
        .append("AND pr.PRICECLASS_ID    ")
        .append(isHold ? " IN :priceClassIds " : " NOT IN :priceClassIds ")
        .append("AND TICKETABLE           = ")
        .append(InventoryConstants.TICKETABLE_TRUE.getValue())
        .append(" AND SEATSALESSTATUS      = ")
        .append(isHold ? InventoryConstants.SALES_STATUS_HOLD.getValue()
            : InventoryConstants.SALES_STATUS_AVAILABLE.getValue())
        .append(isHold ? " AND SYSTEM_SALE_CODE_ID IN :holdCodeIds "
            : " AND SYSTEM_SALE_CODE_ID IS NULL ")
        .append("ORDER BY PRICE_CAT_ID ASC, ")
        .append("VENUE_SECTION_LC_ID ASC ");
    
    Query query = em.createNativeQuery(queryStr.toString())
    .setParameter("priceClassIds", priceClassIds)
    .setParameter("productId", productId);

    if (isHold) {
      query.setParameter("holdCodeIds", holdCodeIds);
    }

    List<Object[]> records = query.getResultList();
    if(CollectionUtils.isEmpty(records)) {
      return new HashMap<Pair<Long, Long>, Boolean>();
    }
    
    return records.stream()
        .collect(Collectors.toMap(
            record -> Pair.of(((BigDecimal) record[0]).longValue(),
                ((BigDecimal) record[1]).longValue()),
            record -> ((BigDecimal) record[2]).intValue() == 0 ? Boolean.FALSE : Boolean.TRUE));
  }
}
