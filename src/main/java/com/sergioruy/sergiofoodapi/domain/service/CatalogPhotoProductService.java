package com.sergioruy.sergiofoodapi.domain.service;

import com.sergioruy.sergiofoodapi.domain.model.PhotoProduct;
import com.sergioruy.sergiofoodapi.domain.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CatalogPhotoProductService {

    @Autowired
    private ProductRepository productRepository;

    @Transactional
    public PhotoProduct save(PhotoProduct photo) {
        return productRepository.save(photo);

    }
}
