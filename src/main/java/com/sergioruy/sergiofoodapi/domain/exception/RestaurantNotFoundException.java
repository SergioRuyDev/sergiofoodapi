package com.sergioruy.sergiofoodapi.domain.exception;

public class RestaurantNotFoundException extends EntityNotFoundException {

    private static final long serialUID = 1L;

    public RestaurantNotFoundException(String message) {
        super(message);
    }

    public RestaurantNotFoundException(Long restaurantId) {
        this(String.format("Not exist Restaurant with code %d", restaurantId));
    }
}
