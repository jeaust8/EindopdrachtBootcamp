package nl.aartj.GarageApp.employee;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

import static nl.aartj.GarageApp.security.AppUserRole.*;

@Configuration
public class EmployeeAccountConfig {

    @Bean
    CommandLineRunner configEmployee(EmployeeAccountRepository repository){
        return args -> {
            EmployeeAccount mark = new EmployeeAccount(
                    "Mark",
                    "Rutte",
                    "Presidentweg 11",
                    "4455HA",
                    "Den Haag",
                    "070-559955",
                    "mark.rutte@gmail.com",
                    "$2y$10$ScKk0yZEtJNpDIJHbxIEa.ZW8aZfh/xY.kxIHs7aQ2AlqY6SCw30q",
                    MANAGER.name()); //wachtwoord welkom2021
            EmployeeAccount hugo = new EmployeeAccount(
                    "Hugo",
                    "de Jonge",
                    "Gezondheidsweg 20",
                    "3636TR",
                    "Rotterdam",
                    "010-797979",
                    "hugo.dejonge@gmail.com",
                    "$2y$10$ScKk0yZEtJNpDIJHbxIEa.ZW8aZfh/xY.kxIHs7aQ2AlqY6SCw30q",
                    ADMIN.name()); //wachtwoord welkom2021
            EmployeeAccount denzel = new EmployeeAccount(
                    "Denzel",
                    "Dumfries",
                    "Stoomtrein 22",
                    "6776LP",
                    "Rotterdam",
                    "010-223322",
                    "denzel.dumfries@gmail.com",
                    "$2y$10$ScKk0yZEtJNpDIJHbxIEa.ZW8aZfh/xY.kxIHs7aQ2AlqY6SCw30q",
                    MECHANIC.name()); //wachtwoord welkom2021
            EmployeeAccount daley = new EmployeeAccount(
                    "Daley",
                    "Blind",
                    "Amsterdamseweg 17",
                    "3535JT",
                    "Amsterdam",
                    "020-171717",
                    "daley.blind@gmail.com",
                    "$2y$10$ScKk0yZEtJNpDIJHbxIEa.ZW8aZfh/xY.kxIHs7aQ2AlqY6SCw30q",
                    MECHANIC.name()); //wachtwoord welkom2021

            repository.saveAll(List.of(mark, hugo, denzel, daley));
        };
    }
}
