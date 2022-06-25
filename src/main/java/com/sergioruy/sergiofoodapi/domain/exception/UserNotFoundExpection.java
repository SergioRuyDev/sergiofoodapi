package com.sergioruy.sergiofoodapi.domain.exception;

public class UserNotFoundExpection extends EntityNotFoundException{

    private static final long serialVersionUID = 1L;

    public UserNotFoundExpection(String message) {
        super(message);
    }

    public UserNotFoundExpection(Long userId) {
        this(String.format("Not exist User registered with code %d", userId));
    }
}
