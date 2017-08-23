package com.stixcloud.patron.api;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.stixcloud.common.exception.JsonResponse;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "httpStatus", "accountNo", "statusMessage"})
public class PatronCreationResponse extends JsonResponse implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@JsonProperty("accountNo")
	private String accountNo;
	
	public PatronCreationResponse(){
		super();
	}
	
	public PatronCreationResponse(String httpStatus, String accountNo, String statusMessage){
		this.setAccountNo(accountNo);
		this.setHttpStatus(httpStatus);
		this.setStatusMessage(statusMessage);
	}
	
	public String getAccountNo() {
		return accountNo;
	}
	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}
}
