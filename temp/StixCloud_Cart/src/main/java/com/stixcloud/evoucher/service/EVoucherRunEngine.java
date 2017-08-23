package com.stixcloud.evoucher.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.money.CurrencyUnit;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import com.stixcloud.cart.IShoppingCartService;
import com.stixcloud.common.config.multitenant.TenantContextHolder;
import com.stixcloud.evoucher.api.EVoucherValidation;
import com.stixcloud.evoucher.api.EVoucherValidationRequest;
import com.stixcloud.evoucher.constant.EVoucherConstants;
import com.stixcloud.evoucher.constant.EVoucherEnum;
import com.stixcloud.evoucher.domain.EVoucherCreditCardRegex;
import com.stixcloud.evoucher.domain.EVoucherView;
import com.stixcloud.evoucher.domain.ProductPaymentMethod;
import com.stixcloud.evoucher.domain.ProductPromoterVenue;
import com.stixcloud.evoucher.domain.UserPaymentMethod;
import com.stixcloud.evoucher.types.EvoucherFactory;
import com.stixcloud.evoucher.types.IEvoucher;

@Service
public class EVoucherRunEngine {
  private static final Logger LOGGER = LogManager.getLogger(EVoucherRunEngine.class);

  @Autowired
  private MessageSource messageSource;
  @Autowired
  private IEVoucherService eVoucherService;
  @Autowired
  private IShoppingCartService shoppingCartService;

  private EvoucherFactory evoucherFactory;
  private IEvoucher evoucher;
  private EVoucherValidation validationResult;

  /**
   *
   * @param eVoucherValidationRequest
   * @param eVoucherValidationList
   */
  public List<EVoucherValidation> run(EVoucherValidationRequest eVoucherValidationRequest,
      String cartGuid, Long patronProfileInfoId, CurrencyUnit currencyUnit) {
    Long profileInfoId = TenantContextHolder.getTenant().getProfileInfoId();

    List<EVoucherValidation> eVoucherValidationList = new ArrayList<>();
    List<String> eVoucherIdList = eVoucherValidationRequest.getEvoucherIdList();

    List<String> uniqueEvoucherIdList =
        new ArrayList<String>(eVoucherIdList.stream().collect(Collectors.toSet()));

    List<Long> productIdList = shoppingCartService.getProductIdsByCartGuid(cartGuid);
    List<EVoucherView> eVoucherViewList = eVoucherService.getEVoucherView(uniqueEvoucherIdList);
    List<EVoucherView> eVoucherViewBaseList = filterBaseEVoucher(eVoucherViewList);
    List<EVoucherCreditCardRegex> creditCardRegexList =
        eVoucherService.getCreditCardRegex(uniqueEvoucherIdList);
    List<ProductPaymentMethod> productPaymentMethod =
        eVoucherService.getProductPaymentMethod(productIdList);
    List<UserPaymentMethod> userPaymentMethod = eVoucherService.getUserPaymentMethod(profileInfoId);
    List<ProductPromoterVenue> productPromoterVenueList =
        eVoucherService.getPromoterAndVenueByProductIdList(productIdList);

    Map<Long, List<ProductPaymentMethod>> productPaymentMethodMap = productPaymentMethod.stream()
        .collect(Collectors.groupingBy(ProductPaymentMethod::getProductId));
    Map<Long, List<ProductPromoterVenue>> productPromoterVenueMap = productPromoterVenueList
        .stream().collect(Collectors.groupingBy(ProductPromoterVenue::getProductId));

    Map<String, Long> evoucherQtyMap = eVoucherViewBaseList.stream()
        .collect(Collectors.groupingBy(EVoucherView::geteVoucherType, Collectors.counting()));
    boolean isCreditCardInputted = StringUtils.isBlank(eVoucherValidationRequest.getCreditCardNo())
        ? Boolean.FALSE : Boolean.TRUE;
    for (String evoucherId : uniqueEvoucherIdList) {
      List<EVoucherView> validationRuleList = eVoucherViewList.stream()
          .filter(e -> e.geteVoucherId().equals(evoucherId)).collect(Collectors.toList());
      if (validationRuleList.isEmpty()) {
        validationResult = new EVoucherValidation();
        validationResult.setEVoucherID(evoucherId);
        validationResult.setValidationResult(Boolean.FALSE);
        validationResult.setValidationConstant(EVoucherEnum.VALIDATION_BASIC_FAIL.name());
        eVoucherValidationList.add(validationResult);
        continue;
      }

      List<EVoucherCreditCardRegex> ccRegex = creditCardRegexList.stream()
          .filter(p -> p.getEvoucherId().equals(evoucherId)).collect(Collectors.toList());
      String evoucherType = validationRuleList.get(0).geteVoucherType();
      evoucherFactory = new EvoucherFactory(messageSource, validationRuleList, ccRegex,
          eVoucherValidationRequest.getCreditCardNo(), userPaymentMethod, evoucherQtyMap,
          eVoucherViewList, patronProfileInfoId, eVoucherValidationRequest.getPaymentMethodCode());

      try {
        evoucher = evoucherFactory.getEvoucherType(evoucherType);
      } catch (Exception e) {
        e.printStackTrace();
        validationResult = new EVoucherValidation(validationRuleList.get(0), currencyUnit);
        validationResult.setValidationResult(Boolean.FALSE);
        validationResult.setValidationConstant(EVoucherConstants.EVOUCHER_TYPE_NOT_IMPLEMENTED);
        eVoucherValidationList.add(validationResult);
        continue;
      }
      validationResult = evoucher.validateBasicRule();
      if (!validationResult.isValidationResult()) {
        eVoucherValidationList.add(validationResult);
        continue;
      }
      if (isCreditCardInputted) {
        validationResult = evoucher.validateAtPreCommitOrder();
      } else {
        for (Long productId : productIdList) {
          evoucherFactory.setProductPaymentMethod(productPaymentMethodMap.get(productId));
          evoucherFactory.setProductPromoterVenueList(productPromoterVenueMap.get(productId));
          try {
            evoucher = evoucherFactory.getEvoucherType(evoucherType);
          } catch (Exception e) {
            e.printStackTrace();
            validationResult = new EVoucherValidation(validationRuleList.get(0), currencyUnit);
            validationResult.setValidationResult(Boolean.FALSE);
            validationResult.setValidationConstant(EVoucherConstants.EVOUCHER_TYPE_NOT_IMPLEMENTED);
            break;
          }

          validationResult = evoucher.validateAtApplyEvoucher();
          if (!validationResult.isValidationResult()) {
            break;
          }

        }
      }
      eVoucherValidationList.add(validationResult);
    }

    // Fill error for duplicated e-voucher
    Map<String, EVoucherValidation> evoucherValidationMap = eVoucherValidationList.stream()
        .collect(Collectors.toMap(EVoucherValidation::getEVoucherID, Function.identity()));

    Set<String> evoucherIdSet = new HashSet<>();
    int eVoucherListSize = eVoucherIdList.size();

    List<EVoucherValidation> result = new ArrayList<>();
    result.addAll(Collections.nCopies(eVoucherListSize, null));
    eVoucherValidationList.stream().forEach(e -> {
      String evoucherId = e.getEVoucherID();
      result.remove(eVoucherIdList.indexOf(evoucherId));
      result.add(eVoucherIdList.indexOf(evoucherId), e);
      evoucherIdSet.add(evoucherId);
    });

    for (int i = 0; i < eVoucherListSize; i++) {
      EVoucherValidation validationResult = null;
      String e = eVoucherIdList.get(i);

      if (null == result.get(i)) {
        validationResult = new EVoucherValidation();
        if (evoucherIdSet.contains(e)) {
          try {
            validationResult = (EVoucherValidation) evoucherValidationMap.get(e).clone();
          } catch (CloneNotSupportedException cloneEx) {
            LOGGER.error("Cannot clone evoucher validation with id: " + e, cloneEx);
            validationResult = new EVoucherValidation();
            validationResult.setEVoucherID(e);
          }
          validationResult.setValidationResult(Boolean.FALSE);
          validationResult.setValidationConstant(EVoucherConstants.EVOUCHER_DUPLICATED);
          eVoucherValidationList.add(i, validationResult);
        }
        result.remove(i);
        result.add(i, validationResult);
      }

      // if(StringUtils.isBlank(e)) {
      // result.remove(i);
      // result.add(new EVoucherValidation());
      // } else if (!evoucherIdSet.contains(e) && null == result.get(i)) {
      // try {
      // validationResult = (EVoucherValidation) evoucherValidationMap.get(e).clone();
      // } catch (CloneNotSupportedException cloneEx) {
      // LOGGER.error("Cannot clone evoucher validation with id: " + e, cloneEx);
      // validationResult = new EVoucherValidation();
      // validationResult.setEVoucherID(e);
      // }
      // validationResult.setValidationResult(Boolean.FALSE);
      // validationResult.setValidationConstant(EVoucherConstants.EVOUCHER_DUPLICATED);
      // eVoucherValidationList.add(i, validationResult);
      // }
    }

    return result;

  }

  /*
   * private boolean checkMixCart(EVoucherView eVoucherView, List<EVoucherValidation>
   * eVoucherValidationList, Long patronAcct, List<Long> productIdList) { boolean result = false; if
   * (eVoucherView.getPatronProfileId() != null) { if
   * (!eVoucherView.getPatronProfileId().equals(patronAcct) && !eVoucherView.isGiftVoucher()) {
   * EVoucherValidation eVoucherValidation = new EVoucherValidation(eVoucherView.geteVoucherId(),
   * eVoucherView.getRedeemValue().toString(), eVoucherView.geteVoucherType(), false,
   * EVoucherConstants.VALIDATION_VIOLATE_OWNERSHIP.name());
   * eVoucherValidationList.add(eVoucherValidation); result = true; } else { boolean isWhiteCard =
   * eVoucherService.isMembershipWhiteCard(patronAcct); boolean isEsplanade =
   * eVoucherService.isEsplanadeOrganization(eVoucherView.getOrganizationId()); if (isEsplanade &&
   * isWhiteCard) { List<Long> productList =
   * eVoucherService.getProductOfNoneEsplanade(productIdList); if (!productList.isEmpty()) {
   * EVoucherValidation eVoucherValidation = new EVoucherValidation(eVoucherView.geteVoucherId(),
   * eVoucherView.getRedeemValue().toString(), eVoucherView.geteVoucherType(), false,
   * EVoucherConstants.VALIDATION_VIOLATE_MIX_CARD.name());
   * eVoucherValidationList.add(eVoucherValidation); result = true; } } } } return result; }
   */

  /**
   *
   * @param eVoucherList
   * @return
   */
  private List<EVoucherView> filterBaseEVoucher(List<EVoucherView> eVoucherList) {

    List<EVoucherView> result = new ArrayList<EVoucherView>();
    eVoucherList.forEach(p -> {
      if (!result.contains(p)) {
        result.add(p);
      }
    });

    return result;
  }

}
