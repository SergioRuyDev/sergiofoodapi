package com.sergioruy.sergiofoodapi.infrastructure.repository.spec;

import com.sergioruy.sergiofoodapi.domain.model.Order;
import com.sergioruy.sergiofoodapi.domain.model.filter.OrderFilter;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;

public class OrderSpecs {

    public static Specification<Order> usingFilter(OrderFilter filter) {
        return (root, query, builder) -> {
            if (Order.class.equals(query.getResultType())) {
                root.fetch("restaurant").fetch("kitchen");
                root.fetch("customer");
            }

            var predicates = new ArrayList<Predicate>();

            if (filter.getCustomerId() != null){
                predicates.add(builder.equal(root.get("customer"), filter.getCustomerId()));
            }

            if (filter.getRestaurantId() != null){
                predicates.add(builder.equal(root.get("restaurant"), filter.getRestaurantId()));
            }

            if (filter.getCreateDateStart() != null){
                predicates.add(builder.greaterThanOrEqualTo(root.get("createdDate"), filter.getCreateDateStart()));
            }

            if (filter.getCreateDateEnd() != null){
                predicates.add(builder.lessThanOrEqualTo(root.get("createdDate"), filter.getCreateDateEnd()));
            }

            return builder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
