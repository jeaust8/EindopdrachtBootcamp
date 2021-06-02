package com.example.GarageApp.Customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<Customer> getCustomers() {
        return customerRepository.findAll();
    }

    public void addNewCustomer(Customer customer) {
        Optional<Customer> customerOptional = customerRepository
                .findCustomerByName(customer.getName());
        if (customerOptional.isPresent()) {
            throw new IllegalStateException("Naam reeds bekend.");
        }
        customerRepository.save(customer);
    }

    public void deleteCustomer(Long customerId) {
        Optional<Customer> optionalCustomer = customerRepository.findByCustomerId(customerId);
        if (optionalCustomer.isEmpty()) {
            throw new IllegalStateException("Klant met id " + customerId + " bestaat niet.");
        }
        customerRepository.deleteByCustomerId(customerId);
    }

    @Transactional
    public void updateCustomer(Long customerId,
                               String name,
                               String surName,
                               String address,
                               String zipCode,
                               String city,
                               String email,
                               String phoneNumber,
                               String licensePlateNumber) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new IllegalStateException(
                        "Customer with Id " + customerId + " does not exist"
                ));

        if (name != null &&
                name.length() > 0 &&
                !Objects.equals(customer.getName(), name)) {
            customer.setName(name);
        }

        if (email != null &&
                email.length() > 0 &&
                !Objects.equals(customer.getEmail(), email)) {
            Optional<Customer> customerOptional = customerRepository.findCustomerByEmail(email);
            if (customerOptional.isPresent()) {
                throw new IllegalStateException("E-mail reeds in gebruik");
            }
            customer.setEmail(email);
        }
        customerRepository.saveAll(List.of(customer));
    }
}