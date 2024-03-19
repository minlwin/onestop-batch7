package com.jdc.payroll.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.jdc.payroll.security.service.ApiTokenFilter;

@Configuration
public class ApiSecurityConfiguration {

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		
		http.csrf(csrf -> csrf.disable());
		
		http.authorizeHttpRequests(request -> {
			request.requestMatchers("/public/**").permitAll();
			request.anyRequest().fullyAuthenticated();
		});
		
		http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
		http.addFilterBefore(apiTokenFilter(), UsernamePasswordAuthenticationFilter.class);
		
		return http.build();
	}
	
	@Bean
	ApiTokenFilter apiTokenFilter() {
		return new ApiTokenFilter();
	}
}
