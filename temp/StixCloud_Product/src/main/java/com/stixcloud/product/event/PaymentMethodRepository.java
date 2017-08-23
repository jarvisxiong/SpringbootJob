package com.stixcloud.product.event;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.stixcloud.domain.PaymentMethod;

@Repository
public class PaymentMethodRepository implements IPaymentMethodRepository {

	@Autowired
	EntityManager em;

	@SuppressWarnings("unchecked")
	@Override
	public List<PaymentMethod> getCommonPaymentMethod(Long productId, Long profileInfoId, String promoCode) {
		StringBuffer queryStr = new StringBuffer();
		queryStr.append("SELECT DISTINCT PM.* FROM PAYMENT_METHOD PM ");
		queryStr.append("JOIN PAYMENT_TPL_DETAIL_LIVE PTPLD ON PM.PAYMENTMETHODID = PTPLD.PAYMENT_METHOD_ID ");
		queryStr.append(
				"JOIN PAYMENT_METHOD_TEMPLATE_LIVE PMTL ON PTPLD.PAYMENT_METHOD_TEMPLATE_ID = PMTL.PAYMENTMETHODTEMPLATEID ");
		queryStr.append(
				"JOIN PAYMENT_TPL_PRDT_LIVE PMTPL ON PMTL.PAYMENTMETHODTEMPLATEID = PMTPL.PAYMENT_METHOD_TEMPLATE_ID ");
		queryStr.append("JOIN PRODUCT_LIVE PL ON PMTPL.PRODUCT_ID = PL.PRODUCTID ");
		queryStr.append("JOIN PROFILE_PAYMENT_METHOD PPM ON PM.PAYMENTMETHODID = PPM.PAYMENT_METHOD_ID ");
		queryStr.append("WHERE PPM.PROFILE_INFO_ID = :profileInfoId ");
		queryStr.append("AND PL.PRODUCTID = :productId ");
		queryStr.append("AND PM.PAYMENTMETHODCODE IN ( ");
		queryStr.append("SELECT PAYMENTMETHODCODE FROM PAYMENT_METHOD P ");
        queryStr.append("JOIN PAYMENT_TPL_DETAIL_LIVE PTD ON P.PAYMENTMETHODID = PTD.PAYMENT_METHOD_ID  ");
        queryStr.append("JOIN PAYMENT_METHOD_TEMPLATE_LIVE PMT ON PTD.PAYMENT_METHOD_TEMPLATE_ID = PMT.PAYMENTMETHODTEMPLATEID ");
		queryStr.append("JOIN ONLINE_PROMOTION_SHOW OPS ON OPS.PAYMENT_METHOD_TEMPLATE_ID = PMT.PAYMENTMETHODTEMPLATEID ");
        queryStr.append("JOIN ONLINE_PROMOTION_INFO OPI ON OPS.ONLINE_PROMOTION_INFO_ID = OPI.ONLINEPROMOTIONINFOID ");
		queryStr.append("WHERE OPI.PROMOTION_CODE = :promoCode ");
		queryStr.append("AND OPS.PRODUCT_ID = :productId) ");
		queryStr.append("ORDER BY PM.ORDERMETHOD");
		
		return em.createNativeQuery(queryStr.toString(), PaymentMethod.class).setParameter("productId", productId)
				.setParameter("profileInfoId", profileInfoId).setParameter("promoCode", promoCode).getResultList();
	}

}
