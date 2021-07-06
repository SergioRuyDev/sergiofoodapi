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

@RestController
@RequestMapping(value = "/kitchens")
public class KitchenController {

    @Autowired
    private KitchenRepository kitchenRepository;

    @Autowired
    private RegisterKitchenService registerKitchen;

    @GetMapping
    public List<Kitchen> list() {
        return kitchenRepository.list();
    }

    @GetMapping("/{kitchenId}")
    public ResponseEntity<Kitchen> search(@PathVariable Long kitchenId) {
        Kitchen kitchen =  kitchenRepository.find(kitchenId);

        if (kitchen != null) {
            return ResponseEntity.ok(kitchen);
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Kitchen add(@RequestBody Kitchen kitchen) {
        return registerKitchen.save(kitchen);
    }

    @PutMapping("/{kitchenId}")
    public ResponseEntity<Kitchen> update(@PathVariable Long kitchenId, @RequestBody Kitchen kitchen) {
        Kitchen currentKitchen = kitchenRepository.find(kitchenId);

        if (currentKitchen != null) {
//            currentKitchen.setName(kitchen.getName());
            BeanUtils.copyProperties(kitchen, currentKitchen, "id");
            currentKitchen = registerKitchen.save(currentKitchen);
            return ResponseEntity.ok(currentKitchen);
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{kitchenId}")
    public ResponseEntity<Kitchen> remove(@PathVariable Long kitchenId) {
            try {
                registerKitchen.delete(kitchenId);
                return ResponseEntity.noContent().build();

            } catch (EntityNotFoundException e) {
                return ResponseEntity.notFound().build();

            } catch (EntityUsedException e) {
                return ResponseEntity.status(HttpStatus.CONFLICT).build();
            }
    }
}
