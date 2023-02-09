package com.sergioruy.sergiofoodapi.api.controller;

import com.sergioruy.sergiofoodapi.api.assembler.OrderModelAssembler;
import com.sergioruy.sergiofoodapi.api.assembler.OrderShortModelAssembler;
import com.sergioruy.sergiofoodapi.api.model.OrderModel;
import com.sergioruy.sergiofoodapi.api.model.OrderShortModel;
import com.sergioruy.sergiofoodapi.domain.model.Order;
import com.sergioruy.sergiofoodapi.domain.repository.OrderRepository;
import com.sergioruy.sergiofoodapi.domain.service.RegisterOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/orders")
public class OrderController {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private RegisterOrderService orderService;

    @Autowired
    private OrderModelAssembler orderModelAssembler;

    @Autowired
    private OrderShortModelAssembler orderShortModelAssembler;

    @GetMapping
    public List<OrderShortModel> list() {
        List<Order> allOrders = orderRepository.findAll();

        return orderShortModelAssembler.toCollectionModel(allOrders);
    }

    @GetMapping("/{orderId}")
    public OrderModel getOrder(@PathVariable Long orderId) {
        Order order = orderService.findOrFail(orderId);
        return orderModelAssembler.toModel(order);
    }
}
