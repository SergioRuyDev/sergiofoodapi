package com.sergioruy.sergiofoodapi.domain.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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

    @Column(nullable = true)
    private LocalDateTime confirmDate;

    @Column(nullable = true)
    private LocalDateTime cancelDate;

    @Column(nullable = true)
    private LocalDateTime DeliveryDate;

    @OneToMany(mappedBy = "itemOrdered")
    private List<ItemOrdered> itemOrdereds = new ArrayList<>();

}
