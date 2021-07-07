package com.sergioruy.sergiofoodapi.domain.service;

import com.sergioruy.sergiofoodapi.domain.exception.EntityNotFoundException;
import com.sergioruy.sergiofoodapi.domain.exception.EntityUsedException;
import com.sergioruy.sergiofoodapi.domain.model.State;
import com.sergioruy.sergiofoodapi.domain.repository.StateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class RegisterStateService {

    @Autowired
    private StateRepository stateRepository;

    public State save(State state) {
        return stateRepository.save(state);
    }

    public void delete(Long stateId) {
        try {
            stateRepository.remove(stateId);
        } catch (EmptyResultDataAccessException e) {
            throw new EntityNotFoundException(String.format("Not exist State wit code %d", stateId));
        } catch (DataIntegrityViolationException e) {
            throw new EntityUsedException(String.format("State of code %d is in use and cannot be removed", stateId));
        }
    }
}
