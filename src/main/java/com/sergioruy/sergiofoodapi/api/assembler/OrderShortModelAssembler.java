package com.sergioruy.sergiofoodapi.api.assembler;

import com.sergioruy.sergiofoodapi.api.model.OrderModel;
import com.sergioruy.sergiofoodapi.api.model.OrderShortModel;
import com.sergioruy.sergiofoodapi.domain.model.Order;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrderShortModelAssembler {

    @Autowired
    private ModelMapper modelMapper;

    public OrderShortModel toModel(Order order) {
        return modelMapper.map(order, OrderShortModel.class);
    }

    public List<OrderShortModel> toCollectionModel(List<Order> orders) {
        return orders.stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }
}
