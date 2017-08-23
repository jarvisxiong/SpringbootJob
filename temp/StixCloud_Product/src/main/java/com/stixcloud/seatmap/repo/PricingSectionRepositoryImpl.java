package com.stixcloud.seatmap.repo;

import com.stixcloud.seatmap.dto.PricingSection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

/**
 * Created by chongye on 06-Sep-16.
 */
@Repository
public class PricingSectionRepositoryImpl implements PricingSectionRepository {
  @Autowired
  EntityManager em;

  @Override
  @Transactional(readOnly = true)
  public PricingSection findOne(Long venueSectionPcId) {
    String query =
        "SELECT new com.stixcloud.seatmap.dto.PricingSection( "
            + "SECTPC.priceCatId, "
            + "SECTPC.pricecatnumber, "
            + "SECTLC.venuesectionlcid, "
            + "SECTPC.isfull,"
            + "SECTPC.venuesectionpcid, "
            + "LEVELLC.venuelevellcid, "
            + "SECTLC.areaalias, "
            + "LEVELLC.levelalias, "
            + "SECTLC.sectionalias, "
            + "SECTLC.sectiontype )"
            + "FROM VenueSectionPc SECTPC "
            + "JOIN VenueLevelLc LEVELLC ON SECTPC.levelId = LEVELLC.venuelevellcid "
            + "JOIN VenueSectionLc SECTLC ON SECTPC.parentSectionId = SECTLC.venuesectionlcid "
            + "where SECTPC.venuesectionpcid = :venueSectionPcId ";
    return em.createQuery(query, PricingSection.class)
        .setParameter("venueSectionPcId", venueSectionPcId)
        .getSingleResult();
  }
}
