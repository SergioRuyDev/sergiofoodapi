package com.sergioruy.sergiofoodapi.domain.exception;

public class OrderNotFoundException extends EntityNotFoundException {

    private static final long serialVersionUID = 1L;

    public OrderNotFoundException(String message) {
        super(message);
    }

    public OrderNotFoundException(Long orderId) {
        this(String.format("Not exist Order with code %d", orderId));

    }
}
