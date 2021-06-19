package nl.aartj.GarageApp.car;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class CarConfig {

    @Bean
    CommandLineRunner configCars(CarRepository repository) {
        return args -> {
            Car car1 = new Car(
                    1L,
                    "Nissan",
                    "Micra 2009",
                    "RR-55-YT"
            );

            Car car2 = new Car(
                    1L,
                    "Toyota",
                    "Yaris 2014",
                    "TT-25-LY"
            );

            Car car3 = new Car(
                    2L,
                    "Seat",
                    "Ibiza 2018",
                    "TL-09-ER"
            );

            Car car4 = new Car(
                    3L,
                    "BMW",
                    "M8 2020",
                    "WL-55-AW"
            );

            Car car5 = new Car(
                    3L,
                    "Audi",
                    "Q6 2021",
                    "RT-77-LT"
            );

            Car car6 = new Car(
                    4L,
                    "Microsoft",
                    "iCar XVr 2022",
                    "AP-88-LE"
            );
            repository.saveAll(List.of(car1, car2, car3, car4, car5, car6));
        };
    }
}
