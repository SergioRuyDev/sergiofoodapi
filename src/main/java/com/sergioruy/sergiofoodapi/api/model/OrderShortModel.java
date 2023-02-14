package com.sergioruy.sergiofoodapi.api.model;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Getter
@Setter
public class OrderShortModel {

    private String code;
    private BigDecimal subtotal;
    private BigDecimal taxDelivery;
    private BigDecimal totalAmount;
    private String status;
    private OffsetDateTime createdDate;
    private RestaurantShortModel restaurant;
    private UserModel customer;
}
