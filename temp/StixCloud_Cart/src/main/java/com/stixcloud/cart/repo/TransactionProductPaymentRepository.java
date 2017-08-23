package com.stixcloud.cart.repo;

import com.stixcloud.domain.TransactionProductPayment;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by sengkai on 12/14/2016.
 */
public interface TransactionProductPaymentRepository extends
    CrudRepository<TransactionProductPayment, Long> {
}
