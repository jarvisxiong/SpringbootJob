package com.stixcloud.cart.repo;

import com.stixcloud.domain.EticketDelivery;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by sengkai on 12/14/2016.
 */
public interface ETicketDeliveryRepository
    extends ETicketDeliveryRepositoryCustom, CrudRepository<EticketDelivery, Long> {
  @Query("SELECT e "
      + "FROM OrderDeliveryInfo o JOIN TransactionProduct tp on tp.productId in :productIds and tp.orderId=o.orderdeliveryinfoid join EticketDelivery e "
      + " on e.orderId=o.orderdeliveryinfoid JOIN DeliveryMethod dm on o.deliveryMethod=dm.deliverymethodid "
      + "where dm.deliverymethodcode in ('E_TICKET','SIC_E_TICKET') ")
  List<EticketDelivery> findOrderInfoByProductIds(@Param("productIds") List<Long> productIds);

  @Query("SELECT e "
      + "FROM OrderDeliveryInfo o JOIN  EticketDelivery e "
      + " on e.orderId=o.orderdeliveryinfoid and e.orderId in :orderIds JOIN DeliveryMethod dm on o.deliveryMethod=dm.deliverymethodid "
      + "where dm.deliverymethodcode in ('E_TICKET','SIC_E_TICKET') ")
  List<EticketDelivery> findOrderInfoByOrderIds(@Param("orderIds") List<Long> orderIds);

}
