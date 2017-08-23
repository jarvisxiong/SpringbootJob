package com.sistic.be.cart.api.model;

public class PaymentQueueResponse extends JsonResponse {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7792638057298753516L;
	
	private Boolean proceed;

	public Boolean getProceed() {
		return proceed;
	}

	public void setProceed(Boolean proceed) {
		this.proceed = proceed;
	}


}
