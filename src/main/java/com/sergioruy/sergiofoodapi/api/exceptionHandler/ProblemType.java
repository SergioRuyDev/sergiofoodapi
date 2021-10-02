package com.sergioruy.sergiofoodapi.api.exceptionHandler;

import lombok.Getter;

@Getter
public enum ProblemType {

    ENTITY_NOT_FOUND("/entity-not-found", "Entity not found"),

    ENTITY_IN_USE("/entity-in-use", "Entity is in use"),

    ERROR_BUSINESS("/error-business", "Illegal rule violation");

    private String title;
    private String uri;

    ProblemType(String uri, String title) {
        this.uri = "https://sergiofood.com.br" + uri;
        this.title = title;
    }
}
