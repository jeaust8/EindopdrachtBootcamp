package nl.aartj.GarageApp.Customer;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class CustomerAccountConfig {

    @Bean
    CommandLineRunner configCustomer(CustomerAccountRepository repository) {
        return args -> {
            CustomerAccount joost = new CustomerAccount(
                    "Joost",
                    "van Aartsen",
                    "Hoofdstraat 12",
                    "8101AA",
                    "Raalte",
                    "joost.van.aartsen@gmail.com",
                    "0572-556655",
                    "$2y$10$ScKk0yZEtJNpDIJHbxIEa.ZW8aZfh/xY.kxIHs7aQ2AlqY6SCw30q"
            );

            CustomerAccount mel = new CustomerAccount(
                    "Mel",
                    "van Veen",
                    "Dorpsstraat 55",
                    "6565TT",
                    "Bemmel",
                    "vanveenmel11@gmail.com",
                    "0582-668899",
                    "$2y$10$ScKk0yZEtJNpDIJHbxIEa.ZW8aZfh/xY.kxIHs7aQ2AlqY6SCw30q"
            );

            CustomerAccount arjen = new CustomerAccount(
                    "Arjen",
                    "Robben",
                    "Appelweg 55",
                    "8101AE",
                    "Groningen",
                    "arjen.robben@gmail.com",
                    "0487-555666",
                    "$2y$10$ScKk0yZEtJNpDIJHbxIEa.ZW8aZfh/xY.kxIHs7aQ2AlqY6SCw30q"
            );

            CustomerAccount bill = new CustomerAccount(
                    "Bill",
                    "Gates",
                    "Computerweg 45",
                    "5757RT",
                    "America",
                    "bill.gates@microsoft.nl",
                    "06-35523456",
                    "$2y$10$ScKk0yZEtJNpDIJHbxIEa.ZW8aZfh/xY.kxIHs7aQ2AlqY6SCw30q"
            );

            repository.saveAll(
                    List.of(joost, mel, arjen, bill)
            );
        };
    }
}
