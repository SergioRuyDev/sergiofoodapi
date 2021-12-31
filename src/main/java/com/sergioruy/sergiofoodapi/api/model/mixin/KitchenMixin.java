package com.sergioruy.sergiofoodapi.api.model.mixin;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sergioruy.sergiofoodapi.domain.model.Restaurant;

import java.util.List;

public abstract class KitchenMixin {

    @JsonIgnore
    private List<Restaurant> restaurants;

}
