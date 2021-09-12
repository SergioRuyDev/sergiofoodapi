package com.sergioruy.sergiofoodapi.api.controller;

import com.sergioruy.sergiofoodapi.domain.model.Kitchen;
import com.sergioruy.sergiofoodapi.domain.repository.KitchenRepository;
import com.sergioruy.sergiofoodapi.domain.service.RegisterKitchenService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
        return kitchenRepository.findAll();
    }

    @GetMapping("/{kitchenId}")
    public Kitchen search(@PathVariable Long kitchenId) {
        return registerKitchen.findOrFail(kitchenId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Kitchen add(@RequestBody Kitchen kitchen) {
        return registerKitchen.save(kitchen);
    }

    @PutMapping("/{kitchenId}")
    public Kitchen update(@PathVariable Long kitchenId, @RequestBody Kitchen kitchen) {
        Kitchen currentKitchen = registerKitchen.findOrFail(kitchenId);

        BeanUtils.copyProperties(kitchen, currentKitchen, "id");

        return registerKitchen.save(currentKitchen);
    }

    @DeleteMapping("/{kitchenId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remove(@PathVariable Long kitchenId) {
        registerKitchen.delete(kitchenId);
    }
}
