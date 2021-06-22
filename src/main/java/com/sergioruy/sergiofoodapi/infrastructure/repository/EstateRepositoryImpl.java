package com.sergioruy.sergiofoodapi.infrastructure.repository;

import com.sergioruy.sergiofoodapi.domain.model.Estate;
import com.sergioruy.sergiofoodapi.domain.repository.EstateRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Component
public class EstateRepositoryImpl implements EstateRepository {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<Estate> list() {
        return manager.createQuery("from Estate", Estate.class).getResultList();
    }

    @Override
    public Estate find(Long id) {
        return manager.find(Estate.class, id);
    }

    @Transactional
    @Override
    public Estate save(Estate estate) {
        return manager.merge(estate);
    }

    @Transactional
    @Override
    public void remove(Estate estate) {
        estate = find(estate.getId());
        manager.remove(estate);

    }
}
