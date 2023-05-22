package com.ecommerce.springbootrestapi.exception;

import com.ecommerce.springbootrestapi.model.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException exception) {

        // Create a new ErrorResponse object with the exception message
        ErrorResponse errorResponse = new ErrorResponse(exception.getMessage());

        // Return a new ResponseEntity with the ErrorResponse and the HTTP status code
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<?> handleIllegalArgumentException(IllegalArgumentException exception) {
        // Create a new ErrorResponse object with the exception message
        ErrorResponse errorResponse = new ErrorResponse(exception.getMessage());

        // Return a new ResponseEntity with the ErrorResponse and the HTTP status code
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}
