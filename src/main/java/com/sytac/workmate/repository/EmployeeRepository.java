package com.sytac.workmate.repository;

import org.springframework.data.repository.CrudRepository;

import com.sytac.workmate.model.Employee;

public interface EmployeeRepository  extends CrudRepository<Employee, String>  {


}
