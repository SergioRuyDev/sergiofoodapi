package com.sergioruy.sergiofoodapi.domain.repository;

import com.sergioruy.sergiofoodapi.domain.model.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PermissionRepository extends JpaRepository<Permission, Long> {

    List<Permission> list();
    Permission find(Long id);
    Permission save(Permission permission);
    void Remove(Permission permission);
}
