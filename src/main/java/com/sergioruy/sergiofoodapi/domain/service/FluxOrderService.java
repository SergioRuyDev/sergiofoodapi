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
    public void confirm(String orderCode) {
        Order order = orderService.findOrFail(orderCode);
        order.confirm();
    }

    @Transactional
    public void delivered(String orderCode) {
        Order order = orderService.findOrFail(orderCode);
        order.delivered();
    }

    @Transactional
    public void canceled(String orderCode) {
        Order order = orderService.findOrFail(orderCode);
        order.canceled();
    }
}
