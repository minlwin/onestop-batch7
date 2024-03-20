package com.jdc.payroll.master.input;

import jakarta.validation.constraints.NotBlank;

public record DepartmentFormForUpdate(
		@NotBlank(message = "Please enter department name.")
		String name,
		String description) {

}
