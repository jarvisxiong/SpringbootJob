package com.stixcloud.cart.repo;


import com.stixcloud.domain.SalesSeatInventory;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by chongye on 23-Aug-16.
 */
@Repository
public interface SalesSeatInventoryRepository
    extends CrudRepository<SalesSeatInventory, Long>, SalesSeatInventoryRepositoryCustom {

  @Query("select inv from SalesSeatInventory inv where inv.salesseatinventoryid in :invIds "
      + "and inv.productId = :productId and inv.seatsalesstatus IN (1, 3) "
      + " and inv.ticketable = 1")
  List<SalesSeatInventory> findReservedSeats(@Param("invIds") Iterable<Long> invIds,
                                             @Param("productId") Long productId);
  
  @Query("select inv.salesseatinventoryid, vrlc.rowalias "
      + "from SalesSeatInventory inv "
      + "join VenueSeatLc vslc on inv.venueSeatLcId = vslc.venueseatid "
      + "join VenueRowLc vrlc on vslc.rowId = vrlc.venuerowlcid "
      + "where inv.salesseatinventoryid in :invIds "
      + "AND inv.productId = :productId and inv.seatsalesstatus IN (1, 3)")
  List<Object> findRowAlias(@Param("invIds") Iterable<Long> invIds,
                                     @Param("productId") Long productId);
}
