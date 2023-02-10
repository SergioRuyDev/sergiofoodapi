package com.sergioruy.sergiofoodapi.domain.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "item_order")
public class ItemOrdered {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal unitPrice;
    private BigDecimal totalPrice;
    private Integer quantity;
    private String observations;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Order order;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Product product;

    public void calcPriceTotal() {
        BigDecimal unitPrice = this.getUnitPrice();
        Integer quantity = this.getQuantity();

        if (unitPrice == null) {
            unitPrice = BigDecimal.ZERO;
        }

        if (quantity == null) {
            quantity = 0;
        }

        this.setTotalPrice(unitPrice.multiply(new BigDecimal(quantity)));
    }
}
