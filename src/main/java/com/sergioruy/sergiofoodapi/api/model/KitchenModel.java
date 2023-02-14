package com.sergioruy.sergiofoodapi.api.model;

import com.fasterxml.jackson.annotation.JsonView;
import com.sergioruy.sergiofoodapi.api.model.view.RestaurantView;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class KitchenModel {

    @JsonView(RestaurantView.RestaurantBrief.class)
    private Long id;

    @JsonView(RestaurantView.RestaurantBrief.class)
    private String name;
}
