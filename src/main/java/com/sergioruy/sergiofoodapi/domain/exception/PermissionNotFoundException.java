package com.sergioruy.sergiofoodapi.domain.exception;

public class PermissionNotFoundException extends EntityNotFoundException {

    private static final long serialUID = 1L;

    public PermissionNotFoundException(String message) {
        super(message);
    }

    public PermissionNotFoundException(Long permissionId) {
        this(String.format("Not exist permission with code %d", permissionId));
    }
}
