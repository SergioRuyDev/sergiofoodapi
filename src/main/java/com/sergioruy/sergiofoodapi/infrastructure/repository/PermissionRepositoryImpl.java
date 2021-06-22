package com.sergioruy.sergiofoodapi.infrastructure.repository;

import com.sergioruy.sergiofoodapi.domain.model.Permission;
import com.sergioruy.sergiofoodapi.domain.repository.PermissionRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Component
public class PermissionRepositoryImpl implements PermissionRepository {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<Permission> list() {
        return manager.createQuery("from Permission", Permission.class).getResultList();
    }

    @Override
    public Permission find(Long id) {
        return manager.find(Permission.class, id);
    }

    @Transactional
    @Override
    public Permission save(Permission permission) {
        return manager.merge(permission);
    }

    @Transactional
    @Override
    public void Remove(Permission permission) {
        permission = find(permission.getId());
        manager.remove(permission);
    }
}
