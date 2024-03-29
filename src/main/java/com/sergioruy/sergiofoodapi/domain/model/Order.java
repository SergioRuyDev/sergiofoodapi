package com.sergioruy.sergiofoodapi.domain.model;

import com.sergioruy.sergiofoodapi.domain.exception.BusinessException;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "`order`")
public class Order {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String code;

    private BigDecimal subtotal;
    private BigDecimal taxDelivery;
    private BigDecimal totalAmount;

    @Embedded
    private Address address;

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

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<ItemOrdered> items = new ArrayList<>();

    public void calcTotalAmount() {
        getItems().forEach(ItemOrdered::calcPriceTotal);

        this.subtotal = getItems().stream()
                .map(item -> item.getTotalPrice())
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        this.totalAmount = this.subtotal.add(this.taxDelivery);
    }

    public void confirm() {
        setStatus(StatusOrder.CONFIRMED);
        setConfirmedDate(OffsetDateTime.now());
    }

    public void delivered() {
        setStatus(StatusOrder.DELIVERED);
        setDeliveredDate(OffsetDateTime.now());
    }

    public void canceled() {
        setStatus(StatusOrder.CANCELED);
        setCancelledDate(OffsetDateTime.now());
    }

    private void setStatus(StatusOrder newStatus) {
        if (getStatus().cannotChangeTo(newStatus)) {
            throw new BusinessException(
                    String.format("Status order %s cannot be change from %s to %s",
                            getCode(), getStatus().getDescription(),
                            newStatus.getDescription()));
        }

        this.status = newStatus;
    }

    @PrePersist
    private void generateCode() {
        setCode(UUID.randomUUID().toString());
    }
}
