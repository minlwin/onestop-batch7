package com.jdc.payroll.master.output;

import java.math.BigDecimal;
import java.util.List;

import com.jdc.payroll.domain.master.entity.Position;

public record PositionInfoDetails(
		String departmentCode,
		String departmentName,
		String positionCode,
		String positionName,
		BigDecimal basicSalary,
		BigDecimal otFeesPerHour,
		int anualLeaves,
		List<EmployeeInfo> employees
	) {

	public static PositionInfoDetails from(Position entity) {
		return new PositionInfoDetails(
			entity.getDepartment().getCode(), 
			entity.getDepartment().getName(), 
			entity.getId().getCode(), 
			entity.getPosition(), 
			entity.getBasicSalary(), 
			entity.getOtFeesPerHour(), 
			entity.getAnualLeaves(), 
			entity.getDepartment().getEmployees().stream()
				.filter(a -> a.getPosition().getId().equals(entity.getId()))
				.map(EmployeeInfo::from)
				.toList());
	}

}
