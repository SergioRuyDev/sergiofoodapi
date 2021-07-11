package com.sergioruy.sergiofoodapi.api.controller;

import com.sergioruy.sergiofoodapi.domain.exception.EntityNotFoundException;
import com.sergioruy.sergiofoodapi.domain.exception.EntityUsedException;
import com.sergioruy.sergiofoodapi.domain.model.State;
import com.sergioruy.sergiofoodapi.domain.repository.StateRepository;
import com.sergioruy.sergiofoodapi.domain.service.RegisterStateService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/states")
public class StateController {

    @Autowired
    private StateRepository stateRepository;

    @Autowired
    private RegisterStateService registerState;

    @GetMapping
    public List<State> list() {
        return stateRepository.findAll();
    }

    @GetMapping("/{stateId}")
    public ResponseEntity<State> search(@PathVariable Long stateId) {
        Optional<State> state = stateRepository.findById(stateId);

        return state.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public State add(@RequestBody State state) {
        return registerState.save(state);
    }

    @PutMapping("/{stateId}")
    public ResponseEntity<State> update(@PathVariable Long stateId, @RequestBody State state) {
        State currentState = stateRepository.findById(stateId).orElse(null);

        if (currentState != null) {
            BeanUtils.copyProperties(state, currentState, "id");

            currentState = registerState.save(currentState);
            return ResponseEntity.ok(currentState);
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{stateId}")
    public ResponseEntity<?> remove(@PathVariable Long stateId) {
        try {
            registerState.delete(stateId);
            return ResponseEntity.noContent().build();

        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();

        } catch (EntityUsedException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }
}
