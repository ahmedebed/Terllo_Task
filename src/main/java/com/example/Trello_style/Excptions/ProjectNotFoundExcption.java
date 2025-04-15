package com.example.Trello_style.Excptions;

public class ProjectNotFoundExcption extends RuntimeException {
    public ProjectNotFoundExcption(String message) {
        super(message);
    }
    public ProjectNotFoundExcption(long id) {
        super("Project with ID: [ " + id + " ] is NOT Found");
    }
}
