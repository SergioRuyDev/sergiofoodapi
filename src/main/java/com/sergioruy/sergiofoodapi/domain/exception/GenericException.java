package com.sergioruy.sergiofoodapi.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class GenericException extends RuntimeException {

    private static final long serialUID = 1L;

    public GenericException(String message) {
        super(message);
    }
}
