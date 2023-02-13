package com.sergioruy.sergiofoodapi.domain.service;

import com.sergioruy.sergiofoodapi.domain.exception.BusinessException;
import com.sergioruy.sergiofoodapi.domain.model.Order;
import com.sergioruy.sergiofoodapi.domain.model.StatusOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;

@Service
public class FluxOrderService {

    @Autowired
    private RegisterOrderService orderService;

    @Transactional
    public void confirm(Long orderId) {
        Order order = orderService.findOrFail(orderId);

        if (!order.getStatus().equals(StatusOrder.CREATED)) {
            throw new BusinessException(
                    String.format("Status order %d cannot be change from %s to %s",
                            order.getId(), order.getStatus().getDescription(),
                            StatusOrder.CONFIRMED.getDescription()));
        }

        order.setStatus(StatusOrder.CONFIRMED);
        order.setConfirmedDate(OffsetDateTime.now());
    }
}
