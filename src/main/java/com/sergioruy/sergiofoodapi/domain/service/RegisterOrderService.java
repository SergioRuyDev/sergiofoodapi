package com.sergioruy.sergiofoodapi.domain.service;

import com.sergioruy.sergiofoodapi.domain.exception.OrderNotFoundException;
import com.sergioruy.sergiofoodapi.domain.model.Order;
import com.sergioruy.sergiofoodapi.domain.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegisterOrderService {

    @Autowired
    private OrderRepository orderRepository;

    public Order findOrFail(Long orderId) {
        return orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException(orderId));
    }
}
