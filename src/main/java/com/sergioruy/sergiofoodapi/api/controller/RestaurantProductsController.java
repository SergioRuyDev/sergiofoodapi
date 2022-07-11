package com.sergioruy.sergiofoodapi.api.controller;

import com.sergioruy.sergiofoodapi.api.assembler.ProductInputDisassembler;
import com.sergioruy.sergiofoodapi.api.assembler.ProductModelAssembler;
import com.sergioruy.sergiofoodapi.api.model.ProductModel;
import com.sergioruy.sergiofoodapi.api.model.input.ProductInput;
import com.sergioruy.sergiofoodapi.domain.model.Product;
import com.sergioruy.sergiofoodapi.domain.model.Restaurant;
import com.sergioruy.sergiofoodapi.domain.repository.ProductRepository;
import com.sergioruy.sergiofoodapi.domain.service.RegisterProductService;
import com.sergioruy.sergiofoodapi.domain.service.RegisterRestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/restaurants/{restaurantId}/products")
public class RestaurantProductsController {

    @Autowired
    private RegisterRestaurantService restaurantService;

    @Autowired
    private RegisterProductService productService;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductModelAssembler productModelAssembler;

    @Autowired
    private ProductInputDisassembler productInputDisassembler;

    @GetMapping
    public List<ProductModel> list(@PathVariable Long restaurantId) {
        Restaurant restaurant = restaurantService.findOrFail(restaurantId);

        List<Product> allProducts = productRepository.findByRestaurant(restaurant);

        return productModelAssembler.toCollection(allProducts);
    }

    @GetMapping("/{productId}")
    public ProductModel search(@PathVariable Long restaurantId, @PathVariable Long productId) {
        Product product = productService.findOrFail(restaurantId, productId);

        return productModelAssembler.toModel(product);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductModel add(@PathVariable Long restaurantId, @RequestBody @Valid ProductInput productInput) {
        Restaurant restaurant = restaurantService.findOrFail(restaurantId);

        Product product = productInputDisassembler.toDomainObject(productInput);
        product.setRestaurant(restaurant);

        product = productService.add(product);

        return productModelAssembler.toModel(product);

    }

    @PutMapping("/{productId}")
    public ProductModel update(@PathVariable Long restaurantId, @PathVariable Long productId,
                               @RequestBody @Valid ProductInput productInput) {
        Product currentProduct = productService.findOrFail(restaurantId, productId);

        productInputDisassembler.copyToDomainObject(productInput, currentProduct);

        return productModelAssembler.toModel(currentProduct);

    }

}
