package com.stixcloud.evoucher.api;

import com.stixcloud.cart.api.JsonResponse;
import com.stixcloud.evoucher.rules.EVoucherValidationException;
import com.stixcloud.evoucher.service.EVoucherRunEngine;
import com.stixcloud.policyagent.util.PAUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.javamoney.moneta.Money;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Locale;
import javax.money.CurrencyUnit;
import javax.money.Monetary;
import javax.money.MonetaryAmount;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by dbthan on 10/17/2016.
 */

@RestController
@CrossOrigin
@RequestMapping("/{tenant}")
public class EvoucherValidationController {

  private static final Logger LOGGER = LogManager.getLogger(EvoucherValidationController.class);

  @Autowired
  private MessageSource messageSource;

  @Autowired
  private EVoucherRunEngine eVoucherRunEngine;

  @Value("${properties.max.evouchers}")
  private int maxEvouchers;

  @RequestMapping(value = "/cart/evoucher/validation/{cartGuid}", method = RequestMethod.POST)
  public EvoucherValidationResponse validate(HttpServletResponse response, Locale locale,
                                             @RequestBody(required = true) EVoucherValidationRequest eVoucherValidationRequest,
                                             @PathVariable(value = "cartGuid", required = true) String cartGuid)
      throws EVoucherValidationException {
    Long patronProfileInfoId = PAUtil.getPatronId();
    List<String> evoucherList = eVoucherValidationRequest.getEvoucherIdList();
    boolean needToValidateEvoucher = evoucherList != null;
    if (needToValidateEvoucher) {
      evoucherList.removeIf(v -> StringUtils.isBlank(v));

      needToValidateEvoucher = !evoucherList.isEmpty();
    }

    if (!needToValidateEvoucher) {
      throw new EVoucherValidationException(messageSource.getMessage("evoucher.list.blank.error",
          null, LocaleContextHolder.getLocale()));
    }

    CurrencyUnit currencyUnit = Monetary.getCurrency(locale);
    MonetaryAmount totalRedemmedValue = Money.of(0, currencyUnit);
    EvoucherValidationResponse evoucherResponse = new EvoucherValidationResponse();
    if (eVoucherValidationRequest.getEvoucherIdList().size() > maxEvouchers) {
      evoucherResponse.setExceedMaxQtyAllowed(true);
      return evoucherResponse;
    }

    List<EVoucherValidation> eVoucherValidationList = eVoucherRunEngine
        .run(eVoucherValidationRequest, cartGuid, patronProfileInfoId, currencyUnit);
    for (EVoucherValidation evoucher : eVoucherValidationList) {
      if (evoucher.isValidationResult()) {
        totalRedemmedValue = totalRedemmedValue.add(evoucher.getRedeemValue());
      }
    }

    evoucherResponse.setExceedMaxQtyAllowed(false);
    evoucherResponse.setValidationResultList(eVoucherValidationList);
    evoucherResponse.setTotalRedeemValue(totalRedemmedValue);
    return evoucherResponse;
  }

  @ExceptionHandler(MissingServletRequestParameterException.class)
  @ResponseBody
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public JsonResponse handleMissingParameterException(MissingServletRequestParameterException e,
                                                      Locale locale) {
    LOGGER.error(e.getMessage(), e);
    return new JsonResponse.Builder().httpStatus(HttpStatus.BAD_REQUEST.toString())
        .statusMessage(e.getMessage()).build();
  }

  @ExceptionHandler(EVoucherValidationException.class)
  @ResponseBody
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public JsonResponse handleBadRequestException(EVoucherValidationException e,
                                                Locale locale) {
    LOGGER.error(e.getMessage(), e);
    return new JsonResponse.Builder().httpStatus(HttpStatus.BAD_REQUEST.toString())
        .statusMessage(e.getMessage()).build();
  }
}
