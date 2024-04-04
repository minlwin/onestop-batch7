package com.jdc.payroll.master.input;

import com.jdc.payroll.domain.master.CalculationType;
import com.jdc.payroll.domain.master.entity.Deduction;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public abstract class DeductionForm {
	
	@NotBlank(message = "Please enter allowance name.")
	private String name;
	@NotNull(message = "Please select allowance type.")
	private CalculationType type;
	private String description;
	
	public abstract Deduction getEntity();

}
