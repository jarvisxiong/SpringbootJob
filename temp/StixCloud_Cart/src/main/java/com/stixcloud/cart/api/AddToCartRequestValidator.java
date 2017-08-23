package com.stixcloud.cart.api;

import com.stixcloud.cart.InventoryConstants;
import org.apache.commons.lang3.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * Created by chongye on 8/11/2016.
 */
public class AddToCartRequestValidator implements Validator {
  @Override
  public boolean supports(Class<?> clazz) {
    return AddToCartRequest.class.equals(clazz);
  }

  @Override
  public void validate(Object target, Errors e) {
    ValidationUtils.rejectIfEmpty(e, "productId", "addToCartRequest.error.productId");
    ValidationUtils.rejectIfEmpty(e, "priceCatId", "addToCartRequest.error.priceCatId");
    ValidationUtils.rejectIfEmpty(e, "mode", "addToCartRequest.error.mode");

    AddToCartRequest r = (AddToCartRequest) target;
    if (StringUtils.isNotBlank(r.getMode())) {
      if (r.getMode().equals(InventoryConstants.SECTION_TYPE_RS.getName())) {
        ValidationUtils
            .rejectIfEmpty(e, "priceClassMap", "addToCartRequest.error.priceClassMap");
        ValidationUtils
            .rejectIfEmpty(e, "inventoryList", "addToCartRequest.error.inventoryList");
      } else if (r.getMode().equals(InventoryConstants.SECTION_TYPE_GA.getName())) {
        ValidationUtils.rejectIfEmpty(e, "seatSectionId", "addToCartRequest.error.seatSectionId");
      }
    }
  }
}
