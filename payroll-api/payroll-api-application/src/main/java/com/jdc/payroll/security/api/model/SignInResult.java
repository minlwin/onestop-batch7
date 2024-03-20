package com.jdc.payroll.security.api.model;

import java.util.List;

import lombok.Data;

@Data
public class SignInResult {

	private String loginId;
	private String name;
	private List<String> authorities;
	private String token;
	private boolean activated;
}
