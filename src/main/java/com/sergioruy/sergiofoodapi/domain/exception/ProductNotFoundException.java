package com.sergioruy.sergiofoodapi.domain.exception;

public class ProductNotFoundException extends EntityNotFoundException {

    private static final long serialVersionUID = 1L;

    public ProductNotFoundException(String message) {
        super(message);
    }

    public ProductNotFoundException(Long restaurantId, Long productId) {
        this(String.format("Not exist Product registered with code %d for restaurant with code %d",
                productId, restaurantId));
    }
}
