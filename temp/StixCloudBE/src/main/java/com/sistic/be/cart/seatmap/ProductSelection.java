/**
 * 
 */
package com.sistic.be.cart.seatmap;

import java.io.Serializable;

/**
 * @author jianhong
 *
 */

@Deprecated
public class ProductSelection implements Serializable {

	private static final long serialVersionUID = 4486512549026605904L;
	
	private String internetContentCode;
	private Long productId;
	private String mode;
	
	public String getInternetContentCode() {
		return internetContentCode;
	}
	public void setInternetContentCode(String internetContentCode) {
		this.internetContentCode = internetContentCode;
	}
	public Long getProductId() {
		return productId;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
	}
	public String getMode() {
		return mode;
	}
	public void setMode(String mode) {
		this.mode = mode;
	}
	
}
