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

    private static final String MSG_STATE_IN_USE = "State of code %d is in use and cannot be removed";

    private static final String MSG_STATE_NOT_FOUND = "Not exist State with code %d";

    @Autowired
    private StateRepository stateRepository;

    public State save(State state) {
        return stateRepository.save(state);
    }

    public void delete(Long stateId) {
        try {
            stateRepository.deleteById(stateId);

        } catch (EmptyResultDataAccessException e) {
            throw new EntityNotFoundException(String.format(MSG_STATE_NOT_FOUND, stateId));

        } catch (DataIntegrityViolationException e) {
            throw new EntityUsedException(String.format(MSG_STATE_IN_USE, stateId));
        }
    }

    public State findOrFail(Long stateId) {
        return stateRepository.findById(stateId)
                .orElseThrow(() -> new EntityNotFoundException(
                        String.format(MSG_STATE_NOT_FOUND, stateId)));
    }
}
