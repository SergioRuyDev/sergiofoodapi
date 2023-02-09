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
    private AddressModel addressDelivered;
    private String status;
    private OffsetDateTime createDate;
    private OffsetDateTime confirmedDate;
    private OffsetDateTime cancelledDate;
    private OffsetDateTime deliveredDate;
    private PaymentMethodModel paymentMethod;
    private RestaurantShortModel restaurant;
    private UserModel customer;
    private List<ItemOrderedModel> items;
}
