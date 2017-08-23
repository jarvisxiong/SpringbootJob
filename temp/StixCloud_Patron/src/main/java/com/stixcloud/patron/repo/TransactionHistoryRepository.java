package com.stixcloud.patron.repo;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.stixcloud.patron.domain.TransactionReferenceView;

@Repository
public class TransactionHistoryRepository implements ITransactionHistoryRepository {
  private static final Logger logger = LogManager.getLogger(TransactionHistoryRepository.class);

  @Autowired
  EntityManager em;

  @Override
  public List<BigDecimal> getTransactionIdList(Long profileInfoId, Long patronProfileId,
      List<String> transctionTypeList) {
    logger.info("Start BookingHistoryRepository.getTransactionIdList()");
    StringBuilder queryStr = new StringBuilder();
    queryStr.append("SELECT transactionid ").append(" FROM transaction t ")
        .append(" join transaction_code tc on t.TXN_CODE_ID = tc.TRANSACTIONCODEID ")
        .append(" join master_code_table mct on tc.TXN_CODE_MCT_ID = mct.MASTERCODEID ")
        .append(" WHERE t.patron_id = :patronProfileId ")
        .append(" AND t.OWNER_PROFILE = :profileInfoId AND t.ORDERSTATUS = 1 ")
        .append(" AND mct.DESCRIPTION1 in :transctionTypeList ")
        .append(" ORDER BY t.TRANSACTEDTIME DESC ");

    @SuppressWarnings("unchecked")
    List<BigDecimal> transactionIdList = (List<BigDecimal>) em
        .createNativeQuery(queryStr.toString()).setParameter("patronProfileId", patronProfileId)
        .setParameter("profileInfoId", profileInfoId)
        .setParameter("transctionTypeList", transctionTypeList)
        .getResultList();

    logger.info("End BookingHistoryRepository.getTransactionIdList()");
    return transactionIdList;
  }

  @Override
  public List<TransactionReferenceView> getTransactionReference(List<BigDecimal> transactionIds,
      Long profileInfoId, Long patronProfileId, List<String> transctionTypeList) {
    logger.info("Start BookingHistoryRepository.getTransactionReference()");

    StringBuilder queryStr = new StringBuilder();

    queryStr.append(" SELECT * FROM TRANSACTION_REFERENCE_VIEW ")
        .append("WHERE TRANSACTIONID  IN :transactionIds AND PATRONID = :patronProfileId ")
        .append("AND OWNERPROFILE = :profileInfoId ")
        .append("AND TRANSACTION_TYPE IN :transctionTypeList ");

    logger.info(queryStr.toString());
    logger.info(patronProfileId);
    logger.info(profileInfoId);
    logger.info(transctionTypeList);

    @SuppressWarnings("unchecked")
    List<TransactionReferenceView> result =
        em.createNativeQuery(queryStr.toString(), TransactionReferenceView.class)
            .setParameter("transactionIds", transactionIds)
            .setParameter("patronProfileId", patronProfileId)
            .setParameter("profileInfoId", profileInfoId)
            .setParameter("transctionTypeList", transctionTypeList).getResultList();

    logger.info("End BookingHistoryRepository.getTransactionReference()");
    return result;
  }

}
