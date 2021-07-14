package com.sergioruy.sergiofoodapi.api.controller;

import com.sergioruy.sergiofoodapi.domain.model.Kitchen;
import com.sergioruy.sergiofoodapi.domain.model.Restaurant;
import com.sergioruy.sergiofoodapi.domain.repository.KitchenRepository;
import com.sergioruy.sergiofoodapi.domain.repository.RestaurantRepository;
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
    @GetMapping("/restaurants/by-tax-delivery")
    public List<Restaurant> restaurantsByTaxDelivery(BigDecimal initialTax, BigDecimal finalTax) {
        return restaurantRepository.findByTaxDeliveryBetween(initialTax, finalTax);
    }

    @GetMapping("/restaurants/by-name")
    public List<Restaurant> restaurantsByTaxDelivery(String name, Long kitchenId) {
        return restaurantRepository.findByNameContainingAndKitchenId(name, kitchenId);
    }
}
