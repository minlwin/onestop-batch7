package com.jdc.payroll.master.output;

import com.jdc.payroll.domain.master.entity.Account_;
import com.jdc.payroll.domain.master.entity.Department;
import com.jdc.payroll.domain.master.entity.Department_;
import com.jdc.payroll.domain.master.entity.Employee_;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Root;

public record DepartmentInfo(
		String code,
		String name,
		String managerCode,
		String managerName,
		String managerPhone,
		long employees) {

	public static void select(CriteriaBuilder cb, CriteriaQuery<DepartmentInfo> cq, Root<Department> root) {
		
		var manager = root.join(Department_.manager, JoinType.LEFT);
		var employee = root.join(Department_.employees, JoinType.LEFT);
		
		cq.multiselect(
			root.get(Department_.code),
			root.get(Department_.name),
			manager.get(Employee_.code),
			manager.get(Employee_.account).get(Account_.name),
			cb.count(employee.get(Employee_.code))
		);
		
		cq.groupBy(
			root.get(Department_.code),
			root.get(Department_.name),
			manager.get(Employee_.code),
			manager.get(Employee_.account).get(Account_.name)
		);
	}
}
