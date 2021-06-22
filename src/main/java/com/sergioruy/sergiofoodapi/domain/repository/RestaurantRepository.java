package com.sergioruy.sergiofoodapi.domain.repository;

import com.sergioruy.sergiofoodapi.domain.model.Restaurant;

import java.util.List;

public interface RestaurantRepository {
    List<Restaurant> list();
    Restaurant find(Long id);
    Restaurant save(Restaurant restaurant);
    void remove(Restaurant restaurant);
}
