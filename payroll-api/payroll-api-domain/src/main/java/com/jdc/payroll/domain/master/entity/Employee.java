package com.jdc.payroll.domain.master.entity;

import java.time.LocalDate;

import com.jdc.payroll.domain.AbstractEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@EqualsAndHashCode(callSuper = false)
public class Employee extends AbstractEntity{

	@Id
	private String code;
	
	@OneToOne(optional = false)
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

	@Column(nullable = false)
	private LocalDate assignDate;
	
	private LocalDate statusChangeDate;
	
	public enum Status {
		Provation, Permenant, Retired
	}
	
	public enum Gender {
		Male, Female
	}
	
}
