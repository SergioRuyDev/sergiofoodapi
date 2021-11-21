package com.sergioruy.sergiofoodapi;

import com.sergioruy.sergiofoodapi.domain.exception.EntityNotFoundException;
import com.sergioruy.sergiofoodapi.domain.exception.EntityUsedException;
import com.sergioruy.sergiofoodapi.domain.model.Kitchen;
import com.sergioruy.sergiofoodapi.domain.service.RegisterKitchenService;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.validation.ConstraintViolationException;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.hasSize;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class RegisterKitchenIT {

    @LocalServerPort
    private int port;

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
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();

        given()
                .basePath("/kitchens")
                .port(port)
                .accept(ContentType.JSON)
        .when()
                .get()
        .then()
                .statusCode(HttpStatus.OK.value());
    }

    @Test
    public void shouldHave4Kitchens_WhenConsultKitchens() {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();

        given()
                .basePath("/kitchens")
                .port(port)
                .accept(ContentType.JSON)
        .when()
                .get()
        .then()
                .body("", hasSize(4))
                .body("name", hasItems("Thai", "Indian"));

    }
}
