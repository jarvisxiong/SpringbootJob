package com.stixcloud.seatmap.repo;

import com.stixcloud.domain.VenueOverviewComponentsLc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import javax.persistence.EntityManager;

/**
 * Created by chongye on 06-Sep-16.
 */
@Repository
public class VenueOverviewComponentsLcRepositoryImpl
    implements VenueOverviewComponentsLcRepositoryCustom {
  @Autowired EntityManager em;

  @Override
  @Transactional(readOnly = true)
  public List<VenueOverviewComponentsLc> findAll(Long productId) {
    String query =
        "SELECT COMP FROM VenueOverviewComponentsLc COMP WHERE exists("
            + "SELECT PC FROM PriceConfiguration PC WHERE productid = :productId "
            + "AND layoutconfigid = COMP.lcId)";

    return em.createQuery(query, VenueOverviewComponentsLc.class)
        .setParameter("productId", productId).getResultList();
  }

  @Override
  @Transactional(readOnly = true)
  public Long findSelectedDistinctBlocks(Long productId, List<Long> seatInvIds) {
    String query =
        "SELECT DISTINCT COMP.venueoverviewcomponentslcid "
            + "FROM VenueOverviewComponentsLc COMP "
            + "WHERE exists(SELECT SSI "
            + "FROM SalesSeatInventory SSI "
            + "JOIN VenueSeatLc SEAT ON SSI.venueSeatLcId = SEAT.venueseatid "
            + "JOIN VenueSectionLc SECTION ON SSI.venueSectionLcId = SECTION.venuesectionlcid "
            + "WHERE COMP.venueoverviewcomponentslcid = SEAT.blockId "
            + "AND SECTION.sectiontype = 'RS' "
            + "AND SSI.productId = :productId "
            + "AND SSI.salesseatinventoryid in :seatInvIds)";
    return em.createQuery(query, Long.class)
        .setParameter("productId", productId).setParameter("seatInvIds", seatInvIds)
        .getSingleResult();
  }
}
