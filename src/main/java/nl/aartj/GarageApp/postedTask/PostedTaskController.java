package nl.aartj.GarageApp.postedTask;


import nl.aartj.GarageApp.product.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path= "api/v1/taken")
public class PostedTaskController {
    private final PostedTaskService postedTaskService;
    private final CustomerAccountService customerAccountService;
    private final UserDetailsServiceImpl userDetailsService;

    @Autowired
    public PostedTaskController(PostedTaskService postedTaskService, CustomerAccountService customerAccountService, UserDetailsServiceImpl userDetailsService){
        this.postedTaskService = postedTaskService;
        this.customerAccountService = customerAccountService;
        this.userDetailsService = userDetailsService;
    }

    @GetMapping
    public List<PostedTask> getTasks(){
        return postedTaskService.getTasksCustomer(customerAccountService.getCustomerByEmail(userDetailsService.userEmail).get().getId());
    }


}
