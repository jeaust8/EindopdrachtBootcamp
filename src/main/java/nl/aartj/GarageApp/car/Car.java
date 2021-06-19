package nl.aartj.GarageApp.car;

import javax.persistence.*;

@Entity
@Table
public class Car {
    @Id
    @SequenceGenerator(
            name = "car_sequence",
            sequenceName = "car_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "car_sequence"
    )
    private Long carId;
    private Long customerId;
    private String brand;
    private String model;
    private String licensePlate;

    public Car() {
    }

    public Car(Long carId, Long customerId, String brand, String model, String licensePlate) {
        this.carId = carId;
        this.customerId = customerId;
        this.brand = brand;
        this.model = model;
        this.licensePlate = licensePlate;
    }

    public Car(Long customerId, String brand, String model, String licensePlate) {
        this.customerId = customerId;
        this.brand = brand;
        this.model = model;
        this.licensePlate = licensePlate;
    }

    public Long getCarId() {
        return carId;
    }

    public void setCarId(Long carId) {
        this.carId = carId;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    @Override
    public String
    toString() {
        return "Car{" +
                "carId=" + carId +
                ", customerId=" + customerId +
                ", brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", licensePlate='" + licensePlate + '\'' +
                '}';
    }
}
