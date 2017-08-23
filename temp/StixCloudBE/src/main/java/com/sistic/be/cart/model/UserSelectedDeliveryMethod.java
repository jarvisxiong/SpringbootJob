/**
 * 
 */
package com.sistic.be.cart.model;

import java.io.Serializable;

/**
 * @author jianhong
 *
 */
public class UserSelectedDeliveryMethod implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4269751033584635008L;
	private DeliveryMethodInfo selectedDeliveryMethod;
	private String selectedDeliveryMethodCode;
	
	
	/**
	 * @return the selectedDeliveryMethod
	 */
	public DeliveryMethodInfo getSelectedDeliveryMethod() {
		return selectedDeliveryMethod;
	}
	/**
	 * @param selectedDeliveryMethod the selectedDeliveryMethod to set
	 */
	public void setSelectedDeliveryMethod(DeliveryMethodInfo selectedDeliveryMethod) {
		this.selectedDeliveryMethod = selectedDeliveryMethod;
	}
	/**
	 * @return the selectedDeliveryMethodCode
	 */
	public String getSelectedDeliveryMethodCode() {
		return selectedDeliveryMethodCode;
	}
	/**
	 * @param selectedDeliveryMethodCode the selectedDeliveryMethodCode to set
	 */
	public void setSelectedDeliveryMethodCode(String selectedDeliveryMethodCode) {
		this.selectedDeliveryMethodCode = selectedDeliveryMethodCode;
	}

}
