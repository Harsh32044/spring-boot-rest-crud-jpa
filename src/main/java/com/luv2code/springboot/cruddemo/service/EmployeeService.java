package com.luv2code.springboot.cruddemo.service;

import com.luv2code.springboot.cruddemo.entity.Employee;

import java.util.List;

public interface EmployeeService {

    //create
    Employee save(Employee emp);

    //Get an Employee by ID
    Employee getEmployeeById(Integer id);

    //read a list of Employees
    List<Employee> findAll();

    //delete an employee
    void deleteById(Integer id);
}
