package com.jdc.payroll.master.input;

import java.time.LocalDate;
import java.util.ArrayList;

import org.springframework.util.StringUtils;

import com.jdc.payroll.domain.master.entity.Account_;
import com.jdc.payroll.domain.master.entity.Department_;
import com.jdc.payroll.domain.master.entity.Employee;
import com.jdc.payroll.domain.master.entity.Employee_;
import com.jdc.payroll.domain.master.entity.PositionPk_;
import com.jdc.payroll.domain.master.entity.Position_;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public record EmployeeSearch(
		LocalDate from, 
		LocalDate to,
		String department,
		String position,
		String name
		) {

	public Predicate[] where(CriteriaBuilder cb, Root<Employee> root) {
		var list = new ArrayList<Predicate>();
		
		if(null != from) {
			list.add(cb.greaterThanOrEqualTo(root.get(Employee_.assignDate), from));
		}
		
		if(null != to) {
			list.add(cb.lessThanOrEqualTo(root.get(Employee_.assignDate), to));
		}
		
		if(StringUtils.hasLength(department)) {
			list.add(
				cb.or(
					cb.equal(root.get(Employee_.department).get(Department_.code), department),
					cb.like(cb.lower(root.get(Employee_.department).get(Department_.name)), 
							department.toLowerCase().concat("%"))
				)	
			);
		}

		if(StringUtils.hasLength(position)) {
			list.add(cb.equal(root.get(Employee_.position).get(Position_.id).get(PositionPk_.positionCode), position));
		}
		
		if(StringUtils.hasLength(name)) {
			list.add(cb.like(cb.lower(root.get(Employee_.account).get(Account_.name)), name.toLowerCase().concat("%")));
		}

		return list.toArray(size -> new Predicate[size]);
	}
}
