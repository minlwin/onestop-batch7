package com.jdc.payroll.master.input;

import java.util.ArrayList;

import org.springframework.util.StringUtils;

import com.jdc.payroll.domain.master.entity.Department_;
import com.jdc.payroll.domain.master.entity.Position;
import com.jdc.payroll.domain.master.entity.PositionPk_;
import com.jdc.payroll.domain.master.entity.Position_;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public record PositionSearch(
		String department, 
		String position) {
	
	public Predicate[] where(CriteriaBuilder cb, Root<Position> root) {
		var list = new ArrayList<Predicate>();
		
		if(StringUtils.hasLength(department)) {
			list.add(
				cb.equal(root.get(Position_.department).get(Department_.code), department)
			);
		}
		
		if(StringUtils.hasLength(position)) {
			list.add(cb.or(
				cb.equal(root.get(Position_.id).get(PositionPk_.code), position),
				cb.like(cb.lower(root.get(Position_.position)), position.toLowerCase().concat("%"))
			));
		}
		
		return list.toArray(size -> new Predicate[size]);
	}

}
