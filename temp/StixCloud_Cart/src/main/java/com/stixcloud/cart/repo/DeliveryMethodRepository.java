package com.stixcloud.cart.repo;

import com.stixcloud.domain.DeliveryMethod;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

/**
 * Created by chongye on 19/10/2016.
 */
public interface DeliveryMethodRepository
    extends CrudRepository<DeliveryMethod, Long>, DeliveryMethodRepositoryCustom {

  @Query("SELECT addressrequired  "
      + " from DeliveryMethod "
      + " where deliverymethodcode = :deliveryMethodCode ")
  Boolean getDeliveryMethodIsAddressRequired(
      @Param("deliveryMethodCode") String deliveryMethodCode);

  @Query("SELECT dm  "
      + " from DeliveryMethod dm "
      + " where dm.deliverymethodcode = :deliveryMethodCode ")
  DeliveryMethod getDeliveryMethod(@Param("deliveryMethodCode") String deliveryMethodCode);
}
