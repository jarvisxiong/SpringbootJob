package com.stixcloud.cart.rules.precommit;

import com.stixcloud.cart.ValidateCartException;
import com.stixcloud.cart.rules.BaseCartRule;
import com.stixcloud.evoucher.api.EVoucherValidation;
import com.stixcloud.evoucher.api.EVoucherValidationRequest;
import com.stixcloud.evoucher.service.EVoucherRunEngine;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.easyrules.annotation.Action;
import org.easyrules.annotation.Condition;
import org.easyrules.annotation.Priority;
import org.easyrules.spring.SpringRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

import java.util.List;
import java.util.stream.Collectors;
import javax.money.Monetary;

/**
 * Created by sengkai on 11/28/2016.
 */
@SpringRule
public class EVoucherRule extends BaseCartRule {
  private static final Logger logger = LogManager.getLogger(EVoucherRule.class);
  @Autowired
  private MessageSource messageSource;

  @Autowired
  private EVoucherRunEngine eVoucherRunEngine;

  private List<String> evoucherIdList;
  private EVoucherValidationRequest eVoucherValidationRequest;
  private String cartGuid;
  private Long patronProfileInfoId;


  public void setEvoucherIdList(List<String> evoucherIdList) {
    this.evoucherIdList = evoucherIdList;
  }

  public void seteVoucherValidationRequest(
      EVoucherValidationRequest eVoucherValidationRequest) {
    this.eVoucherValidationRequest = eVoucherValidationRequest;
  }

  public void setCartGuid(String cartGuid) {
    this.cartGuid = cartGuid;
  }

  public void setPatronProfileInfoId(Long patronProfileInfoId) {
    this.patronProfileInfoId = patronProfileInfoId;
  }

  @Priority
  public int getPriority() {
    return Integer.MIN_VALUE;
  }

  @Condition
  public boolean when() {
    return isCartNotEmpty();
  }

  @Action
  public void then() throws ValidateCartException {
    this.setExecuted(true);

    //Set the list intoEVoucherValidation weird way to do it
    eVoucherValidationRequest = new EVoucherValidationRequest();
    eVoucherValidationRequest.setEvoucherIdList(evoucherIdList);

    List<EVoucherValidation>
        eVoucherValidationList =
        eVoucherRunEngine
            .run(eVoucherValidationRequest, cartGuid, patronProfileInfoId,
                Monetary.getCurrency(LocaleContextHolder.getLocale()));

    if (eVoucherValidationList.isEmpty()) {
      throw new ValidateCartException(
          messageSource.getMessage("precommit.error.eVoucherValidation", null, LocaleContextHolder
              .getLocale()));
    }

    boolean
        failedValidation =
        eVoucherValidationList.stream()
            .anyMatch(validation -> !validation.isValidationResult());
    if (failedValidation) {
      List<EVoucherValidation> eVoucherValidations = eVoucherValidationList.stream()
          .filter(validation -> !validation.isValidationResult())
          .collect(Collectors.toList());
      logger.error(eVoucherValidations);
      throw new ValidateCartException(
          messageSource.getMessage("precommit.error.eVoucherValidation", null, LocaleContextHolder
              .getLocale()));
    }
  }
}
