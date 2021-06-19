package nl.aartj.GarageApp.examination;


import javax.persistence.*;

@Entity
@Table
public class Examination {

    @Id
    @SequenceGenerator(name = "examination_sequence", sequenceName = "examination_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "examination_sequence")
    private Long id;
    private Long customerId;
    private Long carId;
    private String phoneNumber;
    private boolean carApproved;
    private boolean repairNecessary;
    private boolean customerConsent;
    private double price;
    private String status;


    public Examination() {
    }

    public Examination(Long id, Long customerId, Long carId, String phoneNumber, boolean carApproved, boolean repairNecessary, boolean customerConsent,  double price, String status) {
        this.id = id;
        this.customerId = customerId;
        this.carId = carId;
        this.phoneNumber = phoneNumber;
        this.carApproved = carApproved;
        this.repairNecessary = repairNecessary;
        this.customerConsent = customerConsent;
        this.price = price;
        this.status = status;
    }

    public Examination(Long customerId, Long carId, String phoneNumber, boolean carApproved, boolean repairNecessary, boolean customerConsent, double price, String status) {
        this.customerId = customerId;
        this.carId = carId;
        this.phoneNumber = phoneNumber;
        this.carApproved = carApproved;
        this.repairNecessary = repairNecessary;
        this.customerConsent = customerConsent;
        this.price = price;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Long getCarId() {
        return carId;
    }

    public void setCarId(Long carId) {
        this.carId = carId;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public boolean isCarApproved(boolean b) {
        return carApproved;
    }

    public void setCarApproved(boolean carApproved) {
        this.carApproved = carApproved;
    }

    public boolean isRepairNecessary(boolean b) {
        return repairNecessary;
    }

    public void setRepairNecessary(boolean repairNecessary) {
        this.repairNecessary = repairNecessary;
    }

    public boolean isCustomerConsent() {
        return customerConsent;
    }

    public void setCustomerConsent(boolean customerConsent) {
        this.customerConsent = customerConsent;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Examination{" +
                "Id=" + id +
                ", customerId=" + customerId +
                ", carId=" + carId +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", carApproved=" + carApproved +
                ", repairNecessary=" + repairNecessary +
                ", customerConsent=" + customerConsent +
                ", price=" + price +
                ", status='" + status + '\'' +
                '}';
    }
}
