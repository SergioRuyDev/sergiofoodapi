package com.sergioruy.sergiofoodapi.api.controller;

import com.sergioruy.sergiofoodapi.domain.model.dto.DailySale;
import com.sergioruy.sergiofoodapi.domain.model.filter.DailySaleFilter;
import com.sergioruy.sergiofoodapi.domain.service.SaleQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/statistics")
public class StatisticsController {

    @Autowired
    private SaleQueryService saleQueryService;


    @GetMapping("/daily-sales")
    public List<DailySale> consultDiarySales(DailySaleFilter filter) {
        return saleQueryService.consultDiarySales(filter);
    }
}
