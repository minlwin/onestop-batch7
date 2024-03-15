package com.jdc.payroll.master.input;

import com.jdc.payroll.domain.master.entity.Department;
import com.jdc.payroll.master.validators.DepartmentCode;

import jakarta.validation.constraints.NotBlank;

public record DepartmentFormForCreate(
		@DepartmentCode(message = "Department code is already used.")
		@NotBlank(message = "Please enter department code.")
		String code,
		@NotBlank(message = "Please enter department name.")
		String name,
		String description
		) {

	public Department entity() {
		var entity = new Department();
		entity.setCode(code);
		entity.setName(name);
		entity.setDescription(description);
		return entity;
	}
}
