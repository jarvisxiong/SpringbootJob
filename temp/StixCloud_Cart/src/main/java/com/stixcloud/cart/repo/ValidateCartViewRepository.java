package com.stixcloud.cart.repo;

import com.stixcloud.domain.ValidateCartView;
import com.stixcloud.domain.ValidateCartViewKey;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by chongye on 3/10/2016.
 */
@Repository
public interface ValidateCartViewRepository
    extends ReadOnlyRepository<ValidateCartView, ValidateCartViewKey> {

  @Query("select view from ValidateCartView view "
      + "where view.key.productId = :productId "
      + "and view.key.pricecategoryid = :pricecategoryid "
      + "and view.priceclasscode in :priceclasscodes")
  List<ValidateCartView> findAll(@Param("productId") Long productId,
                                 @Param("pricecategoryid") Long pricecategoryid,
                                 @Param("priceclasscodes") List<String> priceclasscodes);
}
