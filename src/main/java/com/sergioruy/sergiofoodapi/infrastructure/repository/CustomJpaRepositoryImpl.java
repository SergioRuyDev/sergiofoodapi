package com.sergioruy.sergiofoodapi.infrastructure.repository;

import com.sergioruy.sergiofoodapi.domain.repository.CustomJpaRepository;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import javax.persistence.EntityManager;
import java.util.Optional;

public class CustomJpaRepositoryImpl<T, ID> extends SimpleJpaRepository<T, ID> implements CustomJpaRepository<T, ID> {

    private EntityManager manager;

    public CustomJpaRepositoryImpl(JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager) {
        super(entityInformation, entityManager);
    }

    @Override
    public Optional<T> findFirst() {
        var jpql = "from " + getDomainClass().getName();

        T entity = manager.createQuery(jpql, getDomainClass())
                .setMaxResults(1)
                .getSingleResult();

        return Optional.ofNullable(entity);
    }
}
