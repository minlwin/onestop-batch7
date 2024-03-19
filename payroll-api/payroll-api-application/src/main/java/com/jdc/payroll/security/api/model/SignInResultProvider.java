package com.jdc.payroll.security.api.model;

public interface SignInResultProvider {

	SignInResult signInResult(String token);
}
