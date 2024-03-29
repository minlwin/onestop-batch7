package com.jdc.payroll.security.service;

import static com.jdc.payroll.utils.helpers.EntityOperationHelper.getOne;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jdc.payroll.domain.master.entity.Account;
import com.jdc.payroll.domain.master.entity.Account.Role;
import com.jdc.payroll.domain.master.repo.AccountRepo;
import com.jdc.payroll.security.api.model.Permission;
import com.jdc.payroll.security.api.model.SignInForm;
import com.jdc.payroll.security.api.model.SignInResult;
import com.jdc.payroll.security.api.model.SignInResultForEmployee;

@Service
public class ApiSecurityService {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private ApiTokenProvider tokenProvider;
	
	@Autowired
	private AccountRepo accountRepo;

	@Transactional(readOnly = true)
	public SignInResult signIn(SignInForm form) {
		
		var authentication = authenticationManager.authenticate(form.authentication());
		
		var token = tokenProvider.generate(authentication);
		
		var account = getOne(accountRepo.findOneByUsername(authentication.getName()), "Account", authentication.getName());
		
		return buildResult(account, token);
	}

	private SignInResult buildResult(Account account, String token) {
		
		if(account.getRole() == Role.Admin) {
			return buildResultForAdmin(account, token);
		}
		
		return buildREsultForEmployee(account, token);
	}

	private SignInResult buildREsultForEmployee(Account account, String token) {
		
		var result = new SignInResultForEmployee();

		result.setActivated(account.isActivated());
		result.setAuthorities(List.of(account.getRole().name()));
		result.setLoginId(account.getUsername());
		result.setName(account.getName());
		result.setToken(token);
		
		var employee = account.getEmployee();
		
		result.setAssignDate(employee.getAssignDate());
		result.setDepartmentCode(employee.getDepartment().getCode());
		result.setDepartmentName(employee.getDepartment().getName());
		result.setPositionCode(employee.getPosition().getId().getCode());
		result.setPositionName(employee.getPosition().getId().getPositionCode().getValue());
		result.setStatus(employee.getStatus());
		
		var permissions = employee.getPosition().getPermissions()
				.stream().map(Permission::from).toList();
		
		result.setPermissions(permissions);
		
		return result;
	}

	private SignInResult buildResultForAdmin(Account account, String token) {
		var result = new SignInResult();
		result.setActivated(account.isActivated());
		result.setAuthorities(List.of(account.getRole().name()));
		result.setLoginId(account.getUsername());
		result.setName(account.getName());
		result.setToken(token);
		return result;
	}

	
}
