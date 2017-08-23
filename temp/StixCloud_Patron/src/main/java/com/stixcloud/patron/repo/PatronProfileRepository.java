package com.stixcloud.patron.repo;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class PatronProfileRepository {

  @Autowired
  EntityManager em;

  public Long getSequenceAccNo() {
    String queryStr = "SELECT PATRON_PROFILE_ACCNO_SEQ.nextval AS num FROM dual";
    Object result = em.createNativeQuery(queryStr).getSingleResult();
    return Long.valueOf(result.toString());
  }
}
