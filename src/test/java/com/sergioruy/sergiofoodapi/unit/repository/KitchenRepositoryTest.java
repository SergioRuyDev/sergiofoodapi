package com.sergioruy.sergiofoodapi.unit.repository;

import com.sergioruy.sergiofoodapi.domain.model.Kitchen;
import com.sergioruy.sergiofoodapi.domain.repository.KitchenRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

@DataJpaTest
public class KitchenRepositoryTest {

    @Autowired
    private KitchenRepository kitchenRepository;

    private Kitchen kitchen;


    @BeforeEach
    void setUp() {
        kitchen = Kitchen.builder()
                .name("japanese")
                .build();
    }

    //Junit test for findAll Kitchens operation
    @DisplayName("Junit test for findAll Kitchens operation")
    @Test
    public void given_when_then() {

        //given - is a precondition or a setup
        Kitchen kitchen2 = Kitchen.builder()
                .name("brazilian")
                .build();
        kitchenRepository.save(kitchen);
        kitchenRepository.save(kitchen2);

        //when - is the action or the behavior we are going to test
        List<Kitchen> kitchenList = kitchenRepository.findAll();

        //then - verify the result
        Assertions.assertThat(kitchenList).isNotNull();
        Assertions.assertThat(kitchenList).isEqualTo(2);
    }
}
