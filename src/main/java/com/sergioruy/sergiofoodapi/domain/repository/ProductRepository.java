package com.sergioruy.sergiofoodapi.domain.repository;

import com.sergioruy.sergiofoodapi.domain.model.PhotoProduct;
import com.sergioruy.sergiofoodapi.domain.model.Product;
import com.sergioruy.sergiofoodapi.domain.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>, ProductRepositoryQueries {

    @Query("from Product where restaurant.id = :restaurant and id = :product")
    Optional<Product> findById(@Param("restaurant") Long restaurantId, @Param("product") Long productId);

    List<Product> findAllByRestaurant(Restaurant restaurant);

    @Query("from Product p where p.active = true and p.restaurant = :restaurant")
    List<Product> findActivesByRestaurant(Restaurant restaurant);

    @Query("select f from PhotoProduct f join f.product p where p.restaurant.id = :restaurantId and f.product.id = :productId")
    Optional<PhotoProduct> findPhotoById(Long restaurantId, Long productId);
}
