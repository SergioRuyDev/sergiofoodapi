package com.sergioruy.sergiofoodapi.unit.service;

import com.sergioruy.sergiofoodapi.domain.exception.EntityUsedException;
import com.sergioruy.sergiofoodapi.domain.exception.KitchenNotFoundException;
import com.sergioruy.sergiofoodapi.domain.model.Kitchen;
import com.sergioruy.sergiofoodapi.domain.repository.KitchenRepository;
import com.sergioruy.sergiofoodapi.domain.service.RegisterKitchenService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class RegisterKitchenServiceTest {

    @Mock
    private KitchenRepository kitchenRepository;

    @InjectMocks
    private RegisterKitchenService kitchenService;

    private Kitchen kitchen;

    @BeforeEach
    public void setUp() {
        kitchen = new Kitchen();
        kitchen.setId(1L);
        kitchen.setName("Italian");
    }

    @DisplayName("Unit test for save Kitchen method")
    @Test
    void givenKitchenObject_whenSaveKitchen_thenReturnKitchenObject() {
        given(kitchenRepository.save(kitchen)).willReturn(kitchen);

        Kitchen savedKitchen = kitchenService.save(kitchen);

        assertThat(savedKitchen).isNotNull();
    }

    @DisplayName("Unit test Kitchen Find or Fail method and throws Exception")
    @Test
    void given_kitchenWithNullId_whenFindOrFailCall_thenThrowsException() {
        Kitchen kitchen1 = new Kitchen();
        kitchen1.setId(null);
        assertThrows(KitchenNotFoundException.class, () -> {
            kitchenService.findOrFail(kitchen1.getId());
        });
    }

    @DisplayName("Unit test for delete kitchen by Id")
    @Test
    void givenKitchenId_whenDeleteKitchen_thenDoNothing() {
        willDoNothing().given(kitchenRepository).deleteById(kitchen.getId());

        kitchenService.delete(kitchen.getId());
        //then - verify the result
        verify(kitchenRepository, times(1)).deleteById(kitchen.getId());
    }

    @DisplayName("Unit test for delete kitchen by Id in the negative scenario")
    @Test
    void delete_NonExistentKitchenId_KitchenNotFoundException() {
        // given
        doThrow(EmptyResultDataAccessException.class).when(kitchenRepository).deleteById(kitchen.getId());

        // when + then
        assertThrows(KitchenNotFoundException.class, () -> kitchenService.delete(kitchen.getId()));
    }

    @DisplayName("Unit test for delete kitchen by Id in the negative scenario, throw DataIntegrationException")
    @Test
    void delete_KitchenInUse_EntityUsedException() {
        // given
        doThrow(DataIntegrityViolationException.class).when(kitchenRepository).deleteById(kitchen.getId());

        // when + then
        EntityUsedException ex = assertThrows(EntityUsedException.class, () -> kitchenService.delete(kitchen.getId()));
        assertEquals(String.format(RegisterKitchenService.MSG_KITCHEN_IN_USE, kitchen.getId()), ex.getMessage());
    }
}
