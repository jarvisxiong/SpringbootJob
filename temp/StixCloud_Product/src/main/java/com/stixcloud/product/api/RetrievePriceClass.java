package com.stixcloud.product.api;

import com.stixcloud.domain.RetrievePriceTypeView;
import org.javamoney.moneta.Money;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.money.Monetary;

/**
 * Created by dbthan on 13-Sep-16.
 */

@Component
public class RetrievePriceClass implements IPriceClass {
  @Value("${properties.max.tickets}")
  private int maxTickets;

  /**
   * Convert data from List RetrievePriceTypeView to List PriceClass.
   * @param retrievePriceTypeViewList The retrievePriceTypeViewList
   * @return List PriceClass
   */
  @Override
  public List<PriceClass> getPriceClassListFromPriceTypeView(
      List<RetrievePriceTypeView> retrievePriceTypeViewList) {

    List<PriceClass> priceClassList = new ArrayList<>();
    retrievePriceTypeViewList.forEach(view -> {

      List<Long> pcAvailQty = getAvailableQtyWithoutSalesStatus(view.getMaxQuantity(),
          view.getQuantity(), view.getQuantityStatus());

      String availableQty = "";

      if (!pcAvailQty.isEmpty()) {
        availableQty = pcAvailQty.stream().map(Object::toString).collect(Collectors.joining(","));
      }

      priceClassList.add(
          new PriceClass(view.getPriceClassCode(), view.getPriceClassName(),
              Money.of(view.getPriceValue(),
                  Monetary.getCurrency(LocaleContextHolder.getLocale())),
              availableQty, view.getPasswordRequired()));

    });

    return priceClassList;

  }

  /**
   * Get available quantity from maxQuantity, qualifyingQty and quantityStatus.
   * @param maxQuantity The maxQuantity
   * @param qualifyingQty The qualifyingQty
   * @param quantityStatus The quantityStatus
   * @return Sequence available quantity
   */
  private List<Long> getAvailableQtyWithoutSalesStatus(Long maxQuantity, Long qualifyingQty,
                                                       Integer quantityStatus) {

    maxQuantity = (maxQuantity == 0) ? maxTickets : maxQuantity;

    List<Long> qtys = new ArrayList<>();

    long currQty = (qualifyingQty == 0) ? 1 : qualifyingQty;
    qualifyingQty = (quantityStatus == 1) ? qualifyingQty : 1;
    while (currQty <= maxQuantity) {
      qtys.add(currQty);
      currQty += qualifyingQty;
    }

    if (qtys.isEmpty()) {
      qtys.add(0L);
    }
    return qtys;
  }
}
