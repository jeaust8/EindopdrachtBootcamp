package nl.aartj.GarageApp.bill;

import javax.persistence.*;

@Entity
@Table
public class Bill {
    @Id
    @SequenceGenerator(name = "bill_sequence", sequenceName = "bill_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bill_sequence")
    private Long id;
    private Long customerId;
    private String products;
    private String name;
    private String surName;
    private double priceTotal;
    private double totalPlusTaxes;
    private boolean paid;


    public Bill() {
    }

    public Long getId() {
        return id;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getProducts() {
        return products;
    }

    public void setProducts(String products) {
        this.products = products;
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

    public double getTotalPlusTaxes() {
        return totalPlusTaxes;
    }

    public void setTotalPlusTaxes(double totalPlusTaxes) {
        this.totalPlusTaxes = totalPlusTaxes;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }

    public void setId(Long id) {
        this.id = this.id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isPaid() {
        return paid;
    }

    @Override
    public String toString() {
        return "Bill{" +
                "billId=" + id +
                ", customerId=" + customerId +
                ", products=" + products +
                ", name='" + name + '\'' +
                ", surName='" + surName + '\'' +
                ", priceTotal=" + priceTotal +
                ", totalPlusTaxes=" + totalPlusTaxes +
                ", paid=" + paid +
                '}';
    }
}


