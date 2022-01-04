package com.sergioruy.sergiofoodapi.api.controller;

import com.sergioruy.sergiofoodapi.api.assembler.StateInputDisassembler;
import com.sergioruy.sergiofoodapi.api.assembler.StateModelAssembler;
import com.sergioruy.sergiofoodapi.api.model.StateModel;
import com.sergioruy.sergiofoodapi.api.model.input.StateInput;
import com.sergioruy.sergiofoodapi.domain.model.State;
import com.sergioruy.sergiofoodapi.domain.repository.StateRepository;
import com.sergioruy.sergiofoodapi.domain.service.RegisterStateService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/states")
public class StateController {

    @Autowired
    private StateRepository stateRepository;

    @Autowired
    private RegisterStateService registerState;

    @Autowired
    private StateModelAssembler stateModelAssembler;

    @Autowired
    private StateInputDisassembler stateInputDisassembler;

    @GetMapping
    public List<StateModel> list() {
        return stateModelAssembler.toCollectionModel(stateRepository.findAll());
    }

    @GetMapping("/{stateId}")
    public StateModel search(@PathVariable Long stateId) {
        State state = registerState.findOrFail(stateId);

        return stateModelAssembler.toModel(state);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public StateModel add(@RequestBody StateInput stateInput) {
        State state = stateInputDisassembler.toDomainObject(stateInput);
        return stateModelAssembler.toModel(registerState.save(state));
    }

    @PutMapping("/{stateId}")
    public StateModel update(@PathVariable Long stateId, @RequestBody StateInput stateInput) {
        State currentState = registerState.findOrFail(stateId);

        stateInputDisassembler.copyToDomainObject(stateInput, currentState);
//            BeanUtils.copyProperties(state, currentState, "id");

            return stateModelAssembler.toModel(registerState.save(currentState));
    }

    @DeleteMapping("/{stateId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remove(@PathVariable Long stateId) {
        registerState.delete(stateId);
    }
}
