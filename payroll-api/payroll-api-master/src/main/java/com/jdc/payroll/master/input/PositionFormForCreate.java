package com.jdc.payroll.master.input;

import java.math.BigDecimal;

import com.jdc.payroll.domain.master.entity.Position;
import com.jdc.payroll.domain.master.entity.PositionPk;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record PositionFormForCreate(
		@NotBlank(message = "Please select department.")
		String department,
		@NotBlank(message = "Please enter position code.")
		String positionCode,
		@NotBlank(message = "Please enter position name.")
		String positionName,
		@NotNull(message = "Please enter basic salary.")
		BigDecimal basicSalary,
		@NotNull(message = "Please enter OT fees.")
		BigDecimal otPerHour,
		@NotNull(message = "Please enter anual leaves.")
		Integer anualLeaves
		) {

	public Position entity() {
		var id = new PositionPk();
		id.setDepartmentCode(department);
		id.setPositionCode(positionCode);

		var entity = new Position();
		entity.setId(id);
		entity.setPosition(positionName);
		entity.setBasicSalary(basicSalary);
		entity.setOtFeesPerHour(otPerHour);
		entity.setAnualLeaves(anualLeaves);
		return entity;
	}

}
