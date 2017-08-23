package com.stixcloud.seatmap.repo;

import com.stixcloud.seatmap.dto.PriceCategoryDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import javax.persistence.EntityManager;

/**
 * Created by chongye on 06-Sep-16.
 */
@Repository
public class PriceCategoryDetailRepositoryImpl implements PriceCategoryDetailRepository {
  @Autowired
  EntityManager em;

  @Override
  @Transactional(readOnly = true)
  public List<PriceCategoryDetail> findPriceCategoryDetailByProductId(Long productId) {
    String query =
        "select new com.stixcloud.seatmap.dto.PriceCategoryDetail(" +
            "CAT.pricecategorynumber, CAT.pricecategoryname, COLOR.color, CAT.pricecategoryid) " +
            "from PmtColumnLive COL " +
            "JOIN PriceCategoryLive CAT " +
            "ON COL.pricecategoryId = CAT.pricecategoryid " +
            "JOIN PriceCategoryColor COLOR " +
            "ON CAT.pricecategorynumber = COLOR.pricecategorynumber " +
            "WHERE exists(SELECT PMTL " +
            " FROM PriceModelTemplateLive PMTL " +
            " JOIN PriceModelTplPrdtLive PMTPL " +
            "ON PMTL.pricemodeltemplateid = PMTPL.priceModelTemplateId " +
            " WHERE PMTL.pricemodeltemplateid = COL.pricemodeltemplateId " +
            " AND PMTPL.productId = :productId )";
    return em.createQuery(query, PriceCategoryDetail.class)
        .setParameter("productId", productId)
        .getResultList();
  }
}
