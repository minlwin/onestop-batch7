package com.jdc.payroll.master.output;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.jdc.payroll.domain.master.entity.Employee.Gender;
import com.jdc.payroll.domain.master.entity.Employee.Status;
import com.jdc.payroll.domain.master.entity.EmployeeHistory;
import com.jdc.payroll.domain.master.entity.EmployeeHistory.Type;

public record EmployeeHistoryInfo(
		Type changeType,
		LocalDateTime changeAt,
		String name,
		String phone,
		String email,
		Gender gender,
		LocalDate dob,
		String departmentCode,
		String departmentName,
		String positionCode,
		String positionName,
		Status status,
		LocalDate assignDate,
		LocalDate provationPassDate,
		LocalDate retiredDate,
		String remark) {

	public static EmployeeHistoryInfo from(EmployeeHistory entity) {
		return new EmployeeHistoryInfo(
				entity.getType(), 
				entity.getId().getChangeAt(),
				entity.getName(), 
				entity.getPhone(), 
				entity.getEmail(), 
				entity.getGender(), 
				entity.getDateOfBirth(), 
				entity.getDepartmentCode(), 
				entity.getDepartmentName(), 
				entity.getPosition().name(), 
				entity.getPosition().getValue(), 
				entity.getStatus(), 
				entity.getAssignDate(), 
				entity.getProvationPassDate(),
				entity.getRetireDate(), 
				entity.getRemark());
	}
	
	public String getChangeTypeForDisplay() {
		return changeType.getDesplayName();
	}
}
