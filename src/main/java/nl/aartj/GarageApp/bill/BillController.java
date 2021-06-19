package nl.aartj.GarageApp.bill;

import nl.aartj.GarageApp.placedTask.PlacedTaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/GarageApp/api/v1/bills")
public class BillController {

    private final BillService billService;
    private final PlacedTaskRepository placedTaskRepository;

    @Autowired
    public BillController(BillService billService, PlacedTaskRepository placedTaskRepository){
        this.billService = billService;
        this.placedTaskRepository = placedTaskRepository;
    }

    @GetMapping
    public List<Bill> getBills(){return billService.getBills();}
}
