package com.sergioruy.sergiofoodapi.api.controller;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sergioruy.sergiofoodapi.api.model.KitchenModel;
import com.sergioruy.sergiofoodapi.api.model.RestaurantModel;
import com.sergioruy.sergiofoodapi.domain.exception.BusinessException;
import com.sergioruy.sergiofoodapi.domain.exception.KitchenNotFoundException;
import com.sergioruy.sergiofoodapi.domain.model.Restaurant;
import com.sergioruy.sergiofoodapi.domain.repository.RestaurantRepository;
import com.sergioruy.sergiofoodapi.domain.service.RegisterRestaurantService;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/restaurants")
public class RestaurantController {

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private RegisterRestaurantService restaurantService;

    @GetMapping
    public List<RestaurantModel> list() {
        return toCollectionModel(restaurantRepository.findAll());
    }

    @GetMapping("/{restaurantId}")
    public RestaurantModel search(@PathVariable Long restaurantId) {
        Restaurant restaurant = restaurantService.findOrFail(restaurantId);

        return toModel(restaurant);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RestaurantModel add(@RequestBody @Valid Restaurant restaurant) {
        try {
            return toModel(restaurantService.save(restaurant));
        } catch (KitchenNotFoundException e) {
            throw new BusinessException(e.getMessage(), e);
        }
    }

    @PutMapping("/{restaurantId}")
    public RestaurantModel update(@PathVariable Long restaurantId, @RequestBody Restaurant restaurant) {
        try {
            Restaurant currentRestaurant = restaurantService.findOrFail(restaurantId);

            BeanUtils.copyProperties(restaurant, currentRestaurant, "id", "paymentMethods", "address"
                    , "dateRegister", "products");

            return toModel(restaurantService.save(currentRestaurant));
        } catch (KitchenNotFoundException e) {
            throw new BusinessException(e.getMessage());
        }
    }


    private RestaurantModel toModel(Restaurant restaurant) {
        KitchenModel kitchenModel = new KitchenModel();
        kitchenModel.setId(restaurant.getKitchen().getId());
        kitchenModel.setName(restaurant.getKitchen().getName());

        RestaurantModel restaurantModel = new RestaurantModel();
        restaurantModel.setId(restaurant.getId());
        restaurantModel.setName(restaurant.getName());
        restaurantModel.setTaxDelivery(restaurant.getTaxDelivery());
        restaurantModel.setKitchen(kitchenModel);
        return restaurantModel;
    }

    private List<RestaurantModel> toCollectionModel(List<Restaurant> restaurants) {
        return restaurants.stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }
}

//    @PatchMapping("/{restaurantId}")
//    public Restaurant partialUpdate(@PathVariable Long restaurantId, @RequestBody Map<String, Object> fields, HttpServletRequest request) {
//        Restaurant currentRestaurant = restaurantService.findOrFail(restaurantId);
//
//        merge(fields, currentRestaurant, request);
//
//        return update(restaurantId, currentRestaurant);
//    }
//
//    private void merge(Map<String, Object> dataOrigin, Restaurant restaurantDestiny, HttpServletRequest request) {
//        ServletServerHttpRequest serverHttpRequest = new ServletServerHttpRequest(request);

//        try {
//            ObjectMapper objectMapper = new ObjectMapper();
//            objectMapper.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, true);
//            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true);
//
//            Restaurant restaurantOrigin = objectMapper.convertValue(dataOrigin, Restaurant.class);
//
//            dataOrigin.forEach((nameProperty, valueProperty) -> {
//                Field field = ReflectionUtils.findField(Restaurant.class, nameProperty);
//                field.setAccessible(true);
//
//                Object newValue = ReflectionUtils.getField(field, restaurantOrigin);
//
////            System.out.println(nameProperty + " = " + valueProperty + " = " + newValue);
//
//                ReflectionUtils.setField(field, restaurantDestiny, newValue);
//            });
//        } catch (IllegalArgumentException e) {
//            Throwable rootCause = ExceptionUtils.getRootCause(e);
//            throw new HttpMessageNotReadableException(e.getMessage(), rootCause, serverHttpRequest);
//        }
//    }