package com.stixcloud.barcode.repo;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import com.stixcloud.barcode.domain.BarcodeFieldView;

@Repository
public class BarcodeGenerationRepository implements IBarcodeGenerationRepository {

  @Autowired
  EntityManager em;

  @Override
  @Cacheable(value = "getBarcodeFieldList",
  key = "{#root.methodName, #productId, #priceClassId, #priceCategoryId}")
  public List<BarcodeFieldView> getBarcodeFieldList(Long productId, Long priceClassId,
      Long priceCategoryId) {
    List<BarcodeFieldView> barcodeFieldList = null;
    StringBuffer queryStr = new StringBuffer();
    queryStr.append("SELECT b from BarcodeFieldView b ").append("WHERE b.productId = :productId ")
        .append("AND b.priceClassId = :priceClassId ")
        .append("AND b.priceCategoryId = :priceCategoryId ").append("ORDER BY b.ordering ");
    barcodeFieldList = em.createQuery(queryStr.toString(), BarcodeFieldView.class)
        .setParameter("productId", productId).setParameter("priceClassId", priceClassId)
        .setParameter("priceCategoryId", priceCategoryId).getResultList();
    return barcodeFieldList;
  }

}
