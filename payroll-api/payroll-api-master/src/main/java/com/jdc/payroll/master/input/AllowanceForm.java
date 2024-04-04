package com.jdc.payroll.master.input;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.As;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;
import com.jdc.payroll.domain.master.CalculationType;
import com.jdc.payroll.domain.master.entity.Allowance;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@JsonTypeInfo(
	use = Id.NAME, include = As.EXISTING_PROPERTY, property = "type", visible = true
)
@JsonSubTypes({
	@JsonSubTypes.Type(value = AllowanceFormForFix.class, name = "Fix"),
	@JsonSubTypes.Type(value = AllowanceFormForPercent.class, name = "Percent"),
	@JsonSubTypes.Type(value = AllowanceFormForTier.class, name = "Tier"),	
})
public abstract class AllowanceForm {

	@NotBlank(message = "Please enter allowance name.")
	private String name;
	@NotNull(message = "Please select allowance type.")
	private CalculationType type;
	private String description;
	
	public abstract Allowance getEntity();
}
