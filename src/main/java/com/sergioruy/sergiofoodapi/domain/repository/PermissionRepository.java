package com.sergioruy.sergiofoodapi.domain.repository;

import com.sergioruy.sergiofoodapi.domain.model.Permission;

import java.util.List;

public interface PermissionRepository {

    List<Permission> list();
    Permission find(Long id);
    Permission save(Permission permission);
    void Remove(Permission permission);
}
