package com.stixcloud.cart.repo;

import com.stixcloud.domain.ShoppingCart;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by chongye on 28-Sep-16.
 */
@Repository
public interface ShoppingCartRepository extends CrudRepository<ShoppingCart, String> {
}
