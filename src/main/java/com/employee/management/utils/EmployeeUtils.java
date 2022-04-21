package com.employee.management.utils;

import com.employee.management.entities.response.EmployeeResponse;
import com.employee.management.entities.response.ResponseStatus;

public class EmployeeUtils {

  private EmployeeUtils() {

  }

  public static EmployeeResponse getSuccessResponse(String message) {
    EmployeeResponse response = new EmployeeResponse();
    response.setMessage(message);
    response.setStatus(ResponseStatus.SUCCESS);
    return response;
  }

  public static EmployeeResponse getSuccessResponseWithMetadata(String message, Object metadata) {
    EmployeeResponse response = getSuccessResponse(message);
    response.setMetadata(metadata);
    return response;
  }

  public static EmployeeResponse getFailureResponse(String message) {
    EmployeeResponse response = new EmployeeResponse();
    response.setMessage(message);
    response.setStatus(ResponseStatus.FAILURE);
    return response;
  }
}
