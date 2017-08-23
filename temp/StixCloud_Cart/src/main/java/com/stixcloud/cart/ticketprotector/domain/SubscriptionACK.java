package com.stixcloud.cart.ticketprotector.domain;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "subscriptionACK")
public class SubscriptionACK {

  private String contractNumber;
  private ErrorResponse error;

  public SubscriptionACK() {
  }

  public String getContractNumber() {
    return contractNumber;
  }

  public void setContractNumber(String contractNumber) {
    this.contractNumber = contractNumber;
  }

  public ErrorResponse getError() {
    return error;
  }

  public void setError(ErrorResponse error) {
    this.error = error;
  }

  @XmlRootElement(name = "error")
  @XmlAccessorType(XmlAccessType.FIELD)
  @XmlType(propOrder = {"errorCode", "errorMessage"})
  public static class ErrorResponse {

    private String errorCode;
    private String errorMessage;

    public String getErrorCode() {
      return errorCode;
    }

    public void setErrorCode(String errorCode) {
      this.errorCode = errorCode;
    }

    public String getErrorMessage() {
      return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
      this.errorMessage = errorMessage;
    }
  }
}
