package com.sergioruy.sergiofoodapi.domain.exception;

public class EntityUsedException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public EntityUsedException(String message) {
        super(message);
    }
}
