package com.sergioruy.sergiofoodapi.domain.service;

import com.sergioruy.sergiofoodapi.domain.exception.RestaurantNotFoundException;
import com.sergioruy.sergiofoodapi.domain.model.Kitchen;
import com.sergioruy.sergiofoodapi.domain.model.Restaurant;
import com.sergioruy.sergiofoodapi.domain.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegisterRestaurantService {

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private RegisterKitchenService kitchenService;

    public Restaurant save(Restaurant restaurant) {
        Long kitchenId = restaurant.getKitchen().getId();

        Kitchen kitchen = kitchenService.findOrFail(kitchenId);

        restaurant.setKitchen(kitchen);

        return restaurantRepository.save(restaurant);
    }

    public Restaurant findOrFail(Long restaurantId) {
        return restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new RestaurantNotFoundException(restaurantId));
    }
}
