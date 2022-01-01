package com.sergioruy.sergiofoodapi.api.assembler;

import com.sergioruy.sergiofoodapi.api.model.input.RestaurantInput;
import com.sergioruy.sergiofoodapi.domain.model.Kitchen;
import com.sergioruy.sergiofoodapi.domain.model.Restaurant;
import org.springframework.stereotype.Component;

@Component
public class RestaurantInputDisassembler {

    public Restaurant toDomainObject(RestaurantInput restaurantInput) {
        Restaurant restaurant = new Restaurant();
        restaurant.setName(restaurantInput.getName());
        restaurant.setTaxDelivery(restaurantInput.getTaxDelivery());

        Kitchen kitchen = new Kitchen();
        kitchen.setId(restaurantInput.getKitchen().getId());

        restaurant.setKitchen(kitchen);

        return restaurant;
    }
}
