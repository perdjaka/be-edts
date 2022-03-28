package com.edts.backend.service.impl;

import com.edts.backend.model.Employee;
import com.edts.backend.repository.EmployeeRepository;
import com.edts.backend.service.EmployeeService;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	@Autowired
	private EmployeeRepository employeeRepository;

	@Override
	public List<Employee> getAll() {
		return this.employeeRepository.findAll();
	}

	@Override
	public Optional<Employee> getById(long id) {
		return this.employeeRepository.findById(id);
	}

	@Override
	public Employee saveEmployee(Employee employee) {
		BigDecimal bonus = employee.getSalary().add(employee.getSalary().multiply(employee.getGrade().getRate())).setScale(0, RoundingMode.DOWN);
		employee.setBonus(bonus);
		return this.employeeRepository.save(employee);
		
	}

	@Override
	public void deleteEmployee(long id) {
		this.employeeRepository.deleteById(id);
	}

}
