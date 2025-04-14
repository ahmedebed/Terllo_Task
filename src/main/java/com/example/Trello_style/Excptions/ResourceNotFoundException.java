package com.example.Trello_style.Excptions;

public class ResourceNotFoundException extends RuntimeException{

    public ResourceNotFoundException(String message){
    super(message);
}
}
