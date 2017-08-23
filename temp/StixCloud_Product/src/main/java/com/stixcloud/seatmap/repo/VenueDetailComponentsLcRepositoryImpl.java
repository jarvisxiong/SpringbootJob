package com.stixcloud.seatmap.repo;

import com.stixcloud.domain.VenueDetailComponentsLc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import javax.persistence.EntityManager;

/**
 * Created by chongye on 09-Sep-16.
 */
@Repository
public class VenueDetailComponentsLcRepositoryImpl
    implements VenueDetailComponentsLcRepositoryCustom {
  @Autowired
  EntityManager em;

  @Override
  @Transactional(readOnly = true)
  public List<VenueDetailComponentsLc> retrieveDetailViewComponents(Long productId, Long blockId) {
    String query =
        "SELECT DETAIL "
            + "FROM VenueDetailComponentsLc DETAIL "
            + "JOIN PriceConfiguration PC ON DETAIL.lcId = PC.layoutconfigid "
            + "WHERE PC.productid = :productId "
            + "AND DETAIL.blockId = :blockId";

    return em.createQuery(query, VenueDetailComponentsLc.class)
        .setParameter("productId", productId)
        .setParameter("blockId", blockId)
        .getResultList();
  }
}
