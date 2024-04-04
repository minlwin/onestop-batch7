package com.jdc.payroll.domain.master.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import com.jdc.payroll.domain.master.CalculationTarget;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

@Data
@Embeddable
public class TierItem implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Column(nullable = false)
	private BigDecimal rangeFrom;
	@Column(nullable = false)
	private BigDecimal rangeTo;
	
	@Column(nullable = false)
	private BigDecimal amount;
	@Column(nullable = false)
	private CalculationTarget target;
	@Column(nullable = false)
	private boolean percent;
}
