package com.jdc.payroll.master.input;

import java.util.ArrayList;

import org.springframework.util.StringUtils;

import com.jdc.payroll.domain.master.entity.Account_;
import com.jdc.payroll.domain.master.entity.Department;
import com.jdc.payroll.domain.master.entity.Department_;
import com.jdc.payroll.domain.master.entity.Employee_;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public record DepartmentSearch(
		String department,
		String head) {

	public Predicate [] where(CriteriaBuilder cb, Root<Department> root) {
		var list = new ArrayList<Predicate>();
		
		if(StringUtils.hasLength(department)) {
			var param = department.toLowerCase().concat("%");
			list.add(cb.or(
				cb.like(cb.lower(root.get(Department_.code)), param),
				cb.like(cb.lower(root.get(Department_.name)), param)
			));
		}
 		
		if(StringUtils.hasLength(head)) {
			var employee = root.join(Department_.headOfDepartment, JoinType.LEFT);
			var param = head.toLowerCase().concat("%");
			list.add(cb.or(
				cb.like(cb.lower(employee.get(Employee_.code)), param),
				cb.like(cb.lower(employee.get(Employee_.account).get(Account_.name)), param)
			));
		}

		return list.toArray(size -> new Predicate[size]);
	}
}
