package com.sergioruy.sergiofoodapi.domain.exception;

public class GroupNotFoundException extends EntityNotFoundException {

    private static final long serialUID = 1L;


    public GroupNotFoundException(String message) {
        super(message);
    }

    public GroupNotFoundException(Long groupId) {
        this(String.format("Not exist group with code %d", groupId));
    }
}
