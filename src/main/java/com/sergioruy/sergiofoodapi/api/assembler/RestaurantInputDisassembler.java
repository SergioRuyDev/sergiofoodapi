package com.sergioruy.sergiofoodapi.api.assembler;

import com.sergioruy.sergiofoodapi.api.model.RestaurantModel;
import com.sergioruy.sergiofoodapi.api.model.input.RestaurantInput;
import com.sergioruy.sergiofoodapi.domain.model.Kitchen;
import com.sergioruy.sergiofoodapi.domain.model.Restaurant;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RestaurantInputDisassembler {

    @Autowired
    private ModelMapper modelMapper;

    public Restaurant toDomainObject(RestaurantInput restaurantInput) {
        return modelMapper.map(restaurantInput, Restaurant.class);

//        Restaurant restaurant = new Restaurant();
//        restaurant.setName(restaurantInput.getName());
//        restaurant.setTaxDelivery(restaurantInput.getTaxDelivery());
//
//        Kitchen kitchen = new Kitchen();
//        kitchen.setId(restaurantInput.getKitchen().getId());
//
//        restaurant.setKitchen(kitchen);
//
//        return restaurant;
    }
}
