package com.sergioruy.sergiofoodapi.domain.repository;

import com.sergioruy.sergiofoodapi.domain.model.Order;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends CustomJpaRepository<Order, Long> {
}
