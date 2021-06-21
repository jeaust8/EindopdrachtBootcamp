package nl.aartj.GarageApp.car;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
class CarTest {

    private CarService carService;

    @Autowired
    private CarRepository underTest;

    @Test
    public void itShouldAddNewCar(){
        CarService carService = new CarService(underTest);

        Car car = new Car(1L, 1L, "Tesla", "Model 3", "HH-01-TL");

        carService.addNewCar(car);

        List<Car> cars = carService.getCars();

        assertEquals(cars.get(0).getBrand(), "Tesla");
    }
}
