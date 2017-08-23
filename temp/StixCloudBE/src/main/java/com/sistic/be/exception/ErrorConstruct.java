package com.sistic.be.exception;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;


@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "httpStatus",
        "statusMessage",
        "message",
        "timestamp"
})
public class ErrorConstruct implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4907752756900570323L;
	
//	@JsonProperty("httpStatus")
    private Integer httpStatus;
//    @JsonProperty("statusMessage")
    private String statusMessage;
//    @JsonProperty("message")
    private String message;
//    @JsonProperty("timestamp")
    private String timestamp;
    
    public ErrorConstruct() {
    	super();
    }
    
    private ErrorConstruct(Builder builder) {
    	super();
		httpStatus = builder.httpStatus;
		statusMessage = builder.statusMessage;
		message = builder.message;
		timestamp = builder.timestamp;
	}
    
    // Getter Setter
	public Integer getHttpStatus() {
		return httpStatus;
	}
	public void setHttpStatus(Integer httpStatus) {
		this.httpStatus = httpStatus;
	}
	public String getStatusMessage() {
		return statusMessage;
	}
	public void setStatusMessage(String statusMessage) {
		this.statusMessage = statusMessage;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	
	@Override
	public String toString() {
		return "ErrorConstruct [httpStatus=" + httpStatus + ", statusMessage=" + statusMessage + ", message=" + message
				+ ", timestamp=" + timestamp + "]";
	}
	
	public static final class Builder {
		
	    private Integer httpStatus;
	    private String statusMessage;
	    private String message;
	    private String timestamp;

		public Builder() {
		}

		public Builder(ErrorConstruct copy) {
			this.httpStatus = copy.httpStatus;
			this.statusMessage = copy.statusMessage;
			this.message = copy.message;
			this.timestamp = copy.timestamp;
		}

		public Builder httpStatus(Integer httpStatus) {
			this.httpStatus = httpStatus;
			return this;
		}

		public Builder statusMessage(String statusMessage) {
			this.statusMessage = statusMessage;
			return this;
		}
		
		public Builder message(String message) {
			this.message = message;
			return this;
		}
		
		public Builder timestamp(String timestamp) {
			this.timestamp = timestamp;
			return this;
		}

		public ErrorConstruct build() {
			return new ErrorConstruct(this);
		}
	}
	
}
