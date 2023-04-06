package com.sergioruy.sergiofoodapi.unit.service;

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

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
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
    public void givenKitchenObject_whenSaveKitchen_thenReturnKitchenObject() {
        given(kitchenRepository.save(kitchen)).willReturn(kitchen);

        Kitchen savedKitchen = kitchenService.save(kitchen);

        assertThat(savedKitchen).isNotNull();
    }

    @DisplayName("Unit test for save Kitchen Find or Fail method and throws Exception")
    @Test
    public void given_kitchenWithNullId_whenFindOrFailCall_thenThrowsException() {
        Kitchen kitchen1 = new Kitchen();
        kitchen1.setId(null);
        assertThrows(KitchenNotFoundException.class, () -> {
            kitchenService.findOrFail(kitchen1.getId());
        });
    }

    @DisplayName("Unit test for delete kitchen by Id")
    @Test
    public void givenKitchenId_whenDeleteKitchen_thenDoNothing() {
        long kitchenId = 1L;
        willDoNothing().given(kitchenRepository).deleteById(1L);

        kitchenService.delete(1L);
        //then - verify the result
        verify(kitchenRepository, times(1)).deleteById(kitchenId);

    }
}
