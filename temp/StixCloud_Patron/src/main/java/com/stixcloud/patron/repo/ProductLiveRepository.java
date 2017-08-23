package com.stixcloud.patron.repo;

import com.stixcloud.domain.Organization;
import com.stixcloud.domain.ProductLive;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by sengkai on 12/14/2016.
 */
public interface ProductLiveRepository extends CrudRepository<ProductLive, Long> {
  @Query("select prod from ProductLive prod "
      + "where prod.productid in :productIds "
      + "and prod.stopSalesReasonMctId is not NULL "
      + "and prod.salesstatus <>  :salesStatus")
  List<ProductLive> getOffSalesProducts(@Param("productIds") Iterable<Long> productIds,
                                        @Param("salesStatus") Integer salesStatus);

  @Query("SELECT o  "
      + "FROM ProductLive pl "
      + "join ProductGroupLive  pgl on pl.productGroupId = pgl.productgroupid "
      + "join ProductGroupPromoterLive pgpl on pgl.productgroupid = pgpl.prdtGrpId "
      + "join Organization o on pgpl.promoterId = o.organizationid "
      + "where pl.productid in :productIds ")
  List<Organization> retrieveAvailPromoterList(@Param("productIds") List<Long> productIds);

  @Query("SELECT o  "
      + "FROM ProductLive pl "
      + "join ProductGroupLive  pgl on pl.productGroupId = pgl.productgroupid "
      + "join Venue v on pl.venueId = v.venueid "
      + "join Location l ON v.locationId = l.locationid "
      + "join Organization o on l.organizationId = o.organizationid "
      + "where pl.productid in :productIds ")
  List<Organization> retrieveAvailVenueList(@Param("productIds") List<Long> productIds);
}
