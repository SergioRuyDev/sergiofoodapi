package com.sergioruy.sergiofoodapi.api.model;

import com.fasterxml.jackson.annotation.JsonView;
import com.sergioruy.sergiofoodapi.api.model.view.RestaurantView;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
public class RestaurantModel {

    @JsonView({ RestaurantView.RestaurantBrief.class, RestaurantView.idAndName.class })
    private Long id;

    @JsonView({ RestaurantView.RestaurantBrief.class, RestaurantView.idAndName.class })
    private String name;

    private BigDecimal taxDelivery;

    @JsonView(RestaurantView.RestaurantBrief.class)
    private KitchenModel kitchen;

    private Boolean active;
    private Boolean open;
    private AddressModel address;
}
