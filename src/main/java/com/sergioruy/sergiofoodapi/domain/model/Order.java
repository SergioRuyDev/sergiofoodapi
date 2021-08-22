package com.sergioruy.sergiofoodapi.domain.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Data
@Entity
public class Order {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private BigDecimal subtotal;

    @Column(nullable = false)
    private BigDecimal taxDelivery;

    @Column(nullable = false)
    private BigDecimal totalAmount;

    @Column(nullable = true)
    private LocalDateTime createDate;

    private LocalDateTime confirmDate;
    private LocalDateTime cancelDate;
    private LocalDateTime DeliveryDate;

}
