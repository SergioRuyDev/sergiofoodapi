package com.sergioruy.sergiofoodapi.domain.service;

import com.sergioruy.sergiofoodapi.domain.model.dto.DailySale;
import com.sergioruy.sergiofoodapi.domain.model.filter.DailySaleFilter;

import java.util.List;

public interface SaleQueryService {

    List<DailySale> consultDiarySales(DailySaleFilter filter);
}
