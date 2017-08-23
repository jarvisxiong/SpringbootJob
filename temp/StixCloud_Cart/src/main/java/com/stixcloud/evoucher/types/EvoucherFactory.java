package com.stixcloud.evoucher.types;

import com.stixcloud.evoucher.constant.EVoucherConstants;
import com.stixcloud.evoucher.domain.EVoucherCreditCardRegex;
import com.stixcloud.evoucher.domain.EVoucherView;
import com.stixcloud.evoucher.domain.ProductPaymentMethod;
import com.stixcloud.evoucher.domain.ProductPromoterVenue;
import com.stixcloud.evoucher.domain.UserPaymentMethod;
import org.springframework.context.MessageSource;

import java.util.List;
import java.util.Locale;
import java.util.Map;

public class EvoucherFactory {

  private MessageSource messageSource;
  private List<EVoucherView> validationRuleList;
  private List<EVoucherCreditCardRegex> ccRegex;
  private String creditCardNo;
  private List<ProductPaymentMethod> productPaymentMethod;
  private List<UserPaymentMethod> userPaymentMethod;
  private Map<String, Long> evoucherQtyMap;
  private List<EVoucherView> eVoucherViewList;
  private List<ProductPromoterVenue> productPromoterVenueList;
  private Long patronAccountId;
  private String paymentMethod;

  public EvoucherFactory(MessageSource messageSource, List<EVoucherView> validationRuleList,
                         List<EVoucherCreditCardRegex> ccRegex, String creditCardNo,
                         List<UserPaymentMethod> userPaymentMethod,
                         Map<String, Long> evoucherQtyMap,
                         List<EVoucherView> eVoucherViewList, Long patronAccountId,
                         String paymentMethod) {
    this.messageSource = messageSource;
    this.validationRuleList = validationRuleList;
    this.ccRegex = ccRegex;
    this.creditCardNo = creditCardNo;
    this.userPaymentMethod = userPaymentMethod;
    this.evoucherQtyMap = evoucherQtyMap;
    this.eVoucherViewList = eVoucherViewList;
    this.patronAccountId = patronAccountId;
    this.paymentMethod = paymentMethod;

  }

  public void setProductPaymentMethod(List<ProductPaymentMethod> productPaymentMethod) {
    this.productPaymentMethod = productPaymentMethod;
  }


  public void setProductPromoterVenueList(List<ProductPromoterVenue> productPromoterVenueList) {
    this.productPromoterVenueList = productPromoterVenueList;
  }

  public IEvoucher getEvoucherType(String evoucherType) throws Exception {
    IEvoucher result = null;
    switch (evoucherType) {
      case EVoucherConstants.DBS_E_VOUCHER:
        result =
            new DbsEvoucher(messageSource, ccRegex, creditCardNo, productPaymentMethod,
                userPaymentMethod, evoucherQtyMap, eVoucherViewList, productPromoterVenueList);
        break;
      case EVoucherConstants.OCBC_E_VOUCHER:
        result =
            new OcbcEvoucher(messageSource, paymentMethod, ccRegex, creditCardNo,
                productPaymentMethod, userPaymentMethod, evoucherQtyMap, eVoucherViewList);
        break;
      case EVoucherConstants.ESPLANADE_E_VOUCHER:
        result =
            new EsplanadeEvoucher(messageSource, productPaymentMethod, userPaymentMethod,
                evoucherQtyMap, eVoucherViewList, productPromoterVenueList, patronAccountId);
        break;
      case EVoucherConstants.MASTER_E_VOUCHER:
        result =
            new MasterEvoucher(messageSource, paymentMethod, ccRegex, creditCardNo,
                productPaymentMethod, userPaymentMethod, evoucherQtyMap, eVoucherViewList);
        break;
      case EVoucherConstants.SISTIC_E_VOUCHER:
        result = new SisticEvoucher(messageSource, productPaymentMethod, userPaymentMethod);
        break;
      case EVoucherConstants.NGS_E_VOUCHER:
        result = new NgsEvoucher(messageSource, productPaymentMethod, userPaymentMethod);
        break;
      case EVoucherConstants.NG_RENEWAL_EVOUCHER:
        result = new NgRenewalEvoucher(messageSource, productPaymentMethod, userPaymentMethod);
        break;
      default:
        throw new Exception(messageSource.getMessage("evoucher.generate.error", new Object[]{
            eVoucherViewList.get(0).geteVoucherId(), evoucherType}, Locale.getDefault()));
    }

    if (result != null) {
      result.setEvoucher(validationRuleList);
    }

    return result;
  }
}
