package com.sergioruy.sergiofoodapi.api.controller;

import com.sergioruy.sergiofoodapi.domain.exception.EntityNotFoundException;
import com.sergioruy.sergiofoodapi.domain.exception.EntityUsedException;
import com.sergioruy.sergiofoodapi.domain.model.Kitchen;
import com.sergioruy.sergiofoodapi.domain.repository.KitchenRepository;
import com.sergioruy.sergiofoodapi.domain.service.RegisterKitchenService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/kitchens")
public class KitchenController {

    @Autowired
    private KitchenRepository kitchenRepository;

    @Autowired
    private RegisterKitchenService registerKitchen;

    @GetMapping
    public List<Kitchen> list() {
        return kitchenRepository.findAll();
    }

    @GetMapping("/{kitchenId}")
    public ResponseEntity<Kitchen> search(@PathVariable Long kitchenId) {
        Optional<Kitchen> kitchen =  kitchenRepository.findById(kitchenId);

        return kitchen.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
/*
        if (kitchen.isPresent()) {
            return ResponseEntity.ok(kitchen.get());
        }
*/

    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Kitchen add(@RequestBody Kitchen kitchen) {
        return registerKitchen.save(kitchen);
    }

    @PutMapping("/{kitchenId}")
    public ResponseEntity<Kitchen> update(@PathVariable Long kitchenId, @RequestBody Kitchen kitchen) {
        Optional<Kitchen> currentKitchen = kitchenRepository.findById(kitchenId);

        if (currentKitchen.isPresent()) {
//            currentKitchen.setName(kitchen.getName());
            BeanUtils.copyProperties(kitchen, currentKitchen.get(), "id");
            Kitchen kitchenSaved = registerKitchen.save(currentKitchen.get());
            return ResponseEntity.ok(kitchenSaved);
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{kitchenId}")
    public ResponseEntity<?> remove(@PathVariable Long kitchenId) {
            try {
                registerKitchen.delete(kitchenId);
                return ResponseEntity.noContent().build();

            } catch (EntityNotFoundException e) {
                return ResponseEntity.notFound().build();

            } catch (EntityUsedException e) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
            }
    }
}
