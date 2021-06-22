package com.sergioruy.sergiofoodapi.domain.repository;

import com.sergioruy.sergiofoodapi.domain.model.City;

import java.util.List;

public interface CityRepository {

    List<City> list();
    City find(Long id);
    City save(City city);
    void remove(City city);
}
