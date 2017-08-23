package com.stixcloud.cart.ticketprotector.repo;

import com.stixcloud.cart.ticketprotector.domain.TicketProtector;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketProtectorRepository extends CrudRepository<TicketProtector, Long> {
}
