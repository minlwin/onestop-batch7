package com.jdc.payroll.master.input;

import com.jdc.payroll.domain.master.entity.Holiday.Type;

public record HolidayFormForUpdate(
		Type type,
		String holiday,
		String remark) {

}
