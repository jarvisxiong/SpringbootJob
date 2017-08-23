package com.stixcloud.cart.repo;

import com.stixcloud.domain.OrderDeliveryInfo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by chongye on 19/10/2016.
 */
public interface OrderDeliveryInfoRepository extends CrudRepository<OrderDeliveryInfo, Long> {
  @Query("SELECT o "
      + "FROM OrderDeliveryInfo o JOIN TransactionProduct tp on tp.productId in :productIds and tp.orderId=o.orderdeliveryinfoid "
      + "where rownum=1 ")
  OrderDeliveryInfo findOrderInfoByProductIds(@Param("productIds") List<Long> productIds);

  @Query("SELECT distinct o "
      + "FROM TransactionLineItemProduct tlp JOIN TransactionProduct tp on tlp.txnProductId=tp.txnproductid and tlp.transactionrefnumber=:transactionrefnumber JOIN OrderDeliveryInfo o on o.orderdeliveryinfoid=tp.orderId  "
  )
  List<OrderDeliveryInfo> findOrderInfoByTransactionRefNumber(
      @Param("transactionrefnumber") String transactionrefnumber);
}

