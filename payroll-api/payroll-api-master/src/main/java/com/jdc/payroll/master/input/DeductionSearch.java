package com.jdc.payroll.master.input;

import java.util.ArrayList;

import org.springframework.util.StringUtils;

import com.jdc.payroll.domain.master.CalculationType;
import com.jdc.payroll.domain.master.entity.Deduction;
import com.jdc.payroll.domain.master.entity.Deduction_;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public record DeductionSearch(
		String name,
		CalculationType type) {

	public Predicate[] where(CriteriaBuilder cb, Root<Deduction> root) {
		var list = new ArrayList<Predicate>();
		
		if(null != type) {
			list.add(cb.equal(root.get(Deduction_.type), type));
		}
		
		if(StringUtils.hasLength(name)) {
			list.add(cb.like(cb.lower(root.get(Deduction_.name)), name.toLowerCase().concat("%")));
		}
		
		return list.toArray(size -> new Predicate[size]);
	}
}
