package nl.aartj.GarageApp.Car;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path= "api/v1/car")

public class CarController {

    private final CarService carService;

    @Autowired
    public CarController (CarService carService) {this.carService = carService;}

    @GetMapping
    public List<Car> getCars() {return carService.getCars();}

    @PostMapping
    public void registerNewCar(@RequestBody Car car) {carService.addNewCar(car);}

    @DeleteMapping(path= "{carId}")
    public void deleteCar(@PathVariable("carId") Long carId){
        carService.deleteCar(carId);
    }

    @PutMapping(path= "{carId}")
        public void updateCar(
                @PathVariable("carId") Long carId,
                @RequestParam(required = false) String brand,
                @RequestParam(required = false) String model,
                @RequestParam(required = false) String licensePlate){
            carService.updateCar(carId, brand, model, licensePlate);
        }
}
