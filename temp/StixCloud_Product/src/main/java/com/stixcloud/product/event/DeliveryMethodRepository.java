package com.stixcloud.product.event;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.stixcloud.domain.DeliveryMethod;

@Repository
public class DeliveryMethodRepository implements IDeliveryMethodRepository {
	
	@Autowired
	EntityManager em;

	@SuppressWarnings("unchecked")
	@Override
	public List<DeliveryMethod> getCommonDeliveryMethod(Long productId, Boolean isAllowForGA, Long profileInfoId, String promoCode) {
		String query =
		        "SELECT DISTINCT dm.* "
		            + "FROM {h-schema}delivery_method dm "
		            + "JOIN {h-schema}delivery_tpl_detail_live dtdl ON dm.deliverymethodid = dtdl.delivery_method_id "
		            + "JOIN {h-schema}delivery_method_template_live dmtl "
		            + "  ON dtdl.delivery_method_template_id = dmtl.deliverymethodtemplateid "
		            + "JOIN {h-schema}delivery_tpl_prdt_live dtpl "
		            + " ON dmtl.deliverymethodtemplateid = dtpl.delivery_method_template_id "
		            + "JOIN {h-schema}profile_delivery_method pdm "
		            + "  ON dm.deliverymethodid = pdm.delivery_method_id "
		            + "JOIN {h-schema}profile_info profile ON pdm.profile_info_id = profile.profileinfoid "
		            + "JOIN {h-schema}product_live pl ON dtpl.product_id = pl.productid "
		            + "WHERE dtdl.isapplicable = 'T' "
		            + "   AND sysdate <= "
		            + "   (CASE expiryoption "
		            + "        WHEN 1 "
		            + "          THEN pl.startdate + "
		            + "               (decode(expirywindow, 1, -1, 2, 1) * "
		            + "                (expiryrulevalue / decode(expiryruleunit, 1, 1, 2, 24, 3, 1440))) "
		            + "        WHEN 2 "
		            + "          THEN trunc(pl.startdate) + "
		            + "               (decode(expirywindow, 1, -expiryrulevalue, 2, expiryrulevalue) + "
		            + "                expiryhours / 24 + expiryminutes / 1440) "
		            + "        ELSE expirydate END) "
		            + "  AND pl.productid = :productId "
		            + "  AND profile_info_id = :profileInfoId "
		            + "  AND dm.deliverymethodcode IN ("
		            + "  SELECT deliverymethodcode FROM {h-schema}delivery_method d"
		            + "  JOIN {h-schema}delivery_tpl_detail_live dtd ON d.deliverymethodid = dtd.delivery_method_id "
                    + "  JOIN {h-schema}delivery_method_template_live dmt "
                    + "  ON dtd.delivery_method_template_id = dmt.deliverymethodtemplateid "
                    + "  JOIN {h-schema}online_promotion_show ops ON dmt.deliverymethodtemplateid = ops.delivery_method_template_id "
                    + "  JOIN {h-schema}online_promotion_info opi ON opi.onlinepromotioninfoid = ops.online_promotion_info_id "
		            + "  WHERE opi.promotion_code = :promoCode "
		            + "  AND ops.product_id = :productId "
		            + ") ";

		if (isAllowForGA) {
			query += "AND dtdl.isallowforga = 'T' ";
		}
		query += "ORDER BY dm.ordermethod";

		return em.createNativeQuery(query, DeliveryMethod.class).setParameter("productId", productId)
				.setParameter("profileInfoId", profileInfoId).setParameter("promoCode", promoCode).getResultList();
	}

}
