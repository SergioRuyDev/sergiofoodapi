package com.sergioruy.sergiofoodapi.api.controller;

import com.sergioruy.sergiofoodapi.api.assembler.PhotoProductModelAssembler;
import com.sergioruy.sergiofoodapi.api.model.PhotoProductModel;
import com.sergioruy.sergiofoodapi.api.model.input.PhotoProductInput;
import com.sergioruy.sergiofoodapi.domain.model.PhotoProduct;
import com.sergioruy.sergiofoodapi.domain.model.Product;
import com.sergioruy.sergiofoodapi.domain.service.CatalogPhotoProductService;
import com.sergioruy.sergiofoodapi.domain.service.RegisterProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;

@RestController
@RequestMapping("/restaurants/{restaurantId}/products/{productId}/photo")
public class RestaurantProductPhotoController {


    @Autowired
    private RegisterProductService productService;

    @Autowired
    private CatalogPhotoProductService catalogPhotoProductService;

    @Autowired
    private PhotoProductModelAssembler photoProductModelAssembler;

    @PutMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public PhotoProductModel updatePhoto(@PathVariable Long restaurantId, @PathVariable Long productId,
                                         @Valid PhotoProductInput photoProductInput) {
        Product product = productService.findOrFail(restaurantId, productId);

        MultipartFile file = photoProductInput.getFile();

        PhotoProduct photo = new PhotoProduct();
        photo.setProduct(product);
        photo.setDescription(photoProductInput.getDescription());
        photo.setContentType(file.getContentType());
        photo.setSize(file.getSize());
        photo.setFileName(file.getOriginalFilename());

        PhotoProduct photoSaved = catalogPhotoProductService.save(photo);

        return photoProductModelAssembler.toModel(photoSaved);
    }



    // THIS IMPLEMENTATION WAS JUST FOR PRACTICE UPLOAD FROM MY CPU
//    @PutMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
//    public void updatePhoto(@PathVariable Long restaurantId, @PathVariable Long productId,
//                            @Valid PhotoProductInput photoProductInput) {
//
//        var fileName = UUID.randomUUID().toString() + "_" + photoProductInput.getFile().getOriginalFilename();
//
//        var filePhoto = Path.of("/Users/sergioruy/Desktop/catalog", fileName);
//        System.out.println(photoProductInput.getDescription());
//        System.out.println(filePhoto);
//        System.out.println(photoProductInput.getFile().getContentType());
//
//        try {
//            photoProductInput.getFile().transferTo(filePhoto);
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }
}
