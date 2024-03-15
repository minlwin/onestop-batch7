package com.jdc.payroll.master.output;

public record DepartmentInfo(
		String code,
		String name,
		String managerCode,
		String managerName,
		String managerPhone,
		long employees) {

}
