package com.employee.management.entities.jpa.repo;

import com.employee.management.entities.jpa.table.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, String> {
  List<Employee> findByEmail(String email);
}
