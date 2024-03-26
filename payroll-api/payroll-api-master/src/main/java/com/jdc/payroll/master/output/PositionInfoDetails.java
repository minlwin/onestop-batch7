package com.jdc.payroll.master.output;

import java.math.BigDecimal;
import java.util.List;

import com.jdc.payroll.domain.master.entity.Position;
import com.jdc.payroll.domain.master.entity.PositionPk;

public record PositionInfoDetails(
		PositionPk id,
		String departmentName,
		BigDecimal basicSalary,
		BigDecimal otFeesPerHour,
		int anualLeaves,
		List<EmployeeInfo> employees
	) {
	
	public String getDisplayName() {
		return "%s of %s".formatted(id.getPositionCode().getValue(), departmentName);
	}
	
	public String getPositionCode() {
		return id.getCode();
	}
	
	public String getPositionName() {
		return id.getPositionCode().getValue();
	}

	public static PositionInfoDetails from(Position entity) {
		return new PositionInfoDetails(
			entity.getId(),
			entity.getDepartment().getName(), 
			entity.getBasicSalary(), 
			entity.getOtFeesPerHour(), 
			entity.getAnualLeaves(), 
			entity.getDepartment().getEmployees().stream()
				.filter(a -> a.getPosition().getId().equals(entity.getId()))
				.map(EmployeeInfo::from)
				.toList());
	}

}
