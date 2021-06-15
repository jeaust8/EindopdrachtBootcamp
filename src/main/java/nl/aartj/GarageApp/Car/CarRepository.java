package nl.aartj.GarageApp.Car;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {

    Optional<Car> findCarByCarId(Long carId);
    Optional<Car> findCarByLicensePlate(String licensePlate);
    Optional<Car> deleteByCarId(Long carId);
    Optional<Car> deleteByLicensePlate(String licensePlate);
}
