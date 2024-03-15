package com.jdc.payroll.master.input;

import java.time.LocalDate;

import com.jdc.payroll.domain.master.entity.Holiday;
import com.jdc.payroll.domain.master.entity.Holiday.Type;

public record HolidayFormForCreate(
		LocalDate date,
		Type type,
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
