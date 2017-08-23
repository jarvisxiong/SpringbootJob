/**
 *
 */
package com.stixcloud.product.event;

import com.stixcloud.common.config.multitenant.TenantContextHolder;
import com.stixcloud.domain.PriceTableInfo;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.Query;

@Repository
@Transactional(readOnly = true)
public class PriceTableInfoRepository implements IPriceTableInfoRepository {
	private final Logger logger = LogManager.getLogger(PriceTableInfoRepository.class);

	@Autowired
	EntityManager em;

	@SuppressWarnings("unchecked")
	@Override
  public List<PriceTableInfo> getPriceInfoTable(Long productId, String isPackageClass,
      Set<Long> priceClassIds, Set<Long> priceCatIds, boolean exclusive) {

		StringBuilder queryStringBuilder = new StringBuilder();
		queryStringBuilder.append(" SELECT DISTINCT pti.* ").append(" FROM {h-schema}price_table_info pti ")
				.append(" WHERE productid = :productId ").append("     AND pti.profile_info_id =  ")
				.append(TenantContextHolder.getTenant().getProfileInfoId())
				.append(" AND pti.ispackageclass= :ispackageclass ");
		// PROMO - if promo flow -> get list in configured promo only, otherwise exclude what is configured for all promos
		if(CollectionUtils.isNotEmpty(priceClassIds)){
		  queryStringBuilder.append(" AND price_class_id ").append(exclusive ? " NOT " : "").append(" IN :priceClassIds ");
		}
		
		if(CollectionUtils.isNotEmpty(priceCatIds)){
          queryStringBuilder.append(" AND price_category_id IN :priceCatIds ");
        }
		
		queryStringBuilder.append(" ORDER BY ordering, priceclasscode, pricecategorynumber ");

		Query query = em.createNativeQuery(queryStringBuilder.toString(), PriceTableInfo.class)
				.setParameter("productId", productId)
				.setParameter("ispackageclass", isPackageClass)
				.setHint("org.hibernate.fetchSize", 500);
		
		if(CollectionUtils.isNotEmpty(priceClassIds)){
		  query.setParameter("priceClassIds", priceClassIds);
		}
		
		if(CollectionUtils.isNotEmpty(priceCatIds)){
		  query.setParameter("priceCatIds", priceCatIds);
        }
		
		return query.getResultList();
	}

	@Override
	public List<PriceTableInfo> getPriceInfoTableProducts(List<Long> products, // no longer in use. superceded by seatmapoverview
			String promoCode, String isPackageClass) {

		StringBuilder queryStringBuilder = new StringBuilder();
		queryStringBuilder.append(" SELECT DISTINCT pti.* ").append(" FROM {h-schema}price_table_info pti ")
				.append(" WHERE productid in :productId ").append("     AND pti.profile_info_id =  ")
				.append(TenantContextHolder.getTenant().getProfileInfoId())
				.append("     AND pti.pricevalue is not null ").append("     AND pti.ispackageclass= :ispackageclass ");

		queryStringBuilder.append(" ORDER BY ordering, priceclasscode, pricecategorynumber ");

		Query query = em.createNativeQuery(queryStringBuilder.toString(), PriceTableInfo.class)
				.setParameter("productId", products).setParameter("ispackageclass", isPackageClass);

		return query.getResultList();
	}
}
