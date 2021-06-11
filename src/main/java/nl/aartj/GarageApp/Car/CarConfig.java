package nl.aartj.GarageApp.Car;

import nl.aartj.GarageApp.Product.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CarConfig {

    @Bean
    CommandLineRunner configProducts(ProductRepository repository) {
        return args -> { }
    }
}
