package com.stixcloud.product.event;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.stixcloud.common.config.multitenant.TenantContextHolder;
import com.stixcloud.domain.ProductAvailabilityView;

@Repository
public class ProductAvailabilityRepository implements IProductAvailabilityRepository {

  @Autowired
  EntityManager em;

  @Override
  public List<ProductAvailabilityView> getProductAvailabilityList(String contentCode) {
    StringBuilder query = new StringBuilder();
    query.append("SELECT p FROM ProductAvailabilityView p ")
        .append("WHERE code = :code AND profileInfoId = :profileInfoId");

    return em.createQuery(query.toString(), ProductAvailabilityView.class)
        .setParameter("code", contentCode)
        .setParameter("profileInfoId", TenantContextHolder.getTenant().getProfileInfoId())
        .getResultList();
  }
  
}
