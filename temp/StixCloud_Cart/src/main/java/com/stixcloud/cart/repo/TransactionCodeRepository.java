package com.stixcloud.cart.repo;

import com.stixcloud.domain.TransactionCode;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

/**
 * Created by sengkai on 12/14/2016.
 */
public interface TransactionCodeRepository extends CrudRepository<TransactionCode, Long> {
  @Query("select txnCode from TransactionCode txnCode "
      + "join MasterCodeTable mct on mct.mastercodeid = txnCode.txnCodeMctId "
      + "where txnCode.txndescription = :txnDesc "
      + "and mct.description1 = :desc")
  TransactionCode getTransactionCode(@Param("txnDesc") String transactionReason,
                                     @Param("desc") String transactionCode);
}
