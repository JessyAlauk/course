package com.educandoweb.course.service.exceptions;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(Object id) {
        super("resource not found. Id " + id);
    }
}
