package com.sergioruy.sergiofoodapi.domain.service;

import com.sergioruy.sergiofoodapi.domain.exception.RestaurantNotFoundException;
import com.sergioruy.sergiofoodapi.domain.model.Kitchen;
import com.sergioruy.sergiofoodapi.domain.model.PaymentMethod;
import com.sergioruy.sergiofoodapi.domain.model.Restaurant;
import com.sergioruy.sergiofoodapi.domain.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RegisterRestaurantService {

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private RegisterKitchenService kitchenService;

    @Autowired
    private RegisterPaymentMethodService paymentMethodService;

    public RegisterRestaurantService(RestaurantRepository restaurantRepository) {
    }

    @Transactional
    public Restaurant save(Restaurant restaurant) {
        Long kitchenId = restaurant.getKitchen().getId();

        Kitchen kitchen = kitchenService.findOrFail(kitchenId);

        restaurant.setKitchen(kitchen);

        return restaurantRepository.save(restaurant);
    }

    @Transactional
    public void activate(Long restaurantId) {
        Restaurant currentRestaurant = findOrFail(restaurantId);
        currentRestaurant.activate();
    }

    @Transactional
    public void deactivate(Long restaurantId) {
        Restaurant currentRestaurant = findOrFail(restaurantId);
        currentRestaurant.deactivate();
    }

    @Transactional
    public void removePaymentMethod(Long restaurantId, Long paymentMethodId) {
        Restaurant restaurant = findOrFail(restaurantId);
        PaymentMethod paymentMethod = paymentMethodService.findOrFail(paymentMethodId);

        restaurant.addPaymentMethod(paymentMethod);
    }

    public Restaurant findOrFail(Long restaurantId) {
        return restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new RestaurantNotFoundException(restaurantId));
    }
}
