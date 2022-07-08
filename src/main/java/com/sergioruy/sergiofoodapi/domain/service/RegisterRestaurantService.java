package com.sergioruy.sergiofoodapi.domain.service;

import com.sergioruy.sergiofoodapi.domain.exception.RestaurantNotFoundException;
import com.sergioruy.sergiofoodapi.domain.model.City;
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
    private RegisterCityService cityService;

    @Autowired
    private RegisterPaymentMethodService paymentMethodService;

    @Transactional
    public Restaurant save(Restaurant restaurant) {
        Long kitchenId = restaurant.getKitchen().getId();
        Long cityId = restaurant.getAddress().getCity().getId();

        Kitchen kitchen = kitchenService.findOrFail(kitchenId);
        City city = cityService.findOrFail(cityId);

        restaurant.setKitchen(kitchen);
        restaurant.getAddress().setCity(city);

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
    public void open(Long restaurantId) {
        Restaurant currentRestaurant = findOrFail(restaurantId);

        currentRestaurant.open();
    }

    @Transactional
    public void close(Long restaurantId) {
        Restaurant currentRestaurant = findOrFail(restaurantId);

        currentRestaurant.close();
    }

    @Transactional
    public void detachPaymentMethod(Long restaurantId, Long paymentMethodId) {
        Restaurant restaurant = findOrFail(restaurantId);
        PaymentMethod paymentMethod = paymentMethodService.findOrFail(paymentMethodId);

        restaurant.getPaymentMethods().remove(paymentMethod);
    }

    @Transactional
    public void attachPaymentMethod(Long restaurantId, Long paymentMethodId) {
        Restaurant restaurant = findOrFail(restaurantId);
        PaymentMethod paymentMethod = paymentMethodService.findOrFail(paymentMethodId);

        restaurant.getPaymentMethods().add(paymentMethod);
    }

    public Restaurant findOrFail(Long restaurantId) {
        return restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new RestaurantNotFoundException(restaurantId));
    }
}
