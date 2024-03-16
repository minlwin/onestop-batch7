package com.jdc.payroll.master.output;

import java.math.BigDecimal;
import java.util.ArrayList;

import com.jdc.payroll.domain.master.entity.Department_;
import com.jdc.payroll.domain.master.entity.Position;
import com.jdc.payroll.domain.master.entity.PositionPk_;
import com.jdc.payroll.domain.master.entity.Position_;

import jakarta.persistence.criteria.Root;
import jakarta.persistence.criteria.Selection;

public record PositionInfo(
		String departmentCode,
		String departmentName,
		String positionCode,
		String positionName,
		BigDecimal basicSalary,
		BigDecimal otFeesPerHour,
		int anualLeaves
		) {

	public static Selection<?>[] select(Root<Position> root) {
		var department = root.get(Position_.department);
		var list = new ArrayList<Selection<?>>();
		
		list.add(department.get(Department_.code));
		list.add(department.get(Department_.name));
		list.add(root.get(Position_.id).get(PositionPk_.positionCode));
		list.add(root.get(Position_.position));
		list.add(root.get(Position_.basicSalary));
		list.add(root.get(Position_.otFeesPerHour));
		list.add(root.get(Position_.anualLeaves));
		
		return list.toArray(size -> new Selection<?>[size]);
	}
}
