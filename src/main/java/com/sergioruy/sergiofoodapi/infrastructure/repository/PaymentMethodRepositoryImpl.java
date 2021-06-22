package com.sergioruy.sergiofoodapi.infrastructure.repository;

import com.sergioruy.sergiofoodapi.domain.model.PaymentMethod;
import com.sergioruy.sergiofoodapi.domain.repository.PaymentMethodRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Component
public class PaymentMethodRepositoryImpl implements PaymentMethodRepository {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<PaymentMethod> list() {
        return manager.createQuery("from PaymentMethod", PaymentMethod.class).getResultList();
    }

    @Override
    public PaymentMethod find(Long id) {
        return manager.find(PaymentMethod.class, id);
    }

    @Transactional
    @Override
    public PaymentMethod save(PaymentMethod paymentMethod) {
        return manager.merge(paymentMethod);
    }

    @Transactional
    @Override
    public void remove(PaymentMethod paymentMethod) {
        paymentMethod = find(paymentMethod.getId());
        manager.remove(paymentMethod);
    }
}
