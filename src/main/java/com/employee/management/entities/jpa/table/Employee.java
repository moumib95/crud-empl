package com.employee.management.entities.jpa.table;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Accessors(chain = true)
@Entity
@Table(name = "employee")
@Data
public class Employee {

  @Column(name = "name")
  private String name;

  @Column(name = "address")
  private String address;

  @Id
  @Column(name = "email")
  private String email;

  @Column(name = "phoneNo")
  private String phoneNo;
}
