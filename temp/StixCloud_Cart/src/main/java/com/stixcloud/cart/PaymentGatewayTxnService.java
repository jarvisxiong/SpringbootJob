package com.stixcloud.cart;

import com.stixcloud.cart.repo.PaymentGatewayTxnRepository;
import com.stixcloud.domain.PaymentGatewayTxn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(propagation = Propagation.REQUIRES_NEW)
public class PaymentGatewayTxnService implements IPaymentGatewayTxnService {

  @Autowired
  PaymentGatewayTxnRepository paymentGatewayTxnRepository;

  @Override
  public void createPaymentGateway(List<PaymentGatewayTxn> paymentGatewayTxnList) {
    paymentGatewayTxnRepository.save(paymentGatewayTxnList);

  }

}
