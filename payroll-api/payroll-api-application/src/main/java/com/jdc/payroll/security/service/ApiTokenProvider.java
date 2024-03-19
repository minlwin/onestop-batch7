package com.jdc.payroll.security.service;

import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;

@Service
public class ApiTokenProvider {
	
	private static final String ROLE = "rol";
	
	@Value("${app.token.issuer}")
	private String issuer;
	@Value("${app.token.life}")
	private int lifeTime;
	@Value("${app.token.secret}")
	private String secret;
	
	public String generate(Authentication authentication) {
		
		var issueAt = new Date();
		var calendar = Calendar.getInstance();
		calendar.setTime(issueAt);
		calendar.add(Calendar.MINUTE, lifeTime);
		
		return Jwts.builder()
			.subject(authentication.getName())
			.issuer(issuer)
			.issuedAt(issueAt)
			.expiration(calendar.getTime())
			.compact();		
	}

	public Authentication parse(String token) {
		
		
		
		return null;
	}

}
