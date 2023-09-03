package com.luv2code.springboot.cruddemo.rest;

import com.luv2code.springboot.cruddemo.entity.Employee;
import com.luv2code.springboot.cruddemo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {

    //define and inject EmployeeDAO
    private EmployeeService employeeService;

    @Autowired
    public EmployeeRestController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/employees")
    public List<Employee> findAll() {

        return employeeService.findAll();
    }

    @GetMapping("/employees/{empId}")
    public Employee getEmployeeById(@PathVariable int empId) {
        Employee employee = employeeService.getEmployeeById(empId);

        if (employee == null)
            throw new RuntimeException("Employee not found with given ID - " + empId);

        return employee;
    }

    @PostMapping("/employees")
    public Employee addEmployee(@RequestBody Employee theEmployee) {

        //if some id is passed in JSON, set id to 0
        //force save of new item, instead of update
        theEmployee.setId(0);
        Employee dbEmp = employeeService.save(theEmployee);
        return dbEmp;
    }

    @PutMapping("/employees")
    public Employee updateEmployee(@RequestBody Employee theEmp) {
        Employee dbEmp = employeeService.save(theEmp);
        return dbEmp;
    }

    @DeleteMapping("/employees/{empId}")
    public String deleteEmployee(@PathVariable int empId) {
        Employee employee = employeeService.getEmployeeById(empId);

        //throw exception if null
        if (employee == null)
            throw new EmployeeNotFoundException("Employee not found with ID - " + empId);

        employeeService.deleteById(empId);

        return "Deleted Employee with ID - " + empId;
    }

}
