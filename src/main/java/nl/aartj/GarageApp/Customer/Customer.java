package nl.aartj.GarageApp.Customer;

import javax.persistence.*;

@Entity
@Table
public class Customer {
    @Id
    @SequenceGenerator(
            name = "customer_sequence",
            sequenceName = "customer_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "customer_sequence"
    )
    private Long customerId;
    private String name;
    private String surName;
    private String address;
    private String zipCode;
    private String city;
    private String email;
    private String phoneNumber;

    public Customer() {
    }

    public Customer(Long customerId, String name, String surName, String address, String zipCode, String city, String email, String phoneNumber) {
        this.customerId = customerId;
        this.name = name;
        this.surName = surName;
        this.address = address;
        this.zipCode = zipCode;
        this.city = city;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public Customer(String name, String surName, String address, String zipCode, String city, String email, String phoneNumber) {
        this.name = name;
        this.surName = surName;
        this.address = address;
        this.zipCode = zipCode;
        this.city = city;
        this.email = email;
        this.phoneNumber = phoneNumber;

    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
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

    public String getAddress() { return address; }

    public void setAddress(String address) { this.address = this.address; }

    public String getZipCode() { return zipCode; }

    public void setZipCode(String zipCode) { this.zipCode = this.zipCode; }

    public String getCity() { return city; }

    public void setCity(String city) { this.city = this.city; }

    public String getEmail() {
        return email;
    }

    public void setEmail(String eMail) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + customerId +
                ", name='" + name + '\'' +
                ", surName='" + surName + '\'' +
                ", address='" + address + '\'' +
                ", zipCode='" + zipCode + '\'' +
                ", city='" + city + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber -'" + phoneNumber + '\'' +
                '}';
    }
}



