/**
 * 
 */
package com.sistic.be.cart.model;

import java.io.Serializable;

/**
 * @author jianhong
 *
 */
public class UserSelectedPaymentMethod implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2200456013436145230L;
	private PaymentMethodInfo selectedPaymentMethod;

	/**
	 * @return the selectedPaymentMethod
	 */
	public PaymentMethodInfo getSelectedPaymentMethod() {
		return selectedPaymentMethod;
	}

	/**
	 * @param selectedPaymentMethod the selectedPaymentMethod to set
	 */
	public void setSelectedPaymentMethod(PaymentMethodInfo selectedPaymentMethod) {
		this.selectedPaymentMethod = selectedPaymentMethod;
	}
}
