package com.sergioruy.sergiofoodapi.api.controller;

import com.sergioruy.sergiofoodapi.api.assembler.OrderInputDisassembler;
import com.sergioruy.sergiofoodapi.api.assembler.OrderModelAssembler;
import com.sergioruy.sergiofoodapi.api.assembler.OrderShortModelAssembler;
import com.sergioruy.sergiofoodapi.api.model.OrderModel;
import com.sergioruy.sergiofoodapi.api.model.OrderShortModel;
import com.sergioruy.sergiofoodapi.api.model.input.OrderInput;
import com.sergioruy.sergiofoodapi.domain.exception.BusinessException;
import com.sergioruy.sergiofoodapi.domain.exception.EntityNotFoundException;
import com.sergioruy.sergiofoodapi.domain.model.Order;
import com.sergioruy.sergiofoodapi.domain.model.User;
import com.sergioruy.sergiofoodapi.domain.repository.OrderRepository;
import com.sergioruy.sergiofoodapi.domain.service.RegisterOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
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

    @Autowired
    private OrderInputDisassembler orderInputDisassembler;

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

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OrderModel add(@Valid @RequestBody OrderInput orderInput) {
        try {
            Order newOrder = orderInputDisassembler.toDomainObject(orderInput);

            //todo get the authenticated users
            newOrder.setCustomer(new User());
            newOrder.getCustomer().setId(1L);

            newOrder = orderService.issueOrder(newOrder);

            return orderModelAssembler.toModel(newOrder);
        } catch (EntityNotFoundException e) {
            throw new BusinessException(e.getMessage(), e);
        }
    }
}