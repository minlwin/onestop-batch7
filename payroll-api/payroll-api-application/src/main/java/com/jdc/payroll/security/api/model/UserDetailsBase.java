package com.jdc.payroll.security.api.model;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public abstract class UserDetailsBase implements UserDetails, SignInResultProvider{

	private static final long serialVersionUID = 1L;
	private UserDetails user;
	private String name;
	private boolean activated;
	
	public UserDetailsBase(UserDetails user, String name, boolean activated) {
		super();
		this.user = user;
		this.name = name;
		this.activated = activated;
	}
	
	public String getName() {
		return name;
	}
	
	public boolean isActivated() {
		return activated;
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
