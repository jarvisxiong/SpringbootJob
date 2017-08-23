package com.stixcloud.cart.repo;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.stixcloud.domain.PromoPassword;

@Repository
public class PromoPasswordRepository implements IPromoPasswordRepository {

  @Autowired
  EntityManager em;
  @Override
  public List<PromoPassword> getPromos(List<Long> priceClassIdList) {
    
    StringBuilder query = new StringBuilder("SELECT rownum, pcl.PRICECLASSID AS PRICE_CLASS_ID, ")
        .append(" pp.PROMOTIONPASSWORDID          AS PROMOTION_PASSWORD_ID, ")
        .append(" pr.passwordregexid                  AS PASSWORD_REGEX_ID, ")
        .append(" MAXUSAGEFREQUENCY                      AS MAX_USAGE_FREQUENCY, ")
        .append(" (SELECT SUM(pUsage.USEDTIMES) FROM PASSWORD_USAGE pUsage ")
        .append(" WHERE pUsage.PASSWORD_REGEX_ID = pr.PASSWORDREGEXID ")
        .append(" GROUP BY pUsage.PASSWORD_REGEX_ID) AS NUMBER_USAGE, ")
        .append("  (CASE WHEN pr.PROMOTION_PASSWORD_ID IS NULL THEN NULL ")
        .append(" WHEN pr.NOOFDIGITS is NULL THEN '^' || pr.EXPRESSION ")
        .append(" ELSE '^' || pr.EXPRESSION || '\\d{' || pr.NOOFDIGITS || '}$' ")
        .append(" END) AS REGEX_PATTERN, pp.status, pu.password AS PASSWORD_USAGE, pcl.PRICECLASSNAME as PRICE_CLASS_NAME ")
        .append(" FROM PRICE_CLASS_LIVE pcl ")
        .append(" JOIN PROMOTION_PASSWORD pp ON pcl.PROMOTIONPASSWORD_ID = pp.PROMOTIONPASSWORDID ")
        .append(" JOIN PASSWORD_REGEX pr on  pp.PROMOTIONPASSWORDID = pr.PROMOTION_PASSWORD_ID ")
        .append(" LEFT JOIN PASSWORD_USAGE pu on pu.password_regex_id = pr.passwordregexid and pu.promotion_password_id = pp.PROMOTIONPASSWORDID ")
        ;
    if(priceClassIdList != null && !priceClassIdList.isEmpty()){
      query.append(" WHERE pcl.PRICECLASSID IN :priceClassIdList");
    }
    
    Query squery = em.createNativeQuery(query.toString(), PromoPassword.class);
    if(priceClassIdList != null && !priceClassIdList.isEmpty()){
      squery.setParameter("priceClassIdList", priceClassIdList);
    }
    return squery.getResultList();
  }
  
}
