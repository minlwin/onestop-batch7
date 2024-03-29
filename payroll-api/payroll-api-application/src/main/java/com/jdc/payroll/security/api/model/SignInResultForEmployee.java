package com.jdc.payroll.security.api.model;

import java.time.LocalDate;
import java.util.List;

import com.jdc.payroll.domain.master.entity.Employee.Status;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class SignInResultForEmployee extends SignInResult{

	private String departmentCode;
	private String departmentName;
	private String positionCode;
	private String positionName;
	private Status status;
	private LocalDate assignDate;
	
	private List<Permission> permissions;
}
