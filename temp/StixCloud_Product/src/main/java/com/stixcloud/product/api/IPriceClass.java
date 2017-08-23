package com.stixcloud.product.api;

import com.stixcloud.domain.RetrievePriceTypeView;

import java.util.List;

/**
 * Created by dbthan on 13-Sep-16.
 */

public interface IPriceClass {

  List<PriceClass> getPriceClassListFromPriceTypeView(
      List<RetrievePriceTypeView> retrievePriceTypeViewList);

}
