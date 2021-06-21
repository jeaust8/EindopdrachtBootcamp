package nl.aartj.GarageApp.security;

import nl.aartj.GarageApp.customer.CustomerAccount;
import nl.aartj.GarageApp.customer.CustomerAccountService;
import nl.aartj.GarageApp.employee.EmployeeAccount;
import nl.aartj.GarageApp.employee.EmployeeAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    EmployeeAccountService employeeAccountService;

    @Autowired
    CustomerAccountService customerAccountService;

    public String userEmail;

    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<EmployeeAccount> optionalEmployeeAccount = employeeAccountService.getEmployeeByEmail(email);

        if (optionalEmployeeAccount.isEmpty()) {
            Optional<CustomerAccount> optionalCustomerAccount = customerAccountService.getCustomerByEmail(email);
            CustomerAccount customerAccount = optionalCustomerAccount.get();

            if(optionalCustomerAccount.isPresent()){
                UserDetails user = User.builder()
                        .username(customerAccount.getEmail())
                        .password(customerAccount.getPassword())
                        .roles("ROLE_CUSTOMER")
                        .build();

                userEmail = customerAccount.getEmail();

                return user;


            }

            throw new UsernameNotFoundException("Gebruikersnaam niet gevonden.");
        }

        EmployeeAccount employeeAccount = optionalEmployeeAccount.get();

        UserDetails user = User.builder()
                .username(employeeAccount.getEmail())
                .password(employeeAccount.getPassword())
                .roles(employeeAccount.getRole())
                .build();


        userEmail = employeeAccount.getEmail();


        return user;
    }
}
