package com.sergioruy.sergiofoodapi;

import com.sergioruy.sergiofoodapi.domain.model.Kitchen;
import com.sergioruy.sergiofoodapi.domain.repository.KitchenRepository;
import com.sergioruy.sergiofoodapi.util.DatabaseCleaner;
import com.sergioruy.sergiofoodapi.util.ResourceUtils;
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
import static org.hamcrest.Matchers.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("/application-test.properties")
class RegisterKitchenIT {

    private static final int KITCHEN_ID_NONEXISTENT = 100;

    @LocalServerPort
    private int port;

    @Autowired
    private DatabaseCleaner databaseCleaner;

    @Autowired
    private KitchenRepository kitchenRepository;

    private Kitchen americanKitchen;
    private int amountRegisteredKitchens;
    private String jsonCorrectChineseKitchen;

    @BeforeEach
    public void setUp() {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        RestAssured.port = port;
        RestAssured.basePath = "/kitchens";

        jsonCorrectChineseKitchen = ResourceUtils.getContentFromResource(
                "/json/correct/chinese-kitchen.json");

        databaseCleaner.clearTables();
        prepareData();
    }



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
    public void shouldReturnCorrectAmountOfKitchens_WhenConsultKitchens() {
        given()
                .accept(ContentType.JSON)
        .when()
                .get()
        .then()
                .body("", hasSize(amountRegisteredKitchens));

    }

    @Test
    public void shouldReturnStatus201_WhenRegisterKitchen() {
        given()
                .body(jsonCorrectChineseKitchen)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
        .when()
                .post()
        .then()
                .statusCode(HttpStatus.CREATED.value());
    }
//        Kitchen kitchen = new Kitchen();
//        kitchen.setName("Chinese");
//
//        given()
//                .body(kitchen)
//                .contentType(ContentType.JSON)
//                .accept(ContentType.JSON)
//        .when()
//                .post()
//        .then()
//                .statusCode(HttpStatus.CREATED.value());
//    }

    @Test
    public void shouldReturnCorrectResponseStatus_WhenConsultExistedKitchen() {
        given()
                .pathParams("kitchenId", americanKitchen.getId())
                .accept(ContentType.JSON)
        .when()
                .get("/{kitchenId}")
        .then()
                .statusCode(HttpStatus.OK.value())
                .body("name", equalTo(americanKitchen.getName()));

    }

    @Test
    public void shouldReturnStatus404_WhenConsultKitchenNonExistent() {
        given()
                .pathParams("kitchenId", KITCHEN_ID_NONEXISTENT)
                .accept(ContentType.JSON)
        .when()
                .get("/{kitchenId}")
        .then()
                .statusCode(HttpStatus.NOT_FOUND.value());
    }

    private void prepareData() {
        Kitchen thayKitchen = Kitchen.builder()
                .name("Thay")
                .build();
        kitchenRepository.save(thayKitchen);

        Kitchen americanKitchen = Kitchen.builder()
                .name("American")
                .build();
        kitchenRepository.save(americanKitchen);
    }
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