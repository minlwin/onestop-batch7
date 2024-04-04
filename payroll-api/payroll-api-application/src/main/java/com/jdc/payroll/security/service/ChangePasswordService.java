package com.jdc.payroll.security.service;

import static com.jdc.payroll.utils.helpers.EntityOperationHelper.getOne;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jdc.payroll.domain.master.repo.AccountRepo;
import com.jdc.payroll.security.api.model.ChangePasswordForm;
import com.jdc.payroll.utils.exceptions.ApiBusinessException;
import com.jdc.payroll.utils.response.DataModificationResult;

@Service
public class ChangePasswordService {

	private static final String DOMAIN_NAME = "Account";

	@Autowired
	private PasswordEncoder encoder;
	
	@Autowired
	private AccountRepo repo;

	@Transactional
	@PreAuthorize("isFullyAuthenticated()")
	public DataModificationResult<String> change(ChangePasswordForm form) {
		
		var authentication = SecurityContextHolder.getContext().getAuthentication();
		var username = authentication.getName();
		
		var account = getOne(repo.findOneByUsername(username), DOMAIN_NAME, username);
		
		if(!encoder.matches(form.oldPass(), account.getPassword())) {
			throw new ApiBusinessException(List.of("Please check your old password."));
		}
		
		account.setPassword(encoder.encode(form.newPass()));
		
		if(!account.isActivated()) {
			account.setActivated(true);
		}
		
		return new DataModificationResult<String>(username, "Your password has been changed successfully!");
	}
}
