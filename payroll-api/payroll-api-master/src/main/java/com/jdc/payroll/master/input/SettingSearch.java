package com.jdc.payroll.master.input;

import java.time.LocalDate;
import java.util.ArrayList;

import com.jdc.payroll.domain.master.entity.Settings;
import com.jdc.payroll.domain.master.entity.Settings_;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public record SettingSearch(
		LocalDate from,
		LocalDate to) {

	public Predicate[] where(CriteriaBuilder cb, Root<Settings> root) {
		var list = new ArrayList<Predicate>();
		
		if(null != from) {
			list.add(cb.greaterThanOrEqualTo(root.get(Settings_.effectAt), from));
		}
		
		if(null != to) {
			list.add(cb.lessThanOrEqualTo(root.get(Settings_.effectAt), to));
		}
		
		return list.toArray(a -> new Predicate[a]);
	}
}
