package nl.aartj.GarageApp.placedTask;


import nl.aartj.GarageApp.product.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path= "api/v1/")
public class PlacedTaskController {
    private final PlacedTaskService placedTaskService;

    @Autowired
    public PlacedTaskController(PlacedTaskService placedTaskService){
        this.placedTaskService = placedTaskService;
    }

    @PostMapping(path= "/taak/{productId}")
    public String addTask(@PathVariable("productId") Long productId){return placedTaskService.addNewProduct(productId);}

    @DeleteMapping(path= "/taak/{productId}")
    public String removeTask(@PathVariable("productId") Long productId) {return placedTaskService.removeProduct(productId);}

    @GetMapping
    public List <Product> getProducts(){return placedTaskService.getTaskProducts();}

    @PostMapping(path = "/taak/aanmaken/{customerConsent}/{payment}")
    public ResponseEntity placeTask(@PathVariable ("customerConsent") boolean customerConsent, @PathVariable ("payment") String payment){
        return placedTaskService.placeTask(customerConsent, payment);
    }
}
