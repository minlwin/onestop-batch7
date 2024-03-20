package com.jdc.payroll.master.output;

import java.time.LocalDate;

import com.jdc.payroll.domain.master.entity.Account_;
import com.jdc.payroll.domain.master.entity.Department_;
import com.jdc.payroll.domain.master.entity.Employee;
import com.jdc.payroll.domain.master.entity.Employee_;
import com.jdc.payroll.domain.master.entity.Position_;
import com.jdc.payroll.domain.master.entity.Employee.Status;

import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

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
	
	public static void select(CriteriaQuery<EmployeeInfo> cq, Root<Employee> root) {
		cq.multiselect(
			root.get(Employee_.code),
			root.get(Employee_.account).get(Account_.name),
			root.get(Employee_.phone),
			root.get(Employee_.department).get(Department_.name),
			root.get(Employee_.position).get(Position_.position),
			root.get(Employee_.status),
			root.get(Employee_.assignDate),
			root.get(Employee_.retireDate)			
		);
	}

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
