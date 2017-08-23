package com.stixcloud.cart.repo;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.stixcloud.domain.PaymentMethod;

public class PaymentMethodRepositoryImpl implements PaymentMethodRepositoryCustom {

  @Autowired
  EntityManager em;

  @SuppressWarnings("unchecked")
  @Override
  @Transactional(readOnly = true)
  public List<PaymentMethod> getCommonPaymentMethod(Long productId, Long profileInfoId,
      String promoCode) {
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
    if (!StringUtils.isBlank(promoCode)) {
      queryStr.append("AND PM.PAYMENTMETHODCODE IN ( ");
      queryStr.append("SELECT PAYMENTMETHODCODE FROM PAYMENT_METHOD P ");
      queryStr.append("JOIN PAYMENT_TPL_DETAIL_LIVE PTD ON P.PAYMENTMETHODID = PTD.PAYMENT_METHOD_ID  ");
      queryStr.append("JOIN PAYMENT_METHOD_TEMPLATE_LIVE PMT ON PTD.PAYMENT_METHOD_TEMPLATE_ID = PMT.PAYMENTMETHODTEMPLATEID ");
      queryStr.append("JOIN ONLINE_PROMOTION_SHOW OPS ON OPS.PAYMENT_METHOD_TEMPLATE_ID = PMT.PAYMENTMETHODTEMPLATEID ");
      queryStr.append("JOIN ONLINE_PROMOTION_INFO OPI ON OPS.ONLINE_PROMOTION_INFO_ID = OPI.ONLINEPROMOTIONINFOID ");
      queryStr.append("WHERE OPI.PROMOTION_CODE = :promoCode ");
      queryStr.append("AND OPS.PRODUCT_ID = :productId) ");
    }
    
    queryStr.append("ORDER BY PM.ORDERMETHOD");
    Query query = em.createNativeQuery(queryStr.toString(), PaymentMethod.class)
        .setParameter("productId", productId).setParameter("profileInfoId", profileInfoId);
    if (!StringUtils.isBlank(promoCode)) {
      query = query.setParameter("promoCode", promoCode);
    }
    return query.getResultList();
  }

}
