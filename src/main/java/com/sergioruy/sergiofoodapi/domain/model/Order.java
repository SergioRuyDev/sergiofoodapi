package com.sergioruy.sergiofoodapi.domain.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal subtotal;
    private BigDecimal taxDelivery;
    private BigDecimal totalAmount;

    private LocalDateTime createDate;
    private LocalDateTime confirmDate;
    private LocalDateTime cancelDate;
    private LocalDateTime DeliveryDate;

}
