package com.stixcloud.cart.repo;

import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;

public class ETicketDeliveryRepositoryImpl implements ETicketDeliveryRepositoryCustom {
  @Autowired
  private EntityManager entityManager;

  private int UNIQUE_ID_LENGTH = 10;

  @Override
  public String getUniqueLinkId() {
    String linkId;
    do {
      linkId = RandomStringUtils.randomAlphanumeric(UNIQUE_ID_LENGTH);
    } while (isLinkIdExists(linkId));
    return linkId;
  }

  private boolean isLinkIdExists(String id) {
    String query = "select count(e) from EticketDelivery e where e.linkid = :id";

    Long countRow = entityManager.createQuery(query, Long.class)
        .setParameter("id", id)
        .getSingleResult();

    return countRow > 1;
  }
}
