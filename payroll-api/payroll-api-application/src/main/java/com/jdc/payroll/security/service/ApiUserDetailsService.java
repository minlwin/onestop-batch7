package com.jdc.payroll.security.service;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jdc.payroll.domain.master.entity.Account;
import com.jdc.payroll.domain.master.entity.Employee.Status;
import com.jdc.payroll.domain.master.repo.AccountRepo;

@Service
@Transactional(readOnly = true)
public class ApiUserDetailsService implements UserDetailsService{

	@Autowired
	private AccountRepo repo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		var account = repo.findOneByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException(username));
		
		return User.withUsername(username)
				.password(account.getPassword())
				.authorities(account.getRole().name())
				.disabled(isDisable(account))
				.accountExpired(isExpired(account))
				.build();
	}

	private boolean isExpired(Account account) {
		
		var employee = account.getEmployee();
		if(null != employee) {
			return employee.getStatus() == Status.Retired;
		}
		
		return false;
	}

	private boolean isDisable(Account account) {

		var employee = account.getEmployee();
		if(null != employee) {
			return employee.getAssignDate().compareTo(LocalDate.now()) > 0;
		}

		return false;
	}

}
