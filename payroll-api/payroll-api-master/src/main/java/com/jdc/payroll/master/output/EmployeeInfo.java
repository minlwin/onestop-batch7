package com.jdc.payroll.master.output;

import java.time.LocalDate;

import com.jdc.payroll.domain.master.entity.Employee;
import com.jdc.payroll.domain.master.entity.Employee.Status;

public record EmployeeInfo(
		String code,
		String name,
		String phone,
		String department,
		String position,
		Status status,
		LocalDate assignAt,
		LocalDate retiredAt,
		String remark) {

	public static EmployeeInfo from(Employee entity) {
		return new EmployeeInfo(
			entity.getCode(), 
			entity.getAccount().getName(), 
			entity.getPhone(), 
			entity.getDepartment().getName(), 
			entity.getPosition().getPosition(), 
			entity.getStatus(), 
			entity.getAssignDate(), 
			entity.getRetireDate(), 
			entity.getRemark());
	}
}
