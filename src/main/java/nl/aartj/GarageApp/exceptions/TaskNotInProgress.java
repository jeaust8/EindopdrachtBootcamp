package nl.aartj.GarageApp.exceptions;

public class TaskNotInProgress extends RuntimeException{
    public TaskNotInProgress(String message){super(message);}
}
