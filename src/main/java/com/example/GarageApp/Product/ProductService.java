package com.example.GarageApp.Product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

    @Service
    public class ProductService {

        private final ProductRepository productRepository;

        @Autowired
        public ProductService(ProductRepository productRepository) {
            this.productRepository = productRepository;
        }

        public List<Product> getProducts() {
            return productRepository.findAll();
        }

        public void addNewProduct(Product product) {
            Optional<Product> productOptional = productRepository
                    .findProductByTitle(product.getTitle());
            if (productOptional.isPresent()) {
                throw new IllegalStateException("Naam reeds bekend.");
            }
            productRepository.save(product);
        }

        public void deleteProduct(Long productId) {
            Optional<Product> productOptional = productRepository.findProductByProductId(productId);
            if (productOptional.isEmpty()) {
                throw new IllegalStateException("Product met titel " + productId + " bestaat niet.");
            }
            productRepository.deleteByTitle(productId);
        }

        @Transactional
        public void updateProduct(Long productId, String title,
                                   Double price
                                   ) {
            Product product = productRepository.findProductByTitle(title)
                    .orElseThrow(() -> new IllegalStateException(
                            "Product " + title + " does not exist"
                    ));

            if (title != null &&
                    title.length() > 0 &&
                    !Objects.equals(product.getTitle(), title)) {
                product.setTitle(title);
                product.setPrice(price);
            }


            productRepository.saveAll(List.of(product));
        }
    }

