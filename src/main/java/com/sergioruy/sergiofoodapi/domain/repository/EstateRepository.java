package com.sergioruy.sergiofoodapi.domain.repository;

import com.sergioruy.sergiofoodapi.domain.model.Estate;

import java.util.List;

public interface EstateRepository {

    List<Estate> list();
    Estate find(Long id);
    Estate save(Estate estate);
    void remove(Estate estate);
}
