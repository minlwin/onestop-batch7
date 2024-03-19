package com.jdc.payroll.security.api.model;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public abstract class UserDetailsBase implements UserDetails, SignInResultProvider{

	private static final long serialVersionUID = 1L;
	private UserDetails user;
	private String name;
	
	public UserDetailsBase(UserDetails user, String name) {
		super();
		this.user = user;
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return user.getAuthorities();
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		return user.isAccountNonExpired();
	}

	@Override
	public boolean isAccountNonLocked() {
		return user.isAccountNonLocked();
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return user.isCredentialsNonExpired();
	}

	@Override
	public boolean isEnabled() {
		return user.isEnabled();
	}
}
