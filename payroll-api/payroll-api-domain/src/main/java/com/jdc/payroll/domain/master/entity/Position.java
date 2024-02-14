package com.jdc.payroll.domain.master.entity;

import java.math.BigDecimal;

import com.jdc.payroll.domain.AbstractEntity;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@EqualsAndHashCode(callSuper = false)
public class Position extends AbstractEntity {

	@EmbeddedId
	private PositionPk id;
	
	@ManyToOne
	@JoinColumn(name = "department_code", referencedColumnName = "code", insertable = false, updatable = false)
	private Department department;
	
	@Column(nullable = false)
	private String position;
	
	@Column(nullable = false)
	private BigDecimal basicSalary;

	@Column(nullable = false)
	private BigDecimal otFeesPerHour;
	
	@Column(nullable = false)
	private int anualLeaves;
}
