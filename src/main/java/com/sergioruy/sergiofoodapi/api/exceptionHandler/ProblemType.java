package com.sergioruy.sergiofoodapi.api.exceptionHandler;

import lombok.Getter;

@Getter
public enum ProblemType {

    MESSAGE_UNREADABLE("/message-unreadable", "Message unreadable"),

    ENTITY_IN_USE("/entity-in-use", "Entity is in use"),

    ERROR_BUSINESS("/error-business", "Illegal rule violation"),

    INVALID_PARAMETER("/invalid-parameter", "Invalid Parameter"),

    RESOURCE_NOT_FOUND("/resource-not-found", "Resource not found"),

    ERROR_OF_SYSTEM("/error-of-system", "Error of system"),

    INVALID_DATA("/invalid-data", "invalid data");

    private String title;
    private String uri;

    ProblemType(String uri, String title) {
        this.uri = "https://sergiofood.com" + uri;
        this.title = title;
    }
}
