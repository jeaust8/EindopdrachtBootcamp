package nl.aartj.GarageApp.task;

import nl.aartj.GarageApp.exceptions.TaskNotInProgress;
import nl.aartj.GarageApp.placedTask.PlacedTask;
import nl.aartj.GarageApp.placedTask.PlacedTaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    private final TaskRepository taskRepository;
    private final PlacedTaskRepository placedTaskRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    public TaskService(TaskRepository taskRepository, PlacedTaskRepository placedTaskRepository, EntityManager entityManager){
        this.taskRepository = taskRepository;
        this.placedTaskRepository = placedTaskRepository;
        this.entityManager = entityManager;
    }

    public List<Task> getTasks(){
        ArrayList<Task> tasks = new ArrayList<Task>();
        tasks.addAll(taskRepository.findAll());
        int i = 0;
        for (Task task: tasks) {

            if (task.getStatus().equalsIgnoreCase("task_finished")){
                tasks.remove(i);
            }
            i++;
        }
        return tasks;
    }

    public List<Task> getFinishedTasks(){
        ArrayList<Task> tasks = new ArrayList<Task>();
        tasks.addAll(taskRepository.findAll());
        int i = 0;

        for (Task task: tasks) {

            if (!task.getStatus().equalsIgnoreCase("ready_for_pickup")){
                tasks.remove(i);
            }
            i++;

        }
        return tasks;

    }

    @Transactional
    public String taskTransit(Optional<PlacedTask> taskDetails){
        taskDetails.get().setStatus("task_in_progress");

        entityManager.createNativeQuery("UPDATE placed_task SET status = ? WHERE id = ?")
                .setParameter(1, taskDetails.get().getStatus())
                .setParameter(2, taskDetails.get().getId())
                .executeUpdate();
        entityManager.createNativeQuery("UPDATE task SET status = ? WHERE id = ?")
                .setParameter(1, taskDetails.get().getStatus())
                .setParameter(2, taskDetails.get().getId())
                .executeUpdate();

        return "Taak-status is bijgewerkt.";
    }

    @Transactional
    public String taskDone(Optional<PlacedTask> taskDetails){
        if(taskDetails.get().getStatus().equalsIgnoreCase("task_in_transit")){
            taskDetails.get().setStatus("task_done");

            entityManager.createNativeQuery("UPDATE placed_task SET status = ? WHERE id = ?")
                    .setParameter(1, taskDetails.get().getStatus())
                    .setParameter(2, taskDetails.get().getId())
                    .executeUpdate();
            entityManager.createNativeQuery("UPDATE task SET status = ? WHERE id = ?")
                    .setParameter(1, taskDetails.get().getStatus())
                    .setParameter(2, taskDetails.get().getId())
                    .executeUpdate();

            return "Taak-status is bijgewerkt.";
        }
        else{
            throw new TaskNotInProgress("Taak is nog niet in proces.")
        }
    }
}
