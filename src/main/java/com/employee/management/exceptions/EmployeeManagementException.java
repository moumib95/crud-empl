package com.employee.management.exceptions;

public class EmployeeManagementException extends RuntimeException {


  public EmployeeManagementException(String message) {
    super(message);
  }

  public EmployeeManagementException(String message, Throwable ex) {
    super(message, ex);
  }
}
