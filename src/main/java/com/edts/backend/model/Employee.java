package com.edts.backend.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@SuppressWarnings("serial")
@Entity
@Table(name = "employee")
public class Employee implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(name = "name")
	private String name;
	@Column(name = "salary")
	private BigDecimal salary;
	@Column(name = "bonus")
	private BigDecimal bonus;

	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "grade_id", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Grade grade;
	
	
	
	public Employee() {
		super();
	}

	public Employee(long id, String name, BigDecimal salary, BigDecimal bonus, Grade grade) {
		super();
		this.id = id;
		this.name = name;
		this.salary = salary;
		this.bonus = bonus;
		this.grade = grade;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getSalary() {
		return salary;
	}

	public void setSalary(BigDecimal salary) {
		this.salary = salary;
	}

	public BigDecimal getBonus() {
		return bonus;
	}

	public void setBonus(BigDecimal bonus) {
		this.bonus = bonus;
	}

	public Grade getGrade() {
		return grade;
	}

	public void setGrade(Grade grade) {
		this.grade = grade;
	}

}
