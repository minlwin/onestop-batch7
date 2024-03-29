package com.jdc.payroll.master.input;

import com.jdc.payroll.domain.master.entity.LeaveType;

import jakarta.validation.constraints.NotBlank;

public record LeaveTypeForm(
		@NotBlank(message = "Please enter leave type name.")
		String name,
		int paidDays,
		String remark) {

	public LeaveType entity() {
		var entity = new LeaveType();
		entity.setName(name);
		entity.setPaidDays(paidDays);
		entity.setRemark(remark);
		return entity;
	}
}
