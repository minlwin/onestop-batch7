package com.jdc.payroll.master.output;

import java.time.LocalDate;

import com.jdc.payroll.domain.master.entity.Employee;
import com.jdc.payroll.domain.master.entity.Employee.Gender;
import com.jdc.payroll.domain.master.entity.Employee.Status;

public record EmployeeInfoDetails(
		String code,
		String name,
		String loginId,
		String phone,
		String email,
		LocalDate dateOfBirth,
		Gender gender,
		String department,
		String position,
		Status status,
		LocalDate assignAt,
		LocalDate retiredAt,
		String remark) {

	public static EmployeeInfoDetails from(Employee entity) {
		return new EmployeeInfoDetails(
			entity.getCode(), 
			entity.getAccount().getName(), 
			entity.getAccount().getUsername(), 
			entity.getPhone(), 
			entity.getEmail(), 
			entity.getDateOfBirth(), 
			entity.getGender(), 
			entity.getDepartment().getName(), 
			entity.getPosition().getPosition(), 
			entity.getStatus(), 
			entity.getAssignDate(), 
			entity.getRetireDate(), 
			entity.getRemark());
	}
}
