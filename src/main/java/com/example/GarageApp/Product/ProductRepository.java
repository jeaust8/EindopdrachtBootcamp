package com.example.GarageApp.Product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {


    Optional<Product> findProductByProductId(Long productId);
    Optional<Product> findProductByTitle(String name);
    Optional<Product> existsByTitle(String productTitle);
    Optional<Product> deleteByTitle(Long productTitle);
    Optional<Product> findProductByPrice(Double price);
}
