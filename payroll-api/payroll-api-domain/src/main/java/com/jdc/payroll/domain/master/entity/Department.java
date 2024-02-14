package com.jdc.payroll.domain.master.entity;

import com.jdc.payroll.domain.AbstractEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@EqualsAndHashCode(callSuper = false)
public class Department extends AbstractEntity{

	@Id
	private String code;
	
	@Column(nullable = false, unique = true)
	private String name;
	
	private String description;
	
	@ManyToOne
	private Employee manager;
	
}
