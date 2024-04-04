package com.jdc.payroll.master.input;

import java.math.BigDecimal;

import com.jdc.payroll.domain.master.CalculationTarget;
import com.jdc.payroll.domain.master.entity.Allowance;
import com.jdc.payroll.domain.master.entity.AllowanceForPercent;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class AllowanceFormForPercent extends AllowanceForm{

	@NotNull(message = "Enter Amount for percent calculation.")
	private BigDecimal amount;
	@NotNull(message = "Enter Amount for calculation target.")
	private CalculationTarget target;
	
	@Override
	public Allowance getEntity() {
		var entity = new AllowanceForPercent();
		entity.setName(getName());
		entity.setType(getType());
		entity.setDescription(getDescription());
		entity.setAmount(amount);
		entity.setTarget(target);
		return entity;
	}

}
