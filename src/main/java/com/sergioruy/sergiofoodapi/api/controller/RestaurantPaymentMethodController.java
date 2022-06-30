package com.sergioruy.sergiofoodapi.api.controller;

import com.sergioruy.sergiofoodapi.api.assembler.PaymentMethodModelAssembler;
import com.sergioruy.sergiofoodapi.api.model.PaymentMethodModel;
import com.sergioruy.sergiofoodapi.domain.model.Restaurant;
import com.sergioruy.sergiofoodapi.domain.service.RegisterRestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/restaurants/{restaurantId}/payment-methods")
public class RestaurantPaymentMethodController {

    @Autowired
    private RegisterRestaurantService restaurantService;

    @Autowired
    private PaymentMethodModelAssembler methodModelAssembler;


    @GetMapping
    public List<PaymentMethodModel> list(@PathVariable Long restaurantId) {
        Restaurant restaurant = restaurantService.findOrFail(restaurantId);

        return methodModelAssembler.toCollectionModel(restaurant.getPaymentMethods());
    }
}