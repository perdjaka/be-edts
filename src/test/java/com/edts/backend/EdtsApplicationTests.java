package com.edts.backend;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.edts.backend.model.Employee;
import com.edts.backend.model.Grade;
import com.edts.backend.service.EmployeeService;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class EdtsApplicationTests {

	@Test
	void contextLoads() {
	}
	
	@Autowired
	private EmployeeService employeeServiceMock;
	
	@Test
	public void checkBonusCalculation() throws Exception{

		Grade grade1 = new Grade(1L, "1", "Manager", new BigDecimal(0.1));
		Employee employee1 = new Employee(1L, "jonah bluesky", new BigDecimal(7563000), new BigDecimal(0), grade1);
		Employee foundEmployee1 = employeeServiceMock.saveEmployee(employee1);
		assertEquals(foundEmployee1.getBonus(), new BigDecimal(8319300));

	}
}
