package com.sergioruy.sergiofoodapi.domain.exception;

public class UserNotFoundException extends EntityNotFoundException{

    private static final long serialVersionUID = 1L;

    public UserNotFoundException(String message) {
        super(message);
    }

    public UserNotFoundException(Long userId) {
        this(String.format("Not exist User registered with code %d", userId));
    }
}
