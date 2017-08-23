package com.stixcloud.product.event;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.stixcloud.domain.ProductConfig;

@Repository
//@Transactional(readOnly = true)
public class ProductConfigRepository implements IProductConfigRepository {
	private final Logger logger = LogManager.getLogger(ProductConfigRepository.class);
	
	@Autowired
	EntityManager em;

	@Override
	@Transactional(readOnly = true)
	public List<ProductConfig> getUpdatedShows(LocalDate updatedDate) {
		StringBuilder queryStringBuilder = new StringBuilder();
		queryStringBuilder.append("select pc.productname, pc.startdatetime, pc.enddatetime, ")
			.append("pc.productid, pc.festival, pc.festivalCode, ") 
			.append("pc.productGroupCode, pc.VENUENAME, pc.organization, pc.productcategory, ") 
			.append("pc.PRODUCTCODE, pc.VENUELAYOUTCONFIG_ID, pc.updateddate, pc.venueid ")
	        .append(" from Product_Config pc WHERE updateddate>= :updatedDate ");
		
		Query q=em.createNativeQuery(queryStringBuilder.toString(), ProductConfig.class);
		q.setParameter("updatedDate", updatedDate);
		return q.getResultList();
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<String> getEventTypes(Long productId, Long venueId, Long venueLayoutId) {
		StringBuilder queryStringBuilder = new StringBuilder();
		queryStringBuilder.append("Select distinct sectiontype as sectiontype ") 
			.append("From Venue vn, Venue_Layout_Config vlc, Venue_Section_Lc vslc, Product_Live pl ")
			.append("where vlc.VENUE_ID =vn.VENUEID and pl.VENUE_ID = vn.VENUEID ")
			.append("and vslc.LC_ID = vlc.VENUELAYOUTCONFIGID ")
			.append("and pl.PRODUCTID= :productId ")
			.append("and vn.VENUEID= :venueId ")
			.append("and vlc.VENUELAYOUTCONFIGID= :venueLayoutId ");
		Query q=em.createNativeQuery(queryStringBuilder.toString());
		q.setParameter("productId", productId);
		q.setParameter("venueId", venueId);
		q.setParameter("venueLayoutId", venueLayoutId);
		return q.getResultList();
//		return null;
	}
}
