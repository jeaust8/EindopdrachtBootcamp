package nl.aartj.GarageApp.customer;

import javax.persistence.*;

@Entity
@Table
public class CustomerAccount {
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
    private Long id;
    private String name;
    private String surName;
    private String address;
    private String zipCode;
    private String city;
    private String email;
    private String phoneNumber;
    private String password;
    private String role;

    public CustomerAccount() {
    }

    public CustomerAccount(Long id, String name, String surName, String address, String zipCode, String city, String email, String phoneNumber, String password, String role) {
        this.id = id;
        this.name = name;
        this.surName = surName;
        this.address = address;
        this.zipCode = zipCode;
        this.city = city;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.role = role;
    }

    public CustomerAccount(String name, String surName, String address, String zipCode, String city, String email, String phoneNumber, String password, String role) {
        this.name = name;
        this.surName = surName;
        this.address = address;
        this.zipCode = zipCode;
        this.city = city;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.role = role;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getPassword() {return password;}

    public void setPassword(String password) {this.password = password;}

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surName='" + surName + '\'' +
                ", address='" + address + '\'' +
                ", zipCode='" + zipCode + '\'' +
                ", city='" + city + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                '}';
    }


}



