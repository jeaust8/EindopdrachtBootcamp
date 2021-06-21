package nl.aartj.GarageApp.postedTask;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "GarageApp/api/v1/taken")
public class PostedTaskManagementController {

    private final PostedTaskService postedTaskService;

    public PostedTaskManagementController(PostedTaskService postedTaskService){
        this.postedTaskService = postedTaskService;
    }

    @GetMapping
    public List<PostedTask> getPostedTasks(){return postedTaskService.getTasks();
    }

    @PreAuthorize("hasRole('ROLE_MECHANIC')")
    @PostMapping(path = "ready/{taskId}")
    public String readyTask(@PathVariable("taskId") Long taskId){ return postedTaskService.readyTask(taskId);}
}
