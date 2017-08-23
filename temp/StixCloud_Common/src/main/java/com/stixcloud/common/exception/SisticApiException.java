package com.stixcloud.common.exception;

public class SisticApiException extends Exception {

  /**
   * 
   */
  private static final long serialVersionUID = 4455107215733308437L;
  private String errorCode;
  private String statusMessage;
  
  public SisticApiException(){
    super();
  }
  
  public SisticApiException(String errorCode, String statusMessage){
    this.errorCode = errorCode;
    this.statusMessage = statusMessage;
  }
  
  /**
   * @return the errorCode
   */
  public String getErrorCode() {
    return errorCode;
  }
  /**
   * @param errorCode the errorCode to set
   */
  public void setErrorCode(String errorCode) {
    this.errorCode = errorCode;
  }

  public String getStatusMessage() {
	  return statusMessage;
  }
  
  public void setStatusMessage(String statusMessage) {
	  this.statusMessage = statusMessage;
  }
}
