package com.stixcloud.product.event;


import com.stixcloud.domain.SalesSeatInventory;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import javax.persistence.LockModeType;

/**
 * Created by chongye on 23-Aug-16.
 */
@Repository
public interface ISalesSeatInventoryRepository extends CrudRepository<SalesSeatInventory, Long> {
  @Lock(LockModeType.PESSIMISTIC_WRITE)
  @Transactional(readOnly = true)
  @Query("from SalesSeatInventory where productId = :productId " +
      "and seatsalesstatus = :seatSaleStatus " +
      "and salesseatinventoryid in (:ids)")
  List<SalesSeatInventory> findAllByProductIdAndSeatSalesStatus(
      @Param("productId") Long productId,
      @Param("seatSaleStatus") Integer seatSaleStatus,
      @Param("ids") List<Long> ids);
}
