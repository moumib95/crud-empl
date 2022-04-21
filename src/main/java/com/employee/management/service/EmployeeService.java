package com.employee.management.service;

import com.employee.management.entities.jpa.table.Employee;
import com.employee.management.entities.jpa.repo.EmployeeRepository;
import com.employee.management.entities.response.EmployeeResponse;
import com.employee.management.exceptions.EmployeeManagementException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.employee.management.utils.EmployeeUtils.getSuccessResponse;
import static com.employee.management.utils.EmployeeUtils.getSuccessResponseWithMetadata;

@Service
@Slf4j
public class EmployeeService {

  private final EmployeeRepository repository;

  @Autowired
  public EmployeeService(EmployeeRepository repository) {
    this.repository = repository;
  }

  public EmployeeResponse addEmployee(Employee employee) {
    List<Employee> emp = this.repository.findByEmail(employee.getEmail());
    if (!emp.isEmpty()) {
      throw new EmployeeManagementException("Employee with same Email already exists.");
    }
    this.repository.save(employee);
    return getSuccessResponse("Employee Profile Added Successfully");
  }

  public EmployeeResponse deleteEmployee(String email) {
    this.repository.deleteById(email);
    log.info("Employee {} deleted", email);
    return getSuccessResponse("Employee Deleted Successfully");
  }

  public EmployeeResponse updateEmployee(Employee employee) {
    List<Employee> emp = this.repository.findByEmail(employee.getEmail());
    if (emp.isEmpty()) {
      throw new EmployeeManagementException("Employee doesn't exist in record. Please add the employee");
    }
    deleteEmployee(employee.getEmail());
    addEmployee(employee);
    return getSuccessResponse("Employee Profile Updated Successfully");
  }

  public EmployeeResponse getEmployee(String email) {
    Employee emp = this.repository.findByEmail(email).get(0);
    if (emp == null) {
      throw new EmployeeManagementException("Employee doesn't exist in record. Please add the employee");
    }
    return getSuccessResponseWithMetadata("Employee Record Fetched Successfully", emp);
  }

  public EmployeeResponse getAllEmployee() {
    List<Employee> employees = this.repository.findAll();
    return getSuccessResponseWithMetadata("Employee Data fetched successfully", employees);
  }
}
