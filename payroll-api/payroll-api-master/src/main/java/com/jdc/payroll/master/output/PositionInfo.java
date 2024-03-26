package com.jdc.payroll.master.output;

import java.math.BigDecimal;
import java.util.ArrayList;

import com.jdc.payroll.domain.master.entity.Department_;
import com.jdc.payroll.domain.master.entity.Position;
import com.jdc.payroll.domain.master.entity.PositionPk;
import com.jdc.payroll.domain.master.entity.Position_;

import jakarta.persistence.criteria.Root;
import jakarta.persistence.criteria.Selection;

public record PositionInfo(
		PositionPk id,
		String departmentCode,
		String departmentName,
		BigDecimal basicSalary,
		BigDecimal otFeesPerHour,
		int anualLeaves
		) {
	
	public String getCode() {
		return id.getCode();
	}
	
	public String getPositionCode() {
		return id.getPositionCode().name();
	}
	
	public String getPositionName() {
		return id.getPositionCode().getValue();
	}

	public static PositionInfo from(Position entity) {
		return new PositionInfo(
				entity.getId(),
				entity.getDepartment().getCode(), 
				entity.getDepartment().getName(), 
				entity.getBasicSalary(), 
				entity.getOtFeesPerHour(), 
				entity.getAnualLeaves());
	}

	public static Selection<?>[] select(Root<Position> root) {
		var department = root.get(Position_.department);
		var list = new ArrayList<Selection<?>>();
		
		list.add(root.get(Position_.id));
		list.add(department.get(Department_.code));
		list.add(department.get(Department_.name));
		list.add(root.get(Position_.basicSalary));
		list.add(root.get(Position_.otFeesPerHour));
		list.add(root.get(Position_.anualLeaves));
		
		return list.toArray(size -> new Selection<?>[size]);
	}
	
}
