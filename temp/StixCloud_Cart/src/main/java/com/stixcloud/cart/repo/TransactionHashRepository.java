package com.stixcloud.cart.repo;

import com.stixcloud.domain.TransactionHash;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by sengkai on 12/14/2016.
 */
public interface TransactionHashRepository extends CrudRepository<TransactionHash, Long> {
}
