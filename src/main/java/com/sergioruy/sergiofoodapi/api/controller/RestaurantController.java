package com.sergioruy.sergiofoodapi.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sergioruy.sergiofoodapi.domain.exception.EntityNotFoundException;
import com.sergioruy.sergiofoodapi.domain.exception.GenericException;
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

@RestController
@RequestMapping(value = "/restaurants")
public class RestaurantController {

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private RegisterRestaurantService restaurantService;

    @GetMapping
    public List<Restaurant> list() {
        return restaurantRepository.findAll();
    }

    @GetMapping("/{restaurantId}")
    public Restaurant search(@PathVariable Long restaurantId) {
        return restaurantService.findOrFail(restaurantId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Restaurant add(@RequestBody Restaurant restaurant) {
        try {
            return restaurantService.save(restaurant);
        } catch (EntityNotFoundException e) {
            throw new GenericException(e.getMessage());
        }
    }

    @PutMapping("/{restaurantId}")
    public Restaurant update(@PathVariable Long restaurantId, @RequestBody Restaurant restaurant) {
        Restaurant currentRestaurant = restaurantService.findOrFail(restaurantId);

        BeanUtils.copyProperties(restaurant, currentRestaurant, "id", "paymentMethods", "address"
                , "dateRegister", "products");

        try {
            return restaurantService.save(currentRestaurant);
        } catch (EntityNotFoundException e) {
            throw new GenericException(e.getMessage());
        }
    }

    @PatchMapping("/{restaurantId}")
    public Restaurant partialUpdate(@PathVariable Long restaurantId, @RequestBody Map<String, Object> fields) {
        Restaurant currentRestaurant = restaurantService.findOrFail(restaurantId);

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
