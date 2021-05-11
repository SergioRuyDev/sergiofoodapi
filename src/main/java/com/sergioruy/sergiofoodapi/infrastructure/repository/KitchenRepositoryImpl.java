package com.sergioruy.sergiofoodapi.infrastructure.repository;

import com.sergioruy.sergiofoodapi.domain.model.Kitchen;
import com.sergioruy.sergiofoodapi.domain.repository.KitchenRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Component
public class KitchenRepositoryImpl implements KitchenRepository {
    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<Kitchen> kitchenList() {
        return manager.createQuery("from Kitchen", Kitchen.class).getResultList();
    }

    @Override
    public Kitchen find(Long id) {
        return manager.find(Kitchen.class, id);
    }

    @Override
    @Transactional
    public Kitchen save(Kitchen kitchen) {
        return manager.merge(kitchen);
    }

    @Override
    @Transactional
    public void remove(Kitchen kitchen) {
        kitchen = find(kitchen.getId());
        manager.remove(kitchen);
    }
}
