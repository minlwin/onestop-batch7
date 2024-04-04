package com.jdc.payroll.master.input;

import java.math.BigDecimal;

import com.jdc.payroll.domain.master.CalculationTarget;
import com.jdc.payroll.domain.master.entity.Deduction;
import com.jdc.payroll.domain.master.entity.DeductionForPercent;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class DeductionFormForPercent extends DeductionForm{

	@NotNull(message = "Enter Amount for fix calculation.")
	private BigDecimal amount;
	@NotNull(message = "Enter Amount for calculation target.")
	private CalculationTarget target;

	@Override
	public Deduction getEntity() {
		var entity = new DeductionForPercent();
		entity.setName(getName());
		entity.setType(getType());
		entity.setDescription(getDescription());
		entity.setAmount(amount);
		entity.setTarget(target);
		return entity;
	}

}
