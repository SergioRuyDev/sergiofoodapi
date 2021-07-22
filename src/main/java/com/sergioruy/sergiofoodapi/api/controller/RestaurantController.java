package com.sergioruy.sergiofoodapi.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sergioruy.sergiofoodapi.domain.exception.EntityNotFoundException;
import com.sergioruy.sergiofoodapi.domain.model.Restaurant;
import com.sergioruy.sergiofoodapi.domain.repository.RestaurantRepository;
import com.sergioruy.sergiofoodapi.domain.service.RegisterRestaurantService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping(value = "/restaurants")
public class RestaurantController {

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private RegisterRestaurantService registerRestaurant;

    @GetMapping
    public List<Restaurant> list() {
        return restaurantRepository.findAll();
    }

    @GetMapping("/{restaurantId}")
    public ResponseEntity<Restaurant> search(@PathVariable Long restaurantId) {
        Optional<Restaurant> restaurant = restaurantRepository.findById(restaurantId);

        return restaurant.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
//        if (restaurant.isPresent()) {
//            return ResponseEntity.ok(restaurant.get());
//        }

    }

    @PostMapping
    public ResponseEntity<?> add(@RequestBody Restaurant restaurant) {
        try {
            restaurant = registerRestaurant.save(restaurant);

            return ResponseEntity.status(HttpStatus.CREATED).body(restaurant);

        } catch (EntityNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{restaurantId}")
    public ResponseEntity<?> update(@PathVariable Long restaurantId, @RequestBody Restaurant restaurant) {
        try {
            Restaurant currentRestaurant = restaurantRepository.findById(restaurantId)
                    .orElse(null);

            if (currentRestaurant != null) {
                BeanUtils.copyProperties(restaurant, currentRestaurant, "id", "paymentMethods");

                currentRestaurant = registerRestaurant.save(currentRestaurant);
                return ResponseEntity.ok(currentRestaurant);
            }
            return ResponseEntity.notFound().build();

        } catch (EntityNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PatchMapping("/{restaurantId}")
    public ResponseEntity<?> partialUpdate(@PathVariable Long restaurantId, @RequestBody Map<String, Object> fields) {
        Restaurant currentRestaurant = restaurantRepository.findById(restaurantId).orElse(null);

        if (currentRestaurant == null) {
            return ResponseEntity.notFound().build();

        }
        merge(fields, currentRestaurant);

        return update(restaurantId, currentRestaurant);

    }

    private void merge(Map<String, Object> dataOrigin, Restaurant restaurantDestiny) {
        ObjectMapper objectMapper = new ObjectMapper();
        Restaurant restaurantOrigin = objectMapper.convertValue(dataOrigin, Restaurant.class);

        dataOrigin.forEach((nameProperty, valueProperty) -> {
            Field field = ReflectionUtils.findField(Restaurant.class, nameProperty);
            field.setAccessible(true);

            Object newValue = ReflectionUtils.getField(field, restaurantOrigin);

//            System.out.println(nameProperty + " = " + valueProperty + " = " + newValue);

            ReflectionUtils.setField(field, restaurantDestiny, newValue);
        });
    }
}
