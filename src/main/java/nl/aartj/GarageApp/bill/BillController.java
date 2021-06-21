package nl.aartj.GarageApp.bill;

import nl.aartj.GarageApp.postedTask.PostedTaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/GarageApp/api/v1/bills")
public class BillController {

    private final BillService billService;
    private final PostedTaskRepository postedTaskRepository;

    @Autowired
    public BillController(BillService billService, PostedTaskRepository postedTaskRepository){
        this.billService = billService;
        this.postedTaskRepository = postedTaskRepository;
    }

    @GetMapping
    public List<Bill> getBills(){return billService.getBills();}
}
