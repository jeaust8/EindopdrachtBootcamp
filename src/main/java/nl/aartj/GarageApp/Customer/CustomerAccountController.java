package nl.aartj.GarageApp.Customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1/customer")
public class CustomerAccountController {

    private final CustomerAccountService customerAccountService;

    @Autowired
    public CustomerAccountController(CustomerAccountService customerAccountService) {
        this.customerAccountService = customerAccountService;
    }

    @GetMapping(path = "/profile/{customerId}")
    public List<CustomerAccount> getProfile(@PathVariable("customerId") Long customerId){
        return customerAccountService.getProfile(customerId);
    }

    @GetMapping
    public List<CustomerAccount> getCustomers() {
        return customerAccountService.getCustomers();
    }

    @PostMapping
    public void registerNewCustomer(@RequestBody CustomerAccount customerAccount) {
        customerAccountService.addNewCustomer(customerAccount);
    }

    @DeleteMapping(path = "{customerId}")
    public void deleteCustomer(@PathVariable("customerId") Long customerId) {
        customerAccountService.deleteCustomer(customerId);
    }

    @PutMapping(path= "{customerId}")
        public void updateCustomer(
                @PathVariable("customerId") Long customerId,
                @RequestParam(required = false) String name,
                @RequestParam(required = false) String surName,
                @RequestParam(required = false) String address,
                @RequestParam(required = false) String zipCode,
                @RequestParam(required = false) String city,
                @RequestParam(required = false) String email,
                @RequestParam(required = false) String phoneNumber) {
            customerAccountService.updateCustomer(customerId, name, surName, address, zipCode, city, email, phoneNumber);
        }
}
