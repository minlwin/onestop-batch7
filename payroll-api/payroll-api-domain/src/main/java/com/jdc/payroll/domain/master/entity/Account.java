package com.jdc.payroll.domain.master.entity;

import com.jdc.payroll.domain.AbstractEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@EqualsAndHashCode(callSuper = false)
@SequenceGenerator(name = "ACCOUNT_SEQ", allocationSize = 1)
public class Account extends AbstractEntity{

	@Id
	@GeneratedValue(generator = "ACCOUNT_SEQ")
	private long id;
	
	@Column(nullable = false)
	private String name;
	
	@Column(nullable = false, unique = true)
	private String username;

	@Column(nullable = false)
	private String password;
	
	@Column(nullable = false)
	private Role role;
	
	@OneToOne(mappedBy = "account")
	private Employee employee;
	
	private boolean activated;
	
	public enum Role {
		Admin, Employee
	}
}
