package com.stixcloud.seatmap.repo;

import com.stixcloud.domain.VenueOverviewComponentsPc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import javax.persistence.EntityManager;

/**
 * Created by chongye on 06-Sep-16.
 */
@Repository
public class VenueOverviewComponentsPcRepositoryImpl
    implements VenueOverviewComponentsPcRepositoryCustom {
  @Autowired EntityManager em;

  @Override
  @Transactional(readOnly = true)
  public List<VenueOverviewComponentsPc> findAll(Long productId) {
    String query =
        "SELECT COMP FROM VenueOverviewComponentsPc COMP WHERE exists("
            + "SELECT PC FROM PriceConfiguration PC WHERE productid = :productId "
            + "AND priceconfigurationid = COMP.pcId)";

    return em.createQuery(query, VenueOverviewComponentsPc.class)
        .setParameter("productId", productId).getResultList();
  }
}
