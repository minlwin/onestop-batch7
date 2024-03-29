package com.jdc.payroll.master.output;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.jdc.payroll.domain.master.entity.Settings;
import com.jdc.payroll.domain.master.entity.Settings_;

import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

public record SettingInfo(int id,
		LocalDate effectAt,
		int cutOffDay,
		int payDay,
		LocalDateTime createAt,
		String createdBy) {

	public static void select(CriteriaQuery<SettingInfo> cq, Root<Settings> root) {
		cq.multiselect(
			root.get(Settings_.id),
			root.get(Settings_.effectAt),
			root.get(Settings_.cutOffDay),
			root.get(Settings_.payDay),
			root.get(Settings_.createAt),
			root.get(Settings_.createBy)
		);
	}
	
	public static SettingInfo from(Settings entity) {
		return new SettingInfo(
				entity.getId(), 
				entity.getEffectAt(),
				entity.getCutOffDay(), 
				entity.getPayDay(), 
				entity.getCreateAt(), 
				entity.getCreateBy());
	}
}
