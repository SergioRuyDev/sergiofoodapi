package com.sergioruy.sergiofoodapi.api.model;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ItemOrderedModel {

    private BigDecimal unitPrice;
    private BigDecimal totalPrice;
    private Integer quantity;
    private String observations;
    private ProductShortModel product;

}
