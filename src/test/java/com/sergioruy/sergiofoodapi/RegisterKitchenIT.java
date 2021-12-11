package com.sergioruy.sergiofoodapi;

import com.sergioruy.sergiofoodapi.domain.model.Kitchen;
import com.sergioruy.sergiofoodapi.domain.repository.KitchenRepository;
import com.sergioruy.sergiofoodapi.util.DatabaseCleaner;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.hasSize;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("/application-test.properties")
class RegisterKitchenIT {

    @LocalServerPort
    private int port;

    @Autowired
    private DatabaseCleaner databaseCleaner;

    @Autowired
    private KitchenRepository kitchenRepository;

    @BeforeEach
    public void setUp() {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        RestAssured.port = port;
        RestAssured.basePath = "/kitchens";

        databaseCleaner.clearTables();
        prepareData();
    }

//    @Autowired
//    private RegisterKitchenService registerKitchen;
//
//    @Test
//    public void testRegisterKitchenNoName() {
////        //scenario
////        Kitchen newKitchen = new Kitchen();;
////        newKitchen.setName("Chinese");
////
////        //action
////        newKitchen = registerKitchen.save(newKitchen);
////
////        //validation
////        assertThat(newKitchen).isNotNull();
////        assertThat(newKitchen.getId()).isNotNull();
//
//        Kitchen newKitchen = new Kitchen();
//        newKitchen.setName(null);
//
//        ConstraintViolationException errorExpected =
//                Assertions.assertThrows(ConstraintViolationException.class, () -> {
//                   registerKitchen.save(newKitchen);
//                });
//
//        assertThat(errorExpected).isNotNull();
//    }
//
//    @Test
//    public void fail_When_Delete_KitchenInUsed() {
//        Kitchen newKitchen = new Kitchen();
//        newKitchen.setId(1L);
//
//        EntityUsedException errorExpected =
//                Assertions.assertThrows(EntityUsedException.class, () -> {
//                    registerKitchen.delete(newKitchen.getId());
//                });
//
//    }
//
//    @Test
//    public void fail_When_Delete_Kitchen_NotFound() {
//
//        EntityNotFoundException errorExpected =
//                Assertions.assertThrows(EntityNotFoundException.class, () -> {
//                    registerKitchen.delete(100L);
//                });
//    }

    @Test
    public void shouldReturnStatus_200_WhenConsultKitchens() {
        given()
                .accept(ContentType.JSON)
        .when()
                .get()
        .then()
                .statusCode(HttpStatus.OK.value());
    }

    @Test
    public void shouldHave2Kitchens_WhenConsultKitchens() {
        given()
                .accept(ContentType.JSON)
        .when()
                .get()
        .then()
                .body("", hasSize(2))
                .body("name", hasItems("Thai", "Indian"));

    }

    @Test
    public void shouldReturnStatus201_WhenRegisterKitchen() {
        Kitchen kitchen = new Kitchen();
        kitchen.setName("Chinese");

        given()
                .body(kitchen)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
        .when()
                .post()
        .then()
                .statusCode(HttpStatus.CREATED.value());
    }

    private void prepareData() {
        Kitchen kitchen1 = new Kitchen();
        kitchen1.setName("Thai");
        kitchenRepository.save(kitchen1);

        Kitchen kitchen2 = new Kitchen();
        kitchen2.setName("American");
        kitchenRepository.save(kitchen2);
    }
}