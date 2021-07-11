package com.sergioruy.sergiofoodapi.domain.repository;

import com.sergioruy.sergiofoodapi.domain.model.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CityRepository extends JpaRepository<City, Long> {

}
