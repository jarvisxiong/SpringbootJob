package com.stixcloud.cart.repo;

import com.stixcloud.domain.TransactionRefId;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import javax.persistence.LockModeType;

/**
 * Created by sengkai on 12/15/2016.
 */
public interface TransactionRefIdRepository
    extends CrudRepository<TransactionRefId, Long>, TransactionRefIdCustomRepository {

  @Query("select distinct txnRefId from TransactionRefId txnRefId "
      + "where txnRefId.txndate = :date")
  TransactionRefId getTransactionRefIdForToday(@Param("date") String date);

  @Lock(LockModeType.PESSIMISTIC_WRITE)
  @Query("select txn from TransactionRefId txn where txn.transactionrefid = :transactionRefId")
  TransactionRefId getWithLock(@Param("transactionRefId") Long transactionRefId);
}
