package nl.aartj.GarageApp.task;

import javax.persistence.*;

@Entity
@Table
public class Task {

    @Id
    @SequenceGenerator(name= "task_sequence", sequenceName = "task_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "task_sequence")
    private Long id;
    private Long taskId;
    private Long customerId;
    private Long carId;
    private boolean customerConsent;
    private double priceTotal;
    private boolean paid;
    private String status;

    public Task(){
    }

    public Task(Long id, Long taskId, Long customerId, Long carId, boolean customerConsent, double priceTotal, boolean paid, String status) {
        this.id = id;
        this.taskId = taskId;
        this.customerId = customerId;
        this.carId = carId;
        this.customerConsent = customerConsent;
        this.priceTotal = priceTotal;
        this.paid = paid;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
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

    public boolean isCustomerConsent() {
        return customerConsent;
    }

    public void setCustomerConsent(boolean customerConsent) {
        this.customerConsent = customerConsent;
    }

    public double getPriceTotal() {
        return priceTotal;
    }

    public void setPriceTotal(double priceTotal) {
        this.priceTotal = priceTotal;
    }

    public boolean isPaid() {
        return paid;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", taskId=" + taskId +
                ", customerId=" + customerId +
                ", carId=" + carId +
                ", customerConsent=" + customerConsent +
                ", priceTotal=" + priceTotal +
                ", paid=" + paid +
                ", status='" + status + '\'' +
                '}';
    }
}
