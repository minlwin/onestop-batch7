package com.jdc.payroll.master.input;

import com.jdc.payroll.domain.master.entity.Holiday.Type;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record HolidayFormForUpdate(
		@NotNull(message = "Please select holiday type.")
		Type type,
		@NotBlank(message = "Please enter holiday name.")
		String holiday,
		String remark) {

}
