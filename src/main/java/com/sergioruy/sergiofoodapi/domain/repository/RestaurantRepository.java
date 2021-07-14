package com.sergioruy.sergiofoodapi.domain.repository;

import com.sergioruy.sergiofoodapi.domain.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {

    List<Restaurant> findByTaxDeliveryBetween(BigDecimal initialTax, BigDecimal finalTax);

    List<Restaurant> findByNameContainingAndKitchenId(String name, Long kitchen);
}
