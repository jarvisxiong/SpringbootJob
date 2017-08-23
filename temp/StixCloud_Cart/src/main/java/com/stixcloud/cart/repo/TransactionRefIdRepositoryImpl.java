package com.stixcloud.cart.repo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 * Created by sengkai on 12/15/2016.
 */
@Repository
public class TransactionRefIdRepositoryImpl implements TransactionRefIdCustomRepository {
  @Autowired
  private EntityManager em;

  @Override
  @Transactional(readOnly = true)
  public Long getNextTransactionRefId() {

    String query = "select {h-schema}.transaction_ref_id_seq.nextval from dual ";

    Query q = em.createNativeQuery(query);

    BigDecimal result = (BigDecimal) q.getSingleResult();

    return result.longValue();
  }
}
