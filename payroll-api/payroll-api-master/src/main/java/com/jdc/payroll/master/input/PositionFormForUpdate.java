package com.jdc.payroll.master.input;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record PositionFormForUpdate(
		@NotBlank(message = "Please enter position name.")
		String positionName,
		@NotNull(message = "Please enter basic salary.")
		BigDecimal basicSalary,
		@NotNull(message = "Please enter OT fees.")
		BigDecimal otPerHour,
		@NotNull(message = "Please enter anual leaves.")
		Integer anualLeaves) {

}
