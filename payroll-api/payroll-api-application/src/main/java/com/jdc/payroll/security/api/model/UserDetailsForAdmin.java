package com.jdc.payroll.security.api.model;

import org.springframework.security.core.userdetails.UserDetails;

public class UserDetailsForAdmin extends UserDetailsBase{

	private static final long serialVersionUID = 1L;
	
	public UserDetailsForAdmin(UserDetails user, String name, boolean activated) {
		super(user, name, activated);
	}
	
	@Override
	public SignInResult signInResult(String token) {
		var result = new SignInResult();
		result.setName(getName());
		result.setToken(token);
		result.setLoginId(getUsername());
		result.setAuthorities(getAuthorities().stream().map(a -> a.getAuthority()).toList());
		return result;
	}

}
