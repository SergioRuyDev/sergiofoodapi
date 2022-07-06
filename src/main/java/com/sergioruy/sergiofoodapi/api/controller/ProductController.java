package com.sergioruy.sergiofoodapi.api.controller;

import com.sergioruy.sergiofoodapi.api.assembler.ProductModelAssembler;
import com.sergioruy.sergiofoodapi.api.model.ProductModel;
import com.sergioruy.sergiofoodapi.domain.model.Product;
import com.sergioruy.sergiofoodapi.domain.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductModelAssembler productModelAssembler;

    @GetMapping
    public List<ProductModel> list() {
        List<Product> allProducts = productRepository.findAll();

        return productModelAssembler.toCollection(allProducts);
    }
}
