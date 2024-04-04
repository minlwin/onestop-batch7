package com.jdc.payroll.master.input;

import java.util.ArrayList;

import org.springframework.util.StringUtils;

import com.jdc.payroll.domain.master.CalculationType;
import com.jdc.payroll.domain.master.entity.Allowance;
import com.jdc.payroll.domain.master.entity.Allowance_;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public record AllowanceSearch(
		String name,
		CalculationType type
		) {

	public Predicate[] where(CriteriaBuilder cb, Root<Allowance> root) {
		var list = new ArrayList<Predicate>();
		
		if(null != type) {
			list.add(cb.equal(root.get(Allowance_.type), type));
		}
		
		if(StringUtils.hasLength(name)) {
			list.add(cb.like(cb.lower(root.get(Allowance_.name)), name.toLowerCase().concat("%")));
		}
		
		return list.toArray(size -> new Predicate[size]);
	}
}
