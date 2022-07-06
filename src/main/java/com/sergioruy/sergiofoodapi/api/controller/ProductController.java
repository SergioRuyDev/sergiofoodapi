package com.sergioruy.sergiofoodapi.api.controller;

import com.sergioruy.sergiofoodapi.api.assembler.ProductModelAssembler;
import com.sergioruy.sergiofoodapi.api.model.ProductModel;
import com.sergioruy.sergiofoodapi.domain.model.Product;
import com.sergioruy.sergiofoodapi.domain.model.Restaurant;
import com.sergioruy.sergiofoodapi.domain.repository.ProductRepository;
import com.sergioruy.sergiofoodapi.domain.service.RegisterProductService;
import com.sergioruy.sergiofoodapi.domain.service.RegisterRestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/restaurants/{restaurantId}/products")
public class ProductController {

    @Autowired
    private RegisterRestaurantService restaurantService;

    @Autowired
    private RegisterProductService productService;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductModelAssembler productModelAssembler;

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
}
