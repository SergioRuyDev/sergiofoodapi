package com.sergioruy.sergiofoodapi.api.model;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

@Getter
@Setter
public class OrderModel {


    private Long id;
    private BigDecimal subtotal;
    private BigDecimal taxDelivery;
    private BigDecimal totalAmount;
    private String status;
    private OffsetDateTime createdDate;
    private OffsetDateTime confirmedDate;
    private OffsetDateTime deliveredDate;
    private OffsetDateTime cancelledDate;
    private RestaurantShortModel restaurant;
    private UserModel customer;
    private PaymentMethodModel paymentMethod;
    private AddressModel addressDelivered;
    private List<ItemOrderedModel> items;
}
