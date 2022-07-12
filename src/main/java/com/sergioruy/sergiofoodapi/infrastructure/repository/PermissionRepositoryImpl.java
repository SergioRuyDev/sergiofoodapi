package com.sergioruy.sergiofoodapi.infrastructure.repository;

import com.sergioruy.sergiofoodapi.domain.model.Permission;
import com.sergioruy.sergiofoodapi.domain.repository.PermissionRepository;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Component
public class PermissionRepositoryImpl {

    @PersistenceContext
    private EntityManager manager;

    public List<Permission> list() {
        return manager.createQuery("from Permission", Permission.class).getResultList();
    }

    public Permission find(Long id) {
        return manager.find(Permission.class, id);
    }

    @Transactional
    public Permission save(Permission permission) {
        return manager.merge(permission);
    }

    @Transactional
    public void Remove(Permission permission) {
        permission = find(permission.getId());
        manager.remove(permission);
    }
}
