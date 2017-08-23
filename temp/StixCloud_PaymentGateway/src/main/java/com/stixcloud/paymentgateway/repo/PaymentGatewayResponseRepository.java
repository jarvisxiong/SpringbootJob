package com.stixcloud.paymentgateway.repo;

import com.stixcloud.paymentgateway.api.PaymentGatewayResponse;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by chongye on 28-Sep-16.
 */
@Repository
public interface PaymentGatewayResponseRepository
    extends CrudRepository<PaymentGatewayResponse, String> {
}

