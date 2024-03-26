package com.jdc.payroll.master.output;

import com.jdc.payroll.domain.master.entity.PositionPk.PositionCode;

public record PositionCodeInfo(
		PositionCode code) {
	
	public String getName() {
		return code.getValue();
	}
}
