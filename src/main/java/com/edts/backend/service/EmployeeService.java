package com.edts.backend.service;

import com.edts.backend.model.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {
	public List<Employee> getAll();

	public Optional<Employee> getById(long id);

	public Employee saveEmployee(Employee employee);

	public void deleteEmployee(long id);
}
