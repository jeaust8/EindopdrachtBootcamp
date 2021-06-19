package nl.aartj.GarageApp.placedTask;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "GarageApp/api/v1/taken")
public class PlacedTaskManagementController {

    private final PlacedTaskService placedTaskService;

    public PlacedTaskManagementController(PlacedTaskService placedTaskService){
        this.placedTaskService = placedTaskService;
    }

    @GetMapping
    public List<PlacedTask> getTasks(){return placedTaskService.getTasks();
    }

    @PreAuthorize("hasRole('ROLE_MECHANIC')")
    @PostMapping(path = "ready/{taskId}")
    public String readyTask(@PathVariable("taskId") Long taskId){ return placedTaskService.readyTask(taskId);}
}
