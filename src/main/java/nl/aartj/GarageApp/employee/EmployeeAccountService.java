package nl.aartj.GarageApp.employee;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class EmployeeAccountService {

    private final EmployeeAccountRepository employeeAccountRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public EmployeeAccountService(EmployeeAccountRepository employeeAccountRepository){
        this.employeeAccountRepository = employeeAccountRepository;
    }

    public Optional<EmployeeAccount> getEmployeeByEmail(String email){
        return employeeAccountRepository.findEmployeeByEmail(email);
    }

    public String getEmployeeInfo(Long employeeId){
        String employeeInfo = "Naam: " + employeeAccountRepository.findById(employeeId).get().getName() + " " +
                employeeAccountRepository.findById(employeeId).get().getSurName() + "\n" +
                "Adres: " + employeeAccountRepository.findById(employeeId).get().getAddress() + ", " +
                employeeAccountRepository.findById(employeeId).get().getZipCode() + " " +
                employeeAccountRepository.findById(employeeId).get().getCity() + "\n" +
                "E-mailadres: " + employeeAccountRepository.findById(employeeId).get().getEmail();

        return employeeInfo;
    }

    @Transactional
    public void updateEmployee(Long employeeId,
                              String name,
                              String surName,
                              String email,
                              String phoneNumber,
                              String address,
                              String zipCode,
                              String city,
                              String password,
                              String role){
        EmployeeAccount employeeAccount = employeeAccountRepository.findById(employeeId).orElseThrow(() -> new IllegalStateException(
                "Account met id " + employeeId + " bestaat niet."));

        password = passwordEncoder.encode(password);

        if (name != null && name.length() > 0 && !Objects.equals(employeeAccount.getName(), name)){
            employeeAccount.setName(name);
        }
        if (surName != null && surName.length() > 0 && !Objects.equals(employeeAccount.getSurName(), surName)){
            employeeAccount.setSurName(surName);
        }
        if (email != null && email.length() > 0 && !Objects.equals(employeeAccount.getEmail(), email)) {
            Optional<EmployeeAccount> accountOptional = employeeAccountRepository.findEmployeeByEmail(email);
            if (accountOptional.isPresent()) {
                throw new IllegalStateException("E-mailadres reeds in gebruik.");
            } else {
                employeeAccount.setEmail(email);
            }
        }
        if (phoneNumber != null && phoneNumber.length() > 0 && !Objects.equals(employeeAccount.getPhoneNumber(), phoneNumber)) {
            employeeAccount.setPhoneNumber(phoneNumber);
        }
        if (address != null && address.length() > 0 && !Objects.equals(employeeAccount.getAddress(), address)) {
            employeeAccount.setAddress(address);
        }
        if (zipCode != null && zipCode.length() > 0 && !Objects.equals(employeeAccount.getZipCode(), zipCode)) {
            employeeAccount.setZipCode(zipCode);
        }
        if (city != null && city.length() > 0 && !Objects.equals(employeeAccount.getCity(), city)) {
            employeeAccount.setCity(city);
        }
        if (password != null && password.length() > 0 && !Objects.equals(employeeAccount.getPassword(), password)) {
            employeeAccount.setPassword(password);
        }

        employeeAccountRepository.saveAll(List.of(employeeAccount));
    }

    public List<EmployeeAccount> getEmployeeAccounts() {
        return employeeAccountRepository.findAll();
    }

    public Optional<EmployeeAccount> getEmployeeAccount(String email) {
        return employeeAccountRepository.findEmployeeByEmail(email);
    }

    public String addNewEmployeeAccount(EmployeeAccount employeeAccount) {
        Optional<EmployeeAccount> employeeOptional = employeeAccountRepository
                .findEmployeeByEmail(employeeAccount.getName());
        if (employeeOptional.isPresent()) {
            throw new IllegalStateException("Naam reeds bekend.");
        }
        employeeAccountRepository.save(employeeAccount);

        return employeeAccount.getName() + " is toegevoegd aan het systeem.";
    }
}
