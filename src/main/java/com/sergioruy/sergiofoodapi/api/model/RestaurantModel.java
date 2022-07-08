package com.sergioruy.sergiofoodapi.api.model;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
public class RestaurantModel {

    private Long id;
    private String name;
    private BigDecimal taxDelivery;
    private KitchenModel kitchen;
    private Boolean active;
    private AddressModel address;
    private Boolean open;
}
