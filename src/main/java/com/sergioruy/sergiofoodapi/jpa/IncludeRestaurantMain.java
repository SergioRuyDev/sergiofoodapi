package com.sergioruy.sergiofoodapi.jpa;

import com.sergioruy.sergiofoodapi.SergiofoodapiApplication;
import com.sergioruy.sergiofoodapi.domain.model.Restaurant;
import com.sergioruy.sergiofoodapi.domain.repository.RestaurantRepository;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

public class IncludeRestaurantMain {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new SpringApplicationBuilder(SergiofoodapiApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);

        RestaurantRepository restaurantRepository = applicationContext.getBean(RestaurantRepository.class);

        Restaurant restaurant1 = new Restaurant();
        restaurant1.setName("Masallah Indian");

        Restaurant restaurant2 = new Restaurant();
        restaurant2.setName("Delhi Indian");

        restaurant1 = restaurantRepository.add(restaurant1);
        restaurant2 = restaurantRepository.add(restaurant2);

        System.out.printf("%d - %s\n", restaurant1.getId(), restaurant1.getName());
        System.out.printf("%d - %s\n", restaurant2.getId(), restaurant2.getName());
    }
}
