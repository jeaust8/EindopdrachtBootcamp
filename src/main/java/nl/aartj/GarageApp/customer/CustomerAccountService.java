package nl.aartj.GarageApp.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class CustomerAccountService {

    private final CustomerAccountRepository customerAccountRepository;

    @Autowired
    public CustomerAccountService(CustomerAccountRepository customerAccountRepository) {
        this.customerAccountRepository = customerAccountRepository;
    }

    public Optional<CustomerAccount> getAccountByEmail(String email){
        return customerAccountRepository.findAccountByEmail(email);
    }

    public List<CustomerAccount> getCustomers() {
        return customerAccountRepository.findAll();
    }

    public List<CustomerAccount> getProfile(Long customerId){
       Optional<CustomerAccount> accountOptional = customerAccountRepository.findById(customerId);
       CustomerAccount account = null;

        if(accountOptional.isPresent()){
            account = accountOptional.get();


        }
        return List.of(account);

    }

    public String addNewCustomer(CustomerAccount customerAccount) {
        Optional<CustomerAccount> customerOptional = customerAccountRepository
                .findCustomerByName(customerAccount.getName());
        if (customerOptional.isPresent()) {
            throw new IllegalStateException("Naam reeds bekend.");
        }
        customerAccountRepository.save(customerAccount);

        return customerAccount.getName() + " is toegevoegd aan het systeem.";
    }

    public void deleteCustomer(Long customerId) {
        Optional<CustomerAccount> optionalCustomer = customerAccountRepository.findById(customerId);
        if (optionalCustomer.isEmpty()) {
            throw new IllegalStateException("Klant met id " + customerId + " bestaat niet.");
        }
        customerAccountRepository.deleteById(customerId);
    }

    @Transactional
    public void updateCustomer(Long customerId,
                               String name,
                               String surName,
                               String address,
                               String zipCode,
                               String city,
                               String email,
                               String phoneNumber) {
        CustomerAccount customerAccount = customerAccountRepository.findById(customerId)
                .orElseThrow(() -> new IllegalStateException(
                        "Customer with Id " + customerId + " does not exist"
                ));

        if (name != null &&
                name.length() > 0 &&
                !Objects.equals(customerAccount.getName(), name)) {
            customerAccount.setName(name);
        }

        if (surName != null &&
                surName.length() > 0 &&
                !Objects.equals(customerAccount.getSurName(), surName)) {
            customerAccount.setSurName(surName);
        }

        if (address != null &&
                address.length() > 0 &&
                !Objects.equals(customerAccount.getAddress(), address)) {
            customerAccount.setAddress(address);
        }

        if (zipCode != null &&
                zipCode.length() > 0 &&
                !Objects.equals(customerAccount.getZipCode(), zipCode)) {
            customerAccount.setZipCode(zipCode);
        }

        if (city != null &&
                city.length() > 0 &&
                !Objects.equals(customerAccount.getCity(), city)) {
            customerAccount.setCity(city);
        }

        if (email != null &&
                email.length() > 0 &&
                !Objects.equals(customerAccount.getEmail(), email)) {
            Optional<CustomerAccount> customerOptional = customerAccountRepository.findCustomerByEmail(email);
            if (customerOptional.isPresent()) {
                throw new IllegalStateException("E-mail reeds in gebruik");
            }
            customerAccount.setEmail(email);

            if (phoneNumber != null &&
                    phoneNumber.length() > 0 &&
                    !Objects.equals(customerAccount.getPhoneNumber(), phoneNumber)) {
                customerAccount.setName(phoneNumber);
            }
        }
        customerAccountRepository.saveAll(List.of(customerAccount));
    }
}
