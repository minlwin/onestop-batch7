package com.jdc.payroll.master.input;

import com.jdc.payroll.domain.master.entity.Employee.Status;

import jakarta.validation.constraints.NotNull;

public record EmployeeFormForUpdateStatus(
		@NotNull(message = "Please select status.")
		Status status,
		String remark
		) {

}
