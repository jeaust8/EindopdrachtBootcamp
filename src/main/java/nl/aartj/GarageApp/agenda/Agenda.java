package nl.aartj.GarageApp.agenda;


import javax.persistence.*;

@Entity
@Table
public class Agenda{
    @Id
    @SequenceGenerator(name= "agenda_sequence", sequenceName = "agenda_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "agenda_sequence")
    private Long id;
    private Long carId;
    private Long customerId;
    private String surName;
    private String phoneNumber;
    private String date;
    private String time;

    public Agenda() {
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCarId(Long carId) {
        this.carId = carId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public Long getCarId() {
        return carId;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public String getSurName() {
        return surName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getTime() {
        return time;
    }
}



