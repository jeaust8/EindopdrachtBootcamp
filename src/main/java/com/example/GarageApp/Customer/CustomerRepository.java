package com.example.GarageApp.Customer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository
        extends JpaRepository<Customer, Long> {

    @Query("SELECT c FROM Customer c WHERE c.name = ?1")
    Optional<Customer> findByCustomerId(Long customerId);
    Optional<Customer> deleteByCustomerId(Long customerId);
    Optional<Customer> findCustomerByName(String name);
    Optional<Customer> findCustomerBySurName(String surName);
    Optional<Customer> findCustomerByAddress(String address);
    Optional<Customer> findCustomerByZipCode(String zipCode);
    Optional<Customer> findCustomerByCity(String city);
    Optional<Customer> findCustomerByEmail(String email);
    Optional<Customer> findCustomerByPhoneNumber(String phoneNumber);
    Optional<Customer> findCustomerByLicensePlateNumber(String licensePlateNumber);
}
