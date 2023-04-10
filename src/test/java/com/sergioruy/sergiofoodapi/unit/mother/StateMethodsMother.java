package com.sergioruy.sergiofoodapi.unit.mother;

import com.sergioruy.sergiofoodapi.domain.model.State;
import lombok.NoArgsConstructor;

import java.util.Optional;

@NoArgsConstructor
public class StateMethodsMother {

    public static State getStateModel() {
        State state = new State();
        state.setId(1L);
        state.setName("New York");
        return state;
    }

    public static Optional<State> getOptionalState() {
        return Optional.of(getStateModel());
    }

    public static Optional<State> getEmptyStateModel() {
        return Optional.empty();
    }
}
