package com.jdc.payroll.master.output;

import java.time.LocalDate;

import com.jdc.payroll.domain.master.entity.Holiday;
import com.jdc.payroll.domain.master.entity.Holiday.Type;
import com.jdc.payroll.domain.master.entity.Holiday_;

import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

public record HolidayInfo(
		LocalDate date,
		Type type,
		String holiday,
		String remark,
		boolean deleted
		) {

	public static void select(CriteriaQuery<HolidayInfo> cq, Root<Holiday> root) {
		// select h.date, h.type, h.holiday, h.remark, h.deleted
		cq.multiselect(
			root.get(Holiday_.date),
			root.get(Holiday_.type),
			root.get(Holiday_.holiday),
			root.get(Holiday_.remark),
			root.get(Holiday_.deleted)			
		);
	}
}
