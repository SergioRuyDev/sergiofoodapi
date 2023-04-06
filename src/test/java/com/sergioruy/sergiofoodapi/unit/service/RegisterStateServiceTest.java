package com.sergioruy.sergiofoodapi.unit.service;

import com.sergioruy.sergiofoodapi.domain.exception.KitchenNotFoundException;
import com.sergioruy.sergiofoodapi.domain.exception.StateNotFoundException;
import com.sergioruy.sergiofoodapi.domain.model.Kitchen;
import com.sergioruy.sergiofoodapi.domain.model.State;
import com.sergioruy.sergiofoodapi.domain.repository.StateRepository;
import com.sergioruy.sergiofoodapi.domain.service.RegisterStateService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.EmptyResultDataAccessException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.notNull;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class RegisterStateServiceTest {

    @Mock
    private StateRepository stateRepository;

    @InjectMocks
    private RegisterStateService stateService;

    private State state;

    @BeforeEach
    public void setUp() {
        state = new State();
        state.setId(1L);
        state.setName("New York");
    }

    @DisplayName("Unit test for save State method")
    @Test
    public void givenStateObject_whenSaveState_thenReturnStateObject() {
        given(stateRepository.save(state)).willReturn(state);

        State savedState = stateService.save(state);

        assertThat(savedState).isNotNull();
    }

    @DisplayName("Unit test for save State Find or Fail method and throws Exception")
    @Test
    public void given_stateWithNullId_whenFindOrFailCall_thenThrowsException() {
        State state1 = new State();
        state1.setId(null);
        assertThrows(StateNotFoundException.class, () -> {
            stateService.findOrFail(state1.getId());
        });
    }

    @DisplayName("Unit test for delete state by Id")
    @Test
    public void givenStateId_whenDeleteState_thenDoNothing() {
        long stateId = 1L;
        willDoNothing().given(stateRepository).deleteById(1L);

        stateService.delete(1L);
        //then - verify the result
        verify(stateRepository, times(1)).deleteById(stateId);

    }

//    @DisplayName("Unit test for delete state by Id")
//    @Test
//    public void givenStateId_whenDeleteState_thenThrowsException() {
//        State state1 = new State();
//        state1.setId(null);
//
//        assertThrows(EmptyResultDataAccessException.class, () -> {
//            stateService.delete();
//        });
//    } todo
}
