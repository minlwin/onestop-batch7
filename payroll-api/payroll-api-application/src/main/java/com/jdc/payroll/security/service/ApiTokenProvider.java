package com.jdc.payroll.security.service;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class ApiTokenProvider {

	public Authentication parse(String token) {
		return null;
	}

	public String generate(Authentication authentication) {
		// TODO Auto-generated method stub
		return null;
	}
}
