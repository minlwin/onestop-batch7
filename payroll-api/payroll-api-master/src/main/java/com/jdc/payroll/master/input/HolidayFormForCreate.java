package com.jdc.payroll.master.input;

import java.time.LocalDate;

import com.jdc.payroll.domain.master.entity.Holiday;
import com.jdc.payroll.domain.master.entity.Holiday.Type;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record HolidayFormForCreate(
		@NotNull(message = "Please enter date.")
		LocalDate date,
		@NotNull(message = "Please select holiday type.")
		Type type,
		@NotBlank(message = "Please enter holiday name.")
		String holiday,
		String remark) {

	public Holiday entity() {
		var entity = new Holiday();
		entity.setDate(date);
		entity.setType(type);
		entity.setHoliday(holiday);
		entity.setRemark(remark);
		return entity;
	}
}
