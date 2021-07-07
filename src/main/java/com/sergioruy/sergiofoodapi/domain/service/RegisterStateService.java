package com.sergioruy.sergiofoodapi.domain.service;

import com.sergioruy.sergiofoodapi.domain.model.State;
import com.sergioruy.sergiofoodapi.domain.repository.StateRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class RegisterStateService {

    @Autowired
    private StateRepository stateRepository;

    public State save(State state) {
        return stateRepository.save(state);
    }
}
