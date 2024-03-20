package com.jdc.payroll.master.input;

import com.jdc.payroll.domain.master.entity.Department;
import com.jdc.payroll.master.validators.DepartmentCodeForUnique;

import jakarta.validation.constraints.NotBlank;

public record DepartmentFormForCreate(
		@DepartmentCodeForUnique(message = "Department code is already used.")
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
