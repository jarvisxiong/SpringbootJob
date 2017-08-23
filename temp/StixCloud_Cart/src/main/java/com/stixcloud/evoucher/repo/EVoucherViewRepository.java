package com.stixcloud.evoucher.repo;


import com.stixcloud.evoucher.domain.EVoucherCreditCardRegex;
import com.stixcloud.evoucher.domain.EVoucherView;
import com.stixcloud.evoucher.domain.ProductPaymentMethod;
import com.stixcloud.evoucher.domain.ProductPromoterVenue;
import com.stixcloud.evoucher.domain.UserPaymentMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

/**
 * Created by dbthan on 13/10/2016.
 */

@Repository
public class EVoucherViewRepository implements IEVoucherViewRepository {

  @Autowired
  EntityManager em;

  TypedQuery<EVoucherView> typedQuery;

  @Override
  public List<EVoucherView> getEVoucherView(List<String> eVoucherIdList) {
    StringBuilder queryStr = new StringBuilder();
    queryStr.append("SELECT ev ").append("FROM EVoucherView ev ")
        .append("WHERE ev.eVoucherId IN :eVoucherIdList ")
        .append("ORDER BY ev.eVoucherId, ev.priority");

    return em.createQuery(queryStr.toString(), EVoucherView.class)
        .setParameter("eVoucherIdList", eVoucherIdList).getResultList();
  }

  @Override
  public List<ProductPaymentMethod> getProductPaymentMethod(List<Long> productIdList) {

    StringBuilder queryStr = new StringBuilder();
    queryStr.append("SELECT ppm ");
    queryStr.append("FROM ProductPaymentMethod ppm ");
    queryStr.append("WHERE ppm.productId IN :productId ");

    return em.createQuery(queryStr.toString(), ProductPaymentMethod.class)
        .setParameter("productId", productIdList).getResultList();
  }

  @Override
  public List<UserPaymentMethod> getUserPaymentMethod(Long profileInfoId) {

    StringBuilder queryStr = new StringBuilder();
    queryStr.append("SELECT upm ");
    queryStr.append("FROM UserPaymentMethod upm ");
    queryStr.append("WHERE upm.profileInfoId = :profileInfoId ");

    return em.createQuery(queryStr.toString(), UserPaymentMethod.class)
        .setParameter("profileInfoId", profileInfoId).getResultList();
  }

  @Override
  public List<EVoucherCreditCardRegex> getCreditCardRegex(List<String> eVoucherIdList) {

    StringBuilder queryStr = new StringBuilder();
    queryStr.append("SELECT ecc ").append("FROM EVoucherCreditCardRegex ecc ")
        .append("WHERE ecc.evoucherId IN :eVoucherIdList ");

    return em.createQuery(queryStr.toString(), EVoucherCreditCardRegex.class)
        .setParameter("eVoucherIdList", eVoucherIdList).getResultList();
  }

  @Override
  public List<ProductPromoterVenue> getPromoterAndVenueByProductIdList(List<Long> productIds) {
    StringBuilder queryStr = new StringBuilder();
    queryStr.append("SELECT ppv ").append("FROM ProductPromoterVenue ppv ")
        .append("WHERE ppv.productId IN :productIds ");

    return em.createQuery(queryStr.toString(), ProductPromoterVenue.class)
        .setParameter("productIds", productIds).getResultList();
  }
}
