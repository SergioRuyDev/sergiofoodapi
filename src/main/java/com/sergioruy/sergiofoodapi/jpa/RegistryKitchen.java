package com.sergioruy.sergiofoodapi.jpa;

import com.sergioruy.sergiofoodapi.domain.model.Kitchen;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Component
public class RegistryKitchen {

    @PersistenceContext
    private EntityManager manager;

    public List<Kitchen> kitchenList() {
        return manager.createQuery("from Kitchen", Kitchen.class).getResultList();
    }

    @Transactional
    public Kitchen add(Kitchen kitchen) {
        return manager.merge(kitchen);
    }
}
