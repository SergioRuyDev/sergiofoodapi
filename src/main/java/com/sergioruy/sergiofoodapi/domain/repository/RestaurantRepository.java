package com.sergioruy.sergiofoodapi.domain.repository;

import com.sergioruy.sergiofoodapi.domain.model.Restaurant;

import java.util.List;

public interface RestaurantRepository {
    List<Restaurant> all();
    Restaurant findById(Long id);
    Restaurant add(Restaurant restaurant);
    void remove(Restaurant restaurant);
}
