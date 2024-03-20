package com.jdc.payroll.domain.master.entity;

import java.time.LocalDate;
import java.util.List;

import com.jdc.payroll.domain.AbstractEntity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@Table(
	indexes = {
		@Index(columnList = "assign_date")
	}
)
@EqualsAndHashCode(callSuper = false)
public class Employee extends AbstractEntity{

	@Id
	private String code;
	
	@OneToOne(optional = false, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	private Account account;
	
	@ManyToOne(optional = false)
	private Department department;
	
	@ManyToOne(optional = false)
	private Position position;
	
	@Column(nullable = false)
	private String phone;
	@Column(nullable = false)
	private String email;
	
	@Column(nullable = false)
	private Gender gender;

	@Column(nullable = false)
	private LocalDate dateOfBirth;
	
	@Column(nullable = false)
	private Status status;

	@Column(nullable = false, name = "assign_date")
	private LocalDate assignDate;
	
	private LocalDate provationPassDate;
	private LocalDate retireDate;
	
	private String remark;
	
	@OneToMany(mappedBy = "employee")
	private List<EmployeeHistory> history;
	
	public enum Status {
		Provation, Permenant, Retired
	}
	
	public enum Gender {
		Male, Female
	}
	
}
