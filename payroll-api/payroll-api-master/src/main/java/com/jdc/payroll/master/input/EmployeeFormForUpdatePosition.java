package com.jdc.payroll.master.input;

import com.jdc.payroll.master.validators.PositionCodeForValid;

public record EmployeeFormForUpdatePosition(
		@PositionCodeForValid
		String position,
		String remark
) {

}
