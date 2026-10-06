package com.educandoweb.course.resource.exceptions;

import com.educandoweb.course.service.exceptions.DataBaseException;
import com.educandoweb.course.service.exceptions.ResourceNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<StandardError> handlerResourceNotFound(ResourceNotFoundException e,
                                                          HttpServletRequest request) {
        var error = "Resource not found";
        var status = HttpStatus.NOT_FOUND;
        var err = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(DataBaseException.class)
    public ResponseEntity<StandardError> handlerDatabaseError(DataBaseException e,
                                                            HttpServletRequest request) {
        var error = "Database error";
        var status = HttpStatus.BAD_REQUEST;
        var err = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<StandardError> handleException(Exception e,
                                                         HttpServletRequest request) {
        var error = "Internal server error";
        var status = HttpStatus.INTERNAL_SERVER_ERROR;
        var err = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }
}
