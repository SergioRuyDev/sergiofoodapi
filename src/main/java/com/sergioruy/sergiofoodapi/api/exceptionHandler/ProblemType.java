package com.sergioruy.sergiofoodapi.api.exceptionHandler;

import lombok.Getter;

@Getter
public enum ProblemType {

    ENTITY_NOT_FOUND("entity-not-found", "Entity not found");

    private String title;
    private String uri;

    ProblemType(String uri, String title) {
        this.uri = "https://sergiofood.com.br" + uri;
        this.title = title;
    }
}
