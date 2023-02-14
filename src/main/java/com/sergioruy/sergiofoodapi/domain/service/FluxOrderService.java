package com.sergioruy.sergiofoodapi.domain.service;

import com.sergioruy.sergiofoodapi.domain.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class FluxOrderService {

    @Autowired
    private RegisterOrderService orderService;

    @Transactional
    public void confirm(Long orderId) {
        Order order = orderService.findOrFail(orderId);
        order.confirm();
    }

    @Transactional
    public void delivered(Long orderId) {
        Order order = orderService.findOrFail(orderId);
        order.delivered();
    }

    @Transactional
    public void canceled(Long orderId) {
        Order order = orderService.findOrFail(orderId);
        order.canceled();
    }
}
