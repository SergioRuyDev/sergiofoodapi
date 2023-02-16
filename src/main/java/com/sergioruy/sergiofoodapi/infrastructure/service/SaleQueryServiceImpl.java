package com.sergioruy.sergiofoodapi.infrastructure.service;

import com.sergioruy.sergiofoodapi.domain.model.dto.DailySale;
import com.sergioruy.sergiofoodapi.domain.model.filter.DailySaleFilter;
import com.sergioruy.sergiofoodapi.domain.service.SaleQueryService;

import java.util.List;

public class SaleQueryServiceImpl implements SaleQueryService {

    @Override
    public List<DailySale> consultDiarySales(DailySaleFilter filter) {
        return null;
    }
}
