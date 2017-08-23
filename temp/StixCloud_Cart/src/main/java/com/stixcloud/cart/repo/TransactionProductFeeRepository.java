package com.stixcloud.cart.repo;

import com.stixcloud.domain.TransactionProductFee;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by sengkai on 12/14/2016.
 */
public interface TransactionProductFeeRepository
    extends CrudRepository<TransactionProductFee, Long> {

  @Query("SELECT sum(tpf.feeamount) "
      + "FROM TransactionLineItemProduct t,TransactionProductFee tpf,FeeClass fc,Fee f "
      + "where t.transactionrefnumber = :transactionRefNumber "
      + "and  t.txnProductId = tpf.txnProductId "
      + "and  fc.feeclassid=f.feeClassId "
      + "and  fc.feeclassname in ('Service Fee','Booking Fee') "
      + "and  tpf.feeId=f.feeid "
      + "and  tpf.iscredit=1 ")
  BigDecimal findBookingFeeByTransactionRefNumber(
      @Param("transactionRefNumber") String transactionRefNumber);

  @Query("SELECT tp.productId, tli.priceclasscode, tli.priceCategoryId,tpf.feeamount "
      + "FROM TransactionLineItemProduct t,TransactionProductFee tpf,TransactionProduct tp,FeeClass fc,Fee f, "
      + "TransactionLineItem tli "
      + "where t.transactionrefnumber = :transactionRefNumber "
      + "and  t.txnLineItemId = tli.txnlineitemid  "
      + "and  t.txnProductId = tp.txnproductid  "
      + "and  tp.txnproductid = tpf.txnProductId "
      + "and  fc.feeclassid=f.feeClassId "
      + "and  fc.feeclassname in ('Service Fee','Booking Fee') "
      + "and  tpf.feeId=f.feeid "
      + "and  tpf.iscredit=1 group by tp.productId, tli.priceclasscode, tli.priceCategoryId,tpf.feeamount")
  List<Object[]> findBookingFeeListByTransactionRefNumber(
      @Param("transactionRefNumber") String transactionRefNumber);
}
