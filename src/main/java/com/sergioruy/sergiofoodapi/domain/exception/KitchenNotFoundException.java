package com.sergioruy.sergiofoodapi.domain.exception;

public class KitchenNotFoundException extends EntityNotFoundException {

    private static final long serialUID = 1L;

    public KitchenNotFoundException(String message) {
        super(message);
    }

    public KitchenNotFoundException(Long kitchenId) {
        this(String.format("Not exist Kitchen with code %d", kitchenId));
    }
}
