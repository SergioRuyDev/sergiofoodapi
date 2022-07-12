package com.sergioruy.sergiofoodapi.domain.service;

import com.sergioruy.sergiofoodapi.domain.exception.PermissionNotFoundException;
import com.sergioruy.sergiofoodapi.domain.model.Permission;
import com.sergioruy.sergiofoodapi.domain.repository.PermissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegisterPermissionService {

    @Autowired
    private PermissionRepository permissionRepository;

    public Permission findOrFail(Long permissionId) {
        return permissionRepository.findById(permissionId).orElseThrow(
                () -> new PermissionNotFoundException(permissionId));
    }
}
