package nl.aartj.GarageApp.Customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/customer")
public class CustomerController {

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public List<Customer> getCustomers() {
        return customerService.getCustomers();
    }

    @PostMapping
    public void registerNewCustomer(@RequestBody Customer customer) {
        customerService.addNewCustomer(customer);
    }

    @DeleteMapping(path = "{customerId}")
    public void deleteCustomer(@PathVariable("customerId") Long customerId) {
        customerService.deleteCustomer(customerId);
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
            customerService.updateCustomer(customerId, name, surName, address, zipCode, city, email, phoneNumber);
        }
}
