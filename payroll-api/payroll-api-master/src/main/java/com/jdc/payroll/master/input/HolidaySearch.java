package com.jdc.payroll.master.input;

import java.time.LocalDate;

import com.jdc.payroll.domain.master.entity.Holiday.Type;

public record HolidaySearch(
		Type type,
		LocalDate from,
		LocalDate to,
		String name,
		Boolean deleted
		) {

}
