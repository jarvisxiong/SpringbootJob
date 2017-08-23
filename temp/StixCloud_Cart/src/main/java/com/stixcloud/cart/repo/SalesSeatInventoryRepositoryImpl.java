package com.stixcloud.cart.repo;

import com.stixcloud.domain.SalesSeatInventory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 * Created by chongye on 6/10/2016.
 */
public class SalesSeatInventoryRepositoryImpl implements SalesSeatInventoryRepositoryCustom {
  @Autowired
  EntityManager em;

  @Override
  public List<SalesSeatInventory> findAvailableSeatsForGA(Long productId, Long priceCatId,
                                                          Long seatSectionId, int quantity) {
    StringBuffer hqlString = new StringBuffer();
    hqlString.append("select inv from SalesSeatInventory inv ")
        .append("where inv.productId = :productId ")
        .append("and inv.priceCatId = :priceCatId ")
        .append("and inv.seatsalesstatus IN (0) ")
        .append("and inv.ticketable = true ");
    if (seatSectionId != -1) { //Donation  Product no need to check venueSectionLcId
      hqlString.append("and inv.venueSectionLcId = :seatSectionId ");
    }
    hqlString.append("order by inv.salesseatinventoryid");

    Query query = em.createQuery(hqlString.toString(), SalesSeatInventory.class);
    query.setParameter("productId", productId);
    query.setParameter("priceCatId", priceCatId);
    if (seatSectionId != -1) { //seatSectionId  -1 will be passed for Addon GA Product
      query.setParameter("seatSectionId", seatSectionId);
    }
    query.setMaxResults(quantity);
    return query.getResultList();
  }
}
