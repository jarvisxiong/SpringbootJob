package com.stixcloud.cart.repo;

import java.util.List;

import com.stixcloud.domain.PromoPassword;

/**
 * @author dbthan created 04/05/2017
 */

public interface IPromoPasswordRepository {

  List<PromoPassword> getPromos(List<Long> priceClassIdList);
}
