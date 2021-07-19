package com.sergioruy.sergiofoodapi.infrastructure.repository.spec;

import com.sergioruy.sergiofoodapi.domain.model.Restaurant;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;

public class RestaurantSpecs {

    public static Specification<Restaurant> withTaxFree() {
        return (root, query, builder) ->
                builder.equal(root.get("taxDelivery"), BigDecimal.ZERO);
    }

    public static Specification<Restaurant> withNameSimilar(String name) {
        return (root, query, builder) ->
                builder.equal(root.get("name"), "%" + name + "%");
    }
}
