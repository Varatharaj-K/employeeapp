package com.teckas.employeeapp.service;

import com.teckas.employeeapp.exception.ApplicationException;
import com.teckas.employeeapp.models.db.EmployeeDAO;
import com.teckas.employeeapp.models.request.EmployeeAddRequest;
import com.teckas.employeeapp.repository.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {


    @Autowired
    private EmployeeRepo employeeRepo;
    public void addEmployee(EmployeeAddRequest employeeAddRequest) {
        EmployeeDAO employeeDAO = new EmployeeDAO();
        employeeDAO.setName(employeeAddRequest.getName());
        employeeDAO.setAddress(employeeAddRequest.getAddress());
        employeeDAO.setPosition(employeeAddRequest.getPosition());
        employeeDAO.setSalary(employeeAddRequest.getSalary());
        employeeRepo.save(employeeDAO);
    }

    public List<EmployeeDAO> fetchAllEmployees() {
        List<EmployeeDAO> employeeDAOList = employeeRepo.findAll();
        return employeeDAOList;
    }

    public EmployeeDAO fetchEmployeeById(int employeeId) throws ApplicationException {
        EmployeeDAO employeeDAO = employeeRepo.findByEmployeeId(employeeId);
        if (employeeDAO == null) {
            throw new ApplicationException("Employee not found");
        }
        return employeeDAO;
    }

    public void updateEmployee(EmployeeDAO employeeDAO) throws ApplicationException {
        EmployeeDAO employeeDAO1 = employeeRepo.findByEmployeeId(employeeDAO.getEmployeeId());
        if (employeeDAO1 == null) {
            throw new ApplicationException("Employee not found");
        }
        employeeRepo.save(employeeDAO);
    }

    public void deleteEmployee(int employeeId) throws ApplicationException {
        EmployeeDAO employeeDAO = employeeRepo.findByEmployeeId(employeeId);
        if (employeeDAO == null) {
            throw new ApplicationException("Employee not found");
        }
        employeeRepo.delete(employeeDAO);
    }
}
