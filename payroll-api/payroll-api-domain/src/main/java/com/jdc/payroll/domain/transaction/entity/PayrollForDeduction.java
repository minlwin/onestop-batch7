package com.jdc.payroll.domain.transaction.entity;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
public class PayrollForDeduction {

	@EmbeddedId
	private PayrollForItemPk id;
	
	@ManyToOne
	@JoinColumn(name = "pay_month", referencedColumnName = "pay_month", insertable = false, updatable = false)
	@JoinColumn(name = "employee_code", referencedColumnName = "employee_code", insertable = false, updatable = false)
	private Payroll payroll;
	
	@Column(nullable = false)
	private String particular;
	@Column(nullable = false)
	private int times;
	@Column(nullable = false)
	private BigDecimal unitAmount;
	@Column(nullable = false)
	private BigDecimal totalAmount;

}
