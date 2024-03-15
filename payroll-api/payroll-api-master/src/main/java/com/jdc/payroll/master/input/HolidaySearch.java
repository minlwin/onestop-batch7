package com.jdc.payroll.master.input;

import java.time.LocalDate;
import java.util.ArrayList;

import org.springframework.util.StringUtils;

import com.jdc.payroll.domain.master.entity.Holiday;
import com.jdc.payroll.domain.master.entity.Holiday_;
import com.jdc.payroll.domain.master.entity.Holiday.Type;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public record HolidaySearch(
		Type type,
		LocalDate from,
		LocalDate to,
		String name,
		Boolean deleted
		) {

	public Predicate[] where(CriteriaBuilder cb, Root<Holiday> root) {
		var list = new ArrayList<Predicate>();
		
		if(null != type) {
			// h.type = ?1
			list.add(cb.equal(root.get(Holiday_.type), type));
		}
		
		if(null != from) {
			list.add(cb.greaterThanOrEqualTo(root.get(Holiday_.date), from));
		}

		if(null != to) {
			list.add(cb.lessThanOrEqualTo(root.get(Holiday_.date), to));
		}
		
		if(StringUtils.hasLength(name)) {
			list.add(cb.like(cb.lower(root.get(Holiday_.holiday)), name.toLowerCase().concat("%")));
		}
		
		if(null != deleted) {
			// h.type = ?1
			list.add(cb.equal(root.get(Holiday_.deleted), deleted));
		}

		return list.toArray(size -> new Predicate[size]);
	}
}
