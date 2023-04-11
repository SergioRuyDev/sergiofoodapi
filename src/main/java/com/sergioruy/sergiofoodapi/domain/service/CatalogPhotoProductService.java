package com.sergioruy.sergiofoodapi.domain.service;

import com.sergioruy.sergiofoodapi.domain.model.PhotoProduct;
import com.sergioruy.sergiofoodapi.domain.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class CatalogPhotoProductService {

    @Autowired
    private ProductRepository productRepository;

    @Transactional
    public PhotoProduct save(PhotoProduct photo) {
        Long restaurantId = photo.getRestaurantId();
        Long productId = photo.getProduct().getId();
        Optional<PhotoProduct> photoExist = productRepository.findPhotoById(restaurantId, productId);
        if (photoExist.isPresent()) {
            productRepository.delete(photoExist.get());
        }

        return productRepository.save(photo);

    }
}
