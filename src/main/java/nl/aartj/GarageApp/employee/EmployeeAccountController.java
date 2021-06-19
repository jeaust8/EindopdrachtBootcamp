package nl.aartj.GarageApp.employee;

import nl.aartj.GarageApp.customer.CustomerAccount;
import nl.aartj.GarageApp.customer.CustomerAccountService;
import nl.aartj.GarageApp.task.Task;
import nl.aartj.GarageApp.task.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/employee")
public class EmployeeAccountController {

    private final CustomerAccountService accountService;
    private final EmployeeAccountService employeeAccountService;
    private final TaskService taskService;

@Autowired
    public EmployeeAccountController(CustomerAccountService accountService, EmployeeAccountService employeeAccountService, TaskService taskService) {
        this.accountService = accountService;
        this.employeeAccountService = employeeAccountService;
        this.taskService = taskService;
}
    @PreAuthorize("hasRole('ROLE_MANAGER')" + "|| hasRole('ROLE_BACKOFFICE')")
    @GetMapping(path = "/list")
    public List<EmployeeAccount> getEmployeeAccount()
    {return employeeAccountService.getEmployeeAccounts();}

    // http://localhost:8080/api/v1/employee/customer/new

    @GetMapping(path = "/tasks/pickup")
    public List<Task> getFinishedCars(){
        return taskService.getFinishedTasks();
    }

    @PostMapping(path = "/customer/new")
    public String addCustomer(
            @RequestParam(required = true) String name,
            @RequestParam(required = true) String surName,
            @RequestParam(required = true) String address,
            @RequestParam(required = true) String zipCode,
            @RequestParam(required = true) String city,
            @RequestParam(required = true) String email,
            @RequestParam(required = true) String phoneNumber,
            @RequestParam(required = true) String password){

        CustomerAccount newAccount = new CustomerAccount(name, surName, address, zipCode, city, email, phoneNumber, password);

        return accountService.addNewCustomer(newAccount);

    }
}
