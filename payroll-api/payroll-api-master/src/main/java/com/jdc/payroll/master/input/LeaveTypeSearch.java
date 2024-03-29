package com.jdc.payroll.master.input;

import java.util.ArrayList;

import org.springframework.util.StringUtils;

import com.jdc.payroll.domain.master.entity.LeaveType;
import com.jdc.payroll.domain.master.entity.LeaveType_;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public record LeaveTypeSearch(
		String name,
		Integer from,
		Integer to) {

	public Predicate[] where(CriteriaBuilder cb, Root<LeaveType> root) {
		var list = new ArrayList<Predicate>();
		
		if(StringUtils.hasLength(name)) {
			list.add(cb.like(cb.lower(root.get(LeaveType_.name)), name.toLowerCase().concat("%")));
		}
		
		if(null != from && from > 0) {
			list.add(cb.ge(root.get(LeaveType_.paidDays), from));
		}
		
		if(null != to && to > 0) {
			list.add(cb.le(root.get(LeaveType_.paidDays), to));
		}

		return list.toArray(a -> new Predicate[a]);
	}
}
