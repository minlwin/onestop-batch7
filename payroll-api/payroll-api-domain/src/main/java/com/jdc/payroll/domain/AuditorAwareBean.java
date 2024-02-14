package com.jdc.payroll.domain;

import java.util.Optional;

import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Service;

@Service
public class AuditorAwareBean implements AuditorAware<String>{

	@Override
	public Optional<String> getCurrentAuditor() {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

}
