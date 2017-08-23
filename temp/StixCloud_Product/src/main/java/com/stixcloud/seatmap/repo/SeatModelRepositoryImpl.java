package com.stixcloud.seatmap.repo;

import com.stixcloud.seatmap.dto.SeatModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import javax.persistence.EntityManager;

/**
 * Created by chongye on 15-Sep-16.
 */
@Repository
public class SeatModelRepositoryImpl implements SeatModelRepository {
  @Autowired
  EntityManager em;

  @Override
  @Transactional(readOnly = true)
  public List<SeatModel> getSectionsForBlock(Long productId, Long blockId) {
    String queryStr =
        "SELECT new com.stixcloud.seatmap.dto.SeatModel("
            + "SEAT.venueseatid, "
            + "COMP.venueoverviewcomponentslcid, "
            + "SSI.salesseatinventoryid, "
            + "SSI.spankey, "
            + "SEAT.seatalias, "
            + "SEAT.seatno, "
            + "SEAT.rank, "
            + "SSI.ticketable, "
            + "SEAT.seatmaplocation, "
            + "SEAT.seattype, "
            + "SEAT.seatattributes, "
            + "SEAT.nearestentrance, "
            + "SEAT.tickettype, "
            + "SSI.seatsalesstatus, "
            + "PCL.priceclasscode, "
            + "SECT.areaalias, "
            + "SSI.priceCatId) "
            + "FROM SalesSeatInventory SSI "
            + "JOIN VenueSeatLc SEAT ON SSI.venueSeatLcId = SEAT.venueseatid "
            + "JOIN VenueSectionLc SECT ON SSI.venueSectionLcId = SECT.venuesectionlcid "
            + "LEFT JOIN PriceClassLive PCL ON SSI.priceClassId = PCL.priceclassid "
            + "JOIN VenueOverviewComponentsLc COMP ON SEAT.blockId = COMP.venueoverviewcomponentslcid "
            + "WHERE SSI.productId = :productId "
            + "AND SEAT.ticketable = 1 "
            + "AND COMP.venueoverviewcomponentslcid = :blockId "
            + "ORDER BY SEAT.venueseatid DESC";

    return em.createQuery(queryStr, SeatModel.class)
        .setParameter("productId", productId)
        .setParameter("blockId", blockId)
        .getResultList();
  }
}
