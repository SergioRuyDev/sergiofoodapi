package com.sergioruy.sergiofoodapi.domain.service;

import com.sergioruy.sergiofoodapi.domain.exception.EntityUsedException;
import com.sergioruy.sergiofoodapi.domain.exception.KitchenNotFoundException;
import com.sergioruy.sergiofoodapi.domain.model.Kitchen;
import com.sergioruy.sergiofoodapi.domain.repository.KitchenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RegisterKitchenService {

    private static final String MSG_KITCHEN_IN_USE = "Kitchen of code %d is in use and cannot be removed";


    @Autowired
    private KitchenRepository kitchenRepository;

    @Transactional
    public Kitchen save(Kitchen kitchen) {
        return kitchenRepository.save(kitchen);
    }

    @Transactional
    public void delete(Long kitchenId) {
        try {
            kitchenRepository.deleteById(kitchenId);
            kitchenRepository.flush();

        } catch (EmptyResultDataAccessException e) {
            throw new KitchenNotFoundException(kitchenId);

        } catch (DataIntegrityViolationException e) {
            throw new EntityUsedException(
                    String.format(MSG_KITCHEN_IN_USE, kitchenId));
        }
    }

    public Kitchen findOrFail(Long kitchenId) {
        return kitchenRepository.findById(kitchenId)
                .orElseThrow(() -> new KitchenNotFoundException(kitchenId));
    }
}
