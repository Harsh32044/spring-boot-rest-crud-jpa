package com.luv2code.springboot.cruddemo.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class EmployeeRestExceptionHandler {

    //to handle any possible exceptions
    @ExceptionHandler
    public ResponseEntity<EmployeeErrorResponse> handleException(Exception e) {
        EmployeeErrorResponse employeeErrorResponse = new EmployeeErrorResponse();

        employeeErrorResponse.setMessage(e.getMessage());
        employeeErrorResponse.setStatus(HttpStatus.BAD_REQUEST.value());
        employeeErrorResponse.setTimeStamp(System.currentTimeMillis());

        return new ResponseEntity<>(employeeErrorResponse, HttpStatus.BAD_REQUEST);
    }

    //to handle specifically EmployeeNotFoundException

    @ExceptionHandler
    public ResponseEntity<EmployeeErrorResponse> handleException(EmployeeNotFoundException e) {
        EmployeeErrorResponse employeeErrorResponse = new EmployeeErrorResponse();

        employeeErrorResponse.setMessage(e.getMessage());
        employeeErrorResponse.setTimeStamp(System.currentTimeMillis());
        employeeErrorResponse.setStatus(HttpStatus.NOT_FOUND.value());

        return new ResponseEntity<>(employeeErrorResponse, HttpStatus.NOT_FOUND);
    }
}
