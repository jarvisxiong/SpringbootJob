package com.stixcloud.product.event;

import com.stixcloud.common.config.multitenant.TenantContextHolder;
import com.stixcloud.domain.DetailSeatmapProducts;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.Query;

@Repository
@Transactional(readOnly = true)
public class DetailSeatmapRepository implements IDetailSeatmapRepository {

  @Autowired
  EntityManager em;

  @Override
  public List<DetailSeatmapProducts> getDetailSeatmap(long productId, long priceCatId,
      Set<Long> seatIds, boolean exclusive) {
    StringBuilder query = new StringBuilder();
    query.append("SELECT ds ");
    query.append("FROM DetailSeatmapProducts ds ");
    query.append("WHERE ds.productId = ?1 ");
    query.append("AND ds.priceCatId = ?2 ");
    query.append("AND ds.profileInfoId = ")
        .append(TenantContextHolder.getTenant().getProfileInfoId());
    
    if (CollectionUtils.isNotEmpty(seatIds)) {
      query.append(" AND ds.inventoryId IN ?3");
    }

    Query squery = em.createQuery(query.toString(), DetailSeatmapProducts.class)
        .setParameter(1, productId)
        .setParameter(2, priceCatId)
        .setHint("org.hibernate.fetchSize", 500);
    if(CollectionUtils.isNotEmpty(seatIds)){
      squery.setParameter(3, seatIds);
    }
    return squery.getResultList();
  }
  
}
