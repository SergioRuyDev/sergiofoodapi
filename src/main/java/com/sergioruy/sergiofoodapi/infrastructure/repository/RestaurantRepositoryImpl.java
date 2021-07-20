package com.sergioruy.sergiofoodapi.infrastructure.repository;

import com.sergioruy.sergiofoodapi.domain.model.Restaurant;
import com.sergioruy.sergiofoodapi.domain.repository.RestaurantRepository;
import com.sergioruy.sergiofoodapi.domain.repository.RestaurantRepositoryQueries;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
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
import java.util.ArrayList;
import java.util.List;

import static com.sergioruy.sergiofoodapi.infrastructure.repository.spec.RestaurantSpecs.withNameSimilar;
import static com.sergioruy.sergiofoodapi.infrastructure.repository.spec.RestaurantSpecs.withTaxFree;

@Repository
public class RestaurantRepositoryImpl implements RestaurantRepositoryQueries {

    @PersistenceContext
    private EntityManager manager;

    @Autowired @Lazy
    private RestaurantRepository restaurantRepository;

    @Override
    public List<Restaurant> find(String name, BigDecimal taxDeliveryInitial, BigDecimal taxDeliveryFinal) {

        CriteriaBuilder builder = manager.getCriteriaBuilder();

        CriteriaQuery<Restaurant> criteria = builder.createQuery(Restaurant.class);

        Root<Restaurant> root = criteria.from(Restaurant.class);

        var predicates = new ArrayList<Predicate>();

        if (StringUtils.hasText(name)) {
            predicates.add(builder.like(root.get("name"), "%" + name + "%"));
        }

        if (taxDeliveryInitial != null) {
            predicates.add(builder.greaterThanOrEqualTo(root.get("taxDelivery"), taxDeliveryInitial));
        }

        if (taxDeliveryFinal != null) {
            predicates.add(builder.lessThanOrEqualTo(root.get("taxDelivery"), taxDeliveryFinal));
        }
        criteria.where(predicates.toArray(new Predicate[0]));

        TypedQuery<Restaurant> query = manager.createQuery(criteria);
        return query.getResultList();
    }

    @Override
    public List<Restaurant> findWithFreeTax(String name) {
        return restaurantRepository.findAll(withTaxFree().and(withNameSimilar(name)));
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