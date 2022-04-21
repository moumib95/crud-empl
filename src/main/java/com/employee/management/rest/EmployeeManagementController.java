package com.employee.management.rest;

import com.employee.management.entities.jpa.table.Employee;
import com.employee.management.entities.response.EmployeeResponse;
import com.employee.management.exceptions.EmployeeManagementException;
import com.employee.management.service.EmployeeService;
import com.employee.management.utils.EmployeeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = { "/employees" })
public class EmployeeManagementController {

  private static final String CREATE_URI = "/create";
  private static final String DELETE_URI = "/delete";
  private static final String UPDATE_URI = "/update";
  private static final String GET_ONE_URI = "/get";

  private final EmployeeService employeeService;

  @Autowired
  public EmployeeManagementController(EmployeeService employeeService) {
    this.employeeService = employeeService;
  }

  @GetMapping(path = GET_ONE_URI, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<EmployeeResponse> getEmployee(@RequestParam String email) {
    EmployeeResponse response = employeeService.getEmployee(email);
    return new ResponseEntity<EmployeeResponse>(response, HttpStatus.OK);
  }

  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<EmployeeResponse> getAllEmployee() {
    EmployeeResponse response = employeeService.getAllEmployee();
    return new ResponseEntity<EmployeeResponse>(response, HttpStatus.OK);
  }

  @PostMapping(path = CREATE_URI, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<EmployeeResponse> addEmployee(@RequestBody Employee employee) {
    EmployeeResponse response = employeeService.addEmployee(employee);
    return new ResponseEntity<EmployeeResponse>(response, HttpStatus.CREATED);
  }

  @PutMapping(path = UPDATE_URI, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<EmployeeResponse> updateEmployee(@RequestBody Employee employee) {
    EmployeeResponse response = employeeService.updateEmployee(employee);
    return new ResponseEntity<EmployeeResponse>(response, HttpStatus.OK);
  }

  @DeleteMapping(path = DELETE_URI, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<EmployeeResponse> deleteEmployee(@RequestParam String email) {
    EmployeeResponse response = employeeService.deleteEmployee(email);
    return new ResponseEntity<EmployeeResponse>(response, HttpStatus.OK);
  }

  @ExceptionHandler({ EmployeeManagementException.class })
  public ResponseEntity<EmployeeResponse> handleException() {
    EmployeeResponse response = EmployeeUtils.getFailureResponse("Error occurred while processing. " +
            "Please ensure correct values are passed");
    return new ResponseEntity<EmployeeResponse>(response, HttpStatus.INTERNAL_SERVER_ERROR);
  }

}
