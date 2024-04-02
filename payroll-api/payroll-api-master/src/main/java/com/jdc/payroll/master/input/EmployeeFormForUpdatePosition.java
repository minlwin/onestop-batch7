package com.jdc.payroll.master.input;

import com.jdc.payroll.domain.master.entity.PositionPk.PositionCode;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record EmployeeFormForUpdatePosition(
		@NotNull(message = "Please select position.")
		PositionCode position,
		@NotBlank(message = "Please enter remark for change.")
		String remark
) {

}
