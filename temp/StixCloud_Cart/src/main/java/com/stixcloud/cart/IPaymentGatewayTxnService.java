package com.stixcloud.cart;

import com.stixcloud.domain.PaymentGatewayTxn;

import java.util.List;

public interface IPaymentGatewayTxnService {
  public void createPaymentGateway(List<PaymentGatewayTxn> paymentGatewayTxnList);
}
