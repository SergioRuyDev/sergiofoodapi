package com.sergioruy.sergiofoodapi.infrastructure.repository;

import com.sergioruy.sergiofoodapi.domain.model.Restaurant;
import com.sergioruy.sergiofoodapi.domain.repository.RestaurantRepositoryQueries;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

@Repository
public class RestaurantRepositoryImpl implements RestaurantRepositoryQueries {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<Restaurant> find(String name, BigDecimal taxDeliveryInitial, BigDecimal taxDeliveryFinal) {

        CriteriaBuilder builder = manager.getCriteriaBuilder();

        CriteriaQuery<Restaurant> criteria = builder.createQuery(Restaurant.class);
        Root<Restaurant> root = criteria.from(Restaurant.class);

        Predicate namePredicate = builder.like(root.get("name"), "%" + name + "%");

        Predicate taxInitialPredicate = builder.greaterThanOrEqualTo(root.get("tax_delivery"), taxDeliveryInitial);
        Predicate taxFinalPredicate = builder.lessThanOrEqualTo(root.get("tax_delivery"), taxDeliveryFinal);

        criteria.where(namePredicate, taxInitialPredicate, taxFinalPredicate);

        TypedQuery<Restaurant> query = manager.createQuery(criteria);
        return query.getResultList();
    }
}

/*        var jpql = new StringBuilder();
        jpql.append("from Restaurant where 0 = 0 ");

        var parameters = new HashMap<String, Object>();

        if (StringUtils.hasLength(name)) {
            jpql.append("and name like :name ");
            parameters.put("name", "%" + name + "%");
        }

        if (taxDeliveryInitial != null) {
            jpql.append("and taxDelivery >= :taxInitial ");
            parameters.put("taxInitial", taxDeliveryInitial);
        }

        if (taxDeliveryFinal != null) {
            jpql.append("and taxDelivery <= :taxFinal ");
            parameters.put("taxFinal", taxDeliveryFinal);
        }

        TypedQuery<Restaurant> query = manager.createQuery(jpql.toString(), Restaurant.class);

        parameters.forEach((key, value) -> query.setParameter(key, value));
        return query.getResultList();*/