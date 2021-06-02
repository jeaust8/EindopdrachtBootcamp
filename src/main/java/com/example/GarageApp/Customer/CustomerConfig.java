package com.example.GarageApp.Customer;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class CustomerConfig {

    @Bean
    CommandLineRunner configCustomer(CustomerRepository repository) {
        return args -> {
            Customer joost = new Customer(
                    "Joost",
                    "van Aartsen",
                    "Hoofdstraat 12",
                    "8101AA",
                    "Raalte",
                    "joost.van.aartsen@gmail.com",
                    "0572-556655",
                    "10-MA-21"
            );

            Customer mel = new Customer(
                    "Mel",
                    "van Veen",
                    "Dorpsstraat 55",
                    "6565TT",
                    "Bemmel",
                    "m.van.veen@gmail.com",
                    "0582-668899",
                    "08-NV-21"
            );

            Customer arjen = new Customer(
                    "Arjen",
                    "Robben",
                    "Appelweg 55",
                    "8101AE",
                    "Groningen",
                    "arjen.robben@gmail.com",
                    "0487-555666",
                    "54-YT-08"
            );

            Customer bill = new Customer(
                    "Bill",
                    "Gates",
                    "Computerweg 45",
                    "5757RT",
                    "America",
                    "bill.gates@microsoft.nl",
                    "06-35523456",
                    "64-YI-99"
            );

            repository.saveAll(
                    List.of(joost, mel, arjen, bill)
            );
        };
    }
}
