package com.sergioruy.sergiofoodapi.domain.repository;

import com.sergioruy.sergiofoodapi.domain.model.Kitchen;

import java.util.List;

public interface KitchenRepository {

    List<Kitchen> all();
    Kitchen findById(Long id);
    Kitchen add(Kitchen kitchen);
    void remove(Kitchen kitchen);
}
