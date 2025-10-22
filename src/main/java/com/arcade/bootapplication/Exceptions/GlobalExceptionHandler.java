package com.arcade.bootapplication.Exceptions;

import com.arcade.bootapplication.Entity.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorMessage> handleResourceNotFound(ResourceNotFoundException ex, WebRequest request) {
        return buildErrorResponse(ex, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DatabaseOperationException.class)
    public ResponseEntity<ErrorMessage> handleDatabaseError(DatabaseOperationException ex, WebRequest request) {
        return buildErrorResponse(ex, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(StudentNotFoundException.class)
    public ResponseEntity<ErrorMessage> handleStudentNotFound(StudentNotFoundException ex, WebRequest request) {
        return buildErrorResponse(ex, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorMessage> handleGeneralError(Exception ex, WebRequest request) {
        return buildErrorResponse(new Exception("An unexpected error occurred."), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private ResponseEntity<ErrorMessage> buildErrorResponse(Exception ex, HttpStatus status) {
        ErrorMessage errorMessage = new ErrorMessage(status, ex.getMessage());
        return ResponseEntity.status(status).body(errorMessage);
    }
}
