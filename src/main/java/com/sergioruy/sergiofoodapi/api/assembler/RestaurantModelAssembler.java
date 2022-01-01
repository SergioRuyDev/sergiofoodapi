package com.sergioruy.sergiofoodapi.api.assembler;

import com.sergioruy.sergiofoodapi.api.model.KitchenModel;
import com.sergioruy.sergiofoodapi.api.model.RestaurantModel;
import com.sergioruy.sergiofoodapi.domain.model.Restaurant;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class RestaurantModelAssembler {

    @Autowired
    private ModelMapper modelMapper;

    public RestaurantModel toModel(Restaurant restaurant) {
        return modelMapper.map(restaurant, RestaurantModel.class);

//        KitchenModel kitchenModel = new KitchenModel();
//        kitchenModel.setId(restaurant.getKitchen().getId());
//        kitchenModel.setName(restaurant.getKitchen().getName());
//
//        RestaurantModel restaurantModel = new RestaurantModel();
//        restaurantModel.setId(restaurant.getId());
//        restaurantModel.setName(restaurant.getName());
//        restaurantModel.setTaxDelivery(restaurant.getTaxDelivery());
//        restaurantModel.setKitchen(kitchenModel);
//        return restaurantModel;
    }

    public List<RestaurantModel> toCollectionModel(List<Restaurant> restaurants) {
        return restaurants.stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }

}
