package com.jdc.payroll.master.input;

import java.time.LocalDate;

import com.jdc.payroll.domain.master.entity.Settings;

public record SettingForm(
		LocalDate effectAt,
		int cutOffDay,
		int payDay
		) {

	public Settings entity() {
		var entity = new Settings();
		entity.setEffectAt(effectAt);
		entity.setCutOffDay(cutOffDay);
		entity.setPayDay(payDay);
		return entity;
	}
}
