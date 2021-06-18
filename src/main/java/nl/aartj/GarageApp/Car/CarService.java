package nl.aartj.GarageApp.Car;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class CarService {

    private final CarRepository carRepository;

    @Autowired
    public CarService(CarRepository carRepository){
        this.carRepository = carRepository;
    }

    public List<Car> getCars(){return carRepository.findAll();}

    public void addNewCar(Car car){
        Optional<Car> carOptional = carRepository.findCarByCarId(car.getCarId());
        if (carOptional.isPresent()){
    throw new IllegalStateException("Auto reeds in het systeem.");
        }
        carRepository.save(car);
    }

    public void deleteCar(Long carId) {
        Optional<Car> optionalCar = carRepository.findCarByCarId(carId);
        if (optionalCar.isEmpty()) {
            throw new IllegalStateException("Auto met id " + carId + " is niet bekend.");
        }
        carRepository.deleteByCarId(carId);
    }
@Transactional
    public void updateCar(Long carId,
                          String brand,
                          String model,
                          String licensePlate){
        Car car = carRepository.findCarByCarId(carId).orElseThrow(() -> new IllegalStateException(
                "Auto met Id " + carId + " staat niet in het systeem."
        ));

        if (brand != null &&
        !Objects.equals(car.getBrand(), brand)) {
            car.setBrand(brand);
        }

        if (model != null &&
        !Objects.equals(car.getModel(), model)) {
            car.setModel(model);
        }

        if (licensePlate != null &&
        !Objects.equals(car.getLicensePlate(), licensePlate)) {
            car.setLicensePlate(licensePlate);
        }
    }
}
