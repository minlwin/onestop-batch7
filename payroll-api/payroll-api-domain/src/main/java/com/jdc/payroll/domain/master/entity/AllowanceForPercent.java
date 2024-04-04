package com.jdc.payroll.domain.master.entity;

import java.math.BigDecimal;

import com.jdc.payroll.domain.master.CalculationTarget;
import com.jdc.payroll.domain.master.CalculationType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@EqualsAndHashCode(callSuper = false)
public class AllowanceForPercent extends Allowance{

	public AllowanceForPercent() {
		setType(CalculationType.Percent);
	}
	
	@Column(nullable = false)
	private BigDecimal amount;
	
	@Column(nullable = false)
	private CalculationTarget target;
	
}
