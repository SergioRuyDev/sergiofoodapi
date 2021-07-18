package com.sergioruy.sergiofoodapi.domain.repository;

import com.sergioruy.sergiofoodapi.domain.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {

    List<Restaurant> findByTaxDeliveryBetween(BigDecimal initialTax, BigDecimal finalTax);

//    List<Restaurant> findByNameContainingAndKitchenId(String name, Long kitchen);
//    @Query("from Restaurant where name like %:name% and kitchen.id = :id")
    List<Restaurant> consultByName(String name, @Param("id") Long kitchen);

    Optional<Restaurant> findFirstByNameContaining(String name);

    List<Restaurant> findTop2ByNameContaining(String name);

    int countByKitchenId(Long kitchen);
}
