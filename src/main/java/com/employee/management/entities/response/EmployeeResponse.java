package com.employee.management.entities.response;

import lombok.Data;

@Data
public class EmployeeResponse {
  private ResponseStatus status;
  private String message;
  private Object metadata;
}
