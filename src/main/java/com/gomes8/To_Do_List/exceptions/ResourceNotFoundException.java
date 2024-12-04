package com.gomes8.To_Do_List.exceptions;

public class ResourceNotFoundException extends RuntimeException{

    public ResourceNotFoundException( Object id) {
        super("Resource not found. Id " + id);
    }
}
