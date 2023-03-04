package com.sergioruy.sergiofoodapi.domain.service;

import com.sergioruy.sergiofoodapi.domain.model.filter.DailySaleFilter;

public interface SaleReportService {

    byte[] issueDailySales(DailySaleFilter filter, String timeOffset);
}
