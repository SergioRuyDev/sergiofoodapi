package com.sergioruy.sergiofoodapi.api.controller;

import com.sergioruy.sergiofoodapi.domain.model.Kitchen;
import com.sergioruy.sergiofoodapi.domain.model.Restaurant;
import com.sergioruy.sergiofoodapi.domain.repository.KitchenRepository;
import com.sergioruy.sergiofoodapi.domain.repository.RestaurantRepository;
import com.sergioruy.sergiofoodapi.infrastructure.repository.spec.RestaurantWithNameSimilarSpec;
import com.sergioruy.sergiofoodapi.infrastructure.repository.spec.RestaurantWithTaxFreeSpec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private KitchenRepository kitchenRepository;

    @Autowired
    private RestaurantRepository restaurantRepository;

    @GetMapping("/kitchens/by-name")
    public List<Kitchen> allKitchenByName(String name) {
        return kitchenRepository.findAllByName(name);
    }

    @GetMapping("/kitchens/unique-by-name")
    public Optional<Kitchen> kitchenByName(String name) {
        return kitchenRepository.findByName(name);
    }

    @GetMapping("/kitchens/exists")
    public boolean kitchenExists(String name) {
        return kitchenRepository.existsByName(name);
    }

    @GetMapping("/restaurants/by-tax-delivery")
    public List<Restaurant> restaurantsByTaxDelivery(BigDecimal initialTax, BigDecimal finalTax) {
        return restaurantRepository.queryByTaxDeliveryBetween(initialTax, finalTax);
    }

    @GetMapping("/restaurants/by-name")
    public List<Restaurant> restaurantByName(String name, Long kitchenId) {
        return restaurantRepository.consultByName(name, kitchenId);
    }

    @GetMapping("/restaurants/first-name")
    public Optional<Restaurant> restaurantFirstName(String name) {
        return restaurantRepository.findFirstByNameContaining(name);
    }

    @GetMapping("/restaurants/top2-by-name")
    public List<Restaurant> restaurantsTop2ByName(String name) {
        return restaurantRepository.findTop2ByNameContaining(name);
    }

    @GetMapping("/restaurants/by-name-and-delivery")
    public List<Restaurant> restaurantsByNameDelivery(String name, BigDecimal taxDeliveryInitial,
                                                      BigDecimal taxDeliveryFinal) {
        return restaurantRepository.find(name, taxDeliveryInitial, taxDeliveryFinal);
    }

    @GetMapping("/restaurants/count-by-kitchen")
    public int restaurantsCountByKitchen(Long kitchenId) {
        return restaurantRepository.countByKitchenId(kitchenId);
    }

    @GetMapping("/restaurants/with-free-tax")
    public List<Restaurant> restaurantsTaxFree(String name) {
        var withTaxFree = new RestaurantWithTaxFreeSpec();
        var withNameSimilar = new RestaurantWithNameSimilarSpec(name);

        return restaurantRepository.findAll(withTaxFree.and(withNameSimilar));
    }

}
