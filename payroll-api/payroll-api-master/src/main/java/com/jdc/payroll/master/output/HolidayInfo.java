package com.jdc.payroll.master.output;

import java.time.LocalDate;

import com.jdc.payroll.domain.master.entity.Holiday.Type;

public record HolidayInfo(
		LocalDate date,
		Type type,
		String holiday,
		String remark,
		boolean deleted
		) {

}
