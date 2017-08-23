package com.stixcloud.cart;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.money.CurrencyUnit;
import javax.money.Monetary;
import javax.money.MonetaryAmount;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.javamoney.moneta.Money;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import com.stixcloud.cart.repo.DeliveryMethodRepository;
import com.stixcloud.cart.repo.FeeAttributesRepository;
import com.stixcloud.cart.repo.FeeChargeRuleRepository;
import com.stixcloud.cart.repo.FeeRepository;
import com.stixcloud.cart.repo.FeeRuleRepository;
import com.stixcloud.cart.repo.FeeRuleTableRepository;
import com.stixcloud.cart.repo.FeeTemplateLiveRepository;
import com.stixcloud.cart.repo.PriceCategoryLiveRepository;
import com.stixcloud.common.config.multitenant.TenantContextHolder;
import com.stixcloud.domain.CartItem;
import com.stixcloud.domain.DeliveryMethod;
import com.stixcloud.domain.Fee;
import com.stixcloud.domain.FeeAttributes;
import com.stixcloud.domain.FeeChargeRule;
import com.stixcloud.domain.FeeRule;
import com.stixcloud.domain.FeeRuleTable;
import com.stixcloud.domain.FeeTemplateLive;
import com.stixcloud.domain.PriceCategoryLive;
import com.stixcloud.domain.ShoppingCart;

/**
 * Created by chongye on 19/10/2016.
 */
@Service
public class FeeService implements IFeeService {
  private static final Logger logger = LogManager.getLogger(FeeService.class);
  @Autowired
  private FeeRepository feeRepository;
  @Autowired
  private DeliveryMethodRepository deliveryMethodRepository;
  @Autowired
  private FeeRuleTableRepository feeRuleTableRepository;
  @Autowired
  private PriceCategoryLiveRepository priceCategoryLiveRepository;
  @Autowired
  private FeeAttributesRepository feeAttributesRepository;
  @Autowired
  private FeeRuleRepository feeRuleRepository;
  @Autowired
  private FeeTemplateLiveRepository feeTemplateLiveRepository;
  @Autowired
  private FeeChargeRuleRepository feeChargeRuleRepository;
  @Autowired
  private ShoppingCartService shoppingCartService;

  @Override
  public List<DeliveryMethod> getCommonDeliveryMethods(String cartGuid, Long profileInfoId)
      throws CartException {
    CurrencyUnit currencyUnit = Monetary.getCurrency(LocaleContextHolder.getLocale());
    ShoppingCart cart = shoppingCartService.getCart(cartGuid);
    Set<Fee> deliveryFees =
        retrieveMatchingFeesForShoppingCart(cart, FeeConstants.FeeCategory.DELIVERY_BASED);

    List<String> levyBys =
        Arrays.asList(FeeConstants.LevyBy.SHOW.getName(), FeeConstants.LevyBy.OUTLET.getName(),
            FeeConstants.LevyBy.ADDRESS.getName());
    //retrieve all FeeAttributes and group by delivery method id
    Map<Long, List<FeeAttributes>> feeAttributesMapForDelivery =
        deliveryFees.stream()
            .map(fee -> feeAttributesRepository.getFeeAttributesByFeeIdAndLevyBy(fee.getFeeid(),
                FeeConstants.FeeClassChargeType.OUTSIDE_CHARGE_TYPE.getValue(), levyBys))
            .flatMap(List::stream)
            .collect(Collectors.groupingBy(FeeAttributes::getMethodCode));

    // Set<Long> productIds =
    //    cart.getCartItems().stream().map(CartItem::getProductId).collect(Collectors.toSet());
    Boolean hasGA = cart.getCartItems().stream().anyMatch(
        cartItem -> cartItem.getMode().equals(InventoryConstants.SECTION_TYPE_GA.getName()));
    
    
    // Map<Long, List<String>> productPriceClassMap = cart.getCartItems().stream().collect(Collectors.groupingBy(CartItem::getProductId, Collectors.mapping(CartItem::getPriceClass, Collectors.toList())));
    
    //intersect all the delivery methods for different products
    // List<DeliveryMethod> deliveryMethods = productIds.stream().map(
    //    productId -> deliveryMethodRepository.getDeliveryMethodsForProduct(productId, hasGA,
    //        TenantContextHolder.getTenant().getProfileInfoId(), productPriceClassMap.get(productId)))
    //    .reduce((deliveryMethods1, deliveryMethods2) -> {
    //      deliveryMethods1.retainAll(deliveryMethods2);
    //      return deliveryMethods1;
    //    }).orElse(new ArrayList<>());
	
    List<DeliveryMethod> deliveryMethods = cart.getCartItems().stream()
        .map(entry -> deliveryMethodRepository.getCommonDeliveryMethod(
            entry.getProductId(), hasGA,
            TenantContextHolder.getTenant().getProfileInfoId(), entry.getPriceClass(), entry.getPromoCode()))
        .reduce((deliveryMethods1, deliveryMethods2) -> {
          deliveryMethods1.retainAll(deliveryMethods2);
          return deliveryMethods1;
        }).orElse(new ArrayList<>());
    
    List<DeliveryMethod> deliveryMethodList =
        calculateDeliveryFee(deliveryMethods, feeAttributesMapForDelivery, currencyUnit);
    deliveryMethodList.forEach(logger::debug);
    return deliveryMethodList;
  }
  
  private List<DeliveryMethod> calculateDeliveryFee(List<DeliveryMethod> deliveryMethods,
      Map<Long, List<FeeAttributes>> feeAttributesMapForDelivery, CurrencyUnit currencyUnit) {
    List<DeliveryMethod> deliveryMethodList = new ArrayList<>();

    if (feeAttributesMapForDelivery == null || feeAttributesMapForDelivery.isEmpty()) {
      deliveryMethodList = deliveryMethods.stream().map(dm -> {
        dm.setCharge(Money.zero(currencyUnit));
        return dm;
      }).collect(Collectors.toList());
    } else {
      deliveryMethodList = deliveryMethods.stream()
          .filter(dm -> feeAttributesMapForDelivery.containsKey(dm.getDeliverymethodid()))
          .map(dm -> {
            MonetaryAmount charge = Money.zero(currencyUnit);
            List<FeeAttributes> feeAttrs =
                feeAttributesMapForDelivery.get(dm.getDeliverymethodid());

            BigDecimal chargeValue = feeAttrs.stream().map(f -> {
              if (f != null && f.getChargetype() == FeeConstants.ChargeType.FIXED.getValue()) {
                List<FeeChargeRule> feeChargeRules =
                    feeChargeRuleRepository.getFeeChargeRule(f.getFeeattributeid(),
                        f.getChargetype(), FeeConstants.RuleType.FLAT.getValue());
                return feeChargeRules.get(0).getChargedvalue();
              }
              return BigDecimal.ZERO;
            }).reduce((v1, v2) -> v1.max(v2)).orElse(BigDecimal.ZERO);

            charge = Money.of(chargeValue, currencyUnit);

            dm.setCharge(charge);
            return dm;
          }).collect(Collectors.toList());
    }

    return deliveryMethodList;
  }

  @Override
  public MonetaryAmount getBookingFee(Long productId, Long priceCatId, String priceClassCode,
                                      MonetaryAmount ticketPrice) {
    CurrencyUnit currencyUnit = Monetary.getCurrency(LocaleContextHolder.getLocale());

    Set<Fee> bookingFees =
        getFeesForProduct(FeeConstants.FeeCategory.FLAT_BASED, productId, priceCatId,
            priceClassCode);
    List<String> levyBys = Arrays.asList(FeeConstants.LevyBy.PER_TICKET.getName());
    FeeAttributes feeAttr =
        bookingFees.stream()
            .map(fee -> feeAttributesRepository.getFeeAttributesByFeeIdAndLevyBy(fee.getFeeid(),
                FeeConstants.FeeClassChargeType.OUTSIDE_CHARGE_TYPE.getValue(), levyBys))
            .flatMap(List::stream
            ).findFirst().orElse(null);

    BigDecimal chargeValue = BigDecimal.ZERO;
    if (feeAttr != null) {
      List<FeeChargeRule> feeChargeRules =
          feeChargeRuleRepository
              .getFeeChargeRule(feeAttr.getFeeattributeid(), feeAttr.getChargetype(),
                  FeeConstants.RuleType.RANGE.getValue());

      chargeValue = feeChargeRules.stream()
          .filter(f -> matchChargeRule(f, ticketPrice.getNumber().doubleValueExact()))
          .map(FeeChargeRule::getChargedvalue).findFirst().orElse(BigDecimal.ZERO);
    } else {
      logger.info(
          "No booking fee found! Set to $0."
              + "\nproductid: " + productId
              + "\npriceCatId: " + priceCatId
              + "\npriceClassCode: " + priceClassCode
              + "\nticketPrice: " + ticketPrice);
    }
    return Money.of(chargeValue, currencyUnit);
  }

  /**
   * Retrieve fees from cart items in shopping cart by category defined
   * @return Set of fee
   */
  public Set<Fee> retrieveMatchingFeesForShoppingCart(ShoppingCart cart,
                                                      FeeConstants.FeeCategory feeCategory) {
    //find the matching fee rule for each cart item
    return cart.getCartItems().stream().map(cartItem ->
        getFeesForProduct(
            feeCategory, cartItem.getProductId(), cartItem.getPriceCatId(),
            cartItem.getPriceClass()))
        .flatMap(Collection::stream)
        .collect(Collectors.toSet());
  }

  /**
   * Get fees for cart item
   * @return set of fees
   */
  public Set<Fee> getFeesForProduct(FeeConstants.FeeCategory feeCategory, Long productId,
                                    Long priceCatId, String priceClassCode) {
    FeeRuleTable feeRuleTable =
        feeRuleTableRepository.getFeeRuleTable(productId);
    logger.debug("getFeesForProduct details " + productId + " " +
        priceCatId + " " + priceClassCode);
    logger.debug("feeRuleTable " + feeRuleTable);

    //if there is no feeruletable attached or no matching feerule is found, will attempt to
    //use the standard fee rule table
    FeeRule
        matchedFeeRule =
        getMatchingFeeRule(productId, priceCatId, priceClassCode, feeRuleTable);
    logger.debug("matchedFeeRule for product " + productId + " " + matchedFeeRule);

    if (matchedFeeRule != null) {
      //filter out fee matching fee category
      return feeRepository
          .getFeeByCategoryAndFeeRuleId(feeCategory.getName(), matchedFeeRule.getFeeruleid());
    }
    return new HashSet<>();
  }

  public FeeRule getMatchingFeeRule(Long productId, Long priceCatId, String priceClassCode,
                                    FeeRuleTable feeRuleTable) {
    FeeRule matchedFeeRule = null;
    if (feeRuleTable == null ||
        (matchedFeeRule = getFeeRule(priceCatId, priceClassCode, feeRuleTable.getFeeruletableid()))
            == null) {
      FeeTemplateLive assignedFeeTemplate =
          feeTemplateLiveRepository.getFeeTemplateLiveByProductId(productId);
      if (assignedFeeTemplate.getIsstandardruleapbl()) {
        FeeRuleTable standardFeeRuleTable = feeRuleTableRepository.getStandardFeeRuleTable(1, 1);
        matchedFeeRule =
            getFeeRule(priceCatId, priceClassCode, standardFeeRuleTable.getFeeruletableid());
      }
    }
    return matchedFeeRule;
  }

  /**
   * Get the first matching fee rule. Transaction code defaulted to 'Purchase' and 'Sales' for now
   * as shopping cart will only contain items for sales.
   */
  private FeeRule getFeeRule(Long priceCatId, String priceClassCode, Long feeRuleTableId) {
    //populate price cat and price class
    PriceCategoryLive priceCategoryLive =
        priceCategoryLiveRepository.findOne(priceCatId);
    String priceCat = priceCategoryLive.getPricecategorynumber().toString();

    //retrieve matching FeeRule with ordersequence
    List<Object[]> matchingFeeRules =
        feeRuleRepository
            .findMatchingFeeRule("Purchase", "Sales",
                TenantContextHolder.getTenant().getProfileInfoId(), feeRuleTableId);

    //stream into map of <ordersequence, FeeRule>
    Map<Long, FeeRule> feeRuleHashMap =
        matchingFeeRules.stream().map(o -> o)
            .collect(Collectors.toMap(o -> (Long) o[0], o -> (FeeRule) o[1]));

    Map.Entry<Long, FeeRule> feeRuleEntry = feeRuleHashMap.entrySet().stream()
        .filter(e -> verifyPriceClassMatch(e.getValue().getPriceclass(), priceClassCode)
            && verifyPriceCategoryMatch(e.getValue().getPricecategory(), priceCat))
        .findFirst().orElse(null);

    logger.debug(" feeRuleEntry " + feeRuleEntry);

    return (feeRuleEntry != null ? feeRuleEntry.getValue() : null);
  }

  /**
   * Matches the price category.
   * @param currPriceCategory the curr price category
   * @param priceCategory the price category
   * @return true, if successful
   */
  private boolean verifyPriceCategoryMatch(String currPriceCategory, String priceCategory) {
    return (currPriceCategory.equals(FeeConstants.FEE_RULE_ALL_KEYWORD) ||
        Arrays.asList(currPriceCategory.split(",")).contains(priceCategory));
  }

  /**
   * Matches the price class.
   * @param currPriceClass the curr price class
   * @param priceClass the price class
   * @return true, if successful
   */
  private boolean verifyPriceClassMatch(String currPriceClass, String priceClass) {
    return (currPriceClass.equals(FeeConstants.FEE_RULE_ALL_KEYWORD) ||
        Arrays.asList(currPriceClass.split(",")).contains(priceClass));
  }

  /**
   * Matches the charge rule in accordance with the parameter given as input (if the parameter falls
   * within the range as specified in the fee charge rule).
   * @param feeChargeRule the fee charge rule
   * @param parameter the parameter
   * @return true, if successful
   */
  private boolean matchChargeRule(FeeChargeRule feeChargeRule, Double parameter) {
    boolean match = false;
    if (StringUtils.isNotBlank(feeChargeRule.getOperatorone()) && StringUtils
        .isNotBlank(feeChargeRule.getOperatortwo())) {
      match =
          verifyRange(feeChargeRule.getOperatorone().trim(), feeChargeRule.getOperatortwo().trim(),
              feeChargeRule.getOperandone().doubleValue(),
              feeChargeRule.getOperandtwo().doubleValue(), parameter);
    } else if (StringUtils.isNotBlank(feeChargeRule.getOperatorone()) && StringUtils
        .isBlank(feeChargeRule.getOperatortwo())) {
      match =
          verifyLimit(feeChargeRule.getOperatorone().trim(),
              parameter, feeChargeRule.getOperandone().doubleValue());
    } else if (StringUtils.isBlank(feeChargeRule.getOperatorone()) && StringUtils
        .isNotBlank(feeChargeRule.getOperatortwo())) {
      match =
          verifyLimit(feeChargeRule.getOperatortwo().trim(),
              feeChargeRule.getOperandtwo().doubleValue(), parameter);
    }
    return match;
  }

  /**
   * Verifies whether the charge corresponds to the the to amount with respect to the operator
   * passed as argument.
   * @param operator the operator to
   * @param firstAmt the to amt
   * @param secondAmt the charge
   * @return true, if successful
   */
  private boolean verifyLimit(String operator, double firstAmt, double secondAmt) {
    if (operator.equals(">")) {
      if (secondAmt > firstAmt) {
        return true;
      }
    } else if (operator.equals(">=")) {
      if (secondAmt >= firstAmt) {
        return true;
      }
    } else if (operator.equals("<")) {
      if (secondAmt < firstAmt) {
        return true;
      }
    } else if (operator.equals("<=")) {
      if (secondAmt <= firstAmt) {
        return true;
      }
    }
    return false;
  }

  /**
   * Verifies if the from amount and to amount corresponds to the charge with respect to the
   * operator given as input.
   * @param operatorFrom the operator from
   * @param operatorTo the operator to
   * @param frmAmt the frm amt
   * @param toAmt the to amt
   * @param charge the charge
   * @return true, if successful
   */
  private boolean verifyRange(String operatorFrom, String operatorTo, double frmAmt,
                              double toAmt, double charge) {
    if (operatorFrom.equals(">") && operatorTo.equals(">")) {
      if (frmAmt > charge && charge > toAmt) {
        return true;
      }
    } else if (operatorFrom.equals(">=") && operatorTo.equals(">")) {
      if (frmAmt >= charge && charge > toAmt) {
        return true;
      }
    } else if (operatorFrom.equals(">") && operatorTo.equals(">=")) {
      if (frmAmt > charge && charge >= toAmt) {
        return true;
      }
    } else if (operatorFrom.equals(">=") && operatorTo.equals(">=")) {
      if (frmAmt >= charge && charge >= toAmt) {
        return true;
      }
    } else if (operatorFrom.equals("<") && operatorTo.equals("<")) {
      if (frmAmt < charge && charge < toAmt) {
        return true;
      }
    } else if (operatorFrom.equals("<") && operatorTo.equals("<=")) {
      if (frmAmt < charge && charge <= toAmt) {
        return true;
      }
    } else if (operatorFrom.equals("<=") && operatorTo.equals("<")) {
      if (frmAmt <= charge && charge < toAmt) {
        return true;
      }
    } else if (operatorFrom.equals("<=") && operatorTo.equals("<=")) {
      if (frmAmt <= charge && charge <= toAmt) {
        return true;
      }
    }
    return false;
  }
}
