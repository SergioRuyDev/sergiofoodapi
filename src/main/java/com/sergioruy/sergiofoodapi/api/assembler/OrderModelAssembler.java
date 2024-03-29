package com.sergioruy.sergiofoodapi.api.assembler;

import com.sergioruy.sergiofoodapi.api.model.OrderModel;
import com.sergioruy.sergiofoodapi.domain.model.Order;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrderModelAssembler {

    @Autowired
    private ModelMapper modelMapper;

    public OrderModel toModel(Order order) {
        return modelMapper.map(order, OrderModel.class);
    }

    public List<OrderModel> toCollectionModel(List<Order> orders) {
        return orders.stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }
}
