package com.sergioruy.sergiofoodapi.domain.exception;

public class StateNotFoundException extends EntityNotFoundException {

    private static final long serialUID = 1L;

    public StateNotFoundException(String message) {
        super(message);
    }

    public StateNotFoundException(Long stateId) {
        this(String.format("Not exist State with code %d", stateId));
    }
}
