package com.stixcloud.evoucher.rules;

import com.stixcloud.evoucher.constant.EVoucherEnum;
import com.stixcloud.evoucher.domain.EVoucherView;
import com.stixcloud.evoucher.domain.ProductPaymentMethod;
import com.stixcloud.evoucher.domain.UserPaymentMethod;
import org.easyrules.annotation.Action;
import org.easyrules.annotation.Condition;
import org.easyrules.annotation.Priority;
import org.easyrules.spring.SpringRule;
import org.springframework.context.MessageSource;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.stream.Collectors;

@SpringRule
public class RedeemRestrictionsRule extends BaseEVoucherRule {

  private List<ProductPaymentMethod> productPaymentMethod;
  private List<UserPaymentMethod> userPaymentMethod;

  public RedeemRestrictionsRule() {
    super();
  }

  public RedeemRestrictionsRule(MessageSource messageSource,
                                List<ProductPaymentMethod> productPaymentMethod,
                                List<UserPaymentMethod> userPaymentMethod) {
    this.messageSource = messageSource;
    this.productPaymentMethod = productPaymentMethod;
    this.userPaymentMethod = userPaymentMethod;
  }

  @Priority
  public int getPriority() {
    return getEVoucherView().getPriority();
  }

  @Condition
  public boolean when() {
    return productPaymentMethod != null && userPaymentMethod != null
        && !productPaymentMethod.isEmpty() && !userPaymentMethod.isEmpty();
  }

  @Action
  public void then() throws EVoucherValidationException {

    setExecuted(Boolean.TRUE);
    EVoucherView eVoucher = getEVoucherView();
    String errorMessageKey = null;
    String errorConstant = null;
    Set<String> paymentMethodCodes = null;
    switch (eVoucher.getAttributeValue()) {
      case "RESTRICT_WITHIN":
        List<ProductPaymentMethod> combinedPaymentMethod =
            retainCommonEVoucherPaymentMethods(productPaymentMethod, userPaymentMethod);
        paymentMethodCodes =
            combinedPaymentMethod.stream().map(p -> p.getPaymentMethodCode())
                .collect(Collectors.toSet());
        if (!paymentMethodCodes.contains(eVoucher.geteVoucherType())) {
          errorMessageKey = "evoucher.rule.redeem.restriction.within.error";
          errorConstant = EVoucherEnum.VALIDATION_RULE_REDEEM_RESTRICTIONS_WITHIN_FAIL.name();
        }
        break;
      case "RESTRICT_OUTSIDE":
        paymentMethodCodes =
            excludeCommonEVoucherPaymentMethods(productPaymentMethod, userPaymentMethod);
        if (!paymentMethodCodes.contains(eVoucher.geteVoucherType())) {
          errorMessageKey = "evoucher.rule.redeem.restriction.outside.error";
          errorConstant = EVoucherEnum.VALIDATION_RULE_REDEEM_RESTRICTIONS_OUTSIDE_FAIL.name();
        }

        break;
      case "RESTRICT_ALL":
        errorMessageKey = "evoucher.rule.redeem.restriction.all.error";
        errorConstant = EVoucherEnum.VALIDATION_RULE_REDEEM_RESTRICTIONS_ALL_FAIL.name();
        break;
      case "RESTRICT_NONE":
        return;
    }
    if (errorMessageKey != null) {
      throw new EVoucherValidationException(messageSource.getMessage(errorMessageKey, null,
          Locale.getDefault()), errorConstant);
    }
  }

  private List<ProductPaymentMethod> retainCommonEVoucherPaymentMethods(
      List<ProductPaymentMethod> productPaymentMethods,
      List<UserPaymentMethod> userPaymentMethods) {

    List<ProductPaymentMethod> combinedPaymentMethod = new ArrayList<ProductPaymentMethod>();
    productPaymentMethods.forEach(item -> {
      userPaymentMethods.forEach(iteme -> {
        if (item.getPaymentMethodId().equals(iteme.getPaymentMethodId())) {
          combinedPaymentMethod.add(item);
        }
      });
    });
    return combinedPaymentMethod;
  }

  private Set<String> excludeCommonEVoucherPaymentMethods(
      List<ProductPaymentMethod> productPaymentMethods,
      List<UserPaymentMethod> userPaymentMethods) {

    Set<String> productPaymentMethodCodes =
        productPaymentMethods.stream().map(p -> p.getPaymentMethodCode())
            .collect(Collectors.toSet());
    Set<String> userPaymentMethodCodes =
        userPaymentMethods.stream().map(p -> p.getPaymentMethodCode()).collect(Collectors.toSet());

    Set<String> productPaymentMethodCodesOriginal = new HashSet<>(productPaymentMethodCodes);

    productPaymentMethodCodes.removeAll(userPaymentMethodCodes);
    userPaymentMethodCodes.removeAll(productPaymentMethodCodesOriginal);
    productPaymentMethodCodes.addAll(userPaymentMethodCodes);
    return productPaymentMethodCodes;
  }
}
