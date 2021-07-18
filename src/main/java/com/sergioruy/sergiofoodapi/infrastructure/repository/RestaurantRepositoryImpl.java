package com.sergioruy.sergiofoodapi.infrastructure.repository;

import com.sergioruy.sergiofoodapi.domain.model.Restaurant;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.math.BigDecimal;
import java.util.List;

@Repository
public class RestaurantRepositoryImpl {

    @PersistenceContext
    private EntityManager manager;

    public List<Restaurant> find(String name, BigDecimal taxDeliveryInitial, BigDecimal taxDeliveryFinal) {
        var jpql = "from Restaurant where name like :name " + "and taxDelivery between :taxInitial and :taxFinal";

        return manager.createQuery(jpql, Restaurant.class)
                .setParameter("name", "%" + name + "%")
                .setParameter("taxInitial", taxDeliveryInitial)
                .setParameter("taxFinal", taxDeliveryFinal)
                .getResultList();
    }
}
