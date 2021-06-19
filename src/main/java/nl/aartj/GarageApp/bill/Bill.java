package nl.aartj.GarageApp.bill;

import javax.persistence.*;

@Entity
@Table
public class Bill {
    @Id
    @SequenceGenerator(name = "bill_sequence", sequenceName = "bill_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bill_sequence")
    private Long billId;
    private Long customerId;
    private Long productId;
    private String name;
    private String surName;
    private double priceTotal;
    private boolean paid;

    public Bill() {
    }

    public Bill(Long billId, Long customerId, Long productId, String name, String surName, double priceTotal, boolean paid) {
        this.billId = billId;
        this.customerId = customerId;
        this.productId = productId;
        this.name = name;
        this.surName = surName;
        this.priceTotal = priceTotal;
        this.paid = paid;
    }

    public Bill(Long customerId, Long productId, String name, String surName, double priceTotal, boolean paid) {
        this.customerId = customerId;
        this.productId = productId;
        this.name = name;
        this.surName = surName;
        this.priceTotal = priceTotal;
        this.paid = paid;
    }

    public Long getBillId() {
        return billId;
    }

    public void setId(Long id) {
        this.billId = billId;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public double getPriceTotal() {
        return priceTotal;
    }

    public void setPriceTotal(double priceTotal) {
        this.priceTotal = priceTotal;
    }

    public boolean isPaid(boolean b) {
        return paid;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }

    @Override
    public String toString() {
        return "Bill{" +
                "billId=" + billId +
                ", customerId=" + customerId +
                ", productId=" + productId +
                ", name='" + name + '\'' +
                ", surName='" + surName + '\'' +
                ", priceTotal=" + priceTotal +
                ", paid=" + paid +
                '}';
    }
}


