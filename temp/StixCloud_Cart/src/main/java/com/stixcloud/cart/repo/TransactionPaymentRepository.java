package com.stixcloud.cart.repo;

import com.stixcloud.domain.TransactionPayment;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by sengkai on 12/14/2016.
 */
public interface TransactionPaymentRepository extends CrudRepository<TransactionPayment, Long> {
  @Query("SELECT tp "
      + "FROM TransactionPayment tp "
      + "where tp.transactionrefnumber = :transactionRefNumber ")
  List<TransactionPayment> findByTransactionRefNumber(
      @Param("transactionRefNumber") String transactionRefNumber);


}
