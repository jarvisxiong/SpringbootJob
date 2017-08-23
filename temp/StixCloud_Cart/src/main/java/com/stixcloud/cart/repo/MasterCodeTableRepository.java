package com.stixcloud.cart.repo;

import com.stixcloud.domain.MasterCodeTable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by sengkai on 12/14/2016.
 */
public interface MasterCodeTableRepository extends CrudRepository<MasterCodeTable, Long> {
  @Query("SELECT mct.description1 "
      + "FROM MasterCodeTable mct "
      + "  JOIN FeeAttributes attr ON mct.mastercodeid = attr.levyByMctId "
      + "  JOIN FeeVersionHistory hist "
      + "    ON attr.feeVersionHistoryId = hist.feeversionhistoryid "
      + "  JOIN DeliveryMethod dm ON attr.methodCode = dm.deliverymethodid "
      + "WHERE hist.feeId = :feeId "
      + "AND dm.deliverymethodcode = :deliveryMethodCode")
  String getLevyByForDeliveryMethodCode(@Param("deliveryMethodCode") String deliveryMethodCode,
                                        @Param("feeId") Long feeId);

  @Query(" SELECT mct FROM MasterCodeTable mct "
      + " WHERE mct.categorycode = :categorycode ")
  List<MasterCodeTable> retrieveCategoryList(@Param("categorycode") String categorycode);

  @Query(" SELECT pa.productid FROM ProductAttributeLive pa,MasterCodeTable mct "
      + " WHERE pa.productid=:productid and pa.prdtAttrMctID=mct.mastercodeid and mct.description2 = '134217728' ")
  Long retrieveSCBWaiverProduct(@Param("productid") Long productid);

}
