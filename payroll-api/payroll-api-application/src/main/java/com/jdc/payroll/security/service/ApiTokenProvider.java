package com.jdc.payroll.security.service;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.stream.Collectors;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ApiTokenProvider {
	
	private static final String ROLE = "rol";
	
	@Value("${app.token.issuer}")
	private String issuer;
	@Value("${app.token.life}")
	private int lifeTime;
	@Value("${app.token.secret}")
	private String secret;
	
	private SecretKey secretKey;
	
	@PostConstruct
	private void postConstruct() {
		secretKey = SecretKeys.decode(secret);
	}
	
	public String generate(Authentication authentication) {
		
		var issueAt = new Date();
		var calendar = Calendar.getInstance();
		calendar.setTime(issueAt);
		calendar.add(Calendar.MINUTE, lifeTime);
		
		var authorities = authentication.getAuthorities().stream()
				.map(a -> a.getAuthority())
				.collect(Collectors.joining(","));
		
		return Jwts.builder()
			.subject(authentication.getName())
			.claim(ROLE, authorities)
			.issuer(issuer)
			.issuedAt(issueAt)
			.expiration(calendar.getTime())
			.signWith(secretKey)
			.compact();		
	}

	public Authentication parse(String token) {
		
		try {
			var payload = Jwts.parser()
					.requireIssuer(issuer)
					.verifyWith(secretKey)
					.build().parseSignedClaims(token).getPayload();
				
				var username = payload.getSubject();
				var array = payload.get(ROLE, String.class)
						.split(",");
				var authorities = Arrays.stream(array)
						.map(a -> new SimpleGrantedAuthority(a))
						.toList();	
				
			return UsernamePasswordAuthenticationToken
					.authenticated(username, null, authorities);
		} catch (Exception e) {
			// Nothing to do
			log.info(e.getMessage());
		}
		
		return null;
	}

}
