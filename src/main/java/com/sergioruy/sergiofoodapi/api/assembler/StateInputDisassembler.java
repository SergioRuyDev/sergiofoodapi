package com.sergioruy.sergiofoodapi.api.assembler;

import com.sergioruy.sergiofoodapi.api.model.input.StateInput;
import com.sergioruy.sergiofoodapi.domain.model.State;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

public class StateInputDisassembler {

    @Autowired
    private ModelMapper modelMapper;

    public State toDomainObject(StateInput stateInput) {
        return modelMapper.map(stateInput, State.class);
    }

    public void copyToDomainObject(StateInput stateInput, State state) {
        modelMapper.map(stateInput, state);
    }
}
