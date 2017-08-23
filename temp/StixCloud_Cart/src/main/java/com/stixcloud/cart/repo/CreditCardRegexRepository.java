package com.stixcloud.cart.repo;

import com.stixcloud.domain.CreditCardRegex;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by chongye on 28/11/2016.
 */
public interface CreditCardRegexRepository extends CrudRepository<CreditCardRegex, Long> {
  @Query("SELECT regex "
      + "FROM DeliveryMtdCcRange ccrange "
      + "  JOIN DeliveryMethod dm "
      + "    ON ccrange.deliveryMethodId = dm.deliverymethodid "
      + "  JOIN CreditCard cc ON ccrange.creditCardRangeId = cc.creditcardid "
      + "  JOIN CreditCardRegex regex ON cc.creditcardid = regex.creditCardId "
      + "WHERE dm.deliverymethodcode = :deliveryMethodCode")
  List<CreditCardRegex> getCreditCardRegexesByDeliveryMethodCode(
      @Param("deliveryMethodCode") String deliveryMethodCode);

  @Query("SELECT distinct regex "
      + "FROM ProductLive p  "
      + "  JOIN PriceModelTplPrdtLive pmodel ON p.productid = pmodel.productId  "
      + "  JOIN PriceClassLive pclass  "
      + "    ON p.productGroupId = pclass.productgroupId AND pclass.ispackageclass = false  "
      + "  JOIN CreditCard cc ON pclass.creditcardrangeId = cc.creditcardid  "
      + "  JOIN CreditCardRegex regex ON cc.creditcardid = regex.creditCardId  "
      + "WHERE cc.status = 1 "
      + "and p.productid = :productId "
      + "and pclass.priceclasscode = :priceClassCode")
  List<CreditCardRegex> getCreditCardRegexesByProductIdAndPriceClassCode(
      @Param("productId") Long productId,
      @Param("priceClassCode") String priceClassCode);

  @Query("SELECT distinct regex "
      + "  FROM  CreditCard cc "
      + "  JOIN CreditCardRegex regex ON cc.creditcardid = regex.creditCardId and cc.creditcardname=:ccName "
      + "WHERE cc.status = 1 ")
  List<CreditCardRegex> getCreditCardRegexesByCreditCardName(
      @Param("ccName") String ccName);
}
