package com.sergioruy.sergiofoodapi.infrastructure.repository;

import com.sergioruy.sergiofoodapi.domain.model.City;
import com.sergioruy.sergiofoodapi.domain.repository.CityRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class CityRepositoryImpl implements CityRepository {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<City> list() {
        return null;
    }

    @Override
    public City find(Long id) {
        return null;
    }

    @Override
    public City save(City city) {
        return null;
    }

    @Override
    public void remove(City city) {

    }
}
