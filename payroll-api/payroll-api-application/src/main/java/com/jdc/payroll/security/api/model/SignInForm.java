package com.jdc.payroll.security.api.model;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import jakarta.validation.constraints.NotBlank;

public record SignInForm(
		@NotBlank(message = "Plase enter user name.")
		String username,
		@NotBlank(message = "Plase enter password.")
		String password
		) {

	public Authentication authentication() {
		return UsernamePasswordAuthenticationToken.unauthenticated(username, password);
	}
}
