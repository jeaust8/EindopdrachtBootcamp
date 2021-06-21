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

    public boolean isCarApproved(boolean b) {
        return carApproved;
    }

    public void setCarApproved(boolean carApproved) {
        this.carApproved = carApproved;
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
