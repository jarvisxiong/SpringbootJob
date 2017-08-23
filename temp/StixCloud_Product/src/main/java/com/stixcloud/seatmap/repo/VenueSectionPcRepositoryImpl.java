package com.stixcloud.seatmap.repo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import javax.persistence.EntityManager;

/**
 * Created by chongye on 06-Sep-16.
 */
@Repository
public class VenueSectionPcRepositoryImpl implements VenueSectionPcRepositoryCustom {
  @Autowired
  EntityManager em;

  @Override
  @Transactional(readOnly = true)
  public Long findPcSectionId(Long productId, List<Long> seatInvIds) {
    String query =
        "SELECT DISTINCT SECTIONPC.venuesectionpcid " +
            "FROM VenueSectionPc SECTIONPC WHERE EXISTS ( " +
            "SELECT SECTIONLC FROM VenueSectionLc SECTIONLC " +
            "JOIN SalesSeatInventory SSI ON SECTIONLC.venuesectionlcid = SSI.venueSectionLcId " +
            "WHERE SECTIONPC.parentSectionId = SECTIONLC.venuesectionlcid " +
            "AND SECTIONPC.priceCatId = SSI.priceCatId " +
            "AND SSI.productId = :productId " +
            "AND SSI.salesseatinventoryid IN :seatInvIds )";

    return em.createQuery(query, Long.class)
        .setParameter("productId", productId)
        .setParameter("seatInvIds", seatInvIds)
        .getSingleResult();
  }
}
