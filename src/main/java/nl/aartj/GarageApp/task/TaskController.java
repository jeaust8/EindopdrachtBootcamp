package nl.aartj.GarageApp.task;


import nl.aartj.GarageApp.placedTask.PlacedTask;
import nl.aartj.GarageApp.placedTask.PlacedTaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/v1/customer/tasks")
public class TaskController {

    private final TaskService taskService;
    private final PlacedTaskRepository placedTaskRepository;

    @Autowired
    public TaskController(TaskService taskService, PlacedTaskRepository placedTaskRepository){
        this.taskService = taskService;
        this.placedTaskRepository = placedTaskRepository;
    }

    @GetMapping
    public List<Task> getTasks(){return taskService.getTasks();}

    @PreAuthorize("hasRole('ROLE_MECHANIC')")
    @PostMapping(path= "/taskdone/{taskId}")
    public String taskDone(@PathVariable ("taskId") Long taskId){
        Optional<PlacedTask> taskDetails = placedTaskRepository.findById(taskId);
        return taskService.taskDone(taskDetails);
    }
}
