package com.sergioruy.sergiofoodapi.jpa;

import com.sergioruy.sergiofoodapi.SergiofoodapiApplication;
import com.sergioruy.sergiofoodapi.domain.model.Restaurant;
import com.sergioruy.sergiofoodapi.domain.repository.RestaurantRepository;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import java.util.Optional;

public class FindRestaurantMain {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new SpringApplicationBuilder(SergiofoodapiApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);

        RestaurantRepository restaurantRepository = applicationContext.getBean(RestaurantRepository.class);

        Optional<Restaurant> restaurant = restaurantRepository.findById(1L);

        System.out.println(restaurant.get().getName());
    }
}
