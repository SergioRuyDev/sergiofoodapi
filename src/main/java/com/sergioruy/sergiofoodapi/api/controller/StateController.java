package com.sergioruy.sergiofoodapi.api.controller;

import com.sergioruy.sergiofoodapi.domain.model.State;
import com.sergioruy.sergiofoodapi.domain.repository.StateRepository;
import com.sergioruy.sergiofoodapi.domain.service.RegisterStateService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/states")
public class StateController {

    @Autowired
    private StateRepository stateRepository;

    @Autowired
    private RegisterStateService registerState;

    @GetMapping
    public List<State> list() {
        return stateRepository.list();
    }

    @GetMapping("/{stateId}")
    public ResponseEntity<State> search(@PathVariable Long stateId) {
        State state = stateRepository.find(stateId);

        if (state != null) {
            return ResponseEntity.ok(state);
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{stateId}")
    public ResponseEntity<State> update(Long stateId, @RequestBody State state) {
        State curretState = stateRepository.find(stateId);

        if (curretState != null) {
            BeanUtils.copyProperties(state, curretState, "id");
            curretState = registerState.save(curretState);
            return ResponseEntity.ok(curretState);
        }

        return ResponseEntity.notFound().build();
    }
}
