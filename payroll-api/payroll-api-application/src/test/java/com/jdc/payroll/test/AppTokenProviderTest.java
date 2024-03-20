package com.jdc.payroll.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.jdc.payroll.security.service.ApiTokenProvider;

@SpringBootTest
public class AppTokenProviderTest {
	
	@Autowired
	private ApiTokenProvider tokenProvider;

	@Test
	void test() {
		
		var input = UsernamePasswordAuthenticationToken
				.authenticated("maungmaung", null, List.of(new SimpleGrantedAuthority("Admin")));
		
		var token = tokenProvider.generate(input);
		
		System.out.println(token);
		
		var output = tokenProvider.parse(token);
		
		System.out.println(output.getName());
		assertEquals(input.getName(), output.getName());
		
		var authority = output.getAuthorities().stream()
				.map(a -> a.getAuthority())
				.collect(Collectors.joining(","));
		System.out.println(authority);
		
		assertEquals("Admin", authority);
	}
}
