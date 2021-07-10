package com.sergioruy.sergiofoodapi.domain.repository;

import com.sergioruy.sergiofoodapi.domain.model.Kitchen;

import java.util.List;

public interface KitchenRepository {

    List<Kitchen> list();
    List<Kitchen> consultByName(String name);
    Kitchen find(Long id);
    Kitchen save(Kitchen kitchen);
    void remove(Long id);
}
