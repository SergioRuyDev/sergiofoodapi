package com.sergioruy.sergiofoodapi.domain.repository;

import com.sergioruy.sergiofoodapi.domain.model.PaymentMethod;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PaymentMethodRepository extends JpaRepository<PaymentMethod, Long> {

    List<PaymentMethod> list();
    PaymentMethod save(PaymentMethod paymentMethod);
    PaymentMethod find(Long id);
    void remove(PaymentMethod paymentMethod);
}
