package com.sergioruy.sergiofoodapi.api.assembler;

import com.sergioruy.sergiofoodapi.api.model.StateMother;
import com.sergioruy.sergiofoodapi.domain.model.State;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class StateModelAssembler {

    @Autowired
    private ModelMapper modelMapper;

    public StateMother toModel(State state) {
        return modelMapper.map(state, StateMother.class);
    }

    public List<StateMother> toCollectionModel(List<State> states) {
        return states.stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }
}
