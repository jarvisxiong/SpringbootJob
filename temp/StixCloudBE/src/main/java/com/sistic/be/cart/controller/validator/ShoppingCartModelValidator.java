package com.sistic.be.cart.controller.validator;

import com.sistic.be.cart.model.ShoppingCartModel;
import com.sistic.be.exception.ShoppingCartModelException;
import com.sistic.be.exception.constant.ValidationConsts;

/**
 * Validates the ShoppingCartModel to be valid for thymeleaf to display without parse errors
 * @author jianhong
 *
 */

public class ShoppingCartModelValidator {
	
	public static void validate(ShoppingCartModel shoppingCartModel) throws ShoppingCartModelException {
		if (shoppingCartModel.getLineItemList() == null) {
			throw new ShoppingCartModelException(ValidationConsts.NO_LINE_ITEMS);
		}
		else if (shoppingCartModel.getCommonDeliveryMethod() == null) {
			throw new ShoppingCartModelException(ValidationConsts.NO_COMMON_DELIVERY);
		}
		else if (shoppingCartModel.getCommonPaymentMethod() == null) {
			throw new ShoppingCartModelException(ValidationConsts.NO_COMMON_PAYMENT); 
		}
		else if (shoppingCartModel.getCurrencyUnit() == null) {
			throw new ShoppingCartModelException(ValidationConsts.NO_CURRENCY_UNIT);
		}
	}

}
