package com.sergioruy.sergiofoodapi.domain.repository;

import com.sergioruy.sergiofoodapi.domain.model.PaymentMethod;

import java.util.List;

public interface PaymentMethodRepository {

    List<PaymentMethod> list();
    PaymentMethod find(Long id);
    PaymentMethod save(PaymentMethod paymentMethod);
    void remove(PaymentMethod paymentMethod);
}
