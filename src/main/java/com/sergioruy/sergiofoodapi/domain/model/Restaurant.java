package com.sergioruy.sergiofoodapi.domain.model;

import com.sergioruy.sergiofoodapi.core.validation.Groups;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.groups.ConvertGroup;
import javax.validation.groups.Default;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Restaurant {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @NotNull
//    @NotEmpty
//    @NotBlank
    @Column(nullable = false)
    private String name;

//    @DecimalMin("1")
//    @NotNull
//    @PositiveOrZero
    @Column(name = "tax_delivery", nullable = false)
    private BigDecimal taxDelivery;

//    @Valid
//    @ConvertGroup(from = Default.class, to = Groups.KitchenId.class)
//    @NotNull
    @ManyToOne
    @JoinColumn(name = "kitchen_id", nullable = false)
    private Kitchen kitchen;

    @Embedded
    private Address address;

    private boolean active = Boolean.TRUE;

    @CreationTimestamp
    @Column(nullable = false, columnDefinition = "datetime")
    private OffsetDateTime dateRegister;

    @UpdateTimestamp
    @Column(nullable = false, columnDefinition = "datetime")
    private OffsetDateTime dateUpdate;

    @ManyToMany
    @JoinTable(name = "restaurant_payment_method", joinColumns = @JoinColumn(name = "restaurant_id"),
            inverseJoinColumns = @JoinColumn(name = "payment_method_id"))
    private Set<PaymentMethod> paymentMethods = new HashSet<>();

    @OneToMany(mappedBy = "restaurant")
    private List<Product> products = new ArrayList<>();

    public void activate() {
        setActive(true);
    }

    public void deactivate() {
        setActive(false);
    }

    public boolean removePaymentMethod(PaymentMethod paymentMethod) {
        return getPaymentMethods().remove(paymentMethod);
    }

    public boolean addPaymentMethod(PaymentMethod paymentMethod) {
        return getPaymentMethods().add(paymentMethod);
    }

}
