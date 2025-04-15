package com.example.Trello_style.Excptions;

public class UserNotFoundExceptions extends RuntimeException{
    public UserNotFoundExceptions(long id) {
        super("User with ID: [ " + id + " ] is NOT Found");
    }

}
