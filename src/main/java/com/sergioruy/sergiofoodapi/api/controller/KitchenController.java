package com.sergioruy.sergiofoodapi.api.controller;

import com.sergioruy.sergiofoodapi.api.assembler.KitchenInputDisassembler;
import com.sergioruy.sergiofoodapi.api.assembler.KitchenModelAssembler;
import com.sergioruy.sergiofoodapi.api.model.KitchenModel;
import com.sergioruy.sergiofoodapi.api.model.input.KitchenInput;
import com.sergioruy.sergiofoodapi.domain.model.Kitchen;
import com.sergioruy.sergiofoodapi.domain.repository.KitchenRepository;
import com.sergioruy.sergiofoodapi.domain.service.RegisterKitchenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping(value = "/kitchens")
public class KitchenController {

    @Autowired
    private KitchenRepository kitchenRepository;

    @Autowired
    private RegisterKitchenService registerKitchen;

    @Autowired
    private KitchenModelAssembler kitchenModelAssembler;

    @Autowired
    private KitchenInputDisassembler kitchenInputDisassembler;

    @GetMapping
    public List<KitchenModel> list() {
        return kitchenModelAssembler.toCollectionModel(kitchenRepository.findAll());
    }

    @GetMapping("/{kitchenId}")
    public KitchenModel search(@PathVariable Long kitchenId) {
        Kitchen kitchen = registerKitchen.findOrFail(kitchenId);

        return kitchenModelAssembler.toModel(kitchen);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public KitchenModel add(@RequestBody @Valid KitchenInput kitchenInput) {
        Kitchen kitchen = kitchenInputDisassembler.toDomainObject(kitchenInput);

        return kitchenModelAssembler.toModel(registerKitchen.save(kitchen));
    }

    @PutMapping("/{kitchenId}")
    public KitchenModel update(@PathVariable Long kitchenId, @RequestBody KitchenInput kitchenInput) {
        Kitchen currentKitchen = registerKitchen.findOrFail(kitchenId);

        kitchenInputDisassembler.copyToDomainObject(kitchenInput, currentKitchen);
//        BeanUtils.copyProperties(kitchen, currentKitchen, "id");

        return kitchenModelAssembler.toModel(registerKitchen.save(currentKitchen));
    }

    @DeleteMapping("/{kitchenId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remove(@PathVariable Long kitchenId) {
        registerKitchen.delete(kitchenId);
    }
}
