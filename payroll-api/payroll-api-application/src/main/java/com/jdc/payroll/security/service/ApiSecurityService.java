package com.jdc.payroll.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Service;

import com.jdc.payroll.security.api.model.SignInForm;
import com.jdc.payroll.security.api.model.SignInResult;
import com.jdc.payroll.security.api.model.SignInResultProvider;

@Service
public class ApiSecurityService {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private ApiTokenProvider tokenProvider;

	public SignInResult signIn(SignInForm form) {
		
		var authentication = authenticationManager.authenticate(form.authentication());
		var token = tokenProvider.generate(authentication);
		
		if(authentication.getPrincipal() instanceof SignInResultProvider signInResultProvider) {
			return signInResultProvider.signInResult(token);
		}
		
		return null;
	}
	
}
