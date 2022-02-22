package com.sergioruy.sergiofoodapi.domain.exception;


public class PaymentMethodNotFoundException extends EntityNotFoundException {

    private static final long serialUID = 1L;

    public PaymentMethodNotFoundException(String message) {
        super(message);
    }

    public PaymentMethodNotFoundException(Long paymentMethodId) {
        this(String.format("Not exist Payment Method with code %d", paymentMethodId));
    }
}
