package com.jdc.payroll.master.output;

import java.util.List;

import com.jdc.payroll.domain.master.entity.Department;

public record DepartmentInfoDetails(
		String code,
		String name,
		String description, 
		EmployeeInfoDetails manager,
		List<EmployeeInfo> employees) {
	
	public static DepartmentInfoDetails from(Department entity) {
		return new DepartmentInfoDetails(
				entity.getCode(), 
				entity.getName(), 
				entity.getDescription(), 
				EmployeeInfoDetails.from(entity.getManager()), 
				entity.getEmployees().stream().map(EmployeeInfo::from).toList());
	}

}
