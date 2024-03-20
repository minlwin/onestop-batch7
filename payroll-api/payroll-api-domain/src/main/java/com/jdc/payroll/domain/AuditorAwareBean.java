package com.jdc.payroll.domain;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.jdc.payroll.domain.master.repo.AccountRepo;

@Service
public class AuditorAwareBean implements AuditorAware<String>{

	@Autowired
	private AccountRepo repo;
	
	@Override
	@Transactional(readOnly = true, propagation = Propagation.REQUIRES_NEW)
	public Optional<String> getCurrentAuditor() {
		
		return Optional.ofNullable(SecurityContextHolder.getContext())
			.map(a -> a.getAuthentication())
			.map(a -> a.getName())
			.flatMap(a -> repo.findOneByUsername(a))
			.map(a -> a.getName())
			.or(() -> Optional.of("System"));
	}

}
