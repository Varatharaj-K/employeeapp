package com.teckas.employeeapp.repository;

import com.teckas.employeeapp.models.db.EmployeeDAO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepo extends JpaRepository<EmployeeDAO,Integer> {

    EmployeeDAO findByEmployeeId(int employeeId);
}
