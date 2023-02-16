package com.sergioruy.sergiofoodapi.domain.model.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class DailySale {

    private LocalDate date;
    private Long totalSales;
    private BigDecimal totalInvoiced;
}
