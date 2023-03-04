package com.sergioruy.sergiofoodapi.api.controller;

import com.sergioruy.sergiofoodapi.domain.model.dto.DailySale;
import com.sergioruy.sergiofoodapi.domain.model.filter.DailySaleFilter;
import com.sergioruy.sergiofoodapi.domain.service.SaleQueryService;
import com.sergioruy.sergiofoodapi.domain.service.SaleReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/statistics")
public class StatisticsController {

    @Autowired
    private SaleQueryService queryService;

    @Autowired
    private SaleReportService reportService;


    @GetMapping(path = "/daily-sales", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<DailySale> consultDiarySales(DailySaleFilter filter,
                                             @RequestParam(required = false, defaultValue = "+00:00") String timeOffset) {
        return queryService.consultDiarySales(filter, timeOffset);
    }

    @GetMapping(path = "/daily-sales", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<byte[]> consultDiarySalesPdf(DailySaleFilter filter,
                                             @RequestParam(required = false, defaultValue = "+00:00") String timeOffset) {
        byte[] bytesPdf = reportService.issueDailySales(filter, timeOffset);

        var headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=daily-sales.pdf");

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_PDF)
                .headers(headers)
                .body(bytesPdf);
    }
}
