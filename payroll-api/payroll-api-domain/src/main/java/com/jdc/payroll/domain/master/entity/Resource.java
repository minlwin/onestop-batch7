package com.jdc.payroll.domain.master.entity;

import com.jdc.payroll.domain.AbstractEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@EqualsAndHashCode(callSuper = false)
public class Resource extends AbstractEntity{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(nullable = false, unique = true)
	private String name;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false, name = "resource_type")
	private Type type;
	
	@Column(nullable = false, name = "base_urls")
	private String baseUrls;
	
	private String description;
	
	public enum Type {
		Master, Transaction
	}
}
