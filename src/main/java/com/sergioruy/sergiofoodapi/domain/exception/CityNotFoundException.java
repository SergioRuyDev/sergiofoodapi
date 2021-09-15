package com.sergioruy.sergiofoodapi.domain.exception;

public class CityNotFoundException extends EntityNotFoundException {

    private static final long serialUID = 1L;

    public CityNotFoundException(String message) {
        super(message);
    }

    public CityNotFoundException(Long cityId) {
        this(String.format("Not exist City with code %d", cityId));
    }
}
