package com.jdc.payroll.domain.master.repo;

import java.util.Optional;

import com.jdc.payroll.domain.BaseRepository;
import com.jdc.payroll.domain.master.entity.Account;

public interface AccountRepo extends BaseRepository<Account, Long>{

	Optional<Account> findOneByUsername(String string);
}
