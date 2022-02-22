package com.sergioruy.sergiofoodapi.domain.service;

import com.sergioruy.sergiofoodapi.domain.exception.EntityUsedException;
import com.sergioruy.sergiofoodapi.domain.exception.PaymentMethodNotFoundException;
import com.sergioruy.sergiofoodapi.domain.model.PaymentMethod;
import com.sergioruy.sergiofoodapi.domain.repository.PaymentMethodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RegisterPaymentMethodService {

    private static final String MSG_PAYMENT_IN_USE = "Payment Method of code %d is in use and cannot be removed";


    @Autowired
    private PaymentMethodRepository paymentMethodRepository;


    @Transactional
    public PaymentMethod save(PaymentMethod paymentMethod) {
        return paymentMethodRepository.save(paymentMethod);
    }

    @Transactional
    public void delete(Long paymentMethodId) {
        try {
            paymentMethodRepository.deleteById(paymentMethodId);
            paymentMethodRepository.flush();

        } catch (EmptyResultDataAccessException e) {
            throw new PaymentMethodNotFoundException(paymentMethodId);

        } catch (DataIntegrityViolationException e) {
            throw new EntityUsedException(
                    String.format(MSG_PAYMENT_IN_USE, paymentMethodId));
        }
    }


    public PaymentMethod findOrFail(Long paymentMethodId) {
        return paymentMethodRepository.findById(paymentMethodId)
                .orElseThrow(() -> new PaymentMethodNotFoundException(paymentMethodId));
    }
}
