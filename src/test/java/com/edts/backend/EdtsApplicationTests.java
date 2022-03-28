package com.edts.backend;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.edts.backend.model.Employee;
import com.edts.backend.model.Grade;
import com.edts.backend.service.EmployeeService;
import com.edts.backend.service.GradeService;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
class EdtsApplicationTests {

	@Test
	void contextLoads() {
	}

	@MockBean
	private EmployeeService employeeServiceMock;

	@Test
	public void checkBonusCalculation() throws Exception {

		Grade grade1 = new Grade(1L, "1", "Manager", new BigDecimal("0.1"));
		Employee employee1 = new Employee();
		employee1.setGrade(grade1);
		employee1.setName("employee 1");
		employee1.setSalary(new BigDecimal("7563000"));
		employee1.setBonus(employee1.getSalary().add(employee1.getSalary().multiply(grade1.getRate())).setScale(0,
				RoundingMode.DOWN));
		Mockito.when(employeeServiceMock.saveEmployee(employee1)).thenReturn(employee1);
		Employee foundEmployee1 = employeeServiceMock.saveEmployee(employee1);
		assertEquals(new BigDecimal("8319300"), foundEmployee1.getBonus());

		Grade grade2 = new Grade(2L, "2", "Supervisor", new BigDecimal("0.06"));
		Employee employee2 = new Employee();
		employee2.setGrade(grade2);
		employee2.setName("employee 2");
		employee2.setSalary(new BigDecimal("5124000"));
		employee2.setBonus(employee2.getSalary().add(employee2.getSalary().multiply(grade2.getRate())).setScale(0,
				RoundingMode.DOWN));
		Mockito.when(employeeServiceMock.saveEmployee(employee2)).thenReturn(employee2);
		Employee foundEmployee2 = employeeServiceMock.saveEmployee(employee2);
		assertEquals(new BigDecimal("5431440"), foundEmployee2.getBonus());

		Grade grade3 = new Grade(3L, "3", "Staff", new BigDecimal("0.03"));
		Employee employee3 = new Employee();
		employee3.setGrade(grade3);
		employee3.setName("employee 3");
		employee3.setSalary(new BigDecimal("3980000"));
		employee3.setBonus(employee3.getSalary().add(employee3.getSalary().multiply(grade3.getRate())).setScale(0,
				RoundingMode.DOWN));
		Mockito.when(employeeServiceMock.saveEmployee(employee3)).thenReturn(employee3);
		Employee foundEmployee3 = employeeServiceMock.saveEmployee(employee3);
		assertEquals(new BigDecimal("4099400"), foundEmployee3.getBonus());
	}
}
