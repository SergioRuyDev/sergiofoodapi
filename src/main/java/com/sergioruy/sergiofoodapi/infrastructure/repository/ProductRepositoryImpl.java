package com.sergioruy.sergiofoodapi.infrastructure.repository;

import com.sergioruy.sergiofoodapi.domain.model.PhotoProduct;
import com.sergioruy.sergiofoodapi.domain.repository.ProductRepositoryQueries;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class ProductRepositoryImpl implements ProductRepositoryQueries {

    @PersistenceContext
    private EntityManager manager;

    @Transactional
    @Override
    public PhotoProduct save(PhotoProduct photo) {
        return manager.merge(photo);
    }

    @Transactional
    @Override
    public void delete(PhotoProduct photo) {
        manager.remove(photo);
    }
}
