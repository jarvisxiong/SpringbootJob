package com.stixcloud.cart.repo;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.stixcloud.domain.DeliveryMethod;

/**
 * Created by chongye on 19/10/2016.
 */
public class DeliveryMethodRepositoryImpl implements DeliveryMethodRepositoryCustom {
  @Autowired
  EntityManager em;

  @Override
  @Transactional(readOnly = true)
  public List<DeliveryMethod> getDeliveryMethodsForProduct(Long productId,
                                                           Boolean isAllowForGA,
                                                           Long profileInfoId) {
    String query =
        "SELECT DISTINCT dm.DELIVERYMETHODID, dm.addressrequired, dm.deliverymethodname, dm.deliverytype, dm.description, dm.emailrequired, dm.mobilenorequired, dm.ordermethod, dm.deliverymethodcode, dm.tickettype "
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
            + "  AND pl.productid = ?1 "
            + "  AND profile_info_id = ?2 ";

    if (isAllowForGA) {
      query += "AND dtdl.isallowforga = 'T' ";
    }
    query += "ORDER BY dm.ordermethod";

    return em.createNativeQuery(query, DeliveryMethod.class)
        .setParameter(1, productId)
        .setParameter(2, profileInfoId)
        .getResultList();
  }
  
  @Override
  public List<DeliveryMethod> getDeliveryMethodsForProduct(Long productId, Boolean isAllowForGA,
      Long profileInfoId, List<String> priceClassCodeList) {
    
    String query = "SELECT DISTINCT dm.DELIVERYMETHODID, dm.addressrequired, dm.deliverymethodname, dm.deliverytype, dm.description, dm.emailrequired, dm.mobilenorequired, dm.ordermethod, dm.deliverymethodcode, dm.tickettype "
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
            + "  AND pl.productid = ?1 "
            + "  AND profile_info_id = ?2 "
            + "  AND dm.DELIVERYMETHODID NOT in (select DELIVERY_METHOD_ID from {h-schema}PRICECLASS_EXCLUDE_DM_LIVE pedl "
            + "		JOIN  {h-schema}price_class_live pclass on pedl.PRICE_CLASS_ID = pclass.PRICECLASSID "
            + "		where pl.PRODUCT_GROUP_ID = pclass.PRODUCTGROUP_ID "
            + "		and pclass.PRICECLASSCODE IN ?3) ";
    

    if (isAllowForGA) {
      query += " AND dtdl.isallowforga = 'T' ";
    }
    query += " ORDER BY dm.ordermethod";

    return em.createNativeQuery(query, DeliveryMethod.class)
        .setParameter(1, productId)
        .setParameter(2, profileInfoId)
        .setParameter(3, priceClassCodeList)
        .getResultList();
  }
  
  @SuppressWarnings("unchecked")
  @Override
  @Transactional(readOnly = true)
  public List<DeliveryMethod> getCommonDeliveryMethod(Long productId, Boolean isAllowForGA,
      Long profileInfoId, String priceClass, String promoCode) {
    String query =
        "SELECT DISTINCT dm.DELIVERYMETHODID, dm.addressrequired, dm.deliverymethodname, dm.deliverytype, dm.description, dm.emailrequired, dm.mobilenorequired, dm.ordermethod, dm.deliverymethodcode, dm.tickettype "
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
            + "   AND pl.productid = :productId "
            + "   AND profile_info_id = :profileInfoId ";

    if (StringUtils.isNotBlank(priceClass)) {
      query += "   AND dm.DELIVERYMETHODID NOT in (select DELIVERY_METHOD_ID from {h-schema}PRICECLASS_EXCLUDE_DM_LIVE pedl "
          + "     JOIN  {h-schema}price_class_live pclass on pedl.PRICE_CLASS_ID = pclass.PRICECLASSID "
          + "     where pl.PRODUCT_GROUP_ID = pclass.PRODUCTGROUP_ID "
          + "     and pclass.PRICECLASSCODE = :priceClass) ";
    }

    if (!StringUtils.isBlank(promoCode)) {
      query += "  AND dm.deliverymethodcode IN ("
          + "  SELECT deliverymethodcode FROM {h-schema}delivery_method d"
          + "  JOIN {h-schema}delivery_tpl_detail_live dtd ON d.deliverymethodid = dtd.delivery_method_id "
          + "  JOIN {h-schema}delivery_method_template_live dmt "
          + "  ON dtd.delivery_method_template_id = dmt.deliverymethodtemplateid "
          + "  JOIN {h-schema}online_promotion_show ops ON dmt.deliverymethodtemplateid = ops.delivery_method_template_id "
          + "  JOIN {h-schema}online_promotion_info opi ON opi.onlinepromotioninfoid = ops.online_promotion_info_id "
          + "  WHERE opi.promotion_code = :promoCode "
          + "  AND ops.product_id = :productId "
          + ") ";
    }
    if (isAllowForGA) {
      query += "AND dtdl.isallowforga = 'T' ";
    }
    query += "ORDER BY dm.ordermethod";
    Query queryManager =
        em.createNativeQuery(query, DeliveryMethod.class).setParameter("productId", productId)
            .setParameter("profileInfoId", profileInfoId);
    if (StringUtils.isNotBlank(priceClass)) {
      queryManager = queryManager.setParameter("priceClass", priceClass);
    }
    
    if (!StringUtils.isBlank(promoCode)) {
      queryManager = queryManager.setParameter("promoCode", promoCode);
    }
    return queryManager.getResultList();
  }
}
