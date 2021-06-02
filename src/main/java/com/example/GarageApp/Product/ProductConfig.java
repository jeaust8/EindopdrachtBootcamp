package com.example.GarageApp.Product;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class ProductConfig {

    @Bean
    CommandLineRunner configProducts(ProductRepository repository) {
        return args -> {
            Product MOT = new Product(
                    1L,
                    "APK",
                    35.00
            );

            Product serviceSmall = new Product(
                    2L,
                    "Onderhoudsbeurt Klein",
                    200.00
            );
            Product serviceLarge = new Product(
                    3L,
                    "Onderhoudsbeurt Groot",
                    400.00
            );

            repository.saveAll(
                    List.of(MOT, serviceSmall, serviceLarge)
            );
        };
    }
}

