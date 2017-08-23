/**
 * 
 */
package com.sistic.be.cart.api.model;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CartGuidResponse extends JsonResponse {

	private static final long serialVersionUID = 4256951869511933514L;
	
	@JsonProperty("cartGuid")
    private String cartGuid;
	
	public CartGuidResponse() {
		super();
	}
	
	public CartGuidResponse(Integer httpStatus, Integer status, String statusMessage, String cartGuid) {
		super(httpStatus, status, statusMessage);
		this.cartGuid = cartGuid;
    }

	@JsonProperty("cartGuid")
	public String getCartGuid() {
		return cartGuid;
	}

	public void setCartGuid(String cartGuid) {
		this.cartGuid = cartGuid;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		ToStringBuilder toStringBuilder = new ToStringBuilder(this, ToStringStyle.JSON_STYLE);
		toStringBuilder.append("cartGuid", cartGuid);
		return toStringBuilder.toString();
	}
	
}