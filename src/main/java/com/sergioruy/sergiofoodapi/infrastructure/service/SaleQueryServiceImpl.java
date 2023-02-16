package com.sergioruy.sergiofoodapi.infrastructure.service;

import com.sergioruy.sergiofoodapi.domain.model.Order;
import com.sergioruy.sergiofoodapi.domain.model.dto.DailySale;
import com.sergioruy.sergiofoodapi.domain.model.filter.DailySaleFilter;
import com.sergioruy.sergiofoodapi.domain.service.SaleQueryService;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Date;
import java.util.List;

@Repository
public class SaleQueryServiceImpl implements SaleQueryService {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<DailySale> consultDiarySales(DailySaleFilter filter) {
        var builder = manager.getCriteriaBuilder();
        var query = builder.createQuery(DailySale.class);
        var root = query.from(Order.class);

        var functionCreatedDate = builder.function("date", Date.class, root.get("createdDate"));

        var selection = builder.construct(DailySale.class,
                functionCreatedDate,
                builder.count(root.get("id")),
                builder.sum(root.get("totalAmount")));

        query.select(selection);
        query.groupBy(functionCreatedDate);

        return manager.createQuery(query).getResultList();
    }
}
