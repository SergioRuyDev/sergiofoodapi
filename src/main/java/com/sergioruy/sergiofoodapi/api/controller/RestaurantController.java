package com.sergioruy.sergiofoodapi.api.controller;

import com.sergioruy.sergiofoodapi.domain.model.Restaurant;
import com.sergioruy.sergiofoodapi.domain.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/restaurants")
public class RestaurantController {

    @Autowired
    private RestaurantRepository restaurantRepository;

    @GetMapping
    public List<Restaurant> list() {
        return restaurantRepository.list();
    }

    @GetMapping("/{restaurantId}")
    public ResponseEntity<Restaurant> search(@PathVariable Long restaurantId) {
        Restaurant restaurant = restaurantRepository.find(restaurantId);

        if (restaurant != null) {
            return ResponseEntity.ok(restaurant);
        }

        return ResponseEntity.notFound().build();
    }
}
