package com.stixcloud.cart.repo;

import com.stixcloud.domain.TransactionProduct;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by sengkai on 12/14/2016.
 */
public interface TransactionProductRepository extends CrudRepository<TransactionProduct, Long> {
}
