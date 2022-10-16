package com.teckas.employeeapp.controller;

import com.teckas.employeeapp.exception.ApplicationException;
import com.teckas.employeeapp.models.db.EmployeeDAO;
import com.teckas.employeeapp.models.request.EmployeeAddRequest;
import com.teckas.employeeapp.models.response.ResponseMessage;
import com.teckas.employeeapp.models.response.ResponseStatus;
import com.teckas.employeeapp.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * The controller class receives the request from clients based on the endpoint
 * */
@RestController
@RequestMapping(value = "/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    /**
     * POST request to create a new employee
     * @param employeeAddRequest employee details (JSON)
     * @return Employee creation success message/Failure message
     * */
    @PostMapping
    public ResponseEntity<?> addEmployee(@RequestBody EmployeeAddRequest employeeAddRequest) {
        employeeService.addEmployee(employeeAddRequest);
        return new ResponseEntity<>(new ResponseMessage(ResponseStatus.Successful,"Employee created Successfully"),HttpStatus.OK);
    }

    /**
     * GET request to fetch all the employee details
     * @return Employees list/Empty array (JSON)
     * */
    @GetMapping
    public ResponseEntity<?> getAllEmployees() {
        List<EmployeeDAO> employeeDAOList = employeeService.fetchAllEmployees();
        return new ResponseEntity<>(employeeDAOList,HttpStatus.OK);
    }

    /**
     * Fetch the Employee based on employee ID
     * @param employeeId ID of the corresponding employee (Path variable)
     * @return Employee details (JSON)
     * */
    @GetMapping(value = "/{employeeId}")
    public ResponseEntity<?> getEmployeeById(@PathVariable int employeeId) {
        try {
            EmployeeDAO employeeDAO = employeeService.fetchEmployeeById(employeeId);
            return new ResponseEntity<>(employeeDAO,HttpStatus.OK);
        } catch (ApplicationException e) {
            return new ResponseEntity<>(new ResponseMessage(ResponseStatus.Failure,e.getErrorMessage()),HttpStatus.FORBIDDEN);
        }
    }

    /**
     * Update the employee details based on employee ID
     * @param employeeDAO employee details (JSON)
     * @return Employee updated success message/ Employee not found failure message (JSON)
     * */
    @PutMapping
    public ResponseEntity<?> updateEmployee(@RequestBody EmployeeDAO employeeDAO) {
        try {
            employeeService.updateEmployee(employeeDAO);
            return new ResponseEntity<>(new ResponseMessage(ResponseStatus.Successful,"Employee details updated successfully!"),HttpStatus.OK);
        } catch (ApplicationException e) {
            return new ResponseEntity<>(new ResponseMessage(ResponseStatus.Failure,e.getErrorMessage()),HttpStatus.FORBIDDEN);
        }
    }

    /**
     * Delete the employee details based on employee ID
     * @param employeeId ID of the corresponding employee (Path variable)
     * @return Employee deleted success message/ Employee not found failure message (JSON)
     * */
    @DeleteMapping("/{employeeId}")
    public ResponseEntity<?> deleteEmployee(@PathVariable int employeeId) {
        try {
            employeeService.deleteEmployee(employeeId);
            return new ResponseEntity<>(new ResponseMessage(ResponseStatus.Successful,"Employee details deleted successfully!"),HttpStatus.OK);
        } catch (ApplicationException e) {
            return new ResponseEntity<>(new ResponseMessage(ResponseStatus.Failure,e.getErrorMessage()),HttpStatus.FORBIDDEN);
        }
    }
}
