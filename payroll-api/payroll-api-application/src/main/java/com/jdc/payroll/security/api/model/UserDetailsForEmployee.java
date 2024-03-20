package com.jdc.payroll.security.api.model;

import org.springframework.security.core.userdetails.UserDetails;

import com.jdc.payroll.domain.master.entity.Employee;

public class UserDetailsForEmployee extends UserDetailsBase{

	private static final long serialVersionUID = 1L;
	private Employee employee;
	
	public UserDetailsForEmployee(UserDetails user, Employee employee, String name) {
		super(user, name);
		this.employee = employee;
	}

	@Override
	public SignInResult signInResult(String token) {
		var result = new SignInResultForEmployee();
		result.setName(getName());
		result.setToken(token);
		result.setLoginId(getUsername());
		result.setAuthorities(getAuthorities().stream().map(a -> a.getAuthority()).toList());
		
		result.setAssignDate(employee.getAssignDate());
		result.setStatus(employee.getStatus());
		result.setDepartmentCode(employee.getDepartment().getCode());
		result.setDepartmentName(employee.getDepartment().getName());
		result.setPositionCode(employee.getPosition().getId().getPositionCode());
		result.setPositionName(employee.getPosition().getPosition());
		
		return result;
	}

}
