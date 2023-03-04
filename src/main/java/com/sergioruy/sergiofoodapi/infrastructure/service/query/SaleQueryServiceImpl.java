package com.sergioruy.sergiofoodapi.infrastructure.service.query;

import com.sergioruy.sergiofoodapi.domain.model.Order;
import com.sergioruy.sergiofoodapi.domain.model.StatusOrder;
import com.sergioruy.sergiofoodapi.domain.model.dto.DailySale;
import com.sergioruy.sergiofoodapi.domain.model.filter.DailySaleFilter;
import com.sergioruy.sergiofoodapi.domain.service.SaleQueryService;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
public class SaleQueryServiceImpl implements SaleQueryService {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<DailySale> consultDiarySales(DailySaleFilter filter, String timeOffset) {
        var builder = manager.getCriteriaBuilder();
        var query = builder.createQuery(DailySale.class);
        var root = query.from(Order.class);
        var predicates = new ArrayList<Predicate>();

        var functionConvertTzCreatedDate = builder.function(
                "convert_tz", Date.class, root.get("createdDate"),
                builder.literal("+00:00"), builder.literal(timeOffset));

        var functionCreatedDate = builder.function("date", Date.class, functionConvertTzCreatedDate);

        var selection = builder.construct(DailySale.class,
                functionCreatedDate,
                builder.count(root.get("id")),
                builder.sum(root.get("totalAmount")));

        if (filter.getRestaurantId() != null) {
            predicates.add(builder.equal(root.get("restaurant"), filter.getRestaurantId()));
        }

        if (filter.getCreateDateStart() != null) {
            predicates.add(builder.greaterThanOrEqualTo(root.get("createdDate"),
                    filter.getCreateDateStart()));
        }

        predicates.add(root.get("status").in(
                StatusOrder.CONFIRMED, StatusOrder.DELIVERED));

        query.select(selection);
        query.where(predicates.toArray(new Predicate[0]));
        query.groupBy(functionCreatedDate);

        return manager.createQuery(query).getResultList();
    }
}
