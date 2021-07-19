package com.sergioruy.sergiofoodapi.infrastructure.repository.spec;

import com.sergioruy.sergiofoodapi.domain.model.Restaurant;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.math.BigDecimal;

public class RestaurantWithTaxFreeSpec implements Specification<Restaurant> {

    private static final long serialVersionUID = 1L;

    @Override
    public Predicate toPredicate(Root<Restaurant> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        return builder.equal(root.get("taxDelivery"), BigDecimal.ZERO);
    }
}
