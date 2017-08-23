package com.stixcloud.evoucher.rules;

import com.stixcloud.evoucher.constant.EVoucherEnum;
import com.stixcloud.evoucher.domain.EVoucherView;
import com.stixcloud.evoucher.domain.ProductPromoterVenue;
import org.easyrules.annotation.Action;
import org.easyrules.annotation.Condition;
import org.easyrules.annotation.Priority;
import org.easyrules.spring.SpringRule;
import org.springframework.context.MessageSource;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

/**
 * Created by dbthan on 13/10/2016.
 */

@SpringRule
public class OrganizationRule extends BaseEVoucherRule {

  private List<ProductPromoterVenue> productPromoterVenueList;

  public OrganizationRule() {
    super();
  }

  public OrganizationRule(MessageSource messageSource,
                          List<ProductPromoterVenue> productPromoterVenueList) {
    this.messageSource = messageSource;
    this.productPromoterVenueList = productPromoterVenueList;
  }

  @Priority
  public int getPriority() {
    return getEVoucherView().getPriority();
  }

  @Condition
  public boolean when() {
    return productPromoterVenueList != null && !productPromoterVenueList.isEmpty();
  }

  @Action
  public void then() throws EVoucherValidationException {

    setExecuted(Boolean.TRUE);
    EVoucherView eVoucher = getEVoucherView();
    List<Long> promoterIdList =
        productPromoterVenueList.stream().map(p -> p.getPromoterId()).collect(Collectors.toList());
    if (!promoterIdList.contains(Long.valueOf(eVoucher.getAttributeValue()))) {
      throw new EVoucherValidationException(messageSource.getMessage(
          "evoucher.rule.organization.error", null, Locale.getDefault()),
          EVoucherEnum.VALIDATION_RULE_ORG_FAIL.name());
    }
  }
}
