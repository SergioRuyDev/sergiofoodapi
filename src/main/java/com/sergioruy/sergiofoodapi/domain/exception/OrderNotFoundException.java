package com.sergioruy.sergiofoodapi.domain.exception;

public class OrderNotFoundException extends EntityNotFoundException {

    private static final long serialVersionUID = 1L;

    public OrderNotFoundException(String orderCode) {
        super(String.format("Not exist Order with code %s", orderCode));
    }
}
