package nl.aartj.GarageApp.EmployeeAccount;


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

    public Optional<EmployeeAccount> getAccountByEmail(String email){
        return employeeAccountRepository.findAccountByEmail(email);
    }

    public String getAccountInfo(Long accountId){
        String accountInfo = "Naam: " + employeeAccountRepository.findById(accountId).get().getName() + " " +
                employeeAccountRepository.findById(accountId).get().getSurName() + "\n" +
                "Adres: " + employeeAccountRepository.findById(accountId).get().getAddress() + ", " +
                employeeAccountRepository.findById(accountId).get().getZipCode() + " " +
                employeeAccountRepository.findById(accountId).get().getCity() + "\n" +
                "E-mailadres: " + employeeAccountRepository.findById(accountId).get().getEmail();

        return accountInfo;
    }

    @Transactional
    public void updateAccount(Long accountId,
                              String name,
                              String surName,
                              String email,
                              String phoneNumber,
                              String address,
                              String zipCode,
                              String city,
                              String password){
        EmployeeAccount employeeAccount = employeeAccountRepository.findById(accountId).orElseThrow(() -> new IllegalStateException(
                "Account met id " + accountId + " bestaat niet."));

        password = passwordEncoder.encode(password);

        if (name != null && name.length() > 0 && !Objects.equals(employeeAccount.getName(), name)){
            employeeAccount.setName(name);
        }
        if (surName != null && surName.length() > 0 && !Objects.equals(employeeAccount.getSurName(), surName)){
            employeeAccount.setSurName(surName);
        }
        if (email != null && email.length() > 0 && !Objects.equals(employeeAccount.getEmail(), email)) {
            Optional<EmployeeAccount> accountOptional = employeeAccountRepository.findAccountByEmail(email);
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

    public List<EmployeeAccount> getAccounts() {
        return employeeAccountRepository.findAll();
    }

    public Optional<EmployeeAccount> getAccount(String email) {
        return employeeAccountRepository.findAccountByEmail(email);
    }

    public List<EmployeeAccount> getEmployeeAccounts() {
        return employeeAccountRepository.findAll();
    }

//    public ArrayList<PlacedOrder> getOrders(Long accountId) {
//        ArrayList<PlacedOrder> placedOrders = new ArrayList<PlacedOrder>();
//        placedOrders.addAll(placedOrderRepository.findAll());
//        int i = 0;
//
//        for (PlacedOrder placedOrder: placedOrders) {
//            if(!placedOrder.getCustomerId().equals(accountId)){
//                placedOrders.remove(i);
//            }
//            i++;
//
//        }
//        return placedOrders;
//    }
}
