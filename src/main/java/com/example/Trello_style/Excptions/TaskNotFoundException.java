package com.example.Trello_style.Excptions;

public class TaskNotFoundException extends RuntimeException {
    public TaskNotFoundException(String message) {
        super(message);
    }
    public TaskNotFoundException(long id) {
        super("Task with id " + id + " not found");
    }
}
