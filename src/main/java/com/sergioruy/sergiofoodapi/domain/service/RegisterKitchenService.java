package com.sergioruy.sergiofoodapi.domain.service;

import com.sergioruy.sergiofoodapi.domain.exception.EntityUsedException;
import com.sergioruy.sergiofoodapi.domain.exception.KitchenNotFoundException;
import com.sergioruy.sergiofoodapi.domain.model.Kitchen;
import com.sergioruy.sergiofoodapi.domain.repository.KitchenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class RegisterKitchenService {

    private static final String MSG_KITCHEN_IN_USE = "Kitchen of code %d is in use and cannot be removed";


    @Autowired
    private KitchenRepository kitchenRepository;

    public Kitchen save(Kitchen kitchen) {
        return kitchenRepository.save(kitchen);
    }

    public void delete(Long kitchenId) {
        try {
            kitchenRepository.deleteById(kitchenId);

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
