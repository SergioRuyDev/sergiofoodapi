package com.sergioruy.sergiofoodapi.api.controller;

import com.sergioruy.sergiofoodapi.api.assembler.RestaurantInputDisassembler;
import com.sergioruy.sergiofoodapi.api.assembler.RestaurantModelAssembler;
import com.sergioruy.sergiofoodapi.api.model.RestaurantModel;
import com.sergioruy.sergiofoodapi.api.model.input.RestaurantInput;
import com.sergioruy.sergiofoodapi.domain.exception.BusinessException;
import com.sergioruy.sergiofoodapi.domain.exception.CityNotFoundException;
import com.sergioruy.sergiofoodapi.domain.exception.KitchenNotFoundException;
import com.sergioruy.sergiofoodapi.domain.model.Restaurant;
import com.sergioruy.sergiofoodapi.domain.repository.RestaurantRepository;
import com.sergioruy.sergiofoodapi.domain.service.RegisterRestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/restaurants")
public class RestaurantController {

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private RegisterRestaurantService restaurantService;

    @Autowired
    private RestaurantModelAssembler restaurantModelAssembler;

    @Autowired
    private RestaurantInputDisassembler restaurantInputDisassembler;

    @GetMapping
    public List<RestaurantModel> list() {
        return restaurantModelAssembler.toCollectionModel(restaurantRepository.findAll());
    }

    @GetMapping("/{restaurantId}")
    public RestaurantModel search(@PathVariable Long restaurantId) {
        Restaurant restaurant = restaurantService.findOrFail(restaurantId);

        return restaurantModelAssembler.toModel(restaurant);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RestaurantModel add(@RequestBody @Valid RestaurantInput restaurantInput) {
        try {
            Restaurant restaurant = restaurantInputDisassembler.toDomainObject(restaurantInput);

            return restaurantModelAssembler.toModel(restaurantService.save(restaurant));
        } catch (KitchenNotFoundException e) {
            throw new BusinessException(e.getMessage(), e);
        }
    }

    @PutMapping("/{restaurantId}")
    public RestaurantModel update(@PathVariable Long restaurantId, @RequestBody @Valid RestaurantInput restaurantInput) {
        try {
//            Restaurant restaurant = restaurantInputDisassembler.toDomainObject(restaurantInput);

            Restaurant currentRestaurant = restaurantService.findOrFail(restaurantId);

            restaurantInputDisassembler.copyToDomainObject(restaurantInput, currentRestaurant);

//            BeanUtils.copyProperties(restaurant, currentRestaurant, "id", "paymentMethods", "address"
//                    , "dateRegister", "products");

            return restaurantModelAssembler.toModel(restaurantService.save(currentRestaurant));
        } catch (KitchenNotFoundException | CityNotFoundException e) {
            throw new BusinessException(e.getMessage());
        }
    }

    @PutMapping("/{restaurantId}/active")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void activate(@PathVariable Long restaurantId) {
        restaurantService.activate(restaurantId);
    }

    @DeleteMapping("/{restaurantId}/active")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deactivate(@PathVariable Long restaurantId) {
        restaurantService.deactivate(restaurantId);
    }

    @PutMapping("{restaurantId}/opened")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void open(@PathVariable Long restaurantId) {
        restaurantService.open(restaurantId);
    }

    @PutMapping("/{restaurantId}/closed")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void close(@PathVariable Long restaurantId) {
        restaurantService.close(restaurantId);
    }
}

//    @PatchMapping("/{restaurantId}")
//    public Restaurant partialUpdate(@PathVariable Long restaurantId, @RequestBody Map<String, Object> fields, HttpServletRequest request) {
//        Restaurant currentRestaurant = restaurantService.findOrFail(restaurantId);
//
//        merge(fields, currentRestaurant, request);
//
//        return update(restaurantId, currentRestaurant);
//    }
//
//    private void merge(Map<String, Object> dataOrigin, Restaurant restaurantDestiny, HttpServletRequest request) {
//        ServletServerHttpRequest serverHttpRequest = new ServletServerHttpRequest(request);

//        try {
//            ObjectMapper objectMapper = new ObjectMapper();
//            objectMapper.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, true);
//            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true);
//
//            Restaurant restaurantOrigin = objectMapper.convertValue(dataOrigin, Restaurant.class);
//
//            dataOrigin.forEach((nameProperty, valueProperty) -> {
//                Field field = ReflectionUtils.findField(Restaurant.class, nameProperty);
//                field.setAccessible(true);
//
//                Object newValue = ReflectionUtils.getField(field, restaurantOrigin);
//
////            System.out.println(nameProperty + " = " + valueProperty + " = " + newValue);
//
//                ReflectionUtils.setField(field, restaurantDestiny, newValue);
//            });
//        } catch (IllegalArgumentException e) {
//            Throwable rootCause = ExceptionUtils.getRootCause(e);
//            throw new HttpMessageNotReadableException(e.getMessage(), rootCause, serverHttpRequest);
//        }
//    }