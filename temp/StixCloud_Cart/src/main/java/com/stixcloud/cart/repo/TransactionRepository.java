package com.stixcloud.cart.repo;

import com.stixcloud.domain.Transaction;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

/**
 * Created by sengkai on 12/14/2016.
 */
public interface TransactionRepository extends CrudRepository<Transaction, Long> {

  @Query("SELECT t "
      + "FROM Transaction t "
      + "where t.transactionrefnumber = :transactionRefNumber ")
  Transaction findByTransactionRefNumber(
      @Param("transactionRefNumber") String transactionRefNumber);

}
