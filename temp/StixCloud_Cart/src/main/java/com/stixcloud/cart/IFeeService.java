package com.stixcloud.cart;

import com.stixcloud.domain.DeliveryMethod;
import com.stixcloud.domain.Fee;
import com.stixcloud.domain.FeeRule;
import com.stixcloud.domain.FeeRuleTable;
import com.stixcloud.domain.ShoppingCart;

import java.util.List;
import java.util.Set;
import javax.money.MonetaryAmount;

/**
 * Created by chongye on 19/10/2016.
 */
public interface IFeeService {
  List<DeliveryMethod> getCommonDeliveryMethods(String cartGuid, Long profileInfoId)
      throws CartException;

  MonetaryAmount getBookingFee(Long productId, Long priceCatId, String priceClassCode,
                               MonetaryAmount ticketPrice);

  Set<Fee> retrieveMatchingFeesForShoppingCart(ShoppingCart cart,
                                               FeeConstants.FeeCategory feeCategory);

  Set<Fee> getFeesForProduct(FeeConstants.FeeCategory feeCategory, Long productId,
                             Long priceCatId, String priceClassCode);

  FeeRule getMatchingFeeRule(Long productId, Long priceCatId, String priceClassCode,
                             FeeRuleTable feeRuleTable);
}
