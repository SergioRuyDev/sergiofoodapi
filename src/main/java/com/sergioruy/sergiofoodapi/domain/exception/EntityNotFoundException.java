package com.sergioruy.sergiofoodapi.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

//@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Entity not found.")
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public abstract class EntityNotFoundException extends BusinessException {

    private static final long serialVersionUID = 1L;

    public EntityNotFoundException(String message) {
        super(message);
    }

}
