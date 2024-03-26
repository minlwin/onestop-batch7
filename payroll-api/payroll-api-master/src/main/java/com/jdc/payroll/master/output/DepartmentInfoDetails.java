package com.jdc.payroll.master.output;

import java.util.List;

import com.jdc.payroll.domain.master.entity.Department;

public record DepartmentInfoDetails(
		String code,
		String name,
		String description, 
		EmployeeInfo hod,
		List<EmployeeInfo> employees, 
		List<PositionInfo> positions) {
	
	public static DepartmentInfoDetails from(Department entity) {
		return new DepartmentInfoDetails(
				entity.getCode(), 
				entity.getName(), 
				entity.getDescription(), 
				null != entity.getHeadOfDepartment() ? EmployeeInfo.from(entity.getHeadOfDepartment()) : null, 
				entity.getEmployees().stream().map(EmployeeInfo::from).toList(),
				entity.getPositions().stream().map(PositionInfo::from).toList()
				);
	}

}
