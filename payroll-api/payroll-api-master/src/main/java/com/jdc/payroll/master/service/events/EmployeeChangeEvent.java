package com.jdc.payroll.master.service.events;

import com.jdc.payroll.domain.master.entity.Employee;
import com.jdc.payroll.domain.master.entity.EmployeeHistory.Type;

public record EmployeeChangeEvent(
		Type changeType,
		Employee employee
		) {

}
