package nl.aartj.GarageApp.placedTask;


import javax.persistence.*;

@Entity
@Table
public class PlacedTask {

    @Id
    @SequenceGenerator(name = "task_sequence", sequenceName = "task_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "task_sequence")
    private Long id;
    private Long customerId;
    private String taskDate;
    private double priceTotal;
    private boolean customerConsent;
    private String payment;
    private String status;
    private boolean pickup;

    public PlacedTask(){

    }

    public PlacedTask(Long id, Long customerId, String taskDate, double priceTotal, boolean customerConsent, String payment, String status, boolean pickup) {
        this.id = id;
        this.customerId = customerId;
        this.taskDate = taskDate;
        this.priceTotal = priceTotal;
        this.customerConsent = customerConsent;
        this.payment = payment;
        this.status = status;
        this.pickup = pickup;
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

    public String getTaskDate() {
        return taskDate;
    }

    public void setTaskDate(String taskDate) {
        this.taskDate = taskDate;
    }

    public double getPriceTotal() {
        return priceTotal;
    }

    public void setPriceTotal(double priceTotal) {
        this.priceTotal = priceTotal;
    }

    public boolean isCustomerConsent() {
        return customerConsent;
    }

    public void setCustomerConsent(boolean customerConsent) {
        this.customerConsent = customerConsent;
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean isPickup() {
        return pickup;
    }

    public void setPickup(boolean taskReady) {
        this.pickup = taskReady;
    }
}
