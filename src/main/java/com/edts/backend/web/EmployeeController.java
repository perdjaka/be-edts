package com.edts.backend.web;

import com.edts.backend.model.Employee;
import com.edts.backend.model.Grade;
import com.edts.backend.service.EmployeeService;
import com.edts.backend.service.GradeService;
import com.edts.backend.util.HeaderUtil;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeController {
	@Autowired
	private EmployeeService employeeService;

	@Autowired
	private GradeService gradeService;

	private static final String ENTITY_NAME = "Employee";

	@GetMapping(value = "/employee")
	public ResponseEntity<List<Employee>> getAllEmployees() {
		List<Employee> employees = this.employeeService.getAll();
		return new ResponseEntity<>(employees, HttpStatus.OK);
	}

	@PostMapping(value = "/grade/{code}/employee/add")
	public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee, @PathVariable("code") String code)
			throws URISyntaxException {
		Employee temp = this.gradeService.getByCode(code).map(grade -> {
			employee.setGrade(grade);
			return this.employeeService.saveEmployee(employee);
		}).orElseThrow(() -> new RuntimeException("Not found grade with id = " + code));
		return ResponseEntity.created(new URI("/grade/" + code + "/employee/add/" + temp.getId()))
				.headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, String.valueOf(temp.getId()))).body(temp);
	}

	@PutMapping(value = "/grade/{code}/employee/update")
	public ResponseEntity<Employee> updateEmployee(@PathVariable("code") String code, @RequestBody Employee employee) {
		Grade targetGrade = this.gradeService.getByCode(code)
				.orElseThrow(() -> new RuntimeException("Not found Grade with code = " + code));
		Optional<Employee> currentEmployeeOpt = this.employeeService.getById(employee.getId());
		if (!currentEmployeeOpt.isPresent()) {
			throw new RuntimeException("Not found Employee with id = " + employee.getId());
		}
		Employee currentEmployee = currentEmployeeOpt.get();
		currentEmployee.setGrade(targetGrade);
		currentEmployee.setName(employee.getName());
		currentEmployee.setSalary(employee.getSalary());
		
		Employee result = this.employeeService.saveEmployee(currentEmployee);
		return ResponseEntity.ok()
				.headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, String.valueOf(employee.getId())))
				.body(result);
	}

	@DeleteMapping(value = "/employee/delete/{id}")
	public ResponseEntity<Void> deleteEmployee(@PathVariable("id") long id) {
		if (!this.employeeService.getById(id).isPresent()) {
			throw new RuntimeException("Not found Employee with id = " + id);
		}
		this.employeeService.deleteEmployee(id);
		return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, String.valueOf(id)))
				.build();
	}
}
