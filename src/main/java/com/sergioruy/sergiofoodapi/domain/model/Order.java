package com.sergioruy.sergiofoodapi.domain.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "`order`")
public class Order {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal subtotal;
    private BigDecimal taxDelivery;
    private BigDecimal totalAmount;

    @Embedded
    private Address addressDelivered;

    @Enumerated(EnumType.STRING)
    private StatusOrder status = StatusOrder.CREATED;

    @CreationTimestamp
    private OffsetDateTime createdDate;

    private OffsetDateTime confirmedDate;
    private OffsetDateTime cancelledDate;
    private OffsetDateTime deliveredDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private PaymentMethod paymentMethod;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Restaurant restaurant;

    @ManyToOne
    @JoinColumn(name = "user_customer_id", nullable = false)
    private User customer;

    @OneToMany(mappedBy = "order")
    private List<ItemOrdered> items = new ArrayList<>();

    public void calcTotalAmount() {
        this.subtotal = getItems().stream()
                .map(items ->items.getTotalPrice())
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        this.totalAmount = this.subtotal.add(this.taxDelivery);
    }

    public void defineDelivery() {
        setTaxDelivery(getRestaurant().getTaxDelivery());
    }

    public void attachOrdersToItems() {
        getItems().forEach(items -> items.setOrder(this));
    }
}
