package com.stixcloud.barcode.repo;

import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.stixcloud.barcode.domain.BarcodeFieldValuesDataView;

@Repository
public class BarcodeFieldValuesRepository implements IBarcodeFieldValuesRepository {

  @Autowired
  EntityManager em;

  @Override
  public List<BarcodeFieldValuesDataView> getBarcodeValues(Set<Long> seatInvIdList, Long priceClassId,
      Long productId) {
    StringBuffer query = new StringBuffer();
    query.append("SELECT DISTINCT b FROM BarcodeFieldValuesDataView b ")
        .append("WHERE b.productId = :productId ")
        .append("AND b.priceClassId = :priceClassId ")
        .append("AND b.seatInventoryId IN :seatInvIdList ");

    return em.createQuery(query.toString(), BarcodeFieldValuesDataView.class)
        .setParameter("productId", productId).setParameter("priceClassId", priceClassId)
        .setParameter("seatInvIdList", seatInvIdList).getResultList();
    
  }
}
