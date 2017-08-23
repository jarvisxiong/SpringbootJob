package com.stixcloud.product.event;

import com.stixcloud.domain.AddonMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import javax.persistence.EntityManager;

/**
 * Created by chetan on 05-Dec-16.
 */
@Repository
public class AddOnProductRepositoryImpl implements AddOnProductRepository {
  @Autowired
  EntityManager em;

  @Override
  @Transactional(readOnly = true)
  public List<AddonMapping> findAddOnProductsByEventProductIds(
      List<Long> eventProductIds) {
    // TODO Auto-generated method stub
    String query =
        "select DISTINCT am " +
            "from AddonMapping am " +
            "JOIN AddonMappingProducts amp " +
            "ON amp.addonMappingID=am.addOnMappingID " +
            "WHERE amp.productID in :productIds " +
            "and am.is_applicable=1";
    return em.createQuery(query, AddonMapping.class)
        .setParameter("productIds", eventProductIds)
        .getResultList();
  }
  
  @Override
  @Transactional(readOnly = true)
  public List<AddonMapping> findAddOnProductsByOrganizationId(
      List<Long> orgIds) {
    // TODO Auto-generated method stub
	  
    String query =
        "select distinct am.* from ADDON_MAPPING am,product_live pl,product_group_live pgl,PRDT_GROUP_PROMOTER  pgm "+
         "where  pl.product_group_id=pgl.productgroupid "+
         "and pgm.prdt_grp_id=pgl.productgroupid "+
         "and am.addon_product_id=pl.productid "+
         "and pgm.promoter_id in (?1) "+
         "and am.is_applicable = 1";
    return em.createNativeQuery(query, AddonMapping.class)
    		.setParameter(1, orgIds)
            .getResultList();
  }
  
  @Override
  @Transactional(readOnly = true)
  public List<Long> findOrganizationIdForProducts(
      List<Long> productIds) {
    // TODO Auto-generated method stub
	  
    String query =
    		"select pgm.promoter_id from product_live pl,product_group_live pgl,PRDT_GROUP_PROMOTER  pgm "+
    		"where  pl.product_group_id=pgl.productgroupid "+
    		"and pgm.prdt_grp_id=pgl.productgroupid "+
    		"and pl.productid in (?1) ";
    return em.createNativeQuery(query)
    		.setParameter(1, productIds)
            .getResultList();
  }

}
