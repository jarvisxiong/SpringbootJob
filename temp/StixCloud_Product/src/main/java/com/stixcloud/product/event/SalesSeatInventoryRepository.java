package com.stixcloud.product.event;


import com.stixcloud.domain.SalesSeatInventory;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

import javax.persistence.LockModeType;

/**
 * Created by chongye on 23-Aug-16.
 */
@Repository
public interface SalesSeatInventoryRepository
    extends CrudRepository<SalesSeatInventory, Long> {
  @Lock(LockModeType.PESSIMISTIC_WRITE)
  @Transactional(readOnly = true)
  @Query("select ssi from SalesSeatInventory ssi where ssi.productId = :productId " +
      "and ssi.seatsalesstatus = :seatSaleStatus and ssi.salesseatinventoryid in :ids ")
  List<SalesSeatInventory> findAllByProductIdAndSeatSalesStatus(
      @Param("productId") Long productId,
      @Param("seatSaleStatus") Integer seatSaleStatus,
      @Param("ids") List<Long> ids);
  
  @Lock(LockModeType.PESSIMISTIC_WRITE)
  @Transactional(readOnly = true)
  @Query("from SalesSeatInventory where productId = :productId " +
      "and systemSaleCodeId in (:systemSaleCodeIds) "
      + "and seatsalesstatus = :status")
  List<SalesSeatInventory> findAllByProductIdAndHoldCodeIds(
      @Param("productId") Long productId,
      @Param("systemSaleCodeIds") List<Long> systemSaleCodeIds, @Param("status") int status);
  
  @Lock(LockModeType.PESSIMISTIC_WRITE)
  @Transactional(readOnly = true)
  @Query("from SalesSeatInventory where productId = :productId " +
      "and venueSectionLcId in (:venueSectionLcIds)")
  List<SalesSeatInventory> findAllByProductIdAndSeatSectionIds(
      @Param("productId") Long productId,
      @Param("venueSectionLcIds") Set<Long> venueSectionLcIds);
  
  @Lock(LockModeType.PESSIMISTIC_WRITE)
  @Transactional(readOnly = true)
  @Query("from SalesSeatInventory where productId = :productId " +
      "and salesseatinventoryid in (:seatInvIds)")
  List<SalesSeatInventory> findAllByProductIdAndSeatInvIds(
      @Param("productId") Long productId,
      @Param("seatInvIds") List<Long> seatInvIds);
  
  @Lock(LockModeType.PESSIMISTIC_WRITE)
  @Transactional(readOnly = true)
  @Query("from SalesSeatInventory where productId IN (:productIds) and seatsalesstatus = 2)")
  List<SalesSeatInventory> findHoldSeatByProductIds(
      @Param("productIds") Iterable<Long> productIds);
}
