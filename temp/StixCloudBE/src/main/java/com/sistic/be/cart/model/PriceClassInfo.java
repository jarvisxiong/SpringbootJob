/**
 * 
 */
package com.sistic.be.cart.model;

import java.io.Serializable;

/**
 * @author jianhong
 *
 */
public class PriceClassInfo implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8230231536096452472L;
	
	private String priceClassName;
	private String priceClassCode;
	
	public String getPriceClassName() {
		return priceClassName;
	}
	public void setPriceClassName(String priceClassName) {
		this.priceClassName = priceClassName;
	}
	public String getPriceClassCode() {
		return priceClassCode;
	}
	public void setPriceClassCode(String priceClassCode) {
		this.priceClassCode = priceClassCode;
	}
	
}
